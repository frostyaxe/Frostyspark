FROM maven:latest
WORKDIR /test
COPY . /test
CMD mvn clean test