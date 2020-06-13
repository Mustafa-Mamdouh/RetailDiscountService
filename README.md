# RetailDiscountService

How to test
*First apply creation script from db_scripts to your mysql db
*Second go to retail-discount-service-impl folder and run the below command after change the username and password to you local database instance credentails
	mvn spring-boot:run -Drun.arguments=--spring.datasource.username=root,--spring.datasource.username=root1
*Now you can test the application through api localhost:8086/discount

*Example of request Body
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


