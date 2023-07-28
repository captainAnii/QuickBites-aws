<div align="center">
  <h1>QuickBites Food Delivery Platform</h1>
</div>
----

### 🚀 Frameworks and Language Used
- Framework: **Spring Boot**
- Language: **Java**
----

### 🌊 Data Flow

#### Controllers
| Controller       | Endpoints                                                                                             |
|------------------|-------------------------------------------------------------------------------------------------------|
| AdminController  | /admin/signup, /admin/signIn, /restaurant, /restaurant/{id}, /craveCrafter, /craveCrafter/{id}        |
| FoodieController | /foodie/signIn, /foodie/signup, /foodie/signOut, /feastFile/schedule, /feastFile/cancel, /restaurants |
|                  | /craveCrafters, /rating, /ratings/restaurant/{id}                                                    |
----
### Services
| Service             | Description                                                               |
|---------------------|---------------------------------------------------------------------------|
| AdminService        | Provides admin-related business logic and manages restaurants/crave crafters |
| AuthTokenService    | Validates and authenticates foodies and admins based on their email and authentication tokens |
| CraveCrafterService | Handles crave crafter-related operations, such as adding and deleting crave crafters |
| FeastFileService    | Handles feast file-related operations, including fetching, scheduling, and canceling feast files |
| FoodieService       | Provides methods for foodie-related actions, such as signing up, signing in, signing out, and scheduling feast files |
| RatingService       | Handles rating-related operations, such as adding ratings and retrieving ratings for restaurants |
| RestaurantService   | Manages restaurant-related operations, such as adding, deleting, and fetching restaurants |
----
### Repositories
| Repository       | Description                                                 |
|------------------|-------------------------------------------------------------|
| AdminRepo        | Manages CRUD operations for Admin entities                 |
| AuthTokenRepo    | Manages CRUD operations for AuthenticationToken entities   |
| CraveCrafterRepo | Manages CRUD operations for CraveCrafter entities          |
| FeastFileRepo    | Manages CRUD operations for FeastFile entities             |
| FoodieRepo       | Manages CRUD operations for Foodie entities                |
| RatingRepo       | Manages CRUD operations for Rating entities                |
| RestaurantRepo   | Manages CRUD operations for Restaurant entities            |
----
### Usage
- Once the QuickBite Food Delivery System is up and running, you can interact with it using various API endpoints. Here are some example requests using cURL:

- ***Add a foodie***:
```
curl -X POST -H "Content-Type: application/json" -d '{
    "foodieName": "JohnDoe",
    "foodieEmail": "john.doe@example.com",
    "password": "john123",
    "address": "123 Main St, City",
    "contactNumber": "9876543210"
}'
http://localhost:8080/quickbite/signup
```
- ***Foodie Sign In***:
```
curl -X POST -H "Content-Type: application/json" -d '{
    "email": "john.doe@example.com",
    "password": "john123"
}'
http://localhost:8080/quickbite/signIn
```
- ***Foodie Sign Out***:
```
curl -X DELETE -H "email: john.doe@example.com" -H "token: <auth_token>"
http://localhost:8080/quickbite/signOut
```
- ***Fetch all restaurants***:
```
curl http://localhost:8080/quickbite/restaurants
```
- ***Fetch all crave crafters***:
```
curl http://localhost:8080/quickbite/craveCrafters
```
- ***Add a rating***:
```
curl -X POST -H "Content-Type: application/json" -d '{
    "restaurantId": <restaurant_id>,
    "foodieId": <foodie_id>,
    "ratingValue": 4,
    "comment": "Great experience!"
}'
http://localhost:8080/quickbite/rating
```
- ***Get ratings for a restaurant***:
```
curl http://localhost:8080/quickbite/ratings/restaurant/<restaurant_id>
```
- ***Schedule a feast file***:
```
curl -X POST -H "Content-Type: application/json" -d '{
    "craveCrafter": {
        "id": <crave_crafter_id>
    },
    "status": "SCHEDULED",
    "quantity": 2,
    "orderScheduledTime": "2023-07-28T18:00:00",
    "foodie": {
        "id": <foodie_id>
    }
}'
http://localhost:8080/quickbite/feastFile/schedule
```
- ***Cancel a feast file***:
```
curl -X DELETE -H "email: john.doe@example.com" -H "token: <auth_token>"
http://localhost:8080/quickbite/feastFile/cancel
```
- Feel free to explore and interact with other API endpoints as well.
----
### Contributing
- Contributions to the QuickBite project are welcome. If you find any issues or have suggestions for improvement, please open an issue or submit a pull request on the GitHub repository.
----
### License
- This project is licensed under the MIT License.
----
### Acknowledgments
- OpenAPI Specification - The specification used for documenting the API endpoints.
- Spring Boot - The framework used for building the QuickBite platform.
- Hibernate - The ORM (Object-Relational Mapping) framework used for interacting with the database.
- MySQL - The relational database management system used for storing food and user data.
---- 
### Contact
- For any questions or inquiries, please contact:
- Project Maintainer: Aniket Yogesh Gosavi
- Email: `aniket9766228627@gmail.com`
