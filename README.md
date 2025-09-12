# SmartCart Advisor
It deliver intelligent product recommendations using collaborative filtering and content-based logic. Built with Spring Boot and MySQL, it exposes RESTful APIs for seamless frontend consumption and is architected for future integration with Python-based ML microservices.

## Features
  - Content-Based Filtering using cosine similarity on product feature vectors
  - Collaborative Filtering based on user-product rating patterns
  - Dynamic Metadata Filtering (brand, color, material, etc.)
  - Batch Similarity Scoring for grouped product recommendations
  - Discount Rule Engine for personalized pricing
  - User Preference Modeling with age, location, and name-based segmentation
  - RESTful APIs for CRUD operations and search endpoints
  - Pagination & Sorting for scalable product discovery

## Architecture Overview

  - Client (Frontend or API Consumer)
     
  - REST Controller (Spring Boot) 
     
  - Service Layer (Advisor Logic) 
  
  - DAO Layer (Spring Data JPA + MySQL) 
     
  - Database (Users, Products, Ratings, Metadata, Features)

## Tech Stack

- Language: Java 17+
  
- Framework: Spring Boot
  
- ORM: Spring Data JPA
  
- Database: MySQL
   
- Validation: Jakarta Bean Validation
  
- Build Tool: Maven

## API Highlights

  - GET /recommendations/{userId}: Get personalized product recommendation

  - POST /search/api/similarity: Search products by feature vector similatrity

  - POST /search/api/combined/search: Unified search using multiple criteria

  - GET /product/api/pricerange: Filter products by price range

  - GET /discount/api/product: Apply discount rules to a product

## Sample Input(Feature Vector Search)
```
  {
  "featureVector": [0.23, 0.87, 0.45, 0.12, 0.67, 0.34]
  }
```
## Sample Output
```
[
  {
    "productId": 101,
    "name": "Eco-Friendly Water Bottle",
    "similarityScore": 0.92
  },
  ...
]
```

## Getting Started

  - Clone the repo
   ```
  git clone https://github.com/risrchanish/smartcart-advisor.git
  ```
  - Configure application.properties
```
spring.datasource.url=jdbc:mysql://<HOST>:<PORT>/<DB>
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```
  - Run the app
```
mvn spring-boot:run
```

## Contributing

 - Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.

  







