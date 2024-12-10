package dark.cat.annotations;

import dark.cat.context.EngineContext;
import dark.cat.managers.RenderManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code InjectPajamaDependency} annotation is used to mark fields in components
 * that require injection of internal dependencies provided by the Pajama framework.
 *
 * <p>When a field is annotated with {@code @InjectPajamaDependency}, the engine context
 * will automatically inject the corresponding internal dependency at runtime. This is
 * typically used for dependencies that are critical to the framework's operation, such as
 * the {@link RenderManager}.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * @InjectPajamaDependency
 * private RenderManager renderManager;
 * }
 * </pre>
 *
 * <p>Dependencies marked with this annotation are injected by the {@link EngineContext}
 * class during the application's startup process, ensuring that framework-specific components
 * are available where needed.
 *
 * @see Inject
 * @see EngineContext
 * @see RenderManager
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectPajamaDependency { }
