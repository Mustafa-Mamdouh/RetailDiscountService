# RetailDiscountService

## Assessment Description
On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount.
2. If the user is an affiliate of the store, he gets a 10% discount.
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.
Write a program with Restful APIs, such that given a bill, it finds the net payable
amount. Please note the stress is on object oriented approach and test coverage.
User interface is not required.\

## How to test

First apply creation script from db_scripts to your mysql db.

Second go to retail-discount-service-impl folder and run the below command after change the username and password to you local database instance credential
```java
	mvn spring-boot:run -Drun.arguments=--spring.datasource.username=root,--spring.datasource.username=root1
```
Run unit tests and generate jacoco coverage report 
```java
	mvn clean verify
```
## Server Data

URL : 8086\
API Path : localhost:8086/discount\
##Example of request Body
```json

{
	"userId":"1",
	"items":[
	{
		"unitPrice":55,
		"itemName":"ie",
		"itemType":"Electronics",
		"quantity":1
	},	{
		"unitPrice":500,
		"itemName":"ie",
		"itemType":"grocery",
		"quantity":1
	}
		]
}
```

## Class Diagram
![alt text](https://raw.githubusercontent.com/Mustafa-Mamdouh/RetailDiscountService/master/class_diagram.png)

## What i have missed
 Full documentation 
