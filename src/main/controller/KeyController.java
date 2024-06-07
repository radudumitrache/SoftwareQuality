package main.controller;

import main.controller.commands.GoToNextSlideCommand;
import main.controller.commands.GoToPrevSlideCommand;
import main.controller.commands.QuitAppCommand;
import main.presentation.Presentation;

import javax.sound.midi.Receiver;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyController extends KeyAdapter implements CommandReceiver
{
    private Presentation presentation; // Commands are given to the presentation
    private final GoToNextSlideCommand nextSlideCommand;
    private final GoToPrevSlideCommand previousSlideCommand;
    private final QuitAppCommand quitCommand;
    public KeyController(Presentation p)
    {
        this.presentation = p;
        this.nextSlideCommand = new GoToNextSlideCommand(this.presentation);
        this.previousSlideCommand = new GoToPrevSlideCommand(this.presentation);
        this.quitCommand = new QuitAppCommand(this.presentation);
    }
    @Override
    public void executeCommand(Command command)
    {
        command.execute();
    }
    public void keyPressed(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                executeCommand(nextSlideCommand);
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                executeCommand(previousSlideCommand);
                break;
            case 'q':
            case 'Q':
                executeCommand(quitCommand);
                break; // Probably never reached!!
            default:
                break;
        }
    }
}
