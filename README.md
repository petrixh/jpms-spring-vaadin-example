# JPMS and Spring Example with Vaadin
This project is a Vaadin Labs project to investigate how Vaadin can be made to run in the Java 9+ module path.  

## Building and Running

### With JPMS
```bash
mvn clean install -Pproduction
```
```bash
cd web
```
```bash
java --add-modules java.instrument --patch-module flow.server=../patchflow/target/patchflow-0.0.1.jar --module-path=target/modules --module example.web/red.jackal.training.spring.jpms.web.WebApplication
```

### Without JPMS
```bash
mvn install
```
```bash
cd web
```
For production mode:
```
mvn package -Pproduction spring-boot:repackage
``` 
For dev mode: 
```bash
mvn package spring-boot:repackage
```
```bash
java -jar target/web-0.0.1.jar
```

Big tanks to Knut Borchers for the original JPMS Spring starter available here: https://github.com/qutax/jpms-spring-example that was modified here to run with Vaadin
