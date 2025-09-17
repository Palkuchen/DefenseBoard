package de.Laplace.game.enemies;

import de.Laplace.game.Entity;
import de.Laplace.gui.Resource;
import de.Laplace.logic.Engine;

import java.awt.*;

public class Blub extends Entity {

    public Blub(int x, int y, Engine engine) {
        super(x, y, engine);
    }

    @Override
    public void draw(Graphics graphics) {
        drawImage(graphics, Resource.BLUB.getImage(), getX(), getY());

        Resource resource = Resource.BLUB;

        double sizeY = getEngine().getSizeY();

        // Health Bar
        drawRect(graphics, Color.DARK_GRAY,
                getX(), getY() - sizeY*0.05,
                (int) (resource.getWidth() * 0.5),
                (int) (resource.getWidth() * 0.1));
        drawRectNotCenteredX(graphics, Color.CYAN,
                getX() - (int)(resource.getWidth() * 0.23), getY() - sizeY*0.05,
                (int) ((resource.getWidth() * 0.45) * getHealthPercentage()),
                (int) (resource.getWidth() * 0.08));
    }

    public double getHealthPercentage() {
        return getHealth() / 100D;
    }

    @Override
    public void onTick(double delta) {
        int centreX = getEngine().getSizeX()/2;
        int centreY = getEngine().getSizeY()/2;

        double speed = 50 * delta; // amount of pixels to walk per Second

        double dx = centreX - getX();
        double dy = centreY - getY();

        double distance = Math.sqrt(dx*dx + dy*dy);

        if (distance < 100) return;

        double moveX = (dx / distance) * speed;
        double moveY = (dy / distance) * speed;

        setX((int) (getX() + moveX));
        setY((int) (getY() + moveY));
    }

    @Override
    public void despawn() {
        super.despawn();
        getEngine().setCoins(getEngine().getCoins()+3);
        getEngine().addKill();
    }
}
