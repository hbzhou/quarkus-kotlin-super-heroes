### Quarkus Kotlin Super Heroes
##### 1.init rest-hero reactive service
```shell
cd quarkus-kotlin-super-heroes

mvn io.quarkus:quarkus-maven-plugin:2.15.3.Final:create \
    -DprojectGroupId=com.itsz.quarkus\
    -DprojectArtifactId=rest-heroes \
    -DclassName="com.itsz.quarkus.superheroes.hero.HeroResource" \
    -Dpath="api/heroes" \
    -Dextensions="kotlin,resteasy-reactive-jackson,quarkus-hibernate-validator,quarkus-smallrye-openapi,quarkus-hibernate-reactive-panache,quarkus-reactive-pg-client"
```

##### 2.init rest-villains classic service
```shell
cd quarkus-kotlin-super-heroes

mvn io.quarkus:quarkus-maven-plugin:2.15.3.Final:create \
    -DprojectGroupId=com.itsz.quarkus\
    -DprojectArtifactId=rest-villains \
    -DclassName="com.itsz.quarkus.superheroes.villain.VillainResource" \
    -Dpath="api/villains" \
    -Dextensions="kotlin,resteasy-reactive-jackson"
```

##### 3.setup graalvm in WSL 2.0 sub system centos
```shell
## downloading graalvm package from github
wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.1/graalvm-ce-java17-linux-amd64-22.3.1.tar.gz
## config jdk env making sure your're using graalvm jdk
java -version
## install native-image
gu install native-image
## install package dependencies for build native image
yum install make gcc zlib-devel -y
```
##### 4.build your native image 
```shell
mvn clean package -Pnative
```