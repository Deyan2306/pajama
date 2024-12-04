package dark.cat;

import dark.cat.annotations.GameLoop;
import dark.cat.context.EngineContext;

public class PajamaApplication {

    public static void run(Class<?> mainClass, String basePackage) throws Exception {

        EngineContext context = new EngineContext(basePackage);

        Object gameLoop = context.getComponent(mainClass);

        if (gameLoop == null || !mainClass.isAnnotationPresent(GameLoop.class)) {
            throw new RuntimeException("[-] No @GameLoop class found!");
        }

        if (gameLoop instanceof Runnable) {
            ((Runnable) gameLoop).run();
        } else {
            throw new RuntimeException("[-] @GameLoop class must implement Runnable");
        }

    }

}
