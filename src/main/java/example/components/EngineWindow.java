package example.components;

import dark.cat.annotations.EngineComponent;

import javax.swing.*;
import java.awt.*;

@EngineComponent
public class EngineWindow {

    private final JFrame frame;

    public EngineWindow() {
        frame = new JFrame("Pajama Application");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Graphics getGraphics() {
        return frame.getGraphics();
    }

}
