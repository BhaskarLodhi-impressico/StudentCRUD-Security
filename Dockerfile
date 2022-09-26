FROM openjdk:11
EXPOSE 8080
ADD target/studentcrud-security.jar studentcrud-security.jar.jar
ENTRYPOINT ["java","-jar","/studentcrud-security.jar"]
