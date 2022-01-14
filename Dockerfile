FROM maven:3.8.4-openjdk-11
RUN apt-get update
RUN apt-get install -y git
#RUN git clone https://github.com/DaviJam/shop-crm.git
#WORKDIR ./shop-crm
COPY . ./data
WORKDIR ./data
RUN mvn clean package -DskipTests=true
EXPOSE 80
WORKDIR ./target
#RUN cd ./shop-crm/target/shop-crm-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "shop-crm-0.0.1-SNAPSHOT.jar"]