# API Documentation

## UserController

Base URL: '/users'

### Endpoints:

- POST '/users'
  Description: Creates a new user account.  
  Request Body: 'UserCreateDto'
  Response: Returns the created user information.  
  Authorization: No authorization required.

- GET '/users/{id}/  
  Description: Retrieves a user by ID.  
  Response: Returns the user information.  
  Authorization: No authorization required.

- GET '/users?name={name}/  
  Description: Retrieves users by their name.  
  Response: Returns a list of users with the specified name.  
  Authorization: No authorization required.
  In Postman: Params (key = name, value = someName)

- GET '/users' 
  Description: Retrieves all users.  
  Response: Returns a list of all users.  
  Authorization: No authorization required.

- POST '/users/{id}/reviews-received'  
  Description: Adds a review for a user.  
  Request Body: 'ReviewDto'
  Response: Returns the updated user information.  
  Authorization: Requires 'ROLE_USER' or 'ROLE_ADMIN'.

- GET '/users/{id}/reviews-received'
  Description: Retrieves reviews received by a user.  
  Response: Returns a list of reviews received.  
  Authorization: No authorization required.

- GET '/users/{id}/reviews-written' 
  Description: Retrieves reviews written by a user.  
  Response: Returns a list of reviews written.  
  Authorization: No authorization required.

- PUT '/users' 
  Description: Updates user information without changing reviews.  
  Request Body: 'UserCreateDto'  
  Response: Returns the updated user information.  
  Authorization: Requires 'ROLE_USER' or 'ROLE_ADMIN'.

---

## ReviewController

Base URL: ‘/reviews’

### Endpoints:

- GET ‘/reviews/{id}’  
  Description: Retrieves a review by ID.  
  Response: Returns the review information.  
  Authorization: No authorization required.

- GET ‘/reviews’  
  Description: Retrieves all reviews.  
  Response: Returns a list of all reviews.  
  Authorization: No authorization required.

- PUT ‘/reviews’  
  Description: Updates a review.  
  Request Body: ‘ReviewDto’  
  Response: Returns the updated review information.  
  Authorization: Requires ‘ROLE_USER’ or ‘ROLE_ADMIN’.

- DELETE ‘/reviews/{id}’  
  Description: Deletes a review by ID.  
  Response: No content returned.  
  Authorization: Requires ‘ROLE_ADMIN’.

---

## AdminController

Base URL: ‘/admin-panel’

### Endpoints:

- PUT ‘/admin-panel/users/{id}’  
  Description: Assigns the "ADMIN" role to a user.  
  Response: Returns the updated user information.  
  Authorization: Requires ‘ROLE_ADMIN’.

- DELETE ‘/admin-panel/users/{id}’  
  Description: Deletes a user by ID.  
  Response: No content returned.  
  Authorization: Requires ‘ROLE_ADMIN’.
