### database-sys-api-sb
* Version: 1.0.0
* Most up to date implementation will be found in branch `develop`.
* Creator: Aaron Renner
* This API project was created in Spring-Boot

### Introduction
This RESTful API provides database resources. This API is fully dynamic meaning it was ideally designed to not need any secific MySQL formatting, just plug-and-play!

### Documentation
* See the Swagger OpenAPI Specification: `https://app.swaggerhub.com/apis-docs/ARTechnology/database-sys-api/1.0.0`
* Postman collection and environment variables in `src/test/resources/postman`

### Getting Started
**Running Locally using IDE**
This project uses Spring profiles, and corresponding application properties .yaml files.
All values from the application properties can be overwritten by the environment!
* Use the following environment variables: 
   * ```spring.profiles.active=<env>```
   
The profiles active environment variable is for selecting active config values. This project has a dev and prod but credentials are hidden!

Note: IDE specific development
* Eclipse - When modifying this API in Eclipse the VM arguments added to the runtime configuration will be prefixed with `-D`.
  * Example: `-Dspring.profiles.active=dev`

**Running on the Command Line**
The command arguments in a terminal also follow the prefix `-D` rule.
```
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Docker & Compose & Maven
This project includes a lightweight, portable maven executable that can be used in place of having maven installed.
You will still need Java installed.

When building this application for production I have included the Dockerfile that can allow for building, preparing
and executing all source code in the base directory. Using CI/CD this can all be automated and I will try to include
an example for using Github workflows.

### Contact

* Aaron Renner (admin@aaronrenner.com)