FROM adoptopenjdk/openjdk8-openj9:alpine-jre
ADD webDemo/build/libs/webDemo-1.0.0.war /webDemo.war
ENTRYPOINT java -jar /webDemo.war