import java.awt.*;

public class TextDirector
{
    private TextStyleBuilder textBuilder;
    private TitleStyleBuilder titleStyleBuilder;
    private SubtitleStyleBuilder subtitleStyleBuilder;
    private TitleStyle titleStyle;
    private TextStyle textStyle;
    private SubtitleStyle subtitleStyle;
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

        this.textBuilder = new TextStyleBuilder();
        this.titleStyleBuilder = new TitleStyleBuilder();
        this.subtitleStyleBuilder = new SubtitleStyleBuilder();
    }
    public static TextDirector getInstance()
    {
        if (instance == null)
        {
            instance = new TextDirector();
        }
        return instance;
    }
    public void constructTitleStyle(TitleStyleBuilder builder)
    {
        builder.setIndent(0);
        builder.setColor(Color.red);
        builder.setFontSize(48);
        builder.setLeading(20);
    }
    public void constructTextStyle (TextStyleBuilder builder)
    {
        builder.setIndent(20);
        builder.setColor(Color.blue);
        builder.setFontSize(40);
        builder.setLeading(10);
    }
    public void constructSubtitleStyle (SubtitleStyleBuilder builder)
    {
        builder.setIndent(90);
        builder.setColor(Color.black);
        builder.setFontSize(24);
        builder.setLeading(10);
    }
    public void constructStyles()
    {
        this.constructTitleStyle(titleStyleBuilder);
        this.constructTextStyle(textBuilder);
        this.constructSubtitleStyle(subtitleStyleBuilder);
        titleStyle = titleStyleBuilder.createStyle();
        textStyle = textBuilder.createStyle();
        subtitleStyle = subtitleStyleBuilder.createStyle();
    }
    public Style  getStyle(StyleType styleType)
    {
        switch (styleType)
        {
            case TITLESTYLE :
            {
                return this.titleStyle;
            }
            case TEXTSTYLE:
            {
                return this.textStyle;
            }
            case SUBTITLESTYLE:
            {
                return this.subtitleStyle;
            }
        }
        return this.textStyle;
    }

}
