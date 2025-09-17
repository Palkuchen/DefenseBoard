package de.Laplace.game.player;

import de.Laplace.game.Entity;

import java.awt.*;

public class Player extends Entity {

    private float currentRotation = 0.1F;

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        // drawImage(graphics, null, getX(), getY());
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.rotate(currentRotation, getX(), getY());
        drawRectCentered(graphics2D, Color.RED, getX(), getY(), 50, 50);
    }
}
