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

### If you have Maven installed

To run the application, use the following command:

```bash
mvn spring-boot:run
```

### If you do not have Maven installed

To run the application, use the following command:

```bash
./mvnw spring-boot:run
```

The `mvnw` command will automatically download and install the correct version of Maven (if it's not already installed), and then it will run the `spring-boot:run` command.

## API Endpoints

The application provides the following endpoints:

### POST /transaction

Creates a new purchase transaction.

Request body:

```json
{
    "description": "string",
    "transactionDate": "string",
    "amount": "number"
}
```

Response body:

```json
{
    "id": "string",
    "description": "string",
    "transactionDate": "string",
    "amount": "number"
}
```

- **description**: A string that describes the transaction.
- **transactionDate**: A string that represents the date of the transaction in the format "yyyy-MM-dd'T'HH:mm:ss".
- **amount**: A number that represents the amount of the transaction.

### GET /transaction

Retrieves all purchase transactions.

Response body:

```json
[
    {
        "id": "string",
        "description": "string",
        "transactionDate": "string",
        "amount": "number"
    }
]
```

- **id**: A string that represents the ID of the transaction.


### GET /transaction/{id}

Retrieves a purchase transaction by ID.

Response body:

```json
{
    "id": "string",
    "description": "string",
    "transactionDate": "string",
    "amount": "number"
}
```

- **id**: A string that represents the ID of the transaction.
- **description**: A string that describes the transaction.
- **transactionDate**: A string that represents the date of the transaction in the format "yyyy-MM-dd'T'HH:mm:ss".
- **amount**: A number that represents the amount of the transaction.

### GET /exchange/transaction/{id}/{currency}

Retrieves a purchase transaction by ID and converts the amount to the specified currency.

Response body:

```json
{
    "id": "string",
    "description": "string",
    "transactionDate": "string",
    "amount": "number",
    "exchangeRate": "string",
    "convertedAmount": "number"
}
```

- **id**: A string that represents the ID of the transaction.
- **description**: A string that describes the transaction.
- **transactionDate**: A string that represents the date of the transaction in the format "yyyy-MM-dd'T'HH:mm:ss".
- **amount**: A number that represents the amount of the transaction.
- **exchangeRate**: A string that represents the currency of the converted amount.
- **convertedAmount**: A number that represents the converted amount of the transaction.
```

### PUT /transaction/{id}

Updates a purchase transaction by ID.

Request body:

```json
{
    "description": "string",
    "transactionDate": "string",
    "amount": "number"
}
```

Response body:

```json
{
    "id": "string",
    "description": "string",
    "transactionDate": "string",
    "amount": "number"
}
```

- **description**: A string that describes the transaction.
- **transactionDate**: A string that represents the date of the transaction in the format "yyyy-MM-dd'T'HH:mm:ss".
- **amount**: A number that represents the amount of the transaction.

### DELETE /transaction/{id}

Deletes a purchase transaction by ID.

Response body:

```json
{
    "id": "string",
    "description": "string",
    "transactionDate": "string",
    "amount": "number"
}
```

- **id**: A string that represents the ID of the transaction.