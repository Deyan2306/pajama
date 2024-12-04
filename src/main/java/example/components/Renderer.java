package example.components;

import dark.cat.annotations.EngineComponent;
import dark.cat.annotations.Inject;

import java.awt.*;

@EngineComponent
public class Renderer {

    @Inject
    private EngineWindow window;

    public void render() {
        Graphics g = window.getGraphics();
        g.clearRect(0, 0, 800, 600);
        g.setColor(Color.BLACK);
        g.fillRect(100, 100, 50, 50);
    }

}
