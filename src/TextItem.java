import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point; // Import for Point
import java.awt.font.TextAttribute; // Import for TextAttribute
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItem implements SlideItem {
	private String text;
	private StyleType styleType;

	private static final String EMPTYTEXT = "No Text Given";

	public TextItem(String text) {
		this.text = text;
		this.styleType = StyleType.STYLELEVEL0;
	}

	public TextItem(StyleType styleType, String string) {
		this.styleType = styleType;
		this.text = string;
	}

	public TextItem() {
		this.styleType = StyleType.STYLELEVEL0;
		this.text = EMPTYTEXT;
	}

	public String getText() {
		return text == null ? "" : text;
	}

	public StyleType getStyleType() {
		return styleType;
	}

	public void setStyleType(StyleType styleType) {
		this.styleType = styleType;
	}

	private List<TextLayout> getLayouts(Graphics g, Style style, float scale) {
		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attributedString = new AttributedString(getText());
		attributedString.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, getText().length());

		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attributedString.getIterator(), frc);

		float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;
		while (measurer.getPosition() < getText().length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}
		return layouts;
	}

	@Override
	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		if (text == null || text.isEmpty()) {
			return;
		}

		List<TextLayout> layouts = getLayouts(g, style, scale);
		Point pen = new Point(x + (int)(style.getIndent() * scale), y + (int)(style.getLeading() * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(style.getColor());

		for (TextLayout layout : layouts) {
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent() + layout.getLeading();
		}
	}

	@Override
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		List<TextLayout> layouts = getLayouts(g, style, scale);
		int xsize = 0, ysize = (int)(style.getLeading() * scale);
		for (TextLayout layout : layouts) {
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			ysize += layout.getAscent() + layout.getDescent() + layout.getLeading();
		}
		return new Rectangle((int)(style.getIndent() * scale), 0, xsize, ysize);
	}
	@Override
	public Style getStyle()
	{
		TextDirector director = TextDirector.getInstance();
		return director.getStyle(this.styleType);
	}

	public String toString() {
		return "TextItem[" + getStyleType() + "," + getText() + "]";
	}
}