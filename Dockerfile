FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/classes /app

EXPOSE 50050

CMD ["java", "-cp", "/app", "HelloWorld"]