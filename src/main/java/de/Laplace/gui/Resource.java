package de.Laplace.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Resource {
    BLUB("src/main/resources/enemy/Blubb.png", 0.2);

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
