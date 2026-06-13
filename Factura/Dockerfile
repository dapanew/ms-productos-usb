FROM openjdk:21-jdk
WORKDIR /app
COPY target/*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 9090
CMD java $JAVA_OPTS -jar /app/app.jar