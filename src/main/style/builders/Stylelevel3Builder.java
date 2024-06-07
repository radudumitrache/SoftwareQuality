package main.style.builders;

import main.style.types.Style;
import java.awt.*;

public class Stylelevel3Builder implements StyleBuilder
{
    private int indent = 90;
    private Color color = Color.BLACK;
    private int fontSize = 24;
    private int leading = 10;

    @Override
    public void setIndent(int indent)
    {
        this.indent = indent;
    }

    @Override
    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
    }

    @Override
    public void setLeading(int leading)
    {
        this.leading = leading;
    }

    @Override
    public Style createStyle()
    {
        return new Style(indent, color, fontSize, leading);
    }
}