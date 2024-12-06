package dark.cat.context;

import dark.cat.annotations.EngineComponent;
import dark.cat.annotations.GameLoop;
import dark.cat.annotations.Inject;
import dark.cat.utils.PajamaLogger;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

import static dark.cat.utils.PajamaResponses.APPLICATION_STARTED_SUCCESSFULLY;
import static dark.cat.utils.PajamaResponses.NO_COMPONENT_FOUND_FOR;

public class EngineContext {

    private final Map<Class<?>, Object> components = new HashMap<>();
    private Class<?> mainClass;

    public EngineContext(String basePackage, Class<?> mainClass) throws Exception {
        setMainClass(mainClass);
        scanAndInitialize(basePackage);
        injectDependencies();
        PajamaLogger.log(APPLICATION_STARTED_SUCCESSFULLY.getMessage());
    }

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

    private void setMainClass(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    public Class<?> getMainClass() {
        return mainClass;
    }

    private String getParentPackage() {
        Package parentPackage = mainClass.getPackage();
        if (parentPackage == null || parentPackage.getName().isEmpty()) {
            return "";
        }

        return parentPackage.getName();
    }


    public <T> T getComponent(Class<T> clazz) {
        return clazz.cast(components.get(clazz));
    }

}
