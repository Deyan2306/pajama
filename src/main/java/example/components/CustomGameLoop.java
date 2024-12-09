package example.components;

import dark.cat.annotations.GameLoop;
import dark.cat.annotations.Inject;
import dark.cat.utils.ThreadManagerPool;

import java.util.concurrent.TimeUnit;

@GameLoop
public class CustomGameLoop implements Runnable {

    @Inject
    private Renderer renderer;

    @Inject
    private InputHandler inputHandler;

    private boolean isRunning = true;

    @Override
    public void run() {
        ThreadManagerPool.runAsync(() -> {
            System.out.println("Thread is running");
            // Game logic here
            while (isRunning) {
                update();
                render();
                sleep(16);
            }
        });
        ThreadManagerPool.scheduleAtFixedRate(() -> {
            // Periodic logic (e.g., AI updates)
        }, 0, 16, TimeUnit.MILLISECONDS);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void render() {
        renderer.render();
    }

    private void update() {
        if (inputHandler.isKeyPressed()) {
            System.out.println("Key Pressed!");
        }
    }
    
}
