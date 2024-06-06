package style.builders;

import style.types.Stylelevel1;

import java.awt.*;

public class Stylelevel1Builder implements StyleBuilder
{
    private int indent;
    private Color color;
    private int fontSize;
    private int leading;

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
    public Stylelevel1 createStyle()
    {
        return new Stylelevel1(indent, color, fontSize, leading);
    }
}