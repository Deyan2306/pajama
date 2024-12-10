# Annotations Documentation (v1.0.0)

The `dark.cat.annotations` package provides a set of custom annotations to enhance the functionality, readability, and structure of your codebase. These annotations are primarily designed to work seamlessly with the framework and mark important components or behaviors.

---

## Table of Contents
1. [Overview](#overview)
2. [Annotations](#annotations)
    - [@EngineComponent](#enginecomponent)
    - [@GameLoop](#gameloop)
    - [@Inject](#inject)
3. [Usage Examples](#usage-examples)

---

## Overview

The annotations in this package simplify the creation and management of framework components by:
- Denoting specific classes or methods with specialized roles in the application.
- Automating dependency injection.
- Enhancing maintainability and clarity.

---

## Annotations

### **@EngineComponent**
Marks a class as a core component of the engine.

#### **Usage**
- Used to define essential building blocks of the framework or game engine.
- Classes annotated with `@EngineComponent` are typically instantiated and managed by the framework.

#### **Target**
- `@Target(ElementType.TYPE)`: Applicable at the class level.

#### **Retention**
- `@Retention(RetentionPolicy.RUNTIME)`: Available at runtime for reflection.

#### **Example**
```java
@EngineComponent
public class PhysicsEngine {
    // Core engine logic here
}
```

---

### **@GameLoop**
Marks a class or method as part of the main game loop.

#### **Usage**
- Used to designate components that are tightly coupled with the update-render loop.
- Ensures the annotated class or method participates in the game’s runtime execution flow.

#### **Target**
- `@Target({ElementType.TYPE, ElementType.METHOD})`: Applicable at the class or method level.

#### **Retention**
- `@Retention(RetentionPolicy.RUNTIME)`: Available at runtime for reflection.

#### **Example**
```java
@GameLoop
public class MainGameLoop implements Runnable {
    @Override
    public void run() {
        while (true) {
            update();
            render();
        }
    }
}
```

---

### **@Inject**
Marks a field for dependency injection.

#### **Usage**
- Used to automatically inject dependencies into a field.
- Simplifies managing dependencies by delegating their creation and initialization to the framework.

#### **Target**
- `@Target(ElementType.FIELD)`: Applicable at the field level.

#### **Retention**
- `@Retention(RetentionPolicy.RUNTIME)`: Available at runtime for reflection.

#### **Example**
```java
@EngineComponent
public class Game {
    @Inject
    private RenderManager renderManager; // Automatically injected by the framework
}
```

---

## Usage Examples

### Combining Annotations
Annotations can be combined for streamlined workflows.

```java
@EngineComponent
@GameLoop
public class WindowRenderer implements Runnable {
    @Inject
    private RenderManager renderManager;

    @Override
    public void run() {
        while (true) {
            render();
        }
    }

    private void render() {
        Graphics graphics = renderManager.getGraphics();
        // Rendering logic
    }
}
```

### Dependency Injection
The `@Inject` annotation automates dependency management.

```java
@EngineComponent
public class AudioManager {
    @Inject
    private Logger logger;

    public void playSound(String soundFile) {
        logger.info("Playing sound: " + soundFile);
        // Sound playback logic
    }
}
```

---

## Notes
1. **Framework Dependency**: These annotations rely on a framework capable of scanning, instantiating, and injecting components at runtime.
2. **Reflection-Based**: Since these annotations are runtime-retained, ensure reflection is supported in your runtime environment.
3. **Error Handling**: If a dependency cannot be resolved, ensure the framework logs meaningful errors for debugging.