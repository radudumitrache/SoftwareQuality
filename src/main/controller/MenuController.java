package main.controller;

import main.accessors.Accessor;
import main.accessors.XMLAccessor;
import main.controller.commands.*;
import main.presentation.Presentation;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.midi.Receiver;
import javax.swing.JOptionPane;

public class MenuController extends MenuBar implements CommandReceiver {

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

    public MenuController(Frame frame, Presentation pres)
    {
        OpenFileCommand openFileCommand = new OpenFileCommand(this.presentation, frame);
        NewFileCommand newFileCommand = new NewFileCommand(this.presentation, frame);
        SaveFileCommand saveFileCommand = new SaveFileCommand(this.presentation, frame);
        ExitPresentationCommand exitCommand = new ExitPresentationCommand(this.presentation);
        GoToNextSlideCommand nextSlideCommand = new GoToNextSlideCommand(this.presentation);
        GoToPrevSlideCommand previousSlideCommand = new GoToPrevSlideCommand(this.presentation);
        GoToSlideCommand goToSlideCommand = new GoToSlideCommand(this.presentation);
        ShowAboutBoxCommand showAboutCommand = new ShowAboutBoxCommand(this.presentation, frame);

        parent = frame;
        presentation = pres;

        Menu fileMenu = new Menu(FILE);
        addMenuItem(fileMenu,OPEN,openFileCommand);
        addMenuItem(fileMenu,NEW,newFileCommand);
        addMenuItem(fileMenu,SAVE,saveFileCommand);
        fileMenu.addSeparator();
        addMenuItem(fileMenu,EXIT,exitCommand);

        Menu viewMenu = new Menu(VIEW);
        addMenuItem(viewMenu,NEXT,nextSlideCommand);
        addMenuItem(viewMenu,PREV,previousSlideCommand);
        addMenuItem(viewMenu,GOTO,goToSlideCommand);


        Menu helpMenu = new Menu(HELP);
        addMenuItem(helpMenu,HELP,showAboutCommand);

        add(fileMenu);
        add(viewMenu);
        setHelpMenu(helpMenu);
    }
    private void addMenuItem(Menu menu,String commandTitle,Command command) {
        MenuItem newMenuItem = mkMenuItem(commandTitle);
        menu.add(newMenuItem);
        newMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                executeCommand(command);
            }
        });
    }
    @Override
    public void executeCommand(Command command)
    {
        command.execute();
    }
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
