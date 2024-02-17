# User Management App 

![run-tests](https://github.com/tymefighter/user-management-app/actions/workflows/run-tests.yml/badge.svg)

A sample web application for managing userManager built using Java. 
It exposes REST APIs using Java Servlets and a UI which is SSR rendered using JSP.

Note: This application was implemented for learning purposes only.

## Features

### UI for user management

#### Adding a new user

https://github.com/tymefighter/user-management-app/assets/40702069/8928b324-09f7-4fd0-879c-f145a5bb70cd

#### Editing an existing user

https://github.com/tymefighter/user-management-app/assets/40702069/380f0607-6f01-4342-b0a5-85a0ad62b55b

### REST APIs for user data querying and mutation

#### Fetch users based on passed params:

```
GET /users?id=<id>&name=<name>&email=<email>&username=<username>
```

<img width="1344" alt="Screenshot 2024-02-17 at 6 50 54 PM" src="https://github.com/tymefighter/user-management-app/assets/40702069/42bd1e9a-6890-4525-ad43-d9d51235fa7a">

#### Create a user

```
POST /users

{
    "name": "Mock",
    "username": "mock",
    "email": "mock.mail@mock.com",
    "password": "MockPass"
}
```

<img width="1345" alt="Screenshot 2024-02-17 at 6 52 35 PM" src="https://github.com/tymefighter/user-management-app/assets/40702069/dd5549f2-92b4-42de-9ad3-a8bcb9ea27a2">


#### Update a user:

```
PUT /users

{
    "name": "Another Mock",
    "password": "MockPassword1"
}
```

<img width="1343" alt="Screenshot 2024-02-17 at 6 53 58 PM" src="https://github.com/tymefighter/user-management-app/assets/40702069/b34a5149-bf6c-4283-bd18-0fd3be75c88e">


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
