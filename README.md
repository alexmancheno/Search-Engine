# Phat Search Engine

## How to contribute
### Backend
#### How to run
1. Change directory into root of Maven project
```
cd backend/backend
```

2. Compile the Maven project
```
mvn clean package
```

3. Run
```
java -jar target/backend-0.1.jar
```

#### Deploying to server
1. Compile the Maven project
```
mvn clean package
```

2. Place the jar file in the server
```
scp ./target/backend-0.1.jar root@172.104.18.156:/root/applications/Search-Engine
```

3. Restart the jar file (while logged into server)
```
service search-engine-backend restart
```

4. Test (while logged into server)
```
curl localhost:8080
```