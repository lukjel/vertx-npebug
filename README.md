# vertx-npebug
NPE exception

Simples possible example to produce NPE error after few refreshes of page (i.e. pressing F5 few times..).

Scenario:

1. Produce fat-jar (mvn clean package)
2. start jar: java -jar PATH_AND_NAME_FAT_JAR
3. open web-browser localhost:8484/test.html
4. refresh page few times
5. go back to console with java - see error logs...
