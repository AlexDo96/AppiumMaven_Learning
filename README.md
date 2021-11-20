# AppiumMaven_Learning
This is for learning Appium Maven at SDETPRO

*** Install Maven project for Appium ***

Step 1: Access to https://maven.apache.org/
+ Download Binary zip archive
+ Unzip apache-maven-3.8.3-bin.zip
+ Add the `bin` directory of the created directory apache-maven-3.8.3 to the `PATH` environment variable
+ Confirm with `mvn -v` in a new shell.

Step 2: Create new Project (in IDE) > Select Maven > Next > Input Name & Location new project > Finish

Step 3: Access to https://mvnrepository.com/
Copy dependency of: 
1. Java Client:
<!-- https://mvnrepository.com/artifact/io.appium/java-client -->
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>7.6.0</version>
</dependency>

2. Apache Commons Lang
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>

3. Apache Commons IO
<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.11.0</version>
</dependency>

4. Guava: Google Core Libraries For Java
<!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>31.0.1-jre</version>
</dependency>

Step 4: Add all dependencies to pom.xml of Maven project

Step 5: 
Click Load Maven Changes on pom.xml (Or press Ctrl + Shift + O) 
Or
Run command: mvn clean install

Step 6: Fetch External Libraries
Right click project (in IDE) > Maven > Reload project
