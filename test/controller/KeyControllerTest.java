package controller;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import presentation.Presentation;

public class KeyControllerTest {

    private Presentation presentationMock;
    private KeyController keyController;

    @BeforeEach
    void setUp() {
        presentationMock = mock(Presentation.class);
        keyController = new KeyController(presentationMock);
    }

    @Test
    void testNextSlideOnPageDown() {
        KeyEvent pageDown = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, KeyEvent.CHAR_UNDEFINED);
        keyController.keyPressed(pageDown);
        verify(presentationMock, times(1)).nextSlide();
    }

    @Test
    void testPreviousSlideOnPageUp() {
        KeyEvent pageUp = new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, KeyEvent.CHAR_UNDEFINED);
        keyController.keyPressed(pageUp);
        verify(presentationMock, times(1)).prevSlide();
    }
}