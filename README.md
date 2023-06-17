# customer-transaction
Rest API displays customer cumulative &amp; monthly transactions.

Implementation:
Used technical stack:
•	Java 17
•	Spring Boot 3
•	Swagger 3
•	Docker
Source code is available in github: https://github.com/subahanih/customer-transaction
Functionality:
•	Created a java class (TransactionService.java) and hardcoded the data.
•	For testing there are 2 account numbers available (SW123 & SW456).
•	Swagger 3 has been enabled to test the end point manually.
•	Java unit test cases written to test both positive and negative cases.
•	API has been dockerized and simplified the deployment with required java and maven dependencies.
•	Logger has been implemented and it is being logged in a directory called “logs” which is present inside source code folder “customer-transaction”.


Installation:
This Restful API is developed using Spring Boot 3, hence, the application can be run by initiating the root Java class (WordFormatApplication.java).
Swagger is available in port 8080 (Ex: localhost:8080/swagger-ui/index.html).

Also, this Restful API has been dockerized, hence, an image file can be created and running in the docker container.

Docker instructions:
Step1: Create an image using docker file provided in the source package (customer-transaction -> Dockerfile).
	>> docker build –t customer-transaction
Step2: Run image using given docker-compose yml file (customer-transaction -> docker-compose.yml).
	>>docker-compose up –d
Step3: Swagger will available in the port 8080, but url should prefixed with docker running IP.
	Ex: 157.138.59.100:8080/swagger-ui/index.html
