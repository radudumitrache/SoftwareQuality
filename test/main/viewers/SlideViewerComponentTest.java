package main.viewers;

import main.presentation.Presentation;
import main.slides.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideViewerComponentTest
{
    private SlideViewerComponent slideViewerComponent;
    private Presentation mockPresentation;
    private Slide mockSlide;
    private JFrame mockFrame;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockSlide = mock(Slide.class);
        mockFrame = mock(JFrame.class);
        slideViewerComponent = new SlideViewerComponent(mockPresentation, mockFrame);
    }

    @Test
    void getPreferredSize() {
        Dimension expectedSize = new Dimension(Slide.WIDTH, Slide.HEIGHT);
        assertEquals(expectedSize, slideViewerComponent.getPreferredSize());
    }

    @Test
    void paintComponent() {
        Graphics g = mock(Graphics.class);

        // Define behavior of presentation and slide
        when(mockPresentation.getSlideNumber()).thenReturn(1);
        when(mockPresentation.getSize()).thenReturn(10);
       // when(mockSlide.draw(any(Graphics.class), any(Rectangle.class), any(JComponent.class))).thenReturn(null);

        slideViewerComponent.update(mockPresentation, mockSlide);

        // Call the paintComponent method
        slideViewerComponent.paintComponent(g);

        // Verify that methods are called on the Graphics object
        verify(g).setColor(Color.white);
        verify(g).fillRect(0, 0, slideViewerComponent.getSize().width, slideViewerComponent.getSize().height);
        verify(g).setFont(any(Font.class));
        verify(g).setColor(Color.black);
        verify(g).drawString("Slides.Slide 2 of 10", 1100, 20);
        verify(mockSlide).draw(any(Graphics.class), any(Rectangle.class), eq(slideViewerComponent));
    }

    @Test
    void update() {
        // Define behavior of presentation
        when(mockPresentation.getTitle()).thenReturn("Test Presentation");

        // Update the slideViewerComponent with a new presentation and slide
        slideViewerComponent.update(mockPresentation, mockSlide);

        // Verify the internal state of the slideViewerComponent
        assertEquals(mockPresentation, slideViewerComponent.presentation);
        assertEquals(mockSlide, slideViewerComponent.slide);
        verify(mockFrame).setTitle("Test Presentation");

        // Update the slideViewerComponent with null data
        slideViewerComponent.update(mockPresentation, null);

        // Verify the slide is set to null
        assertNull(slideViewerComponent.slide);
    }
}
