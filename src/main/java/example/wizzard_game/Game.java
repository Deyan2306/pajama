package example.wizzard_game;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static java.time.Clock.tick;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    public Game() {
        new Window(1000, 563, "Wizzard Game", this);
        start();

        handler = new Handler();

        handler.addObject(new Box(100, 100, ID.BLOCK));
        handler.addObject(new Box(100, 200, ID.BLOCK));
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.RED);
        g.fillRect(0, 0, 1000, 563);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public void tick() {
        handler.tick();
    }

    public static void main(String[] args) {
        new Game();
    }


}
// https://youtu.be/nXzTp61FKR4?list=PLWms45O3n--5vDnNd6aiu1CSWX3JlCU1n