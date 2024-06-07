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
    private Style stylelevel0;
    private Style stylelevel1;
    private Style stylelevel2;
    private Style stylelevel3;

    private static TextDirector instance;

    private TextDirector()
    {
        this.stylelevel0Builder = new Stylelevel0Builder();
        this.stylelevel1Builder = new Stylelevel1Builder();
        this.stylelevel2Builder = new Stylelevel2Builder();
        this.stylelevel3Builder = new Stylelevel3Builder();
    }
    private static class SingletonHelper {
        private static final TextDirector INSTANCE = new TextDirector();
    }
    public static TextDirector getInstance()
    {
        return SingletonHelper.INSTANCE;
    }
    public void constructStyles()
    {

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
