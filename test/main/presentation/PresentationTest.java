package main.presentation;

import main.slides.Slide;
import main.viewers.SlideViewerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PresentationTest {

    private Presentation presentation;
    private SlideViewerComponent mockSlideViewerComponent;

    @BeforeEach
    void setUp() {
        mockSlideViewerComponent = mock(SlideViewerComponent.class);
        presentation = new Presentation(mockSlideViewerComponent);
    }

    @Test
    void getSize() {
        assertEquals(0, presentation.getSize(), "Initial presentation size should be 0");
        presentation.append(new Slide());
        assertEquals(1, presentation.getSize(), "Presentation size should be 1 after appending a slide");
    }

    @Test
    void getTitle() {
        assertEquals("", presentation.getTitle(), "Initial title should be an empty string");
        presentation.setTitle("Test Title");
        assertEquals("Test Title", presentation.getTitle(), "Title should be 'Test Title'");
    }

    @Test
    void setTitle() {
        presentation.setTitle("New Title");
        assertEquals("New Title", presentation.getTitle(), "Title should be 'New Title'");
    }

    @Test
    void getSlideViewComponent() {
        assertEquals(mockSlideViewerComponent, presentation.getSlideViewComponent(), "SlideViewerComponent should be the mock component");
    }

    @Test
    void setSlideViewComponent() {
        SlideViewerComponent newComponent = mock(SlideViewerComponent.class);
        presentation.setSlideViewComponent(newComponent);
        assertEquals(newComponent, presentation.getSlideViewComponent(), "SlideViewerComponent should be the new component");
    }

    @Test
    void getCurrentSlide() {
        Slide slide = new Slide();
        presentation.append(slide);
        presentation.setSlideNumber(0);
        assertEquals(slide, presentation.getCurrentSlide(), "Current slide should be the appended slide");
    }

    @Test
    void setSlideNumber() {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.setSlideNumber(1);
        assertEquals(slide2, presentation.getCurrentSlide(), "Current slide should be the second slide");
    }

    @Test
    void getSlideNumber() {
        Slide slide = new Slide();
        presentation.append(slide);
        presentation.setSlideNumber(0);
        assertEquals(0, presentation.getSlideNumber(), "Slide number should be 0");
    }

    @Test
    void clear() {
        Slide slide = new Slide();
        presentation.append(slide);
        assertEquals(1, presentation.getSize(), "Presentation size should be 1 before clearing");
        presentation.clear();
        assertEquals(0, presentation.getSize(), "Presentation size should be 0 after clearing");
    }

    @Test
    void append() {
        Slide slide = new Slide();
        presentation.append(slide);
        assertEquals(1, presentation.getSize(), "Presentation size should be 1 after appending a slide");
        assertEquals(slide, presentation.getSlide(0), "Appended slide should be retrievable");
    }

    @Test
    void getSlide() {
        Slide slide = new Slide();
        presentation.append(slide);
        assertEquals(slide, presentation.getSlide(0), "Retrieved slide should match the appended slide");
        assertNull(presentation.getSlide(1), "Retrieving a non-existent slide should return null");
    }
}