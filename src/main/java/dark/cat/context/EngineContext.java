package dark.cat.context;

import dark.cat.annotations.EngineComponent;
import dark.cat.annotations.GameLoop;
import dark.cat.annotations.Inject;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static dark.cat.utils.PajamaResponses.APPLICATION_STARTED_SUCCESSFULLY;
import static dark.cat.utils.PajamaResponses.NO_COMPONENT_FOUND_FOR;

public class EngineContext {

    private final Map<Class<?>, Object> components = new HashMap<>();

    public EngineContext(String basePackage) throws Exception {
        scanAndInitialize(basePackage);
        injectDependencies();
        System.out.println(APPLICATION_STARTED_SUCCESSFULLY.getMessage());
    }

    private void scanAndInitialize(String basePackage) throws Exception {
        String path = basePackage.replace('.', '/');

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            File dir = new File(resources.nextElement().toURI());

            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.getName().endsWith(".class")) {
                    String className = basePackage + "." + file.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(className);

                    if (clazz.isAnnotationPresent(EngineComponent.class) || clazz.isAnnotationPresent(GameLoop.class)) {
                        components.put(clazz, clazz.getDeclaredConstructor().newInstance());
                    }
                }
            }
        }
    }

    private void injectDependencies() throws IllegalAccessException {
        for (Object component : components.values()) {
            for (Field field : component.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
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

    public <T> T getComponent(Class<T> clazz) {
        return clazz.cast(components.get(clazz));
    }

}
