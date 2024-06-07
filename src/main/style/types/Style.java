package main.style.types;

import java.awt.Color;
import java.awt.Font;

public class Style
{

    private static final String FONTNAME = "Helvetica";
    private int indent;
    private Color color;
    private int fontSize;
    private int leading;
    private Font font;

    public Style(int indent, Color color, int fontSize, int leading)
    {
        this.indent = indent;
        this.color = color;
        font = new Font(FONTNAME, Font.BOLD, fontSize = fontSize);

        this.leading = leading;
    }

    public String toString()
    {
        return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
    }

    public Font getFont(float scale)
    {
        return font.deriveFont(fontSize * scale);
    }

    public int getIndent()
    {
        return this.indent;
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
