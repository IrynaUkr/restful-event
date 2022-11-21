### Task REST API-Architecture

- Create maven project with 4 modules:

1. event-service-api;
2. event-service-dto;
3. event-service-impl;
4. event-service-rest.

- event-service-rest module should contain EventServiceController which provides REST API interface according to 2nd or
  3rd level of REST API maturity and responds with list of Events.

_In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller.
These components are identified by the @RestController annotation.
A key difference between a traditional MVC controller and the RESTful web service controller is the way
that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering
of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object.
The object data will be written directly to the HTTP response as JSON._

- Document methods in EventServiceController using Swagger 2 annotations.

  **Swagger** is an open source set of rules, specifications and tools for developing and describing RESTful APIs. 
  The Swagger framework allows developers to create interactive, machine and human-readable API documentation.
  Swagger helps users build, document, test and consume RESTful web services. 
  It can be used with both a top-down and bottom-up API development approach.
* In the top-down, or design-first, method, Swagger can be used to design an API before any code is written. 
* In the bottom-up, or code-first method, Swagger takes the code written for an API and generates the documentation.

  http://localhost:8080/swagger-ui.html

- Create runnable Spring Boot JAR with dependencies using spring-boot-maven-plugin.

- implement REST APIs according to all **Richardson Maturity** Level as additional task.
  **Richardson Maturity Model** (RMM) is a four-level scale that indicates extent of API conformity to the REST framework.

The maturity of a service is based on three factors in this model: URI, HTTP Methods and HATEOAS (Hypermedia).
If a service employs these technologies, it’s considered more mature.
This model covers only API architectural style, not data modeling or other design factors.

* Level-1: Resource-Based Address/URI:
  use only HTTP POST for all operations. Every resource is identifiable by its own unique URI, including nested
  resources.
  This resource-based addressing can be considered the starting point for APIs being RESTful.
* Level-2: HTTP Verbs: APIs at this level fully utilize all HTTP commands or verbs such as GET, POST, PUT, and DELETE.
  The request body doesn’t carry the operation information at this level.
  The return codes are also properly used so that clients can check for errors.
* Level-3: HyperMedia/HATEOAS: Most mature level that uses HATEOAS (Hypertext As The Engine Of Application State).
  It's also known as HyperMedia, which basically consists of resource links and forms.
  Establishing connections between resources becomes easy as they don’t require human intervention
  and aids client-driven automation.

Spring HATEOAS offers three abstractions for creating the URI

     * RepresentationModel,
     * Link,
     * WebMvcLinkBuilder. 

We can use these to create the metadata and associate it to the resource representation.
Source: https://www.baeldung.com/spring-hateoas-tutorial_

#### using docker

docker build -t event .
docker run -d -p 8080:8080 event

docker images
docker ps