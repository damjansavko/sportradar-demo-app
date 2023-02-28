# sportradar-demo-app
A demo app for processing and exposing sports data

1. Checkout the project
2. Ensure you have Maven installed (either inside your IDE or standalone)
3. You can package the project into a war using the command '**mvn package**'
     - This will create an exploded folder with the name _demo-app-0.0.1-SNAPSHOT_ inside the _/target_ folder
     - It will run the tests with the surefire plugin and create a report inside /target/surefire-reports
 4. You can run the code using the _jetty-maven-plugin_ using the command '**mvn jetty:run**'
     - You will see a lot of warnings in the console when running the _jetty-maven-plugin_. There is an issue I haven't been able to resolve regarding the way the embedded Jetty loads the Jersey libraries but don't worry they are just warnings
     - Open your browser on **http://localhost:8080/**. You should see a simple web page showcasing the different API calls you can use
