#Projet 6: Les Amis de l'Escalade

##Build packaging
```
 - Dans le terminal, dans la racine de votre projet:
mvm clean package
```

## Run
```
cd target
java -jar escaladefriendsp6ocr.war
```

##Start Web page
- http://localhost:8080

## Application.propertie
```
#PROD
#spring.profiles.active=prod
#DEV
spring.profiles.active=prod
```

## Paramétrage
```
application-prod.properties:
- server.port = 8088
- datasource url = jdbc:mysql://localhost:3306/amisescalade
- username = root
- password = "mettre le mot de passe de votre base de données"
```

##Commande de base de Github
```
git add .
git status
git commit -m "message"
git push (origin master)
mvn spring-boot:run
```