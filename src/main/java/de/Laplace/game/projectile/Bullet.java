package de.Laplace.game.projectile;

import de.Laplace.game.Entity;
import de.Laplace.game.player.Player;
import de.Laplace.gui.Resource;
import de.Laplace.logic.Engine;
import de.Laplace.logic.GameObject;

import java.awt.*;

public class Bullet extends Entity {

    private long spawned = System.currentTimeMillis();
    private double angle;

    public Bullet(int x, int y, Engine engine) {
        super(x, y, engine);
    }

    public void init(double angle) {
        this.angle = angle;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.rotate(angle+Math.PI/2, getX(), getY());
        drawImage(graphics2D, Resource.BULLET.getImage(), getX(), getY());
    }

    @Override
    public void onTick(double delta) {
        if (System.currentTimeMillis() - spawned > 1000) {
            despawn();
            return;
        }

        double speed = getEngine().getSizeX()*0.5*delta;
        setX((int) (getX() + Math.cos(angle)*speed));
        setY((int) (getY() + Math.sin(angle)*speed));

        GameObject closest = getClosestEnemy();
        if (closest == null) return;
        double dx = closest.getX() -getX();
        double dy = closest.getY() - getY();
        double distance = Math.sqrt(dx*dx+dy*dy);
        if (distance < 30) {
            if (closest instanceof Entity entity) entity.setHealth((int) (entity.getHealth() - 15 * (1+getEngine().getLevel(Resource.STRENGTH)/10D)));
            despawn();
        }
    }

    public GameObject getClosestEnemy() {
        double distance = Double.MAX_VALUE;
        GameObject gameObject = null;

        for (GameObject object : getEngine().getGameObjects()) {
            if (object == this) continue;
            if (object instanceof Player) continue;
            if (object instanceof Bullet) continue;
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
}
