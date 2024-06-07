package main.viewers;

import main.controller.KeyController;
import main.controller.MenuController;
import main.presentation.Presentation;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private static SlideViewerFrame instance;


    private static class SingletonHelper {
        private static final SlideViewerFrame INSTANCE = new SlideViewerFrame(JABTITLE, new Presentation());
    }
    private SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setSlideViewComponent(slideViewerComponent);
        setupWindow(slideViewerComponent, presentation);
    }

    public static SlideViewerFrame getInstance(String title, Presentation presentation) {
        return SingletonHelper.INSTANCE;
    }

    public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation)
    {
        setTitle(JABTITLE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        getContentPane().add(slideViewerComponent);
        addKeyListener(new KeyController(presentation));
        setMenuBar(new MenuController(this, presentation));
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }
}

