package main.controller.commands;

import main.controller.Command;
import main.presentation.Presentation;

public class GoToNextSlideCommand extends Command {
    public GoToNextSlideCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        this.presentation.nextSlide();
    }
}
