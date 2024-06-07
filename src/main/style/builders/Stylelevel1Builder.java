package main.style.builders;

import main.style.types.Style;

import java.awt.*;

public class Stylelevel1Builder implements StyleBuilder
{
    private int indent = 20;
    private Color color = Color.BLUE;
    private int fontSize = 40;
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
