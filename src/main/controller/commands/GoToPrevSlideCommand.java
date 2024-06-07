package main.controller.commands;

import main.controller.Command;
import main.presentation.Presentation;

public class GoToPrevSlideCommand extends Command {
    public GoToPrevSlideCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        this.presentation.prevSlide();
    }
}
