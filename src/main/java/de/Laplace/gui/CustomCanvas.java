package de.Laplace.gui;

import de.Laplace.logic.DrawableObject;
import de.Laplace.logic.Engine;
import de.Laplace.logic.GameObject;

import javax.swing.*;
import java.awt.*;

public class CustomCanvas extends JPanel {

    private Engine engine;

    public CustomCanvas(Engine engine) {
        this.engine = engine;
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Resource.BACKGROUND_GRASS.getImage(), 0,0, null);
        if (engine.isEnded()) {
            new End(0,0, engine).draw(g);
            return;
        }
        for (GameObject gameObject : engine.getGameObjects()) {
            gameObject.draw(g.create());
        }
        if (engine.isMenuOpen()) engine.getPlayerLevel().draw(g);
        new ResourceInfo(engine).draw(g);
    }
}

class ResourceInfo extends DrawableObject {

    private Engine engine;

    public ResourceInfo(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void draw(Graphics graphics) {
        int sizeX = engine.getSizeX();
        int sizeY = engine.getSizeY();

        int x = (int) (sizeX*0.1);
        drawTransparentRoundRect(graphics, Color.BLACK, x, sizeY*0.1, sizeX*0.16, sizeY*0.14, 15, 0.9F);
        drawText(graphics, Color.WHITE, "Current Coins: " + engine.getCoins(), 25, x-sizeX*0.06, sizeY*0.08);
        drawText(graphics, Color.WHITE, "Enemies defeated: " + engine.getDefeated(), 25, x-sizeX*0.06, sizeY*0.12);
    }
}