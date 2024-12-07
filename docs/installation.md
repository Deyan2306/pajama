# ⚙️ [Installation]

To get started with **Pajama Framework**, follow these steps:

1. **Add the Maven Dependency**:

   ```xml
   <dependency>
       <groupId>com.darkcat</groupId>
       <artifactId>pajama-framework</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

2. **Build the Project Locally** (if you're developing or want the latest build):
   ```bash
   mvn clean install
   ```

3. **Set up your project** to use the framework:
    - Create a class annotated with `@GameLoop`.
    - Inject components using the `@Inject` annotation.