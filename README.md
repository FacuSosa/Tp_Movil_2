# mobile scaffolding

Este proyecto es un ejemplo básico de una aplicación con funcionalidades comunes y una estructura
basada en las recomendaciones de [Android][1].

## Contenido

La aplicación consume [The Cat API], una api que retorna una foto de un gato al azar.

## Estructura

### Data

Contiene una implementación del patrón repositorio para el acceso a la API de gatos.

Kitties Repo define la interfaz del repositorio, que es implementada por Rest Repo. KittyApiModel es
el modelo usado por la API para el endpoint consumido. (NdA: La convención de la
arquitectura [Domain Driven Design][3] identifica a esta capa como de DTO pero nos regimos por la
convención que propone [Android][4])

### Domain

En Domain está definida la acción de consumir un gato que en principio es el único dominio del
problema de esta app.

### UI

Define la presentación de la información. Un activity contenedor, dos fragmentos visuales y un view
model como contenedor de estado.

## Qué no hay

Este proyecto alcanza para exponer este caso de uso simple. Pero el escalamiento del mismo
corresponderá a cada uno de los proyectos que se construyan en base a este. Es decir, es
responsabilidad tuya 😉️.

Sabiendo esto: hay ciertos patrones que faltan implementar, estos se verán durante la cursada de
Android 2.

### Qué no hay: [StateClass][5]

Cumple la función de modelar qué es lo que se debiera de ver en la pantalla ya sean datos o
activadores.
Por ejemplo, un booleano puede almacenar si hay que mostrar o no un cuadro de información. Son Data
Class sin comportamiento.

### Qué no hay: [Patrón "Offline First"][6]

Una de las capacidades más interesantes del patrón repositorio es la de abstraer a la UI de los
detalles de la implementación de cómo es que se obtiene una infromación específica. Esta abstracción
nos permite implementar patrones más complejos sin perjudicar al resto de la aplicación.

Uno de estos patrones es el de offline first. Este indica como primer fuente de información datos
fuera de línea. Es decir, primero se busca información almacenada en el dispositivo, luego se busca
información en el recurso externo.

Para esto, se "apunta" las vistas a las fuentes locales y se "re hidrata" dichas fuentes con
información obtenida desde una fuente externa.

## Para cerrar

Este proyecto está en evolución pero esperamos que sirva como puntapié inicial para poder construir
aplicaciones desde una base.

[1]: https://developer.android.com/topic/architecture/intro?hl=es-419

[2]: https://www.thecatapi.com/

[3]: https://martinfowler.com/eaaCatalog/dataTransferObject.html

[4]: https://developer.android.com/jetpack/guide/data-layer?hl=es-419#business-models

[5]: https://developer.android.com/jetpack/guide/ui-layer?hl=es-419#define-ui-state

[6]: https://developer.android.com/topic/architecture/data-layer/offline-first?hl=es-419
