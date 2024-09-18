# Code Synthesis Training

This is a Spring Boot project for code synthesis training.

## Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

## Building and Running the Project

1. **Clone the repository:**
   ```sh
   git clone https://github.com/ar-var-il/code-synthesis-training.git
   cd code-synthesis-training/
   ```

2. **Build the Maven project:**
   ```sh
   ./mvnw clean package
   ```
   
3. **Build the Docker image:**
   ```sh
   docker build -t code-synthesis-training:latest .
   ```

4. **Start the Docker containers:**
   ```sh
   docker-compose up -d
   ```

## Accessing the Application

- The application will be available at `http://localhost:8080`.
- MongoDB will be accessible at `mongodb://localhost:27017`.

## Stopping the Docker Containers

To stop the Docker containers, run:
```sh
docker-compose down
```
