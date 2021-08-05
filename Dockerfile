FROM adoptopenjdk/openjdk8-openj9:alpine-jre
ADD webDemo/target/webDemo.war /webDemo.war
ENTRYPOINT java -jar /webDemo.war