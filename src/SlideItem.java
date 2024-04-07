import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;


/** <p>The abstract class for an item on a slide<p>
 * <p>All SlideItems have drawingfunctionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */


public interface SlideItem
{
    // draw the image
    void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer);

    // give the  bounding box of the image
    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle);
}

