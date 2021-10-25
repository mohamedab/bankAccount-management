# BANK ACCOUNT MANAGEMENT

### Description

This application allows to manage a bank account:

* Make a deposit in the account.
* Make a withdrawal from the account.
* Get the history (operation, date, amount, balance) of the operations.

### Project Architecture

The hexagonal architecture pattern is used in this project, it is divided into 3 modules:

* Domain : contains the business logic.
* Infrastructure : contains the logic needed to run the application.
* Application : allows the user to communicate with our application via a RESTFUl API.
* App-backend : bootstraps(launch) the application.

### Building the project

Execute the below command from the root of the repository if you want to build the entire repository :

`mvn clean install`

### Running the application

To run the application, execute the command in the module directory : 

`mvn spring-boot:run`

The application initiate the database and insert values into it using the sql file `data.sql`
located in the resources directory of the module infrastructure.

### Test the application

#### Terminal

To test the various features, run the following commands :

* Make a deposit in the account : 
```
curl --location --request POST 'http://localhost:8080/api/accounts/FR7630001007941234567890185/operations/deposit' \
--header 'Content-Type: application/json' \
--data-raw '{
            "label": "op1",
            "amount": 500.00,
            "date": "2021-10-25",
            "typeOperation": "DEPOSIT"
}'
```
* Make a withdrawal from the account :
```
curl --location --request POST 'http://localhost:8080/api/accounts/FR7630001007941234567890185/operations/withdrawal' \
--header 'Content-Type: application/json' \
--data-raw '{
            "label": "op1",
            "amount": 500.00,
            "date": "2021-10-25",
            "typeOperation": "WITHDRAWAL"
}'
```
* Get the history (operation, date, amount, balance) of the operations :
```
curl --location --request GET 'http://localhost:8080/api/accounts/FR7630001007941234567890185/operations/history'
```

#### By POSTMAN

Import the postman collection file `BankAccount.postman_collection.json` located in root of the project.