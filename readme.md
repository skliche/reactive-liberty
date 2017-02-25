# Introduction
This is a sample project that demonstrates a simple microservice scenario that uses [rxJava](https://github.com/ReactiveX/RxJava), Netflix [Hystrix](https://github.com/Netflix/Hystrix) and integrates with [IBM WebSphere Liberty (micro) profile](https://developer.ibm.com/wasdev/blog/2016/10/26/what-is-microprofile/).
The repository contains two modules:
*   product backend - a RESTful microservice for product indices, product details and recommendations
*   product service - a RESTful microservice that creates a product overview using the backend services in a reactive manner

The folder liberty-server contains a sample liberty server configuration.