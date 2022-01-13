FROM maven:3.8.4-openjdk-11
RUN apt-get update
RUN apt-get install -y git
RUN git clone https://github.com/DaviJam/shop-crm.git
WORKDIR ./shop-crm
RUN mvn clean package
EXPOSE 80
RUN cd ./shop-crm/target/shop-crm-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["/usr/bin/java", "-jar", "./shop-crm/target/shop-crm-0.0.1-SNAPSHOT.jar"]