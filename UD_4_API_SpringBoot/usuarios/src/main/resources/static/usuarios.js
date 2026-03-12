// URL base
const API_URL = 'http://localhost:4000/user';

async function loadUser() {
    const res = await fetch(API_URL);

    // Convertimos la respuesta a JSON
    const users = await res.json();

    // Obtener el cuerpo de la tabla -> id usersTable
    const tbody = document.getElementById('usersTable');
    // Limpiamos los datos anteriores
    tbody.innerHTML = '';
    // Recorremos los usuarios y por cada uno creamos una fila nueva
    users.forEach( u => {
        tbody.innerHTML += `
        <tr>
            <td>${u.id}</td>
            <td>${u.nombre}</td>
            <td>${u.apellido}</td>
            <td>${u.email}</td>
            <td>
                <button onclick="deleteUser(${u.id})">Eliminar</button>
            </td>
        <tr>
        `;
    });

}

async function saveUser() {

    // Obtener los valores del formulario
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const email = document.getElementById('email').value;

    // Validar que todos los datos esten rellenos
    if(!nombre || !apellido || !email){
        alert("Faltan campos por rellenar");
        return;
    }

    // Objeto con los 3 datos del nuevo usuario
    const userData = {nombre, apellido, email};

    console.log(JSON.stringify(userData));

    // Realizamos fetch
    const res = await fetch(API_URL, { 
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData)
    });

    clearForm();
    // Cargamos la tabla de usuario
    loadUser();

}

async function clearForm() {
    document.getElementById('nombre').value = "";
    document.getElementById('apellido').value = "";; 
    document.getElementById('email').value = "";;
}

async function deleteUser(id) {
    // Pedir confirmacion
    if(!confirm('¿Quieres eliminar el usario?')){
        return;
    }

    // Peticion FETCH de tipo DELETE
    const res = await fetch(`${API_URL}/${id}`, { method : 'DELETE'});

    if( !res.ok){
        console.log("Error al eliminar el usuario");
    }

    // Cargamos la tabla de usuario
    loadUser();

}

// Cuando carguemos la página, tenemos que ejecutar loadUser
window.onload = loadUser;