package main.controller.commands;

import main.accessors.Accessor;
import main.accessors.XMLAccessor;
import main.controller.Command;
import main.presentation.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SaveFileCommand extends Command {
    private final Frame frame;

    public SaveFileCommand(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        Accessor xmlAccessor = new XMLAccessor();
        try
        {
            xmlAccessor.saveFile(this.presentation, "dump.xml");
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog(this.frame, "IO Exception: " + exc,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
