package example.components;

import dark.cat.annotations.GameLoop;
import dark.cat.annotations.Inject;

@GameLoop
public class CustomGameLoop implements Runnable {

    @Inject
    private Renderer renderer;

    @Inject
    private InputHandler inputHandler;
    
    private boolean isRunning = true;

    @Override
    public void run() {

        while (isRunning) {
            update();
            render();
            sleep(16);
        }
        
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
