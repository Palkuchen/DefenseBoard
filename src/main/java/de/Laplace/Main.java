package de.Laplace;

import de.Laplace.logic.Engine;
import de.Laplace.logic.GameLogic;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.initToolKit();

        GameLogic gameLogic = new GameLogic(engine);
        gameLogic.startGame();

        engine.initGameLoop();
    }
}