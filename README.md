# Api Clínica Veterinaria-Backend

Este repositorio contiene el backend desarrollado como API REST para la gestión de una clínica veterinaria.

Se integra con el proyecto [**Vet Frontend**](https://github.com/daorozco991-lgtm/vet-frontend).
La aplicación incluye funcionalidades para:

* Gestión de mascotas.
* Gestión de dueños.
* Gestión de citas.
* Autenticación y autorización mediante **JWT (JSON Web Token)**.
* Operaciones CRUD.

Siga las instrucciones a continuación para ejecutar el proyecto localmente.

## Requisitos

* Java 22
* PostgreSQL
* Maven

## Crear la base de datos

1. Clone o descargue este repositorio.
   Clone el repositorio:

```bash

git clone https://github.com/daorozco991-lgtm/vet-backend.git
```

2. Cree una base de datos en PostgreSQL con el nombre que prefiera.
3. Abra el archivo `application-dev.properties`.
4. Configure los valores con los de su base de datos o use un perfil:

```properties
spring.datasource.url=jdbc:postgresql://host:puerto/NOMBRE_BASE_DATOS
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASENA
jwt.secret=EscribeUnaClaveSecretaDe32CaracteresMinimo
```

5. Ejecutar el proyecto

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

Una vez que el backend esté en ejecución, clone y ejecute el proyecto siguiendo las instrucciones en el
readme [**Vet Frontend
**](https://github.com/daorozco991-lgtm/vet-frontend). 