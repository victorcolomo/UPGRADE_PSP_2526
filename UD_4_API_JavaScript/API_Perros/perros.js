document.getElementById("btn").addEventListener("click", mostrarImagen)

function mostrarImagen(){
    // Consulta al recurso: https://dog.ceo/api/breeds/image/random
    fetch('https://dog.ceo/api/breeds/image/random')
        .then(res=> res.json())
        .then(data=> {
            // Mostramos la imagen en el div
            document.getElementById("imagen").innerHTML = `
                <img src="${data.message}" class="img-thumbnail" alt="Imagen perro aleatorio">
            `;
        })
        .catch(error => console.log('Error en fecth',error))
}