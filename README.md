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