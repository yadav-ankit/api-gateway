# api-gateway

The Gateway app of the Microservice.

It takes all the requests and checks if they are valid based on #JWT token.

If not then it generates and returns a new JWT token to the frontEnd (Angular).

All the valid request are then forwarded to another Microservice.
