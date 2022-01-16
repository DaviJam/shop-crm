# shop-crm

SpringBoot application example with 3 pages : user login, user registration and welcome page.
![img](assets/img.png)
![img1](assets/img_1.png)

### 1. Create the application with spring frameworks
```
src
 | 
 + main
    |
    + java
        |
        + eu.ensup.shopcrm
            |
            + controller
                |
                + UserController    #Used to define endpoints and handle resources request
            + domain
                |
                + User              #Entity which is our data model class annotated with @Entity
            + repository
                |
                + UserRepository    #Entity repository used to handle data persistence of User entity. Its should extends the JpaRepository interface 
            + service
                |
                + UserService       #Not mandatory but in this example, we use a service to interact with the Entity Repository 
            + ShopCrmApplication    #Entry point of this app. It should be annotated with @SpringBootApplication. In this example, we also annotated it with @Controller to handle request to '/' path. It redirects to '/user/login'
    + resources
        |
        + static
            |
            + css
               |
               + styles.css         #apply some CSS to make the front end user friendly
            + js
               |
               + app.js             #apply somme behaviour when entering 2 different password (register page) and animations.
        + templates
            |
            + error.html            #This page is shown, if a unknown path is requested
            + login-page.html       #This page is shown on '/' path request. It allows a user to login.
            + register-page.html    #This page is shown on '/user/register' path request. It allows a user to register an account.
            + welcome.html          #This page is shown when the user has logged in successfully.
        + application.yml           #choose which profile to use
        + application-dev.yml       #Developer configuration
        + application-prod.yml      #production configuration
```

Any request for a unknown path is redirected to the default white page. This behavious is defined in the .yml configuration file.
> server.error.whitelabel.enable=true <br>
> server.error.path: /error (default)

### 1. Containerizing the application with Docker
##### Creating the DockerFile
``` 
Dockerfile

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

# Go to target directory
WORKDIR ./target

# Run the springboot app
CMD ["java", "-jar -Dspring.profiles.active=prod", "shop-crm-0.0.1-SNAPSHOT.jar"]
```

#### 2. Creating the docker image and hosting it on the docker hub
1) Login to docker hub from host
    > docker login

2) Build Docker image with tag name shop-crm-server
    > docker build . -t shop-crm-server

3) Tag the image in order to push on docker hub 
    > docker tag shop-crm-server:latest dada971/shop-crm-server

4) Push image to docker hub
    > docker push dada971/shop-crm-server

#### 3. Create docker compose file to add a MySQL database container
```
version: '3.4'
services:
  server:
    image: dada971/shop-crm-server # use image from docker hub
    restart: always     # restart if failure at runtime
    depends_on:
      - db              # indicate that the server depends on our database defined as db
    network_mode: host  # indicate that the server should be exposed to the host network 

  db:
    image: mysql        # this service uses the mysql docker image
    command: --default-authentication-plugin=mysql_native_password # use the native password generator to define a password
    restart: always     # restart if failure at runtime
    cap_add:
      - SYS_NICE        # CAP_SYS_NICE handle error silently
    environment:        # environment variable for the MySQL server
      - MYSQL_USER=${MYSQL_USER} 
      - MYSQL_PASSWORD=${MYSQL_PASSWORD}
      - MYSQL_DATABASE=${MYSQL_DATABASE}
      - MYSQL_ALLOW_EMPTY_PASSWORD=no
    ports:              # indicate that the port should be exposed to the host. TYhis allows the server to acces the database.
      - 3306:3306
```