#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Build the Maven project
./mvnw clean package

# Build the Docker image
docker build -t code-synthesis-training:latest .

# Start the Docker containers
docker-compose up -d