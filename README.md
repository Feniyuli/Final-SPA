# Dinner For Two

## Build

./gradlew build

# Run

* Select "Application.kt" and execute run in Intellij idea
* Run browser on http://localhost:8081 to get access to the swagger UI.

# Database

The database is an embedded database. It is configured in the application.yaml in the resources directory. There is
configuration for production and one for testing.

The test database is in memory only and only existis while tests are running.

In production the database is persisted. It is stored at this location:
~/dinner-prod-db

The database can be accessed through the h2 console at this location:

http://localhost:8081/h2/

Use the correct connection URL:

* For production: jdbc:h2:file:~/dinner-prod-db
* For testing: jdbc:h2:mem:dinner-dev-db