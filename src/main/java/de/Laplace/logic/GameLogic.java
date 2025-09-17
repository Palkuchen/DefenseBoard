package de.Laplace.logic;

import de.Laplace.game.enemies.Blub;
import de.Laplace.game.player.Player;
import de.Laplace.gui.CustomWindow;

public class GameLogic {

    private Engine engine;

    public GameLogic(Engine engine) {
        this.engine = engine;
    }

    public void startGame() {
        // Load Character
        Player player = new Player(engine.getSizeX()/2, engine.getSizeY()/2);
        engine.getGameObjects().add(player);

        // Spawn Entitys
        for (int i = 0; i < 3; i++) {
            Blub blub = new Blub((int) (Math.random()*engine.getSizeX()), (int) (Math.random()*engine.getSizeY()), engine);
            engine.getGameObjects().add(blub);
        }
    }
}
