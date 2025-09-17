package de.Laplace.logic;

import java.awt.*;

public class DrawableObject {

    public void draw(Graphics graphics) {}

    public void drawImage(Graphics graphics, Image image, double x, double y) {
        graphics.create().drawImage(image, (int) (x-image.getWidth(null)/2), (int) (y-image.getHeight(null)/2), null);
    }

    protected static int drawTextCentered(Graphics graphics, Color color, int opacity, String text, int size, double x, double y) {
        Font font = new Font("SansSerif", Font.BOLD, size);
        Graphics2D g2d = (Graphics2D) graphics;

        if (opacity < 1) opacity = 1;
        if (opacity > 255) opacity = 255;
        Color colorWithOpacity = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
        g2d.setColor(colorWithOpacity);

        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(text, (int) (x - fm.stringWidth(text) / 2D), (int) y + size/2);
        return fm.stringWidth(text);
    }

    protected static int drawText(Graphics graphics, Color color, String text, int size, double x, double y) {
        Font font = new Font("SansSerif", Font.BOLD, size);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text, (int) x, (int) y);
        FontMetrics fm = g2d.getFontMetrics();
        return fm.stringWidth(text);
    }

    protected void drawTransparentRoundRect(Graphics graphics, Color color, double x, double y, double width,
                                            double height, int arc, float transparency) {
        if (transparency > 1) transparency = 1;
        if (transparency < 0) transparency = 0;
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        graphics2D.setColor(color);
        graphics2D.fillRoundRect((int) (x-width/2D), (int) (y-height/2D),
                (int) width, (int) height, arc, arc);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    protected static void drawRectCentered(Graphics graphics, Color color, double x, double y, int width, int height) {
        graphics.setColor(color);
        graphics.fillRect((int) (x-width/2D), (int) (y-height/2D), width, height);
    }

    protected static void drawRectNotCenteredX(Graphics graphics, Color color, double x, double y, int width, int height) {
        graphics.setColor(color);
        graphics.fillRect((int) x,(int) y-height/2, width, height);
    }

    protected void drawRoundRectNotCenteredX(Graphics graphics, Color color, double x, double y, int width, int height, int arc) {
        graphics.setColor(color);
        graphics.fillRoundRect((int) x,(int) y-height/2, width, height, arc, arc);
    }

    protected void drawRoundRectNotFilled(Graphics graphics, Color color, double x, double y, int width, int height, int arc, int stroke) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(stroke));
        graphics2D.drawRoundRect((int) x,(int) y-height/2, width, height, arc, arc);
    }

    public void drawRect(Graphics graphics, Color color, double x, double y,
                         int width, int height) {
        graphics.setColor(color);
        graphics.fillRect((int) (x-width/2), (int) (y-height/2), width, height);
    }
}
