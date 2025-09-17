package de.Laplace.gui.menu;

import de.Laplace.gui.Resource;
import de.Laplace.logic.DrawableObject;
import de.Laplace.logic.Engine;

import java.awt.*;
import java.util.HashMap;

public class PlayerLevel extends DrawableObject {

    private Engine engine;
    private HashMap<Resource, Integer> level = new HashMap<>();

    public PlayerLevel(Engine engine) {
        this.engine = engine;
        level.put(Resource.SPEED, 1);
        level.put(Resource.STRENGTH, 1);
        level.put(Resource.UPGRADE, 1);
    }

    @Override
    public void draw(Graphics graphics) {
        int sizeX = engine.getSizeX();
        int sizeY = engine.getSizeY();

        int height = (int) (sizeY*0.4);

        drawTransparentRoundRect(graphics, Color.BLACK, sizeX/2, sizeY/2, sizeX*0.4, height, 15, 0.88F);
        drawTextCentered(graphics, Color.WHITE, 255, "Level", 40, sizeX/2, sizeY/2-height*0.4);

        upgradeButton(graphics, Resource.STRENGTH, sizeX/2, (int) (sizeY/2 + height*0.2), "Strength");
        upgradeButton(graphics, Resource.SPEED, (int) (sizeX/2+sizeX*0.1), (int) (sizeY/2 + height*0.2), "Attack Speed");
        upgradeButton(graphics, Resource.UPGRADE, (int) (sizeX/2-sizeX*0.1), (int) (sizeY/2 + height*0.2), "Weapon Upgrade");

    }

    public void upgradeButton(Graphics graphics, Resource resource, int x, int y, String Title) {
        drawTextCentered(graphics, Color.WHITE, 255, Title, 25, x, y - engine.getSizeY()*0.13);
        drawTextCentered(graphics, Color.WHITE, 255, "Current Level: " + level.get(resource), 22, x, y - engine.getSizeY()*0.09);
        drawImage(graphics, resource.getImage(), x, y);
    }

    public void onClick(int x, int y) {
        int dx1 = x - engine.getSizeX()/2;
        int dy1 = (int) (y - (engine.getSizeY()/2+engine.getSizeY()*0.4*0.2));

        if (Math.sqrt(dx1*dx1+dy1*dy1) < 60) {
            upgrade(Resource.STRENGTH);
        }

        int dx2 = (int) (x - engine.getSizeX()/2 - engine.getSizeX()*0.1);
        int dy2 = (int) (y - (engine.getSizeY()/2+engine.getSizeY()*0.4*0.2));

        if (Math.sqrt(dx2*dx2+dy2*dy2) < 60) {
            upgrade(Resource.SPEED);
        }

        int dx3 = (int) (x - engine.getSizeX()/2 + engine.getSizeX()*0.1);
        int dy3 = (int) (y - (engine.getSizeY()/2+engine.getSizeY()*0.4*0.2));

        if (Math.sqrt(dx3*dx3+dy3*dy3) < 60) {
            upgrade(Resource.UPGRADE);
        }
    }

    public void upgrade(Resource resource) {
        int price = (int) (Math.pow(getLevel().get(resource)+1, 1.5) * 3);
        if (engine.getCoins() >= price) {
            engine.setCoins(engine.getCoins()-price);
            level.put(resource, getLevel().get(resource)+1);
        }
    }

    public HashMap<Resource, Integer> getLevel() {
        return level;
    }
}
