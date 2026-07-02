# Api Clínica Veterinaria-Backend

Esta es una API desarrollada con Java y Spring Boot.

Siga las instrucciones a continuación para ejecutar el proyecto localmente.

## Requisitos

* Java 22
* PostgreSQL
* Maven

## Crear la base de datos

1. Cree una base de datos en PostgreSQL con el nombre que prefiera.
2. Abra el archivo `application-dev.properties`.
3. Configure los siguientes valores con su base de datos:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/NOMBRE_BASE_DE_DATOS
spring.datasource.username=USUARIO
spring.datasource.password=CONTRASEÑA
```

## Ejecutar el proyecto

Desde la raíz del proyecto ejecute:

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

La primera vez que se ejecute el proyecto, Spring Boot creará automáticamente las tablas de la base de datos. Es normal
que aparezcan mensajes de Hibernate en la consola durante este proceso.

## Frontend

Una vez que el backend esté en ejecución, clone y ejecute el proyecto [**Vet Frontend
**](https://github.com/daorozco991-lgtm/vet-frontend) para conectarse a la API. 