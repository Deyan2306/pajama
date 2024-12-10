# Pajama Framework

[![Apache License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  
[![Java](https://img.shields.io/badge/language-Java-orange.svg)](https://www.oracle.com/java/)  
[![Version](https://img.shields.io/badge/version-1.0.0-brightgreen.svg)](https://github.com/Deyan2306/pajama/releases)  
[![Build](https://img.shields.io/github/actions/workflow/status/Deyan2306/pajama/build.yml?label=build&logo=github)](https://github.com/Deyan2306/pajama/actions)  
[![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/Deyan2306/pajama/issues)  
[![Repo Stars](https://img.shields.io/github/stars/Deyan2306/pajama?style=social)](https://github.com/Deyan2306/pajama/stargazers)

**Pajama Framework** is a lightweight, annotation-based Java framework that simplifies game development by providing intuitive support for dependency injection, lifecycle management, and modular design. With a focus on flexibility and minimal setup, Pajama Framework empowers developers to create robust applications or games with ease.

---

## 🎯 Features

- **Annotation-Based Dependency Injection**  
  Simplify your application's architecture with `@Inject` and `@EngineComponent`.

- **Custom Game Loop Support**  
  Build games effortlessly using `@GameLoop` for modular game lifecycle management.

- **Dynamic Component Discovery**  
  Automatically scan, discover, and initialize components at runtime.

- **Extensible & Lightweight**  
  Focus on your application's logic without boilerplate code or unnecessary complexity.

- **Built-in Logging**  
  Easily log messages or errors with the `PajamaLogger`.

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
    <groupId>dark.cat</groupId>
    <artifactId>pajama-framework</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle
```groovy
implementation 'dark.cat:pajama-framework:1.0.0'
```

---

## 🛠️ Usage

Here’s how to get started with Pajama Framework in just a few steps:

1. **Create Components**  
   Annotate your classes with `@EngineComponent`:
   ```java
   @EngineComponent
   public class MyComponent {
       public void doSomething() {
           System.out.println("Component is working!");
       }
   }
   ```

2. **Inject Dependencies**  
   Use `@Inject` to link components:
   ```java
   @EngineComponent
   public class MyService {
       @Inject
       private MyComponent myComponent;

       public void performAction() {
           myComponent.doSomething();
       }
   }
   ```

3. **Define a Game Loop**  
   Add `@GameLoop` to your game class:
   ```java
   @GameLoop
   public class MyGame implements Runnable {
       @Override
       public void run() {
           System.out.println("Game loop running!");
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
│   │   ├── dark/cat/utils/       # Logging and response utilities
│   │   └── example/              # Example implementations
│   └── resources/                # Configuration files (if any)
```

---

## 🌟 Examples

Explore example projects in the [`examples/`](examples/) directory. Use them as templates for your own projects!

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

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
