package dark.cat.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code EngineComponent} annotation identifies a class as a component
 * within the Pajama framework's dependency injection system.
 *
 * <p>Classes annotated with {@code EngineComponent} are automatically scanned,
 * instantiated, and registered in the application context during initialization.
 * These components can be dependencies for other components and are managed by the framework.
 *
 * <p>Usage:
 * <pre>
 *     {@code @EngineComponent
 * public class MyComponent {
 *     // Component logic here
 * }
 *     }
 * </pre>
 *
 * <p>Key Features:
 * <ul>
 *     <li>Marks a class for automatic discovery and registration in the
 *     {@link dark.cat.context.EngineContext}.</li>
 *     <li>Supports dependency injection through the {@link Inject} annotation.</li>
 *     <li>Allows modular design by encapsulating specific functionality
 *     in isolated, reusable components.</li>
 * </ul>
 *
 * <p>Retention and Target:
 * <ul>
 *     <li><b>Retention:</b> {@code RUNTIME} - The annotation is available at runtime
 *     for reflection, enabling the framework to detect and manage components.</li>
 *     <li><b>Target:</b> {@code TYPE} - The annotation can only be applied to classes.</li>
 * </ul>
 *
 * <p>Best Practices:
 * <ul>
 *     <li>Use {@code EngineComponent} to mark classes that encapsulate distinct
 *     pieces of functionality to promote separation of concerns.</li>
 *     <li>Ensure proper dependency injection by annotating dependent fields with {@link Inject}.</li>
 * </ul>
 *
 * @see dark.cat.context.EngineContext
 * @see Inject
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EngineComponent { }