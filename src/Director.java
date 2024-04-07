import java.awt.*;

public class Director
{
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
    public void constructSubtitle (SubtitleStyleBuilder builder)
    {
        builder.setIndent(90);
        builder.setColor(Color.black);
        builder.setFontSize(24);
        builder.setLeading(10);
    }


}
