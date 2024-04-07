import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.text.Style;

import java.io.IOException;

public class BitmapItem implements SlideItem {
  private BufferedImage bufferedImage;
  private String imageName;
  
  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";

// level is equal to item-level; name is the name of the file with the Image
	public BitmapItem(String name) {
		super();
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

// An empty bitmap-item
	public BitmapItem() {
		this( null);
	}

// give the filename of the image
	public String getName() {
		return imageName;
	}
	public String toString() {
		return "BitmapItem[" + imageName + "]";
	}

// give the  bounding box of the image

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		return new Rectangle((int) (myStyle.getIndent() * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading() * scale)) +
				(int) (bufferedImage.getHeight(observer) * scale));
	}

// draw the image

	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer)
	{
		int width = x + (int) (myStyle.indent * scale);
		int height = y + (int) (myStyle.leading * scale);
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
				(int) (bufferedImage.getHeight(observer)*scale), observer);
	}
}
