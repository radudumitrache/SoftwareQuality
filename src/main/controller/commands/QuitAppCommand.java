package main.controller.commands;

import main.controller.Command;
import main.presentation.Presentation;

public class QuitAppCommand extends Command {
    public QuitAppCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        System.exit(0);
    }
}
