package app.game;

import app.Display;

public class Game implements Runnable {

    public static boolean running = true;
    private Display display;

    public int width, height;

    private Thread thread;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        display = new Display(title, width, height);
    }

    private void init() {

    }

    private void tick() {

    }

    private void render() {
    }

    public void run() {
        init();

        while (running) {

            tick();
            render();
        }

        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
