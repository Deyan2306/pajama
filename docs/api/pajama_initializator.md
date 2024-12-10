# `PajamaApplication` Class Documentation (v1.0.0)

The `PajamaApplication` class is the main entry point for initializing and running a game application using the Pajama framework. It simplifies the process of setting up the application context and executing the primary game loop.

---

## Overview

### Purpose
The `PajamaApplication` class:
- Initializes the `EngineContext` for managing application components.
- Locates the main game loop annotated with `@GameLoop`.
- Validates and executes the game loop if it implements `Runnable`.

### Features
1. **Automatic Context Setup**: Derives the base package from the main class or accepts a custom package for component scanning.
2. **Game Loop Management**: Ensures the presence of a valid `@GameLoop` annotated class and invokes its `run` method.
3. **Error Handling**: Provides runtime exceptions for misconfigured main classes.

---

## Class Details

### **Static Field**

#### **`context`**
- **Type**: `EngineContext`
- Description: Holds the shared context for managing application components and their dependencies.

---

### **Static Methods**

#### **`run(Class<?> mainClass)`**
Initializes the application context using the package of the specified main class and executes its game loop.

##### Parameters:
- `mainClass`: The main class of the application annotated with `@GameLoop`.

##### Throws:
- `Exception` if:
    - Context initialization fails.
    - The game loop is misconfigured or invalid.

##### Example:
```java
public static void main(String[] args) {
    PajamaApplication.run(MyGame.class);
}
```

---

#### **`run(Class<?> mainClass, String basePackage)`**
Initializes the application context using a custom base package and executes the game loop of the specified main class.

##### Parameters:
- `mainClass`: The main class of the application annotated with `@GameLoop`.
- `basePackage`: The custom base package to scan for components.

##### Throws:
- `Exception` if:
    - Context initialization fails.
    - The game loop is misconfigured or invalid.

##### Example:
```java
public static void main(String[] args) {
    PajamaApplication.run(MyGame.class, "com.example.mygame");
}
```

---

#### **`runMainClassOnCurrentContext(Class<?> mainClass)`**
Validates the presence and configuration of the game loop in the current context and executes it if valid.

##### Parameters:
- `mainClass`: The class annotated with `@GameLoop` to run.

##### Throws:
- `RuntimeException` if:
    - The main class is not annotated with `@GameLoop`.
    - The main class is not registered in the context.
    - The main class does not implement `Runnable`.

##### Logic:
1. Retrieves the `@GameLoop` annotated class instance from the context.
2. Verifies that the class:
    - Is properly annotated with `@GameLoop`.
    - Implements `Runnable`.
3. Invokes the `run` method of the class if valid.

##### Example:
```java
@GameLoop
public class MyGame implements Runnable {
    @Override
    public void run() {
        System.out.println("Game loop running...");
    }
}

public static void main(String[] args) throws Exception {
    PajamaApplication.run(MyGame.class);
}
```

---

## Annotations

### **`@GameLoop`**
- Marks a class as the main entry point for the application’s game loop.
- The annotated class must implement `Runnable`.

---

## Exceptions

### **Runtime Exceptions**
1. **`NO_GAME_LOOP_FOUND`**:
    - Triggered when no `@GameLoop` annotated class is found in the context.
2. **`GAME_LOOP_HAS_NOT_IMPLEMENTED_RUNNABLE`**:
    - Triggered when the `@GameLoop` class does not implement `Runnable`.

---

## Usage Examples

### Basic Usage
```java
@GameLoop
public class MyGame implements Runnable {
    @Override
    public void run() {
        System.out.println("Starting the game loop...");
        // Game loop logic here
    }
}

public static void main(String[] args) throws Exception {
    PajamaApplication.run(MyGame.class);
}
```

### Custom Package Scanning
```java
@GameLoop
public class MyGame implements Runnable {
    @Override
    public void run() {
        System.out.println("Game loop running with custom package...");
    }
}

public static void main(String[] args) throws Exception {
    PajamaApplication.run(MyGame.class, "com.custom.game");
}
```

---

## Notes
1. **Framework Dependency**: Relies on the `EngineContext` class for component management.
2. **Annotations**: Requires `@GameLoop` to identify the main game loop class.
3. **Runnable Interface**: The main game loop class must implement `Runnable` to be executed.