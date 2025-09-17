package de.Laplace.game.player;

import de.Laplace.game.Entity;
import de.Laplace.game.enemies.Blub;
import de.Laplace.game.projectile.Bullet;
import de.Laplace.gui.Resource;
import de.Laplace.logic.Engine;
import de.Laplace.logic.GameObject;

import java.awt.*;

public class Player extends Entity {

    public Player(int x, int y, Engine engine) {
        super(x, y, engine);
    }

    @Override
    public void draw(Graphics graphics) {
        // drawImage(graphics, null, getX(), getY());
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        drawHealthBar(graphics2D);

        GameObject closest = getClosestEnemy();
        if (closest != null) graphics2D.rotate(getAngle(closest), getX(), getY());
        drawRectCentered(graphics2D, Color.RED, getX(), getY(), 70, 70);
    }

    private void drawHealthBar(Graphics2D g2d) {
        int screenWidth = getEngine().getSizeX();
        int screenHeight = getEngine().getSizeY();

        int barWidth = screenWidth / 2;
        int barHeight = (int) (getEngine().getSizeY()*0.04);
        int barX = (screenWidth - barWidth) / 2;
        int barY = (int) (screenHeight*0.08);

        double healthPercentage = getHealth() / 100D;
        int filledWidth = (int) (barWidth * healthPercentage);

        Color healthColor = new Color((int) (255 * (1 - healthPercentage)), (int) (255 * healthPercentage), 0);

        drawTextCentered(g2d, Color.BLACK, 255, getHealth() + "/100", 40, screenWidth/2, barHeight - screenHeight*0.01);

        drawRoundRectNotCenteredX(g2d, Color.GRAY, barX, barY, barWidth, barHeight, 10);
        drawRoundRectNotCenteredX(g2d, healthColor, screenWidth/2D-barWidth/2D, barY, filledWidth, barHeight, 10);
        drawRoundRectNotFilled(g2d, Color.black, barX, barY, barWidth, barHeight, 10, 3);
    }

    private long lastShot = System.currentTimeMillis();

    @Override
    public void onTick(double delta) {
        GameObject gameObject = getClosestEnemy();
        if (gameObject == null) return;

        if (System.currentTimeMillis() - lastShot > 600 * (1 - getEngine().getLevel(Resource.SPEED)/50D)) {
            Bullet bullet = new Bullet(getEngine().getSizeX()/2, getEngine().getSizeY()/2, getEngine());
            bullet.init(getAngle(gameObject));
            getEngine().getGameObjects().add(bullet);
            lastShot = System.currentTimeMillis();
            System.out.println("shot");
        }

        double dx = gameObject.getX() -getX();
        double dy = gameObject.getY() - getY();
        double distance = Math.sqrt(dx*dx+dy*dy);
        if (distance < 130) {
            System.out.println("Despawn Blob");
            setHealth(getHealth()-10);
            gameObject.despawn();
        }
    }

    @Override
    public GameObject getClosestEnemy() {
        double distance = Double.MAX_VALUE;
        GameObject gameObject = null;

        for (GameObject object : getEngine().getGameObjects()) {
            if (object == this) continue;
            if (object instanceof Bullet) continue;
            if (object instanceof GoldenBullet) continue;
            double dx = object.getX() - getX();
            double dy = object.getY() - getY();
            double currentDis = Math.sqrt(dx*dx+dy*dy);

            if (currentDis < distance) {
                gameObject = object;
                distance = currentDis;
            }
        }

        return gameObject;
    }

    public double getAngle(GameObject gameObject) {
        double dx = gameObject.getX() - getX();
        double dy = gameObject.getY() - getY();
        return Math.atan2(dy, dx);
    }

    @Override
    public void despawn() {
        super.despawn();
        getEngine().setEnded(true);
        getEngine().getGameObjects().clear();
    }
}
