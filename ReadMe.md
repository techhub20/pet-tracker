## PET TRACKING APPLICATION

A Spring Boot Java application that tracks pets (cats and dogs) using different types of trackers . It calculates how many pets are outside/Inside the power saving zone , grouped by pet type and tracker type.

This Application uses two types of data storage mechanisms Real DB like H2(can be used for real time db strorage) and InMemoryDB (used for testing purpose with ActiveProfile('test'))

----

### Reference Documentation
----
````bash
# Tech Stack

-Java 11
-Spring Boot 2+

----
# REST API :
````bash
-Add pet data
-Get all stored pets
-Get summary of pets outside/inside zone, grouped by petType and tracker
----

# Build and Run Main Application
````bash
-mvn clean install
-mvn spring-boot:run
-URL : http://localhost:8080/api/v1/pet-tracker/

##### --------Testing-------#####

Before Testing make sure to run below cmd in the root folder
###mvn spring-boot::run 

---------Curl & Postman -------
#######ADD PETS#######

### Add a Cat (Outside Zone)#####
-------CURL-----
curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "CAT",
"trackerType": "SMALL",
"ownerId": 125,
"inZone": false,
"lostTracker": false
}'

---------POSTMAN -------
URL: http://localhost:8080/api/v1/pet-tracker/addpet
Request: POST
 
Body- Type-JSON
{
"petType": "CAT",
"trackerType": "SMALL",
"ownerId": 125,
"inZone": false,
"lostTracker": false
}

### Add a Cat (Inside Zone)#####
---CURL---

curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "CAT",
"trackerType": "SMALL",
"ownerId": 126,
"inZone": true,
"lostTracker": false
}'

---------POSTMAN -------
URL: http://localhost:8080/api/v1/pet-tracker/addpet
Request: POST
Body -JSON 
{
"petType": "CAT",
"trackerType": "SMALL",
"ownerId": 126,
"inZone": true,
"lostTracker": false
}

### Add a Dog (Outside Zone)#######
--CURL---
curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "DOG",
"trackerType": "medium",
"ownerId": 127,
"inZone": false
}
'
---------POSTMAN -------
URL: http://localhost:8080/api/v1/pet-tracker/addpet
Request: POST
Body-JSON :

{
"petType": "DOG",
"trackerType": "medium",
"ownerId": 127,
"inZone": false
}

### Add Another Dog (Inside Zone)#######
--CURL---
curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "DOG",
"trackerType": "medium",
"ownerId": 128,
"inZone": true
}
'
---------POSTMAN -------
URL: http://localhost:8080/api/v1/pet-tracker/addpet
Request: POST
Body- JSON Data:

{
"petType": "DOG",
"trackerType": "medium",
"ownerId": 128,
"inZone": true
}


#######GET ######

### Get All Pets
--CURL---
  curl --location 'http://localhost:8080/api/v1/pet-tracker/pets'

---------POSTMAN -------
Request: GET
URL: http://localhost:8080/api/v1/pet-tracker/pets



### Get Summary of Pets Outside Zone ######
---------CURL-----------
  curl --location 'http://localhost:8080/api/v1/pet-tracker/outside-zone-status'

---------POSTMAN -------
Request: GET
URL: http://localhost:8080/api/v1/pet-tracker/outside-zone-status


### Get Summary of Pets based on given param inZone(true/false)#######
---------CURL-----------
  curl --location 'http://localhost:8080/api/v1/pet-tracker/inzone-status?inZone=true'

---------POSTMAN -------
Request: GET
URL: http://localhost:8080/api/v1/pet-tracker/inzone-status?inZone=true

####### Run Tests Locally#######

- Run Complete tests

  - mvn test -Dtest=PetControllerTest
  - mvn test -Dtest=PetServiceTest

- Run Individual test methods
  -mvn test -Dtest=PetServiceTest#testAddCatPet_InvalidTrackerTypeException
  -mvn test -Dtest=PetServiceTest#testAddDogPet



# DB- h2 is used
To check real time DB data please make sure to run below cmds in the root folder first
### mvn clean install  
### mvn spring-boot::run 

URL: http://localhost:8080/api/v1/pet-tracker/h2-console
  -check application.properties for db name, user and pwd

---

# SwaggerAPI DOC Reference
To check Swagger Api Doc make sure to run below cmd in the root folder
### mvn spring-boot::run 
URL: http://localhost:8080/api/v1/pet-tracker/swagger-ui/index.html

---
