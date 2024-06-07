package main.controller.commands;

import main.accessors.Accessor;
import main.accessors.XMLAccessor;
import main.controller.Command;
import main.presentation.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenFileCommand extends Command {
    private final Frame frame;

    public OpenFileCommand(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {

            xmlAccessor.loadFile(presentation, "test.xml");
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(this.frame, "IO Exception: " + exc, "Load Error", JOptionPane.ERROR_MESSAGE);
        }
        this.frame.repaint();

    }
}
