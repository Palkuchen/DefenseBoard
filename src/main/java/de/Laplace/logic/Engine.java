package de.Laplace.logic;

import de.Laplace.game.enemies.Blub;
import de.Laplace.game.player.GoldenBullet;
import de.Laplace.gui.CustomWindow;
import de.Laplace.gui.Resource;
import de.Laplace.gui.menu.PlayerLevel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Engine {

    private int defeated = 0;
    private int coins = 10;

    private boolean isEnded = false;
    private boolean menuOpen = false;
    private CustomWindow customWindow;
    private int sizeX, sizeY;
    private long lastLoop;

    private PlayerLevel playerLevel;

    public static java.util.List<GameObject> gameObjects = new ArrayList<>();

    public Engine() {
        playerLevel = new PlayerLevel(this);
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
        Timer timer = new Timer(1000/25, _ -> {
            double delta = (System.nanoTime() - lastLoop)/1E9;
            if (!isEnded) {
                for (GameObject gameObject : getGameObjectCopy()) {
                    gameObject.onTick(delta);
                }
                if (Math.random() < delta/7D) {
                    if (!existGoldenBall()) {
                        GoldenBullet blub = new GoldenBullet((int) (Math.random()*getSizeX()), (int) (Math.random()*getSizeY()), this);
                        getGameObjects().add(blub);
                    }
                }
                if (Math.random() < delta/(2D/Math.max(getDefeated()/10, 1))) respawn();
            }
            customWindow.repaint();
            lastLoop = System.nanoTime();
        });
        timer.start();
    }

    public boolean existGoldenBall() {
        for (GameObject gameObject : getGameObjectCopy()) {
            if (gameObject instanceof GoldenBullet) return true;
        }
        return false;
    }

    public GoldenBullet getGoldenBall() {
        for (GameObject gameObject : getGameObjectCopy()) {
            if (gameObject instanceof GoldenBullet goldenBall) return goldenBall;
        }
        return null;
    }

    public void respawn() {
        Blub blub = new Blub((int) (Math.random()*getSizeX()), (int) (Math.random()*getSizeY()), this);
        getGameObjects().add(blub);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public java.util.List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public java.util.List<GameObject> getGameObjectCopy() {
        return new ArrayList<>(gameObjects);
    }

    public boolean isMenuOpen() {
        return menuOpen;
    }

    public void setMenuOpen(boolean menuOpen) {
        this.menuOpen = menuOpen;
    }

    public PlayerLevel getPlayerLevel() {
        return playerLevel;
    }

    public int getLevel(Resource resource) {
        return playerLevel.getLevel().get(resource);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addKill() {
        defeated++;
    }

    public int getDefeated() {
        return defeated;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }
}
