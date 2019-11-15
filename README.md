# Biometrics API

This API is responsible for collecting a user's biometric information and storing it in a database. The API will
also provide an analysis of the data if enough points are available. 

Currently, the API is built for a single user. In the future, multiple user accounts and user registration 
will be supported.

## API Integrations

The BiometricsAPI currently gathers data from: 
* FitBit for Meal and water consumption
* Garmin Connect for Heart Rate, Movement and Sleep data

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The following dependencies are required to run the API:
* Java 10 or later
* Mysql 5.7 or later
* [A FitBit App](https://dev.fitbit.com/getting-started/) (Optional) 
* A Garmin Connect Account (Optional)

### Compiling

A step by step series of examples that tell you how to get a development env running

1. Checkout code from github
2. Create and initialize a database with the included tables found in [database.sql](database.sql)
3. Run the gradle build command `./gradlew clean build` at project root directory

### Starting the API

- Create `application.properties` at the application root using the template below. Replace example variables with your credentials.

```
# Set Development Environment
spring.profiles.active={development/production}

# Fitbit Specific data import for Meal and Water Consumption
fitbit.enabled={true/false}
fitbit.application.client.id={CLIENT_ID}
fitbit.application.client.secret={CLIENT_SECRET}
fitbit.access.redirect_uri={REDIRECT_URL}

# Garmin Specific Import for user biometrics
garmin.enabled={true/false}

# Database Credentials
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://{SERVER_URL}:3306/{DATABASE}?serverTimezone=UTC
spring.datasource.username={DATABASE_USERNAME}
spring.datasource.password={DATABASE_PASSWORD}
spring.jackson.default-property-inclusion=NON_NULL

# API Security
api.username={API_USERNAME}
api.password={API_PASSWORD}

# SSL certificate for API communications (Optional)
#server.port=8443
#security.require-ssl=true
#server.ssl.key-store={SSL_KEY_STORE}
#server.ssl.keyStoreType=PKCS12
#server.ssl.keyAlias=tomcat
#server.ssl.key-store-password={KEY_STORE_PASSWORD}
```

- Change to the build directory `build/libs/` and run `java -jar *.jar`

The server should now be running on `http://localhost:8080/`. Basic Auth will need to be configured for 
each call.

## Built With

* [Gradle](https://gradle.org/) - Dependency Management