📚 Literatura API
Descripción
Esta es una API de literatura desarrollada en Java utilizando Spring Boot. La API consume datos de la API de Gutendex y persiste la información en una base de datos PostgreSQL.

🚀 Requisitos
Java 11 o superior

Maven 3.6.3 o superior

PostgreSQL 12 o superior

🛠️ Instalación
Clona el repositorio:

sh
(https://github.com/jfajaime/Desafio_Challenge_Literatura.git)
cd literatura-api
Configura la base de datos PostgreSQL:

Crea una base de datos llamada literatura_db.

Configura las credenciales de acceso en el archivo application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literatura_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
Construye el proyecto con Maven:

sh
mvn clean install
Ejecuta la aplicación:

sh
mvn spring-boot:run
📖 Uso
Endpoints
GET /libros: Obtiene una lista de libros desde la API de Gutendex y los persiste en la base de datos.
