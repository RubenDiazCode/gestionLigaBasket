# gestionLigaBasket

Este proyecto es el examen final del Talent Campus organizado por DevCenter. 
Está codificado en java y desarrollado sobre Spring con Maven.

## ¿En que consiste este proyecto?
Este proyecto consiste en que podemos manejar tanto equipos mediante servicios REST, como jugadores y entrenadores de equipos que participan en partidos. Podemos tanto añadirlos como modificarlos o borrarlos, incluso vincularlos entre ellos y buscar información mas especifica acerca de los equipos, sus integrantes o los partidos que juegan. En un principio está pensada para baloncesto pero el modelo se puede extrapolar a cualquier deporte de equipo. 
La base de datos está hecha en MySql y puedes encontrar un script de la misma en la carpeta resources del proyecto.

## Funcionamiento
Recomiendo postman para utilizar esta app. Una vez descargado, lanzamos la app desde el main y en postman, metemos la url para hacer la consulta que queramos hacer. Por ejemplo, para consultar todos los jugadores disponibles en base de datos, elegimos la opción `Get` y escribimos la url `http://localhost:8080/api/jugador/` y nos los devolverá en formato JSON.



