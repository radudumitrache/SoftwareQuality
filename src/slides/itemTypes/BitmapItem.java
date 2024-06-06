package slides.itemTypes;

import style.types.Style;
import style.types.StyleType;
import style.TextDirector;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BitmapItem implements SlideItem
{
    private BufferedImage bufferedImage;
    private String imageName;

    StyleType styleType;

    protected static final String FILE = "File";
    protected static final String NOTFOUND = "not found";

    public BitmapItem(String name)
    {
        imageName = name;
        try
        {
            bufferedImage = ImageIO.read(new File(imageName));
        }
        catch (IOException e)
        {
            System.err.println(FILE + " " + imageName + " " + NOTFOUND);
            bufferedImage = null; // Handle the missing image case
        }
    }

    public BitmapItem()
    {
        this(null);
    }

    public String getName()
    {
        return imageName;
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle)
    {
        if (bufferedImage == null)
        {
            return new Rectangle(0, 0, 0, 0);
        }
        return new Rectangle((int) (myStyle.getIndent() * scale), 0, (int) (bufferedImage.getWidth(observer) * scale), (int) (myStyle.getLeading() * scale) + (int) (bufferedImage.getHeight(observer) * scale));
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer)
    {
        if (bufferedImage != null)
        {
            int width = x + (int) (myStyle.getIndent() * scale);
            int height = y + (int) (myStyle.getLeading() * scale);
            g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale), (int) (bufferedImage.getHeight(observer) * scale), observer);
        }
    }

    @Override
    public String toString()
    {
        return "Slides.BitmapItem[" + imageName + "]";
    }

    @Override
    public Style getStyle()
    {
        TextDirector director = TextDirector.getInstance();
        return director.getStyle(this.styleType);
    }
    @Override
    public String getTagContent()
    {
        return "<item kind=\"image\" style=\"" + this.getStyle().toString() + "\">" + imageName + "</item>";
    }

}