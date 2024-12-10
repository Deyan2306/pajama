package dark.cat.managers;

import dark.cat.annotations.GameLoop;
import dark.cat.utils.PajamaLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Manages rendering operations, including the creation of a window,
 * canvas, and buffer strategy for smooth graphical updates.
 * <li>This class is responsible for initializing a JFrame-based rendering
 * environment, providing access to a {@link GameLoop} object for rendering,
 * and handling buffer swapping for efficient rendering pipelines.</li>
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * RenderManager renderManager = new RenderManager();
 * renderManager.initialize("Game Window", 800, 600);
 * Graphics graphics = renderManager.getGraphics();
 * if (graphics != null) {
 *     graphics.setColor(Color.BLACK);
 *     graphics.fillRect(0, 0, renderManager.getWidth(), renderManager.getHeight());
 *     renderManager.swapBuffers();
 * }
 * renderManager.cleanup();
 * }
 * </pre>
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
public class RenderManager {

    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;

    /**
     * Initializes the RenderManager by creating a window and setting up the rendering canvas.
     *
     * @param title  the title of the window
     * @param width  the width of the rendering area
     * @param height the height of the rendering area
     */
    public void initialize(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setSize(width, height);
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();

        frame.setVisible(true);

        canvas.createBufferStrategy(3); // Triple buffering for smoother rendering
        bufferStrategy = canvas.getBufferStrategy();

        PajamaLogger.log("RenderManager initialized with dimensions: " + width + "x" + height);
    }

    /**
     * Provides a `Graphics` object for rendering operations.
     *
     * This method retrieves the `Graphics` object from the buffer strategy. If the buffer
     * strategy is not yet initialized, it will attempt to create one.
     *
     * @return the `Graphics` object for rendering, or `null` if the buffer strategy could not be created
     */
    public Graphics getGraphics() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            return null;
        }
        return bufferStrategy.getDrawGraphics();
    }

    /**
     * Swaps the buffers to display the rendered frame on the screen.
     *
     * This method is essential for implementing double or triple buffering, ensuring
     * smooth frame transitions without tearing.
     */
    public void swapBuffers() {
        if (bufferStrategy != null) {
            bufferStrategy.show();
        }
    }

    /**
     * Displays the current rendered frame by swapping the buffers.
     *
     * Similar to {@link #swapBuffers()}, this method ensures the buffer strategy is utilized
     * to update the displayed content on the screen.
     */
    public void show() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        if (bufferStrategy != null) {
            bufferStrategy.show();
        }
    }

    /**
     * Clears the screen with a specified color.
     *
     * @param graphics the Graphics object used for drawing
     * @param color    the color to fill the screen with
     */
    public void clearScreen(Graphics graphics, Color color) {
        graphics.setColor(color);
        graphics.fillRect(0 ,0, getWidth(), getHeight());
    }

    /**
     * Calculates the centered position for rendering a string.
     *
     * @param graphics the Graphics object used for rendering
     * @param message  the message to render
     * @return the position as a Point object
     */
    public Point calculateCenteredPosition(Graphics graphics, String message) {
        graphics.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics metrics = graphics.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        return new Point(x, y);
    }

    /**
     * Draws a message at a specific position.
     *
     * @param graphics the Graphics object used for rendering
     * @param message  the message to render
     * @param position the position to render the message
     * @param color    the color of the text
     */
    public void drawMessage(Graphics graphics, String message, Point position, Color color) {
        graphics.setColor(color);
        graphics.drawString(message, position.x, position.y);
    }

    /**
     * Cleans up resources associated with the RenderManager and closes the rendering window.
     *
     * Call this method when the application exits to ensure proper resource cleanup.
     */
    public void cleanup() {
        PajamaLogger.log("Cleaning up RenderManager.");
        if (frame != null) {
            frame.dispose();
        }
    }

    /**
     * Returns the width of the rendering canvas.
     *
     * @return the width of the canvas in pixels
     */
    public int getWidth() {
        return canvas.getWidth();
    }

    /**
     * Returns the height of the rendering canvas.
     *
     * @return the height of the canvas in pixels
     */
    public int getHeight() {
        return canvas.getHeight();
    }

}
