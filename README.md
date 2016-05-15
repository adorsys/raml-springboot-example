# RAML Spring Boot Example

Simple Example defining a RESTful API via [RAML][raml] and implementing it in a [Spring Boot][spring-boot] application. 
The api module contains the [RAML definition](raml-springboot-api/src/main/resources/api.raml) and generates the classes with the [springmvc-raml-plugin][springmvc-raml-plugin].

## Build and Run

    mvn clean package
    java -jar raml-springboot-impl/target/raml-springboot-impl.jar
    
## Test
    
    # Create a new Todo
    curl -i -X POST -H 'Content-Type: application/json' -d '{ "task": "Design the API", "priority": 1 }' localhost:8080/api/todos
    # List all Todos
    curl -i -H 'Accept: application/json' localhost:8080/api/todos
    # Get one Todo by id
    curl -i -H 'Accept: application/json' localhost:8080/api/todos/1

 [raml]: http://raml.org/
 [spring-boot]: http://projects.spring.io/spring-boot/
 [springmvc-raml-plugin]: https://github.com/phoenixnap/springmvc-raml-plugin
