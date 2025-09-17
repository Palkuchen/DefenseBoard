package de.Laplace.logic;

import de.Laplace.gui.CustomWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Engine {

    private CustomWindow customWindow;
    private int sizeX, sizeY;
    private long lastLoop;

    public static java.util.List<GameObject> gameObjects = new ArrayList<>();

    public Engine() {

    }

    public void initToolKit() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        sizeX = screenSize.width;
        sizeY = screenSize.height;
        SwingUtilities.invokeLater(() -> customWindow = new CustomWindow(this));
    }

    public void initGameLoop() {
        lastLoop = System.nanoTime();
        Timer timer = new Timer(1000/60, _ -> {
            double delta = (System.nanoTime() - lastLoop)/1E9;
            for (GameObject gameObject : gameObjects) {
               // gameObject.onTick(delta);
            }
            customWindow.repaint();
            lastLoop = System.nanoTime();
        });
        timer.start();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setCustomWindow(CustomWindow customWindow) {
        this.customWindow = customWindow;
    }

    public java.util.List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
