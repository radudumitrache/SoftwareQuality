import javax.swing.JOptionPane;

import java.io.IOException;

public class JabberPoint
{
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    public static void main(String argv[])
    {

        Presentation presentation = new Presentation();
        SlideViewerFrame.getInstance(JABVERSION, presentation);

        Stylelevel0Builder titleBuilder = new Stylelevel0Builder();
        Stylelevel1Builder textBuilder = new Stylelevel1Builder();
        Stylelevel3Builder subtitleBuilder = new Stylelevel3Builder();
        TextDirector director = TextDirector.getInstance();
        director.constructStyles();
        try
        {
            if (argv.length == 0)
            {
                Accessor.getDemoAccessor().loadFile(presentation, "");
            }
            else
            {
                new XMLAccessor().loadFile(presentation, argv[0]);
            }
            presentation.setSlideNumber(0);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
