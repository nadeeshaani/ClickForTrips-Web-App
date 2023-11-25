# ClickForTrips-Web-App
Click for Trips is a web application developed with Java, and Springboot to learn and practice the basics of Web Application Security

# Run your application in Tomcat 9 with HTTPS with the following steps
### 1. Configure HTTPS
   - Obtain or create an SSL certificate.
   - Configure Tomcat to use HTTPS by updating the server's server.xml file
     Replace the following `keystoreFile` and `keystorePass` with your actual values
      ```
      Connector SSLEnabled="true" acceptCount="100" clientAuth="false"
      disableUploadTimeout="true" enableLookups="false" maxThreads="25"
      port="8443" keystoreFile="{path_to_keystore}/.keystore" keystorePass="{password_of_keystore}"
      protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https"
      secure="true" sslProtocol="TLS"
  


### 2. You can use one of the following ways to run the application in Tomcat 9
1. Using IntelliJ IDEA with Smart Tomcat plugin
2. Manually generating the war file and deploying it to the Tomcat server

##### 1. Using IntelliJ IDEA with Smart Tomcat plugin
  - Open this project in IntelliJ IDEA.
  - Install and configure the Smart Tomcat plugin (if not installed).
  - Configure Smart Tomcat with your Tomcat 9 server.
    - Go to edit configurations in IntelliJ IDEA
    - 
    
     
    
    

    
  - 
