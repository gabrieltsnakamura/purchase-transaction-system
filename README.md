# Purchase Transaction Service

This is a Spring Boot application that provides a RESTful API for managing purchase transactions.

## Features

- Create a new purchase transaction
- Retrieve a purchase transaction by ID
- Retrieve a purchase transaction by ID and convert the amount to a specified currency

## Technologies

- Java
- Spring Boot
- Maven

## Logging

The application uses SLF4J with Logback for logging. Log messages are written to a file. The log file is located in the `logs` directory and the file name includes the date. The configuration for logging is located in `src/main/resources/logback-spring.xml`.

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run