# Java Spring Boot MS 
## What
This repository contains four simple java spring boot projects showcasing how simple services are built using authorization and discovery
The four java projects are
* Authorization - provides simple token based authorization for services
* Registry ( Eureka server ) - enables discovery of services using Eureka
* Book service - Simple CRUD service on H2 DB serving book resources
* Catalog service - Facade service which communicates with book service using feign client to pull data

## Usage

1. Build all the services using mvn
2. Start all the services in the order mentioned above
3.  Get the token from authorization service
```
curl ClientId:secret@localhost:9090/auth/oauth/token -d grant_type=client_credentials

//get the access token and store it in variable
export TOKEN=..
```
4. Test and add data to Book store
```
curl -H "Authorization: Bearer $token” -v localhost:8080/books

  

curl -i -X POST -H "Content-Type:application/json" -H "Authorization: Bearer $TOKEN” -d '{ "name":"Midnights Children", "author":"Salman Rushdie" }' localhost:8080/books
```

5. Now fetch the book data using catalog endpoint 
```
curl -i -X POST -H "Content-Type:application/json" -H "Authorization: Bearer $TOKEN”  localhost:8081/catalog
```


