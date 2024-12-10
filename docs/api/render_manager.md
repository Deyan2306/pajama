# RenderManager Documentation (v1.0.0)

The `RenderManager` class is a core component responsible for handling rendering operations in a graphical application. It sets up and manages a window, canvas, and buffer strategy to ensure smooth and efficient rendering.

## Table of Contents
1. [Overview](#overview)
2. [Initialization](#initialization)
3. [Rendering Methods](#rendering-methods)
4. [Utility Methods](#utility-methods)
5. [Cleanup](#cleanup)
6. [Example Usage](#example-usage)
7. [Dependencies](#dependencies)

---

## Overview
The `RenderManager` provides a foundation for creating graphical applications with features like:
- A `JFrame`-based window environment.
- Triple-buffered rendering for smooth frame updates.
- Helper methods for rendering text and clearing the screen.

---

## Initialization

### `initialize(String title, int width, int height)`
Initializes the `RenderManager` by creating a window and setting up a rendering canvas.

- **Parameters**:
    - `title`: The title of the application window.
    - `width`: The width of the rendering canvas.
    - `height`: The height of the rendering canvas.

- **Example**:
  ```java
  renderManager.initialize("Game Window", 800, 600);
  ```

---

## Rendering Methods

### `getGraphics()`
Provides a `Graphics` object for rendering operations. If the buffer strategy is not initialized, it creates one.

- **Returns**:
    - A `Graphics` object for rendering, or `null` if the buffer strategy is not initialized.

### `swapBuffers()`
Swaps the buffers to display the rendered frame. Ensures smooth rendering transitions.

### `show()`
Displays the current frame by utilizing the buffer strategy. This is an alternative to `swapBuffers()`.

### `clearScreen(Graphics graphics, Color color)`
Clears the screen with the specified color.

- **Parameters**:
    - `graphics`: The `Graphics` object for rendering.
    - `color`: The color to clear the screen.

---

## Utility Methods

### `calculateCenteredPosition(Graphics graphics, String message)`
Calculates the coordinates to center a string within the canvas.

- **Parameters**:
    - `graphics`: The `Graphics` object for rendering.
    - `message`: The string to be centered.

- **Returns**:
    - A `Point` object containing the x and y coordinates.

### `drawMessage(Graphics graphics, String message, Point position, Color color)`
Draws a string at the specified position with the specified color.

- **Parameters**:
    - `graphics`: The `Graphics` object for rendering.
    - `message`: The string to render.
    - `position`: A `Point` object containing x and y coordinates.
    - `color`: The color of the string.

---

## Cleanup

### `cleanup()`
Cleans up resources associated with the `RenderManager`, such as closing the window.

---

## Example Usage

```java
RenderManager renderManager = new RenderManager();
renderManager.initialize("Game Window", 800, 600);

Graphics graphics = renderManager.getGraphics();
if (graphics != null) {
    renderManager.clearScreen(graphics, Color.BLACK);
    
    String message = "Hello, World!";
    Point position = renderManager.calculateCenteredPosition(graphics, message);
    renderManager.drawMessage(graphics, message, position, Color.WHITE);
    
    graphics.dispose();
    renderManager.swapBuffers();
}

renderManager.cleanup();
```

---

## Dependencies
- **Annotations**:
    - `@GameLoop`
- **Logging Utility**:
    - `PajamaLogger`
- **Java Swing/AWT**:
    - `JFrame`, `Canvas`, `BufferStrategy`, `Graphics`, etc.

--- 

## Notes
- **Buffer Strategy**: Triple-buffering is used by default to enhance rendering performance and reduce screen tearing.
- **Thread Safety**: Ensure rendering operations are executed in the correct thread to avoid race conditions.