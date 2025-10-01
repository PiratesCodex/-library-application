Overview

This is a Spring Boot based Online Library Management System. It allows users to register, login, borrow books, manage membership, and view statistics.

The application uses Spring Boot 3, Spring Data JPA, Hibernate, MySQL, and Spring Security (Basic Auth).

Table of Contents

Setup

Database Configuration

Running the Application

API Endpoints

Authentication

Postman Examples

Setup

Clone the repository:

git clone <repo_url> cd online-library

Create a MySQL database:

CREATE DATABASE online_library;

Update application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/online_library spring.datasource.username=root spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect server.port=8080

Build and run:

mvn clean install mvn spring-boot:run

Authentication

Basic Authentication is used for all endpoints except /api/register and /api/login.

Default credentials for testing:

Username: sagar

Password: Sagar@123

Use Postman Authorization → Type: Basic Auth → Enter username & password.

API Endpoints 
1)  
Auth APIs Method URL Body Description

 POST /api/register { "name": "Sagar", "email": "sagar@gmail.com", "password": "Sagar@123" } 

Register a new user 

POST /api/login { "email": "sagar@gmail.com", "password": "Sagar@123" }Login user

2)  
User APIs Method URL Body Description

 GET /api/users - Get all users 
GET /api/users/{id}/details - Get structured user details 
GET /api/users/{id} - Get user by ID
PUT /api/users/{id} { "name":"Sagar Patil", "email":"sagar@gmail.com", "password":"Sagar@123" } Update user 
DELETE /api/users/{id} - Delete user

3)
Book APIs Method URL Body Description 

GET /api/books - Get all books (optional filters: category, status, author, name)
POST /api/books/addBooks { "name":"Java Basics","author":"Author Name","category":"Programming" } Add a new book 
PUT /api/books/status?bookId={id}&status=AVAILABLE	TAKEN - 
POST /api/books/take { "userId":1, "bookId":1, "days":5 } Borrow a book 
DELETE /api/books/{id} - Delete a book 
GET /api/books/category/popularity - Get category popularity

4)
Membership APIs Method URL Body Description

GET /api/membership/validity?userId={id} - Check membership validity Postman Example

Go to Authorization → Type: Basic Auth

Enter username: sagar

Enter password: Sagar@123

Hit any secured endpoint (e.g., /api/books)
