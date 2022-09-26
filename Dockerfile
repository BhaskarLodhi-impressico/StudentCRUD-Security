FROM openjdk:11
EXPOSE 8080
ADD target/StudentCRUD-Security.jar StudentCRUD-Security.jar.jar
ENTRYPOINT ["java","-jar","/StudentCRUD-Security.jar.jar"]