package example.wizzard_game;

import java.awt.*;

public class Box extends GameObject {

    public Box(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += (int) getVelX();
        y += (int) getVelY();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
