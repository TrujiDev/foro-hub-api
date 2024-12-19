# foro-hub-api

API REST para la gestión de un foro, desarrollada con Spring Boot. Este proyecto permite realizar operaciones CRUD de tópicos, aplicar validaciones de negocio y gestionar la autenticación/autorización de usuarios.

## Tecnologías

- Java
- Spring Boot
- Spring Security
- JPA/Hibernate
- Maven
- MySQL

## Requisitos

- JDK 17 o superior
- Maven 3.8.1 o superior
- MySQL 8.0 o superior

## Configuración

1. Clona el repositorio:

   ```sh
   git clone https://github.com/TrujiDev/foro-hub-api.git
   cd foro-hub-api
   ```

2. Configura las variables de entorno necesarias:

   ```sh
   export DATASOURCE_URL=jdbc:mysql://localhost:3306/foro_hub
   export DATASOURCE_USERNAME=tu_usuario
   export DATASOURCE_PASSWORD=tu_contraseña
   export API_SECURITY_SECRET=tu_secreto
   ```

3. Configura el archivo `application-dev.yml` con las variables de entorno:

   ```yaml
   spring:
     datasource:
       url: ${DATASOURCE_URL}
       username: ${DATASOURCE_USERNAME}
       password: ${DATASOURCE_PASSWORD}
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true

   api:
     security:
       secret: ${API_SECURITY_SECRET}
   ```

## Ejecución

Para ejecutar la aplicación, usa el siguiente comando:

```sh
mvn spring-boot:run
```

## Endpoints

### Autenticación

- **POST /auth/login**: Autentica un usuario y devuelve un token JWT.

### Usuarios

- **POST /users/signup**: Registra un nuevo usuario.
- **GET /users/list**: Lista todos los usuarios.
- **GET /users/details/{id}**: Obtiene los detalles de un usuario por ID.

### Tópicos

- **POST /topics**: Crea un nuevo tópico.
- **GET /topics**: Lista todos los tópicos.
- **GET /topics/{id}**: Obtiene los detalles de un tópico por ID.
- **PUT /topics/{id}**: Actualiza un tópico existente.
- **DELETE /topics/{id}**: Elimina un tópico por ID.

## Pruebas

Para ejecutar las pruebas, usa el siguiente comando:

```sh
mvn test
```

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request.