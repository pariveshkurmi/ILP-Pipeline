FROM maven:3-alpine

COPY pom.xml ilp/

COPY src/ ilp/src/

WORKDIR ilp/

RUN mvn clean install

FROM tomcat:latest

COPY target/integrated-learning-project.war /usr/local/tomcat/webapps