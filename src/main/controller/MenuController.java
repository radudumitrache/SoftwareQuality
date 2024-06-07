package main.controller;

import main.accessors.Accessor;
import main.accessors.XMLAccessor;
import main.presentation.Presentation;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.IOException;
import javax.swing.JOptionPane;

public class MenuController extends MenuBar {

    private Frame parent;
    private Presentation presentation;

    private static final long serialVersionUID = 227L;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    private void new_presentation() {
        presentation.clear();
        parent.repaint();
    }

    private void load_presentation() {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, TESTFILE);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }

    private void save_presentation()
    {
        Accessor xmlAccessor = new XMLAccessor();
        try
        {
            xmlAccessor.saveFile(presentation, SAVEFILE);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }
    private void go_to_presentation()
    {
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }

    public MenuController(Frame frame, Presentation pres)
    {
        //TODO see if i can use a pattern to make a function that uses a lambda example
        parent = frame;
        presentation = pres;
        MenuItem menuItem;
        Menu fileMenu = new Menu(FILE);
        fileMenu.add(menuItem = mkMenuItem(OPEN));
        menuItem.addActionListener( e -> load_presentation());
        fileMenu.add(menuItem = mkMenuItem(NEW));
        menuItem.addActionListener( e -> new_presentation());
        fileMenu.add(menuItem = mkMenuItem(SAVE));
        menuItem.addActionListener( e -> save_presentation());
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(EXIT));
        menuItem.addActionListener( e -> presentation.exit(0));
        add(fileMenu);
        Menu viewMenu = new Menu(VIEW);
        viewMenu.add(menuItem = mkMenuItem(NEXT));
        menuItem.addActionListener( e -> presentation.nextSlide());
        viewMenu.add(menuItem = mkMenuItem(PREV));
        menuItem.addActionListener( e -> presentation.prevSlide());
        viewMenu.add(menuItem = mkMenuItem(GOTO));
        menuItem.addActionListener( e -> go_to_presentation());
        add(viewMenu);
        Menu helpMenu = new Menu(HELP);
        helpMenu.add(menuItem = mkMenuItem(ABOUT));
        menuItem.addActionListener(e -> AboutBox.show(parent));
        setHelpMenu(helpMenu);
    }

    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
