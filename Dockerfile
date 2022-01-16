# From maven and java image
FROM maven:3.8.4-openjdk-11
# Update
RUN apt-get update
# Create data directory in container and copy content into it
COPY . ./data
# Choose data directory as working directory
WORKDIR ./data
# Clean and create package using maven project management tool. Skip running test
RUN mvn clean package -DskipTests=true
# Expose port 80 on this container
EXPOSE 80
# Go to target directory and run the springboot app
WORKDIR ./target
CMD ["java", "-jar -Dspring.profiles.active=prod", "shop-crm-0.0.1-SNAPSHOT.jar"]