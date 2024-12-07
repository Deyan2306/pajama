package dark.cat.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code GameLoop} annotation identifies a class as the main game loop
 * in the Pajama framework.
 *
 * <p>This annotation marks a class that manages the primary game logic and
 * execution flow. The annotated class must implement the {@link java.lang.Runnable}
 * interface to define the game loop's behavior.
 *
 * <p>Usage:
 * <pre>
 *     {@code @GameLoop
 * public class MainGameLoop implements Runnable {
 *     public void run() {
 *         // Game loop logic
 *     }
 * }
 *     }
 * </pre>
 *
 * <p>Key Behavior:
 * <ul>
 *     <li>Only one {@code GameLoop} annotated class should be present in the application.</li>
 *     <li>The {@link dark.cat.PajamaApplication} uses this annotation to identify the
 *     main game loop class at runtime.</li>
 *     <li>If no {@code GameLoop} annotated class is found or if the annotated class
 *     does not implement {@code Runnable}, a runtime exception is thrown.</li>
 * </ul>
 *
 * <p>Retention and Target:
 * <ul>
 *     <li><b>Retention:</b> {@code RUNTIME} - The annotation is available at runtime
 *     for reflection.</li>
 *     <li><b>Target:</b> {@code TYPE} - The annotation can only be applied to classes.</li>
 * </ul>
 *
 * @see java.lang.Runnable
 * @see dark.cat.PajamaApplication
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GameLoop { }