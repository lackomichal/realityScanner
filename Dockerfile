FROM openjdk:11-jdk-slim
RUN mkdir -p /usr/share/man/man1mkdir -p /usr/share/man/man1
RUN apt-get update && apt-get install -y maven

COPY . realityScanner

WORKDIR realityScanner
RUN mvn clean install

#EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/RealityScanner.jar"]