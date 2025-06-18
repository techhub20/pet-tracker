## PET TRACKING APPLICATION

A Spring Boot Java application that tracks pets (cats and dogs) using different types of trackers . It calculates how many pets are outside/Inside the power saving zone , grouped by pet type and tracker type.

This Application uses two types of data storage mechanisms Real DB like H2(can be used for real time db strorage) and InMemoryDB (used for testing purpose with ActiveProfile('test'))

----

### Reference Documentation
----
````bash
### Tech Stack

-Java 11
-Spring Boot 2+

----

### REST API to:
````bash
-Add pet data
-Get all stored pets
-Get summary of pets outside/inside zone, grouped by type and tracker

### Build and Run
````bash
-mvn clean install
-mvn spring-boot:run
-localhost-
-http://localhost:8080/api/v1/pet-tracker/

##### ---------Curl cmds for local testing-------

### Add a Cat (Outside Zone)

curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "CAT",
"trackerType": "SMALL",
"ownerId": 125,
"inZone": false,
"lostTracker": false
}'

### Add a Cat (Inside Zone)

curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "CAT",
"trackerType": "SMALL",
"ownerId": 126,
"inZone": true,
"lostTracker": false
}'

### Add a Dog (Outside Zone)

curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "DOG",
"trackerType": "medium",
"ownerId": 127,
"inZone": false
}
'

### Add Another Dog (Inside Zone)

curl --location 'http://localhost:8080/api/v1/pet-tracker/addpet' \
--header 'Content-Type: application/json' \
--data '{
"petType": "DOG",
"trackerType": "medium",
"ownerId": 128,
"inZone": true
}
'

### Get All Pets

curl --location 'http://localhost:8080/api/v1/pet-tracker/pets'

### Get Summary of Pets Outside Zone

curl --location 'http://localhost:8080/api/v1/pet-tracker/outside-zone-status'

### Get Summary of Pets based on given param inZone(true/false)

curl --location 'http://localhost:8080/api/v1/pet-tracker/inzone-status?inZone=true'

### -----Run Tests-----

- Run Complete tests

  - mvn test -Dtest=PetControllerTest
  - mvn test -Dtest=PetServiceTest

- Run Individual test methods
  -mvn test -Dtest=PetServiceTest#testAddCatPet_InvalidTrackerTypeException
  -mvn test -Dtest=PetServiceTest#testAddDogPet

### DB- h2

-mvn clean install
-mvn spring-boot:run

- http://localhost:8080/api/v1/pet-tracker/h2-console
  -check application.properties for db name, user and pwd

### Swagger for API DOC Reference

-mvn clean install
-mvn spring-boot:run

- http://localhost:8080/api/v1/pet-tracker/swagger-ui/index.html
