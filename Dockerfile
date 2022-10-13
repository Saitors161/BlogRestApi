FROM adoptopenjdk:11-jre-hotspot
COPY target/blog-0.0.1-SNAPSHOT-jar-with-dependencies.jar  /blog.jar
ENV HOST_DB=db
ENV fileStorage = /storage
#CMD [ "sleep", "600"]
CMD ["java", "-jar","/blog.jar"]
