package de.Laplace.gui;

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
        for (GameObject gameObject : engine.getGameObjects()) {
            System.out.println("draw " + gameObject);
            gameObject.draw(g);
        }
    }
}
