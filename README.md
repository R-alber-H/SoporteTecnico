# 🛠️ Sistema de Gestión de Soporte Técnico

API RESTful desarrollada con **Spring Boot** para la gestión eficiente de solicitudes de soporte técnico, permitiendo realizar operaciones **CRUD** (Crear, Leer, Actualizar, Eliminar).

---

## 📘 Descripción

Este proyecto resuelve la problemática de la gestión desorganizada de solicitudes al área de soporte técnico mediante una API que **centraliza toda la información**.  
La aplicación permite registrar, consultar, actualizar y eliminar solicitudes de soporte técnico de manera **estructurada y eficiente**.

---

## ⚙️ Tecnologías utilizadas

- **Java 17** o superior  
- **Spring Boot 3.x**  
- **Spring Web** (para APIs REST)  
- **Spring Validation** (para validaciones)  
- **Maven** (gestión de dependencias)  
- **Swagger / OpenAPI** (documentación)  
- **Postman** (pruebas de API)

---

## 🚀 Pasos para ejecutar el proyecto

1. **Clonar el repositorio**

   git clone https://github.com/R-alber-H/SoporteTecnico.git

2. **Compilar el proyecto**

   mvn clean compile

3. **Configuracion de base de datos MySQL**

   Antes de correr la aplicación, edita el archivo src/main/resources/application.properties con tus credenciales:
   
   spring.datasource.url=jdbc:mysql://localhost:3306/<nombreDataBase>?useSSL=false&serverTimezone=UTC
   spring.datasource.username=<usuarioMySQL>
   spring.datasource.password=<contraseñaMySQL>

4. **Ejecutar la aplicación**

   mvn spring-boot:run

5. **Verificar que esté ejecutándose**

  La aplicación estará disponible en: http://localhost:8080

  La documentación Swagger UI en: http://localhost:8080/swagger-ui.html
