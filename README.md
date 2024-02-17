# User Management App 

![run-tests](https://github.com/tymefighter/user-management-app/actions/workflows/run-tests.yml/badge.svg)

A sample web application for managing userManager built using Java. 
It exposes REST APIs using Java Servlets and a UI which is SSR rendered using JSP.

Note: This application was implemented for learning purposes only.

## Features

### UI for user management



### REST APIs for user data querying and mutation

Fetch users based on passed params:

```
GET /users?id=<id>&name=<name>&email=<email>&username=<username>
```

Create a user

```
POST /users

{
    "name": "Mock",
    "username": "mock",
    "email": "mock.mail@mock.com",
    "password": "MockPass"
}
```

Update a user:

```
PUT /users

{
    "name": "Another Mock",
    "password": "MockPassword1"
}
```

## How to run

### Deploy locally

Run the following:
```
./gradlew tomcatRun
```

### Run Tests

Run the following:
```
./gradlew test
```
