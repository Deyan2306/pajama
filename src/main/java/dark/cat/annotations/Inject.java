package dark.cat.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Inject} annotation marks a field for dependency injection.
 *
 * <p>This annotation is part of the Pajama framework's dependency injection mechanism.
 * It allows annotated fields to be automatically populated with the required component
 * from the application's {@link dark.cat.context.EngineContext}.
 *
 * <p>Usage:
 * <pre>
 *     {@code @Inject}
 *     private SomeComponent someComponent;
 * </pre>
 *
 * <p>The {@code Inject} annotation supports field injection and requires that the
 * corresponding dependency be registered as a component within the {@code EngineContext}.
 * If no matching component is found, a runtime exception will be thrown.
 *
 * <p>Retention and Target:
 * <ul>
 *     <li><b>Retention:</b> {@code RUNTIME} - The annotation is available at runtime
 *     for reflection.</li>
 *     <li><b>Target:</b> {@code FIELD} - The annotation can only be applied to fields.</li>
 * </ul>
 *
 * @see dark.cat.context.EngineContext
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject { }