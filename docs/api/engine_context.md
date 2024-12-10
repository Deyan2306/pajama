# `EngineContext` Documentation (v1.0.0)

The `EngineContext` class serves as the core of the framework's component lifecycle management system. It scans, initializes, and wires annotated components while providing mechanisms for dependency injection and runtime context management. This documentation details the class's purpose, features, methods, and usage examples.

---

## Overview

The `EngineContext` is responsible for:
- **Component Scanning**: Locates classes annotated with `@EngineComponent` or `@GameLoop`.
- **Initialization**: Instantiates and stores these components in a managed context.
- **Dependency Injection**: Resolves and injects dependencies annotated with `@Inject`.
- **Lifecycle Management**: Facilitates clean startup and shutdown operations.

### Features:
1. Scans a specified package for framework components.
2. Automates the instantiation and management of annotated components.
3. Supports dependency injection for annotated fields.
4. Provides runtime component retrieval by type.
5. Logs application lifecycle events.

---

## Class Details

### **Constructor**
#### **`EngineContext(String basePackage, Class<?> mainClass)`**
Initializes the application context by scanning and setting up framework components.

##### Parameters:
- `basePackage`: The package to scan for annotated components.
- `mainClass`: The main application class, used to determine the parent package.

##### Throws:
- `Exception` if an error occurs during scanning, initialization, or dependency injection.

##### Example:
```java
EngineContext context = new EngineContext("dark.cat", Main.class);
```

---

### **Fields**

#### **`components`**
- **Type**: `Map<Class<?>, Object>`
- Stores instantiated framework components, where the key is the class type, and the value is the instance.

#### **`mainClass`**
- **Type**: `Class<?>`
- Holds the reference to the main application class for package resolution.

---

### **Methods**

#### **`registerPajamaDependencies()`**
Registers built-in dependencies (e.g., `RenderManager`) into the context for automatic management.

---

#### **`scanAndInitialize(String basePackage)`**
Scans the specified package for classes annotated with `@EngineComponent` or `@GameLoop` and initializes them.

##### Parameters:
- `basePackage`: The base package to scan.

##### Throws:
- `Exception` if errors occur during class loading or instantiation.

---

#### **`scanDirectory(File dir, String packageName)`**
Recursively scans directories for class files, loads them, and initializes components if they are annotated.

##### Parameters:
- `dir`: The directory to scan.
- `packageName`: The corresponding package name.

---

#### **`injectDependencies()`**
Performs dependency injection by resolving and assigning dependencies to fields annotated with `@Inject`.

##### Throws:
- `IllegalAccessException` if field access fails.
- `RuntimeException` if a dependency is not found.

---

#### **`getComponent(Class<T> clazz)`**
Retrieves a managed component by its class type.

##### Parameters:
- `clazz`: The type of the desired component.

##### Returns:
- The component instance, or `null` if no component of the specified type exists.

##### Example:
```java
RenderManager renderManager = context.getComponent(RenderManager.class);
```

---

#### **`setMainClass(Class<?> mainClass)`**
Sets the main application class.

##### Parameters:
- `mainClass`: The main application class.

---

#### **`getMainClass()`**
Returns the main application class.

##### Returns:
- The `Class<?>` of the main application.

---

#### **`getParentPackage()`**
Derives the parent package name from the main class.

##### Returns:
- The parent package name as a `String`.

---

#### **`shutdown()`**
Shuts down the context gracefully by:
1. Terminating managed threads using `ThreadManagerPool.shutdown()`.
2. Logging the shutdown event.

---

## Usage Example

### Creating and Managing Context
```java
public class Main {
    public static void main(String[] args) throws Exception {
        EngineContext context = new EngineContext("dark.cat", Main.class);

        RenderManager renderManager = context.getComponent(RenderManager.class);
        renderManager.start();

        // Shutdown the context gracefully on exit
        Runtime.getRuntime().addShutdownHook(new Thread(context::shutdown));
    }
}
```

### Dependency Injection
```java
@EngineComponent
public class Game {
    @Inject
    private RenderManager renderManager;

    public void start() {
        renderManager.render();
    }
}
```

### Registering and Retrieving Components
```java
EngineContext context = new EngineContext("dark.cat", Main.class);

// Manually adding a component
RenderManager customRenderManager = new RenderManager();
context.getComponents().put(RenderManager.class, customRenderManager);

// Retrieving a component
RenderManager renderManager = context.getComponent(RenderManager.class);
```

---

## Notes
1. **Annotations**: Works in conjunction with `@EngineComponent`, `@GameLoop`, and `@Inject` annotations for component management.
2. **Exception Handling**: Errors during component resolution or dependency injection are logged and handled gracefully.
3. **Reflection-Based**: Relies on reflection; ensure runtime environments support it.