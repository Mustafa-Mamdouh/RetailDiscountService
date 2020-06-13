# RetailDiscountService

## How to test

First apply creation script from db_scripts to your mysql db.

Second go to retail-discount-service-impl folder and run the below command after change the username and password to you local database instance credential
```java
	mvn spring-boot:run -Drun.arguments=--spring.datasource.username=root,--spring.datasource.username=root1
```

## Server Date

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

##Class Diagram
https://github.com/Mustafa-Mamdouh/RetailDiscountService/blob/master/class_diagram.png

##What i have missed
 Full documentation 
 Unit test and code coverage
