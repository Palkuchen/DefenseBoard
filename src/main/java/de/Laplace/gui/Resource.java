package de.Laplace.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Resource {
    BLUB("src/main/resources/enemy/Blubb.png", 0.18),
    BULLET("src/main/resources/projectiles/Bullet.png", 0.06),
    BACKGROUND_GRASS("src/main/resources/player/upgrades/Background_Grass.png", 1.4),
    STRENGTH("src/main/resources/player/upgrades/Strength.png", 0.2),
    SPEED("src/main/resources/player/upgrades/Speed.png", 0.2),
    UPGRADE("src/main/resources/player/upgrades/Upgrade.png", 0.2),
    GOLDEN_BALL("src/main/resources/player/upgrades/GoldenBall.png", 0.16);

    private int width = 0;
    Image image;

    Resource(String path, double scale) {
        image = scaleImage(pathToImage(path), scale);
    }

    public Image pathToImage(String path) {
        Image image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public Image scaleImage(Image image, double scale) {
        width = (int) (image.getWidth(null) * scale);
        int scaledHeight = (int) (image.getHeight(null) * scale);
        return image.getScaledInstance(width, scaledHeight, BufferedImage.TYPE_INT_ARGB);
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

}
