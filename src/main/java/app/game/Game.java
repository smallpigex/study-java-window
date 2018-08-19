package app.game;

import app.Display;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable {

  public static boolean running = false;
  private Display display;
  private String title;
  public int width, height;

  private Thread thread;

  private BufferStrategy bs;
  private Graphics graphics;

  private BufferedImage bufferedImage;
  private SpriteSheet sheet;

  public Game(String title, int width, int height) {
    this.title = title;
    this.width = width;
    this.height = height;
  }

  private void init() {
    display = new Display(title, width, height);
    bufferedImage = ImageLoader.loadImage("/textures/sheet.png");
    sheet = new SpriteSheet(bufferedImage);
  }

  private void tick() {

  }

  private void render() {
    bs = display.getCanvas().getBufferStrategy();
    if (bs == null) {
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    graphics = bs.getDrawGraphics();

    // Clear Screen
    graphics.clearRect(0, 0, width, height);
    //Draw Here!

    graphics.drawImage(sheet.crop(32, 0, 32, 32), 5, 5, null);

    //End Drawing!
    bs.show();
    graphics.dispose();
  }

  public void run() {
    init();

    while (running) {

      try {
        tick();
        render();
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    stop();
  }

  public synchronized void start() {
    if (running) {
      return;
    }
    running = true;
    thread = new Thread(this);
    thread.start();
  }

  public synchronized void stop() {
    if (!running) {
      return;
    }
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
