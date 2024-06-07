package main.controller.commands;

import main.controller.Command;
import main.presentation.Presentation;

public class ExitPresentationCommand extends Command {
    public ExitPresentationCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        this.presentation.exit();
    }
}
