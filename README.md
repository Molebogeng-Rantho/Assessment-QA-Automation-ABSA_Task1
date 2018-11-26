# Assessment-QA-Automation-ABSA
For task one 
-download postman and sign in
-then import the project
-in Task1 folder
-Then right click on the imported project and run.

for task 2
-install maven on eclipse
-click on open file and open the project assessmentProject
-there are maven dependencies in the pom.xml file in the test-output folder 
-which will enable to run the project
-there is a configuration folder where the excel path can be changed, its in a folder named TestData and the chromedriver path can be changed aswell
-should they not be available
-add the following dependencies
<dependencies>			
        <dependency>				
             <groupId>junit</groupId>								
             <artifactId>junit</artifactId>								
             <version>3.8.1</version>								
             <scope>test</scope>								
        </dependency>				
        <dependency>				
            <groupId>org.seleniumhq.selenium</groupId>								
            <artifactId>selenium-java</artifactId>								
            <version>2.45.0</version>								
		</dependency>				
     <!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>6.10</version>
    <scope>test</scope>
</dependency>
     	
           <dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>4.0.0</version>
</dependency>
   

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>4.0.0</version>
</dependency>
    
</dependencies>
