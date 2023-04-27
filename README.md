
# Taste the Difference

This web application was created as a case study project at DHBW Stuttgart in April 2023. The aaplication enables customers to make restaurant reservations, order food, pay and write a review. 
The owners of this application are able to administer restaurant information and settings and get insights on their restaurants.
This includes easy Set Up and navigation within Application, with protected access. 

There is two possibilites to acces the application: (Choose which one you prefer)
1. Setting up Backend + Frontend
2. Setting Up SPA (Single Page Application)

## Table of contents

- Features
- Requirements
- Set Up Frontend
- Set Up Backend
- Set Up SPA
- Authors

## Application Features

For customers:
- Login and GuestArea
- Choose restaurant and access information
- Make a reservation and display booking details
- Order food
- Delete Order
- Rate the restaurant
- Payment in the app

For restaurant owners:
- Login and OwnerArea
- Add restaurants, iteams, workers and tables
- Order life cycle(open, onProcess, Ready, Delivered)
- Delete items,restaurants, reservation
- Display reservation details and revenue

## Requirements

Backend:
- Java 17
- Spring Boot
- Java IDE
- Build: Gradle
- Version Control GitLab: https://git.dhbw-stuttgart.de/case-study-se/project-team1

Frontend:
- TypeScript
- Svelte
- NPM
- Flowbite-svelte
- VS Code / other IDE

## Set Up Backend

1. Clone Frontend GitHub Repository in Intelli J: `https://git.dhbw-stuttgart.de/case-study-se/project-team1/dinner-for-two` 

2. Build:
```bash
./gradlew build   
```

Make sure: 
- Project SDK: JDK-17
- Gradle JVM: 17  

3. Run/Debug:

```bash
  Application.kt    
```

4. Access Swagger UI:
```bash
  http://localhost:8081 or http://localhost:8081/swagger-ui/index.html#/
```

5. Database:

The database is an embedded database. It is configured in the application.yaml in the resources directory. There is configuration for production and one for testing.

The test database is in memory only and only existis while tests are running.

In production the database is persisted. It is stored at this location:

```bash
   ~/dinner-prod-db 
```

The database can be accessed through the h2 console at this location:

```bash
   http://localhost:8081/h2/
```

Use the correct connection URL:
- For production: jdbc:h2:file:~/dinner-prod-db
- For testing: jdbc:h2:mem:dinner-dev-db

6. Add dummy data to application:

Acces database  through the h2 console (http://localhost:8081/h2/) and click "connect". Copy & Paste the following dummy data into the console and click "run":

```bash
https://git.dhbw-stuttgart.de/case-study-se/project-team1/dinner-for-two/-/wikis/uploads/18a39c58beac0c1d967e61b4f792b177/DBdummydata.txt
```


## Set Up Frontend

1. Clone Frontend GitHub Repository in VS Code or other IDE: `https://git.dhbw-stuttgart.de/case-study-se/project-team1/dinner-for-two-ui-new` 

2. Instalments:

```bash
  Install npm
  Install Node.js
  Install flowbite-svelte
  
```

3. Run: 

```bash
  npm run dev
```

4. Acces local host: (For better experience please use Firefox)

```bash
  http://localhost:5173/
```


## Set Up SPA (Single Page Application)
How to start the project?

#For IntelliJ Users:
1. Clone the SPA file from Gitlab
2. Open New Folder in intelliJ (Choose build.gradle file while opening)
3. Make Sure the JDK and Gradle version is 17
4. Press run/debug
5. Open localhost:8081 in any browser (For better experience please use Firefox)
```bash
  http://localhost:8081/
```
5. Load in the dummy data in the Database

```bash
   http://localhost:8081/h2/
```

Use the correct connection URL:
- For production: jdbc:h2:file:~/dinner-prod-db
- For testing: jdbc:h2:mem:dinner-dev-db

Copy & Paste the following dummy data into the console and click "run":

```bash
https://git.dhbw-stuttgart.de/case-study-se/project-team1/dinner-for-two/-/wikis/uploads/18a39c58beac0c1d967e61b4f792b177/DBdummydata.txt
```

6. enjoy!


## Wiki

The Wiki with additional information about the user documentation, further project information and scope, road map and diagrams can be found under: 

https://git.dhbw-stuttgart.de/case-study-se/project-team1/dinner-for-two/-/wikis/Die-Milesteins

## Team: Die Meilsteins

- Feni Yuliastutik
- Laura Makare
- Lyle Braunbart
- Jessica Greger
- Dennis Cassady
- Janis Jäger
- Annika Seemann
- Emanuel Forderer

