package dark.cat;

import dark.cat.annotations.GameLoop;
import dark.cat.context.EngineContext;

import static dark.cat.utils.PajamaResponses.*;

public class PajamaApplication {

    private static EngineContext context;

    public static void run(Class<?> mainClass) throws Exception {
        context = new EngineContext(".", mainClass);
        runMainClassOnCurrentContext(mainClass);
    }

    public static void run(Class<?> mainClass, String basePackage) throws Exception {
        context = new EngineContext(basePackage, mainClass);
        runMainClassOnCurrentContext(mainClass);
    }

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
