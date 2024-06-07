package main.controller.commands;

import main.controller.AboutBox;
import main.controller.Command;
import main.presentation.Presentation;

import java.awt.*;

public class ShowAboutBoxCommand extends Command {
    private final Frame frame;

    public ShowAboutBoxCommand(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        AboutBox.show(frame);
    }
}
