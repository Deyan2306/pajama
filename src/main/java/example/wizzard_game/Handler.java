package example.wizzard_game;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();

    public void tick() {
        for (GameObject currentObject : objects) {
            currentObject.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject currentObject : objects) {
            currentObject.render(g);
        }
    }

    public void addObject(GameObject tempObject) {
        objects.add(tempObject);
    }

    public void removeObject(GameObject tempObject) {
        objects.remove(tempObject);
    }
}
