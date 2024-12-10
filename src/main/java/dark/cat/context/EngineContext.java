package dark.cat.context;

import dark.cat.annotations.EngineComponent;
import dark.cat.annotations.GameLoop;
import dark.cat.annotations.Inject;
import dark.cat.annotations.InjectPajamaDependency;
import dark.cat.managers.RenderManager;
import dark.cat.utils.PajamaLogger;
import dark.cat.utils.ThreadManagerPool;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

import static dark.cat.utils.PajamaResponses.APPLICATION_STARTED_SUCCESSFULLY;
import static dark.cat.utils.PajamaResponses.NO_COMPONENT_FOUND_FOR;

/**
 * The {@code EngineContext} class manages the lifecycle of components within the application framework.
 * It is responsible for scanning, initializing, and wiring components, and handling dependency injection.
 *
 * <p>This class performs the following tasks:
 * <ul>
 *   <li>Scans the specified package for components annotated with {@link EngineComponent} or {@link GameLoop}.</li>
 *   <li>Instantiates and stores component instances in a managed context.</li>
 *   <li>Injects dependencies annotated with {@link Inject} and {@link InjectPajamaDependency} into the respective components.</li>
 *   <li>Registers internal Pajama dependencies that are required by the framework.</li>
 * </ul>
 *
 * <p>Upon successful initialization, the context logs a message indicating that the application has started successfully.
 *
 * <p>Usage:
 * <pre>
 *     {@code
 * EngineContext context = new EngineContext("com.example.app", Main.class);
 * MyComponent component = context.getComponent(MyComponent.class);
 *     }
 * </pre>
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
public class EngineContext {

    /**
     * A map to store and manage component instances, where the key is the class type
     * and the value is the instantiated object.
     */
    private final Map<Class<?>, Object> components = new HashMap<>();

    /**
     * A map to store and manage internal Pajama instances, where the key is the class type
     * and the value is the instantiated object. The stored components belong to the Pajama framework.
     */
    private final Map<Class<?>, Object> internalPajamaComponents = new HashMap<>();

    /**
     * The main application class, used to derive the parent package for scanning.
     */
    private Class<?> mainClass;

    /**
     * Constructs an {@code EngineContext} and initializes components within the specified
     * package. Performs scanning, initialization, and dependency injection.
     *
     * @param basePackage the base package to scan for components
     * @param mainClass the main application class
     * @throws Exception if an error occurs during scanning, initialization, or dependency injection
     */
    public EngineContext(String basePackage, Class<?> mainClass) throws Exception {
        setMainClass(mainClass);
        registerPajamaDependencies();
        scanAndInitialize(basePackage);
        injectDependencies();
        PajamaLogger.log(APPLICATION_STARTED_SUCCESSFULLY.getMessage());
    }

    /**
     * Registers internal Pajama dependencies into the {@code internalPajamaComponents} map.
     * This method is called automatically during initialization.
     */
    private void registerPajamaDependencies() {
        components.put(RenderManager.class, new RenderManager());
    }

    /**
     * Scans the specified base package for annotated classes, initializes them, and stores
     * them in the {@code components} map.
     *
     * @param basePackage the base package to scan
     * @throws Exception if an error occurs during scanning or initialization
     */
    private void scanAndInitialize(String basePackage) throws Exception {
        String path = basePackage.trim().equals(".")
                ? getParentPackage().replace('.', '/')
                : basePackage.replace('.', '/');

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            File dir = new File(resources.nextElement().toURI());
            scanDirectory(dir, basePackage.trim().equals(".") ? getParentPackage() : basePackage);
        }
    }

    /**
     * Recursively scans a directory for classes, loads them, and stores instances of
     * annotated classes in the context.
     *
     * @param dir the directory to scan
     * @param packageName the package name corresponding to the directory
     * @throws Exception if an error occurs while loading a class
     */
    private void scanDirectory(File dir, String packageName) throws Exception {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                String subPackage = packageName.isEmpty() ? file.getName() : packageName + "." + file.getName();
                scanDirectory(file, subPackage);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName.isEmpty()
                        ? file.getName().replace(".class", "")
                        : packageName + "." + file.getName().replace(".class", "");

                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(EngineComponent.class) || clazz.isAnnotationPresent(GameLoop.class)) {
                    components.put(clazz, clazz.getDeclaredConstructor().newInstance());
                }
            }
        }
    }

    /**
     * Performs dependency injection by scanning all fields annotated with {@link Inject}
     * and assigning the corresponding component instances. This method also injects internal
     * Pajama dependencies annotated with {@link InjectPajamaDependency}.
     *
     * @throws Exception if the field cannot be accessed or modified, or if the internal dependency
     *                   injection fails
     */
    private void injectDependencies() throws Exception {
        for (Object component : components.values()) {
            for (Field field : component.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectPajamaDependency.class)) {
                    field.setAccessible(true);
                    injectInternalDependency(field.getType());
                    Object internalDependency = internalPajamaComponents.get(field.getType());

                    if (internalDependency == null) {
                        throw new RuntimeException(NO_COMPONENT_FOUND_FOR.getMessage() + field.getType());
                    }

                    field.set(component, internalDependency);

                } else if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    Object dependency = components.get(field.getType());

                    if (dependency == null) {
                        throw new RuntimeException(NO_COMPONENT_FOUND_FOR.getMessage() + field.getType());
                    }

                    field.set(component, dependency);
                }
            }
        }
    }

    /**
     * Injects the internal Pajama dependency into the context if it is not already present.
     * This method is used to register framework-specific dependencies such as the {@link RenderManager}.
     *
     * @param internalDependencyClass the class type of the internal dependency to inject
     * @throws Exception if the internal dependency cannot be instantiated
     */
    private void injectInternalDependency(Class<?> internalDependencyClass) throws Exception {
        if (!internalPajamaComponents.containsKey(internalDependencyClass)) {
            PajamaLogger.log("Creating new internal dependency: " + internalDependencyClass.getName());
            internalPajamaComponents.put(internalDependencyClass, internalDependencyClass.getDeclaredConstructor().newInstance());
        }
    }

    /**
     * Sets the main application class, which is used to derive the package name for scanning.
     *
     * @param mainClass the main application class
     */
    private void setMainClass(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    /**
     * Gets the main application class.
     *
     * @return the main application class
     */
    public Class<?> getMainClass() {
        return mainClass;
    }

    /**
     * Shuts down the engine context gracefully, releasing any resources and shutting down the thread pool.
     */
    public void shutdown() {
        ThreadManagerPool.shutdown();
        PajamaLogger.log("Engine context shut down gracefully.");
    }

    /**
     * Derives the parent package name from the main application class. This is used to scan the
     * appropriate package for annotated components.
     *
     * @return the parent package name, or an empty string if no package is found
     */
    private String getParentPackage() {
        Package parentPackage = mainClass.getPackage();
        if (parentPackage == null || parentPackage.getName().isEmpty()) {
            return "";
        }
        return parentPackage.getName();
    }

    /**
     * Retrieves a managed component from the context by its class type.
     *
     * @param <T> the type of the component
     * @param clazz the class type of the component
     * @return the component instance, or {@code null} if no component of the specified
     *         type exists
     */
    public <T> T getComponent(Class<T> clazz) {
        return clazz.cast(components.get(clazz));
    }
}