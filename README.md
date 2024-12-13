# Pajama Framework

[![Apache License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  
[![Java](https://img.shields.io/badge/language-Java-orange.svg)](https://www.oracle.com/java/)  
[![Version](https://img.shields.io/badge/version-1.0.0-brightgreen.svg)](https://github.com/Deyan2306/pajama/releases)  
[![Build](https://img.shields.io/github/actions/workflow/status/Deyan2306/pajama/build.yml?label=build&logo=github)](https://github.com/Deyan2306/pajama/actions)  
[![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/Deyan2306/pajama/issues)  
[![Repo Stars](https://img.shields.io/github/stars/Deyan2306/pajama?style=social)](https://github.com/Deyan2306/pajama/stargazers)

**Pajama Framework** is a lightweight, annotation-based Java framework designed to streamline game and application development. By leveraging dependency injection, game lifecycle management, and modular architecture, Pajama helps developers build clean, efficient applications with minimal boilerplate code. The framework is extensible, easy to use, and perfect for small to medium-scale projects.

---

## 🎯 Features

- **Annotation-Based Dependency Injection**  
  Simplify your application's architecture with `@Inject` and `@EngineComponent` annotations.

- **Game Lifecycle Management**  
  Effortlessly manage game loops and initialization with `@GameLoop` and `EngineContext`.

- **Render Management**  
  Integrated `RenderManager` for rendering graphics, clearing the screen, and managing drawing operations with ease.

- **Multi-Threading Support**  
  Automatically manage thread pools with `ThreadManagerPool` for efficient and scalable task execution.

- **Extensible & Lightweight**  
  Focus on your application logic without unnecessary complexity or boilerplate.

- **Built-in Logging**  
  Log messages and errors easily with `PajamaLogger`.

---

> [Documentation](/docs)

## 🚀 Getting Started

### 📋 Prerequisites

- **Java 11** or higher
- **Maven** or **Gradle**

### 📦 Installation

To use Pajama Framework, include it in your project dependencies:

#### Maven
```xml
<dependency>
    <groupId>io.github.pajama-framework</groupId>
    <artifactId>pajama-framework</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle
```groovy
implementation 'io.github.pajama-framework:pajama-framework:1.0.0'
```

---

## 🛠️ Usage

Here’s how to get started with Pajama Framework:

1. **Create Components**  
   Annotate your classes with `@EngineComponent` to define them as components managed by the framework.
   ```java
   @EngineComponent
   public class MyComponent {
       public void doSomething() {
           System.out.println("Component is working!");
       }
   }
   ```

2. **Inject Dependencies**  
   Use `@InjectPajamaDependency` to inject components into your classes.
   ```java
   @EngineComponent
   public class MyService {
       @InjectPajamaDependency
       private MyComponent myComponent;

       public void performAction() {
           myComponent.doSomething();
       }
   }
   ```

3. **Define a Game Loop**  
   Use `@GameLoop` to annotate your game class and manage the game lifecycle.
   ```java
   @GameLoop
   @EngineComponent
   public class MyGame implements Runnable {
       @InjectPajamaDependency
       private RenderManager renderManager;

       @Override
       public void run() {
           renderManager.initialize()
               .setWidth(800)
               .setHeight(600)
               .setTitle("Pajama Game")
               .newThread(pool -> {
                   pool.runAsync(() -> {
                       while (true) {
                           render();
                       }
                   });
               });
       }

       private void render() {
           Optional.ofNullable(renderManager.getGraphics()).ifPresent(graphics -> {
               renderManager.clearScreen(graphics, Color.BLACK);
               String message = "Pajama Framework!";
               Point position = renderManager.calculateCenteredPosition(graphics, message);
               renderManager.drawMessage(graphics, message, position, Color.WHITE);
               graphics.dispose();
               renderManager.show();
           });
       }
   }
   ```

4. **Run the Application**  
   Start your app with `PajamaApplication.run`:
   ```java
   public class Main {
       public static void main(String[] args) throws Exception {
           PajamaApplication.run(MyGame.class);
       }
   }
   ```

---

## 📂 Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── dark/cat/             # Core framework code
│   │   ├── dark/cat/annotations/ # Annotations for components and game loop
│   │   ├── dark/cat/context/     # Engine context and initialization logic
│   │   ├── dark/cat/managers/    # RenderManager and ThreadManagerPool
│   │   ├── dark/cat/utils/       # Logging and response utilities
│   │   └── example/              # Example implementations
│   └── resources/                # Configuration files (if any)
```

---

## 🌟 Examples

Check out the [`examples/`](examples/) directory for sample projects using Pajama Framework. These examples serve as templates for your own game or application development.

---

## 🤝 Contributing

Contributions are welcome! Here’s how you can help:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m "Add new feature"`.
4. Push to the branch: `git push origin feature-name`.
5. Submit a pull request.

For large changes, open an issue first to discuss your ideas.

---

## 📜 License

This project is licensed under the **Apache License 2.0**. See the [LICENSE](LICENSE) file for details.

---

## 📧 Contact

Have questions or suggestions? Reach out!

- **Email**: [deyan.sirakov2006@abv.bg](mailto:deyan.sirakov2006@abv.bg)
- **GitHub Issues**: [Open an Issue](https://github.com/Deyan2306/pajama/issues)

If you enjoy this framework, consider giving the repo a ⭐!

--- 

Feel free to suggest or request changes. Happy coding! 😊