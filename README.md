# Phat Search Engine

## How to contribute
### Backend
#### How to run
1. Change directory into root of Maven project
```
cd search-engine/application
```

2. Compile the Maven project and run it:
```
mvn clean package
java -jar target/engine-0.1.jar
```

Alternatively, you can run the `mvn exec` plugin (faster):
```
mvn compile exec:java
```

#### Deploying to server
1. Compile the Maven project
```
mvn clean package
```

2. Place the jar file in the server
```
scp ./target/engine-0.1.jar root@phatsearch.net:/root/applications/Search-Engine
```

3. Restart the jar file (while logged into server)
```
service search-engine-backend restart
```

4. Test (while logged into server)
```
curl localhost:8080
```