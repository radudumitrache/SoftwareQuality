package main.controller.commands;

import main.controller.Command;
import main.presentation.Presentation;

import java.awt.*;

public class NewFileCommand  extends Command {
    private final Frame frame;

    public NewFileCommand(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        this.presentation.clear();
        this.frame.repaint();
    }
}
