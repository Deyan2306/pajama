# `PajamaLogger` and `PajamaResponses` Documentation (v1.0.0)

---

## **`PajamaLogger` Class**

The `PajamaLogger` class provides a lightweight logging mechanism for the Pajama framework. It is used to log information, warnings, and errors to the console during the application lifecycle.

---

### **Key Features**
1. **Console Logging**: Logs messages with consistent formatting.
2. **Ease of Use**: Simplified logging interface suitable for small-scale applications.

---

### **Methods**

#### **`log(String message)`**
Logs a message to the console.

##### Parameters:
- `message`: The message to be logged.

##### Example:
```java
PajamaLogger.log("Application started successfully.");
```

##### Example:
```java
PajamaLogger.error("Configuration file not found. Using defaults.");
```

#### **`error(String message)`**
Logs an error message to the console with an "ERROR" prefix.

##### Parameters:
- `message`: The error message to be logged.

##### Example:
```java
PajamaLogger.error("Failed to initialize the rendering engine.");
```

---

### **Usage Examples**

**Basic Logging:**
```java
PajamaLogger.log("Engine initialized.");
PajamaLogger.error("Could not find component.");
```

---

## **`PajamaResponses` Enum**

The `PajamaResponses` enum centralizes predefined application messages, improving maintainability and consistency in logs and exceptions.

---

### **Key Features**
1. **Centralized Messaging**: Keeps messages reusable and consistent across the framework.
2. **Readability**: Makes code more readable by replacing hardcoded strings with meaningful enum values.

---

### **Enum Values**

#### **`APPLICATION_STARTED_SUCCESSFULLY`**
- **Message**: `"Application started successfully."`
- Description: Indicates that the application and context have been initialized without errors.

---

#### **`NO_COMPONENT_FOUND_FOR`**
- **Message**: `"No component found for: "`
- Description: Used when dependency injection fails due to a missing component.

---

#### **`NO_GAME_LOOP_FOUND`**
- **Message**: `"No GameLoop class found in the context."`
- Description: Indicates that no class annotated with `@GameLoop` is registered in the context.

---

#### **`GAME_LOOP_HAS_NOT_IMPLEMENTED_RUNNABLE`**
- **Message**: `"The GameLoop class does not implement Runnable."`
- Description: Used when the `@GameLoop` annotated class does not implement the `Runnable` interface.

---

#### **`COMPONENT_INJECTION_SUCCESSFUL`**
- **Message**: `"Component injection completed successfully."`
- Description: Indicates that dependency injection was completed without errors.

---

### **Methods**

#### **`getMessage()`**
Returns the message associated with the enum value.

##### Example:
```java
String message = PajamaResponses.APPLICATION_STARTED_SUCCESSFULLY.getMessage();
System.out.println(message);  // Output: Application started successfully.
```

---

### **Usage Examples**

**Logging Responses:**
```java
PajamaLogger.log(PajamaResponses.APPLICATION_STARTED_SUCCESSFULLY.getMessage());
PajamaLogger.error(PajamaResponses.NO_GAME_LOOP_FOUND.getMessage());
```

**Exception Handling:**
```java
if (gameLoop == null) {
    throw new RuntimeException(PajamaResponses.NO_GAME_LOOP_FOUND.getMessage());
}
```

---

## **Best Practices**
1. **Use `PajamaResponses`**:
    - Replace hardcoded strings with predefined messages to improve maintainability.
2. **Use `PajamaLogger` for all logs**:
    - Ensures consistent log formatting and simplifies debugging.

---

## **Integration Example**

```java
@GameLoop
public class MyGame implements Runnable {
    @Override
    public void run() {
        PajamaLogger.log(PajamaResponses.APPLICATION_STARTED_SUCCESSFULLY.getMessage());
        // Game loop logic...
    }
}

public static void main(String[] args) throws Exception {
    try {
        PajamaApplication.run(MyGame.class);
    } catch (RuntimeException e) {
        PajamaLogger.error(e.getMessage());
    }
}
```