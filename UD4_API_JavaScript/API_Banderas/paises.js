// Peticion a la API
fetch("https://restcountries.com/v3.1/all?fields=name,capital,flags") 
    .then(response => response.json()) 
    .then(data => cargarPaises(data)) 
    .catch(error => console.error('Error:', error)); // Maneja errores


// Funcion para rellenar el select
function cargarPaises(data){
    
    // Obtenemos el select
    const select = document.getElementById("paises");

    // Recorremos la respuesta y por cada pais
    // crear un option
    data.forEach(p => {
        //console.log(p);
        // Creamos el elemento option
        let option = document.createElement("option");
        // Le asignamos un texto
        option.textContent = p.name.common;
        option.value = p.name.common;
        // Guardamos capital
        option.dataset.capital = p.capital;
        option.dataset.flag = p.flags.png;
        // Guardamos pais
        // Tenemos que añadir la opcion al select
        select.appendChild(option)
    });
}

// Cuando cambiemos el select, pintamos los datos en el div
document.getElementById("paises").addEventListener("change", function(){

    console.log("Cambio de pais...");
    // Recuperar el element seleccion
    const seleccionado = this.options[this.selectedIndex];

    const info = document.getElementById("info");

    if(this.value == ""){
        info.innerHTML = `
        <h1>País no seleccionado</h1>
        `;
        return;
    }



    const capital = seleccionado.dataset.capital;
    const flag = seleccionado.dataset.flag;

    info.innerHTML = `
        <h1>Capital ${capital} </h1>
        <img src="${flag}">
    `;


});
