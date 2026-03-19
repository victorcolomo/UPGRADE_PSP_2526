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

    // Obtenemos el div para pintar el mensaje del servidor
    const messageDiv = document.getElementById("message");

    // Validar que todos los datos esten rellenos
    /*if(!nombre || !apellido || !email){
        alert("Faltan campos por rellenar");
        return;
    }*/

    // Objeto con los 3 datos del nuevo usuario
    const userData = {nombre, apellido, email};

    console.log(JSON.stringify(userData));

    try{

        // Realizamos fetch
        const res = await fetch(API_URL, { 
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData)
        });

        // Convertir la respuesa a JSON
        const data = await res.json();

        if(! res.ok){
            messageDiv.className= "error";
            messageDiv.textContent = data.message;
            return;
        }

        messageDiv.className= "success";
        messageDiv.textContent = `Usuario con id ${data.id} con email ${data.email}`;

        clearForm();
        // Cargamos la tabla de usuario
        loadUser();

    } catch(error){
        console.log("Error con el servidor");
        messageDiv.className= "error";
        messageDiv.textContent = "Error al conectarse con el servidor";
    }

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