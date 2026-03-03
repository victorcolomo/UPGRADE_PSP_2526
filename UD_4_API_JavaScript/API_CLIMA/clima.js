
const inputCiudad = document.getElementById("ciudad");
const btnBuscar = document.getElementById("buscar");
const divInfo = document.getElementById("info");
const ulHistorial = document.getElementById("historial");

// Evento cuando pulsemos el boton buscar
btnBuscar.addEventListener('click',buscarClima)

// Declaramos el vector para el historial de búsquedas
let historial = [];

function buscarClima(){

    // Obtenemos el valor del input
    const ciudad = inputCiudad.value.trim();

    if(!ciudad){
        alert("Por favor, introduce el nombre de una ciudad");
        return;
    }

    // Realizamos consulta la API para obtener las coordenadas de la ciudad
    // https://geocoding-api.open-meteo.com/v1/search?name=<nombre_ciudad>&count=1
     fetch(`https://geocoding-api.open-meteo.com/v1/search?name=${ciudad}&count=1`)
        .then(res=> res.json())
        .then(geoData=> 
        {
                console.log(geoData);
                const lat = geoData.results[0].latitude;
                const lon = geoData.results[0].longitude;
                const nombre = geoData.results[0].name + ', ' + geoData.results[0].country;
                
                console.log(lat);
                console.log(lon);

                // Realizar consulta a la API para obtener el tiempo
                //https://api.open-meteo.com/v1/forecast?latitude=<lat>&longitude=<lon>&current_weather=true
                fetch(`https://api.open-meteo.com/v1/forecast?latitude=${lat}&longitude=${lon}&current_weather=true`)
                    .then(res=> res.json())
                    .then(climaData=> 
                    {

                        console.log(climaData);
                        const temp = climaData.current_weather.temperature;
                        const viento = climaData.current_weather.windspeed;
                        const unidadTemp = climaData.current_weather_units.temperature;
                        const unidadViento = climaData.current_weather_units.windspeed;
                        const codigoClima = climaData.current_weather.weathercode;

                        let icono = "";
                        let condicion = "";
                        if (codigoClima == 0){
                            icono = "☀️";
                            condicion = "Despejado";
                        } else if (codigoClima == 1 || codigoClima == 2 || codigoClima == 3){
                            icono = "☁️";
                            condicion = "Nublado";
                        }
                        

                        divInfo.innerHTML = `
                            <h2>Clima en ${nombre}</h2>
                            <p><strong>Temperatura</strong> ${temp} ${unidadTemp}</p>
                            <p><strong>Velocidad del viento: </strong> ${viento} ${unidadViento}</p>
                            <p><strong>${condicion}: </strong> ${icono}</p>
                        `;

                        // 
                        // 

                    });
                //.catch(error => console.log('Error en fecth',error););

                // Actualzar el historia de búsquedas
                actualizarHistorial(nombre);
              
        });
        //.catch(error => console.log('Error en fecth',error))
}

function actualizarHistorial(nombreCiudad){

    // Crear un array con todos los elemento salvo el que acabamos de buscar
    historial = historial.filter(c => c != nombreCiudad);

    // Añadimos la nueva ciudad al principio del array
    historial.unshift(nombreCiudad);

    // Si hay mas de 5 elementos en el array, eliminmos
    if(historial.length > 5){
        historial.pop();
    }

    // Añadimos los elementos del vector en el ul del historial
    ulHistorial.innerHTML = '';

    historial.forEach( c => {
        // Por cada elemento creo un <li> 
        const li = document.createElement('li');
        li.textContent = c;
        // Se añade al <ul> del historial
        ulHistorial.appendChild(li);
    });


}