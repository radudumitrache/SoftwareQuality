package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Frame;

import presentation.Presentation;
import javax.swing.JOptionPane;

public class MenuControllerTest {

    private Frame frameMock;
    private Presentation presentationMock;
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        frameMock = mock(Frame.class);
        presentationMock = mock(Presentation.class);
        menuController = new MenuController(frameMock, presentationMock);
    }

    @Test
    void testLoadPresentation() {
        // Trigger the load presentation action
        menuController.mkMenuItem(MenuController.OPEN).getActionListeners()[0].actionPerformed(null);
        // Verify that the presentation was cleared and repainted
        verify(presentationMock, times(1)).clear();
        verify(frameMock, times(1)).repaint();
    }

    @Test
    void testNewPresentationClearsData() {
        menuController.mkMenuItem(MenuController.NEW).getActionListeners()[0].actionPerformed(null);
        verify(presentationMock, times(1)).clear();
        verify(frameMock, times(1)).repaint();
    }
}
