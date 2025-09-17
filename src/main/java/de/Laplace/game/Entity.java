package de.Laplace.game;

import de.Laplace.logic.Engine;
import de.Laplace.logic.GameObject;

public class Entity extends GameObject {

    private int health = 100;

    public Entity(int x, int y, Engine engine) {
        super(x, y, engine);
    }

    public GameObject getClosestEnemy() {
        double distance = Double.MAX_VALUE;
        GameObject gameObject = null;

        for (GameObject object : getEngine().getGameObjects()) {
            if (object == this) continue;
            double dx = object.getX() - getX();
            double dy = object.getY() - getY();
            double currentDis = Math.sqrt(dx*dx+dy*dy);

            if (currentDis < distance) {
                gameObject = object;
                distance = currentDis;
            }
        }

        return gameObject;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health <= 0) {
            despawn();
        }
    }
}
