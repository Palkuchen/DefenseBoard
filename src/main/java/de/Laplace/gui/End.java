package de.Laplace.gui;

import de.Laplace.logic.Engine;
import de.Laplace.logic.GameObject;

import java.awt.*;

public class End extends GameObject {

    public End(int x, int y, Engine engine) {
        super(x, y, engine);
    }

    @Override
    public void draw(Graphics graphics) {
        drawTextCentered(graphics, Color.BLACK, 255, "You Died", 55, getEngine().getSizeX()/2, getEngine().getSizeY()/2);
    }
}
