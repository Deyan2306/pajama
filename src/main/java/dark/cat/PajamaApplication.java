package dark.cat;

import dark.cat.annotations.GameLoop;
import dark.cat.context.EngineContext;

import static dark.cat.utils.PajamaResponses.GAME_LOOP_MUST_IMPLEMENT_RUNNABLE;
import static dark.cat.utils.PajamaResponses.NO_GAME_LOOP_FOUND;

public class PajamaApplication {

    public static void run(Class<?> mainClass, String basePackage) throws Exception {

        EngineContext context = new EngineContext(basePackage);

        Object gameLoop = context.getComponent(mainClass);

        if (gameLoop == null || !mainClass.isAnnotationPresent(GameLoop.class)) {
            throw new RuntimeException(NO_GAME_LOOP_FOUND);
        }

        if (gameLoop instanceof Runnable) {
            ((Runnable) gameLoop).run();
        } else {
            throw new RuntimeException(GAME_LOOP_MUST_IMPLEMENT_RUNNABLE);
        }

    }

}
