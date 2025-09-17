package de.Laplace.logic;

public class GameObject extends DrawableObject {

    private Engine engine;
    private int x, y;

    public GameObject(int x, int y, Engine engine) {
        this.x = x;
        this.y = y;
        this.engine = engine;
    }

    public void onTick(double delta) {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void despawn() {
        engine.getGameObjects().remove(this);
    }

    public Engine getEngine() {
        return engine;
    }
}
