package main.controller;

import main.controller.commands.GoToNextSlideCommand;
import main.controller.commands.GoToPrevSlideCommand;
import main.controller.commands.QuitAppCommand;
import main.presentation.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KeyControllerTest {

    private KeyController keyController;
    private Presentation mockPresentation;
    private GoToNextSlideCommand mockNextSlideCommand;
    private GoToPrevSlideCommand mockPrevSlideCommand;
    private QuitAppCommand mockQuitCommand;

    @BeforeEach
    void setUp() throws Exception {
        mockPresentation = mock(Presentation.class);
        mockNextSlideCommand = mock(GoToNextSlideCommand.class);
        mockPrevSlideCommand = mock(GoToPrevSlideCommand.class);
        mockQuitCommand = mock(QuitAppCommand.class);

        keyController = new KeyController(mockPresentation);

        // Use reflection to replace the private command fields with mocks
        java.lang.reflect.Field nextSlideField = KeyController.class.getDeclaredField("nextSlideCommand");
        nextSlideField.setAccessible(true);
        nextSlideField.set(keyController, mockNextSlideCommand);

        java.lang.reflect.Field prevSlideField = KeyController.class.getDeclaredField("previousSlideCommand");
        prevSlideField.setAccessible(true);
        prevSlideField.set(keyController, mockPrevSlideCommand);

        java.lang.reflect.Field quitField = KeyController.class.getDeclaredField("quitCommand");
        quitField.setAccessible(true);
        quitField.set(keyController, mockQuitCommand);
    }

    @Test
    void executeCommand() {
        Command mockCommand = mock(Command.class);
        keyController.executeCommand(mockCommand);
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void keyPressed() {
        KeyEvent nextSlideKeyEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, KeyEvent.CHAR_UNDEFINED);
        KeyEvent prevSlideKeyEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, KeyEvent.CHAR_UNDEFINED);
        KeyEvent quitKeyEvent = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q, KeyEvent.CHAR_UNDEFINED);

        keyController.keyPressed(nextSlideKeyEvent);
        verify(mockNextSlideCommand, times(1)).execute();

        keyController.keyPressed(prevSlideKeyEvent);
        verify(mockPrevSlideCommand, times(1)).execute();

        keyController.keyPressed(quitKeyEvent);
        verify(mockQuitCommand, times(1)).execute();
    }
}
