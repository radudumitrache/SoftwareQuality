package main.controller;

import main.presentation.Presentation;

public abstract class Command {
    public Presentation presentation;

    public Command(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public abstract void execute();
}
