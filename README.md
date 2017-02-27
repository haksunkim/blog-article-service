# blog-article-microservice
Spring Boot microservice providing Articles

This service will be turned into microservice talking to middleware service with AMQP(RabbitMQ).
Currently providing REST API for articles (e.g. /articles, /articles/1 and so on)

By default, listening port 3500.

TODO List
1. remove REST API, and turn into microservice listening queue
2. implement pagination
3. implement search by tag, user
