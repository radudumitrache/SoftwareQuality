import java.awt.*;

public interface StyleBuilder
{
    public void setIndent(int indent);

    public void setColor(Color color);

    public void setFontSize(int fontSize);

    public void setLeading(int leading);

    public Style createStyle();
}
