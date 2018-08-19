package app;

import app.game.Game;

import java.util.HashSet;
import java.util.Set;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("smallpigex", 800, 600);
        game.start();
    }
}
