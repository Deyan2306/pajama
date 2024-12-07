package dark.cat;

import dark.cat.annotations.GameLoop;
import dark.cat.context.EngineContext;

import static dark.cat.utils.PajamaResponses.*;

/**
 * The {@code PajamaApplication} class serves as the entry point for initializing and running
 * a game application using the Pajama framework. It is responsible for setting up the
 * {@link EngineContext}, locating the {@link GameLoop} component, and invoking its
 * {@code run} method.
 *
 * <p>Key responsibilities include:
 * <ul>
 *   <li>Initializing the application context by scanning for components.</li>
 *   <li>Validating the presence of a {@link GameLoop} annotated main class.</li>
 *   <li>Executing the game loop if it implements {@link Runnable}.</li>
 * </ul>
 *
 * <p>Usage:
 * <pre>
 *     {@code
 * public static void main(String[] args) {
 *     PajamaApplication.run(MyGame.class);
 * }
 *     }
 * </pre>
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
public class PajamaApplication {

    /**
     * The shared application context responsible for managing components and their dependencies.
     */
    private static EngineContext context;

    /**
     * Starts the application by initializing the {@link EngineContext} and running the
     * {@link GameLoop} component specified by the {@code mainClass}.
     *
     * <p>The default base package for scanning is derived from the main class's package.
     *
     * @param mainClass the main class of the application annotated with {@link GameLoop}
     * @throws Exception if the context initialization or game loop execution fails
     */
    public static void run(Class<?> mainClass) throws Exception {
        context = new EngineContext(".", mainClass);
        runMainClassOnCurrentContext(mainClass);
    }

    /**
     * Starts the application with a specified base package for scanning. Initializes the
     * {@link EngineContext} and runs the {@link GameLoop} component specified by the
     * {@code mainClass}.
     *
     * @param mainClass the main class of the application annotated with {@link GameLoop}
     * @param basePackage the base package to scan for components
     * @throws Exception if the context initialization or game loop execution fails
     */
    public static void run(Class<?> mainClass, String basePackage) throws Exception {
        context = new EngineContext(basePackage, mainClass);
        runMainClassOnCurrentContext(mainClass);
    }

    /**
     * Validates and runs the {@link GameLoop} annotated {@code mainClass} within the current
     * {@link EngineContext}. Ensures the main class implements {@link Runnable} and invokes
     * its {@code run} method.
     *
     * @param mainClass the main class to run
     * @throws RuntimeException if the main class is not annotated with {@link GameLoop},
     *                          is not registered in the context, or does not implement
     *                          {@link Runnable}
     */
    private static void runMainClassOnCurrentContext(Class<?> mainClass) {
        Object gameLoop = context.getComponent(mainClass);

        if (gameLoop == null || !mainClass.isAnnotationPresent(GameLoop.class)) {
            throw new RuntimeException(NO_GAME_LOOP_FOUND.getMessage());
        }

        if (gameLoop instanceof Runnable) {
            ((Runnable) gameLoop).run();
        } else {
            throw new RuntimeException(GAME_LOOP_HAS_NOT_IMPLEMENTED_RUNNABLE.getMessage());
        }
    }
}
