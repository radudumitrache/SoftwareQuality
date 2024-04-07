import java.awt.*;

public class TextDirector
{
    private Stylelevel1Builder stylelevel1Builder;
    private Stylelevel0Builder stylelevel0Builder;
    private Stylelevel2Builder stylelevel2Builder;
    private Stylelevel3Builder stylelevel3Builder;
    private Stylelevel0 titleStyle;
    private Stylelevel1 textStyle;
    private Stylelevel2 stylelevel2;
    private Stylelevel3 subtitleStyle;
    
    private static TextDirector instance;
    private TextDirector()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
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
    public void constructTitleStyle(Stylelevel0Builder builder)
    {
        builder.setIndent(0);
        builder.setColor(Color.red);
        builder.setFontSize(48);
        builder.setLeading(20);
    }
    public void constructTextStyle (Stylelevel1Builder builder)
    {
        builder.setIndent(20);
        builder.setColor(Color.blue);
        builder.setFontSize(40);
        builder.setLeading(10);
    }
    public void constructSubtitleStyle (Stylelevel3Builder builder)
    {
        builder.setIndent(90);
        builder.setColor(Color.black);
        builder.setFontSize(24);
        builder.setLeading(10);
    }
    public void constructStyles()
    {
        this.constructTitleStyle(stylelevel0Builder);
        this.constructTextStyle(stylelevel1Builder);
        this.constructSubtitleStyle(stylelevel3Builder);
        titleStyle = stylelevel0Builder.createStyle();
        textStyle = stylelevel1Builder.createStyle();
        subtitleStyle = stylelevel3Builder.createStyle();
    }
    public Style  getStyle(StyleType styleType)
    {
        switch (styleType)
        {
            case STYLELEVEL1:
            {
                return this.titleStyle;
            }
            case STYLELEVEL0:
            {
                return this.textStyle;
            }
            case STYLELEVEL2:
            {
                return this.subtitleStyle;
            }
        }
        return this.textStyle;
    }

}
