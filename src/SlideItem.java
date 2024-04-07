import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public interface SlideItem
{
    void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer);

    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle);

    Style getStyle();
}

