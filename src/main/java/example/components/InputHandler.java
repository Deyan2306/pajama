package example.components;

import dark.cat.annotations.EngineComponent;

import java.awt.*;
import java.awt.event.KeyEvent;

@EngineComponent
public class InputHandler {

    private boolean keyPressed = false;

    public InputHandler() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keyPressed = true;
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                keyPressed = false;
            }
            return false;
        });
    }

    public boolean isKeyPressed() {
        return keyPressed;
    }

}
