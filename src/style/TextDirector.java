package style;

import style.builders.Stylelevel0Builder;
import style.builders.Stylelevel1Builder;
import style.builders.Stylelevel2Builder;
import style.builders.Stylelevel3Builder;
import style.types.*;

import java.awt.*;

public class TextDirector
{
    private Stylelevel1Builder stylelevel1Builder;
    private Stylelevel0Builder stylelevel0Builder;
    private Stylelevel2Builder stylelevel2Builder;
    private Stylelevel3Builder stylelevel3Builder;
    private Stylelevel0 stylelevel0;
    private Stylelevel1 stylelevel1;
    private Stylelevel2 stylelevel2;
    private Stylelevel3 stylelevel3;

    private static TextDirector instance;

    private TextDirector()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.stylelevel0Builder = new Stylelevel0Builder();
        this.stylelevel1Builder = new Stylelevel1Builder();
        this.stylelevel2Builder = new Stylelevel2Builder();
        this.stylelevel3Builder = new Stylelevel3Builder();
    }

    public static TextDirector getInstance()
    {
        if (instance == null)
        {
            instance = new TextDirector();
        }
        return instance;
    }

    public void constructStyleLevel0(Stylelevel0Builder builder)
    {
        builder.setIndent(0);
        builder.setColor(Color.red);
        builder.setFontSize(48);
        builder.setLeading(20);
    }

    public void constructStyleLevel1(Stylelevel1Builder builder)
    {
        builder.setIndent(20);
        builder.setColor(Color.blue);
        builder.setFontSize(40);
        builder.setLeading(10);
    }

    public void constructStyleLevel2(Stylelevel2Builder builder)
    {
        builder.setIndent(50);
        builder.setColor(Color.black);
        builder.setFontSize(36);
        builder.setLeading(10);
    }

    public void constructStyleLevel3(Stylelevel3Builder builder)
    {
        builder.setIndent(90);
        builder.setColor(Color.black);
        builder.setFontSize(24);
        builder.setLeading(10);
    }

    public void constructStyles()
    {
        this.constructStyleLevel0(stylelevel0Builder);
        this.constructStyleLevel1(stylelevel1Builder);
        this.constructStyleLevel2(stylelevel2Builder);
        this.constructStyleLevel3(stylelevel3Builder);
        stylelevel0 = stylelevel0Builder.createStyle();
        stylelevel1 = stylelevel1Builder.createStyle();
        stylelevel2 = stylelevel2Builder.createStyle();
        stylelevel3 = stylelevel3Builder.createStyle();
    }

    public Style getStyle(StyleType styleType)
    {
        switch (styleType)
        {
            case STYLELEVEL0:
                return this.stylelevel0;
            case STYLELEVEL1:
                return this.stylelevel1;
            case STYLELEVEL2:
                return this.stylelevel2;
            case STYLELEVEL3:
                return this.stylelevel3;
            default:
                throw new IllegalArgumentException("Unknown Style Type");
        }
    }

}
