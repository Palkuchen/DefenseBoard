package de.Laplace.game.player;

import de.Laplace.gui.Resource;
import de.Laplace.logic.Engine;
import de.Laplace.logic.GameObject;

import java.awt.*;

public class GoldenBullet extends GameObject {

    int count = 0;

    public GoldenBullet(int x, int y, Engine engine) {
        super(x, y, engine);
    }

    public void init(int count) {
        this.count = count;
    }

    @Override
    public void draw(Graphics graphics) {
        double scale = Math.max(0.1, Math.pow(1.5 - i, 0.8));
        Image img = Resource.GOLDEN_BALL.getImage();
        int width = (int) (img.getWidth(null) * scale);
        int height = (int) (img.getHeight(null) * scale);
        int drawX = getX() + (img.getWidth(null) - width) / 2;
        int drawY = getY() + (img.getHeight(null) - height) / 2;

        graphics.drawImage(img, drawX, drawY, width, height, null);
    }


    private double i = 0;

    @Override
    public void onTick(double delta) {
        i+= delta;
        if (i > 1.5 - (count/10D)) super.despawn();
    }

    @Override
    public void despawn() {
        super.despawn();
        GoldenBullet blub = new GoldenBullet((int) (Math.random()*getEngine().getSizeX()), (int) (Math.random()*getEngine().getSizeY()), getEngine());
        blub.init(count++);
        getEngine().getGameObjects().add(blub);
        getEngine().setCoins((int) (getEngine().getCoins()+1+getEngine().getCoins()*0.1));
    }
}
