package de.Laplace.game.enemies;

import de.Laplace.game.Entity;
import de.Laplace.gui.Resource;
import de.Laplace.logic.Engine;

import java.awt.*;

public class Blub extends Entity {

    private Engine engine;

    public Blub(int x, int y, Engine engine) {
        super(x, y);
        this.engine = engine;
    }

    @Override
    public void draw(Graphics graphics) {
        drawImage(graphics, Resource.BLUB.getImage(), getX(), getY());
    }

    @Override
    public void onTick(double delta) {
        int centreX = engine.getSizeX()/2;
        int centreY = engine.getSizeX()/2;

        double speed = 50 * delta; // amount of pixels to walk per Second

        double dx = centreX - getX();
        double dy = centreY - getY();

        double distance = Math.sqrt(dx*dx + dy*dy);

        if (distance < 10) return;

        double moveX = (dx / distance) * speed;
        double moveY = (dy / delta) * speed;

        setX((int) (getX() + moveX));
        setY((int) (getY() + moveY));
    }
}
