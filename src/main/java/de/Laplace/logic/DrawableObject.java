package de.Laplace.logic;

import java.awt.*;

public class DrawableObject {

    public void draw(Graphics graphics) {}

    public void drawImage(Graphics graphics, Image image, double x, double y) {
        graphics.create().drawImage(image, (int) (x-image.getWidth(null)/2), (int) (y-image.getHeight(null)/2), null);
    }

    protected static void drawRectCentered(Graphics graphics, Color color, double x, double y, int width, int height) {
        graphics.setColor(color);
        graphics.fillRect((int) (x-width/2D), (int) (y-height/2D), width, height);
    }
}
