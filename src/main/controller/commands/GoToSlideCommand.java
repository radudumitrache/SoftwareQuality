package main.controller.commands;

import main.controller.Command;
import main.presentation.Presentation;

import javax.swing.*;

public class GoToSlideCommand extends Command {
    public GoToSlideCommand(Presentation presentation) {
        super(presentation);
    }

    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog("Page number?");
        int pageNumber = Integer.parseInt(pageNumberStr);
        this.presentation.setSlideNumber(pageNumber - 1);

    }
}
