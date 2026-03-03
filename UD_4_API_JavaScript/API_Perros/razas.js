// Consultas para la lista de razas
// https://dog.ceo/api/breeds/list/all
 fetch('https://dog.ceo/api/breeds/list/all')
    .then(res=> res.json())
    .then(data=> cargarRazas(data.message))
    .catch(error => console.log('Error en fecth',error))


// Funcion para cargar las razas en el select
function cargarRazas(razas){
    // Obtener con DOM el select
    const select = document.getElementById("razas");
    const lista = Object.keys(razas).sort();
    console.log(select);

    lista.forEach(r => {
        // Creamos option
        const option = document.createElement('option');
        option.textContent = r;
        option.value = r;
        // Los añadimos en select
        console.log(option);
        select.appendChild(option);
        
    });
}

function mostrarImagen(){

    // Necesitamos el value del option que hemos seleccionado del select
    const select = document.getElementById("razas");
    const raza = select.value;

    // Recuperamos el div donde vamos vamos "pintar" los datos
    const info = document.getElementById("info");

    if(raza == ""){
         info.innerHTML = "";
         return;
    }


    // Realizamos fecth para obtener los datos del endpoint
    // https://dog.ceo/api/breed/raza/images/random
    fetch(`https://dog.ceo/api/breed/${raza}/images/random`)
        .then(res=> res.json())
        .then(data=> 
            {
                info.innerHTML = `
                    <h2>Raza ${raza}</h2>
                    <img src="${data.message}" class="img-thumbnail" alt="Imagen raza ${raza}">
                `
            }
        )
        .catch(error => console.log('Error en fecth',error))

}

// Eventos
document.getElementById("nuevaImagen").addEventListener('click', mostrarImagen);

// Consultar para generar una imagen de una raza en concreto
