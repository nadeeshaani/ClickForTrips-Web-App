# ClickForTrips-Web-App
Click for Trips is a web application developed with Java, and Springboot to learn and practice the basics of Web Application Security

## Screenshots
![login_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/screenshot_login.png?raw=true)
![okta_login_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image_oktalogin.png?raw=true)
![okta_signup_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image_signup_okta.png?raw=true)
![index_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image_index.png?raw=true)
![bookings_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image_bookings.png?raw=true)
![addbooking_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image_addlogin.png?raw=true)


## Configuration
1. Open `application.properties` (or relevant configuration file).
2. Replace the placeholders with the following provided actual values or add them as runtime variables.
   ```
   ${SPRING_DATASOURCE_URL} -> jdbc:mysql://172.187.178.153:3306/isec_assessment2
   ${SPRING_DATASOURCE_USERENAME} -> isec
   ${SPRING_DATASOURCE_PASSWORD} -> EUHHaYAmtzbv
 
   {OKTA_OAUTH_ISSUER} -> https://dev-17371279.okta.com/oauth2/default
   ${OKTA_CLIENT_ID} -> 0oadcx4kgrRSe3iey5d7
   ${OKTA_CLIENT_SECRET} -> ox8gQbGPRZruOs_UElAvr4KawKrEIGRMYZkpo1prZGoMcMn6U0v1eTw59DTDA0ns
  


## Run your application in Tomcat 9 with HTTPS with the following steps
### 1. Configure HTTPS
   - Obtain or create an SSL certificate.
   - Configure Tomcat to use HTTPS by updating the server's server.xml file
     Replace the following `keystoreFile` and `keystorePass` with your actual values
      ```
      <Connector SSLEnabled="true" acceptCount="100" clientAuth="false"
      disableUploadTimeout="true" enableLookups="false" maxThreads="25"
      port="8443" keystoreFile="{path_to_keystore}/.keystore" keystorePass="{password_of_keystore}"
      protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https"
      secure="true" sslProtocol="TLS"/>
  


### 2. You can use one of the following ways to run the application in Tomcat 9
1. Using IntelliJ IDEA with Smart Tomcat plugin
2. Manually generating the war file and deploying it to the Tomcat server


#####      1. Using IntelliJ IDEA with Smart Tomcat plugin

  - Open this project in IntelliJ IDEA.
  - Install and configure the Smart Tomcat plugin (if not installed).
  - Configure Smart Tomcat with your Tomcat 9 server.
    - Go to edit configurations in IntelliJ IDEA and press the add configuration button as depicted below
      ![config_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image1-configuration.png?raw=true)

    - From the list, choose smart Tomcat
    - Then you can configure how to run your application according to your liking. Here's an example
      
      ![config_img](https://github.com/nadeeshaani/ClickForTrips-Web-App/blob/main/readme_images/image2.png?raw=true)

    - You can either add sensitive data as the runtime variables here (e.g., data source, Okta credentials) or else, hardcode the values in the application.properties. (I'll show you where AND what to add later in this README)
  - Now you are all set up. Use the run button, to run your application on Tomcat 9
  - Access the application with
    https://localhost:8443/clickfortrips


#####      2. Manually generating the war file and deploying it to the Tomcat server

   - Open your command Prompt
   - navigate the folder where your project is
   - Use the following command to clean the project and generate a war file.
        | mvn clean install
   - The above command will generate a `.war` file inside the project's target folder
   - copy that war file, navigate to webapps folder in Tomcat 9 and paste the war file there.
   - rename the war file to `clickfortrips.war` so that it matches the context path
   - Now again open up the command prompt
   - Navigate to the bin folder of Tomcat
   - Use the following command
      |catalina.bat run
   - Access the application with
     https://localhost:8443/clickfortrips

    
    

