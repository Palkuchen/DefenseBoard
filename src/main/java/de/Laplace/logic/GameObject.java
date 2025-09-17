package de.Laplace.logic;

public class GameObject extends DrawableObject {

    private int x, y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void onTick(double delta) {

    }

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
}
