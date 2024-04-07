import java.awt.Color;
import java.awt.Font;

/** <p>Style is for Indent, Color, Font and Leading.</p>
 * <p>Direct relation between style-number and item-level:
 * in Slide style if fetched for an item
 * with style-number as item-level.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class Style {

	private static final String FONTNAME = "Helvetica";
	private  int  indent;
	private Color color;
	private int fontSize;
	private int leading;
	private Font font;


//	public static void createStyles() {
//		styles = new Style[5];
//		// The styles are fixed.
//		styles[0] = new Style(0, Color.red,   48, 20);	// style for item-level 0
//		styles[1] = new Style(20, Color.blue,  40, 10);	// style for item-level 1
//		styles[2] = new Style(50, Color.black, 36, 10);	// style for item-level 2
//		styles[3] = new Style(70, Color.black, 30, 10);	// style for item-level 3
//		styles[4] = new Style(90, Color.black, 24, 10);	// style for item-level 4
//	}
	public Style(int indent, Color color, int fontSize, int leading) {
		this.indent = indent;
		this.color = color;
		font = new Font(FONTNAME, Font.BOLD, fontSize=fontSize);
		this.leading = leading;
	}

	public String toString() {
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}
	public Font getFont(float scale) {
		return font.deriveFont(fontSize * scale);
	}

	public int getIndent()
	{
		return indent;
	}

	public void setIndent(int indent)
	{
		this.indent = indent;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public int getLeading()
	{
		return leading;
	}

	public void setLeading(int leading)
	{
		this.leading = leading;
	}

	public int getFontSize()
	{
		return fontSize;
	}

	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

	public Font getFont()
	{
		return font;
	}

	public void setFont(Font font)
	{
		this.font = font;
	}
}
