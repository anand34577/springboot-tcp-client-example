# Spring Boot TCP Client with REST API

A robust Spring Boot application that provides a TCP client with REST API integration for sending and receiving data from TCP servers. The application follows industry standard practices and includes Swagger documentation.

## Features

- TCP client for sending and receiving data from TCP servers
- REST API for interacting with the TCP client
- Automatic retry mechanism for TCP connections
- Comprehensive error handling
- Swagger UI for API documentation and testing
- Configuration via application.properties

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Usage Examples](#usage-examples)
- [Project Structure](#project-structure)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java 1.8
- Spring Boot
- Spring Integration
- Spring Web
- Lombok
- Swagger/OpenAPI 3.0

## Getting Started

### Prerequisites

- Java 1.8 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/springboot-tcp-client.git
cd springboot-tcp-client
```

2. Build the application:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

## Configuration

The application can be configured through the `application.properties` file:

```properties
# TCP Client Configuration
tcp.client.server-host=localhost
tcp.client.server-port=8081
tcp.client.connection-retry-interval=5000
tcp.client.socket-timeout=5000

# Server Configuration
server.port=8080
```

## API Documentation

The API documentation is available via Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

This provides a comprehensive interface to explore and test all available REST endpoints.

## Usage Examples

### Sending a message to TCP server via REST API

```bash
curl -X POST http://localhost:8080/api/tcp/send \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello TCP Server"}'
```

### Expected Response

```json
{
  "response": "Response from TCP server",
  "success": true,
  "errorMessage": null
}
```

## Project Structure

```
───src
    └───main
        ├───java
        │   └───com
        │       └───example
        │           └───server
        │               └───client
        │                   │   ServletInitializer.java
        │                   │   TcpClientApplication.java
        │                   │
        │                   ├───config
        │                   │       TcpClientConfig.java
        │                   │       TcpProperties.java
        │                   │
        │                   ├───connection
        │                   │   └───factory
        │                   │           TcpNetClientRetryConnectionFactory.java
        │                   │           TcpNetClientRetryConnectionFactory.java~
        │                   │
        │                   ├───controller
        │                   │       TcpController.java
        │                   │       TcpController.java~
        │                   │
        │                   ├───dto
        │                   │   ├───request
        │                   │   │       TcpRequest.java
        │                   │   │
        │                   │   └───response
        │                   │           TcpResponse.java
        │                   │
        │                   ├───gateway
        │                   │   │   TcpClientGateway.java
        │                   │   │
        │                   │   └───impl
        │                   │           TcpClientGatewayImpl.java
        │                   │
        │                   ├───handler
        │                   │   └───exception
        │                   │           GlobalExceptionHandler.java
        │                   │
        │                   └───service
        │                           TcpService.java
        │
        └───resources
            │   application.properties
            │
            ├───static
            └───templates
```

## Error Handling

The application includes comprehensive error handling:

- TCP connection failures are automatically retried
- REST API errors return appropriate HTTP status codes
- Detailed error messages are provided in the response

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
