package presentation;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slides.Slide;
import viewers.SlideViewerComponent;

public class PresentationTest {

    private Presentation presentation;
    private SlideViewerComponent mockViewerComponent;
    private Slide slide1, slide2;

    @BeforeEach
    void setUp() {
        mockViewerComponent = mock(SlideViewerComponent.class);
        presentation = new Presentation(mockViewerComponent);
        slide1 = new Slide();
        slide2 = new Slide();
        presentation.append(slide1);
        presentation.append(slide2);
    }

    @Test
    void testGetSize() {
        assertEquals(2, presentation.getSize(), "Should return correct number of slides.");
    }

    @Test
    void testGetAndSetTitle() {
        String title = "Test Presentation";
        presentation.setTitle(title);
        assertEquals(title, presentation.getTitle(), "Should set and return the correct title.");
    }

    @Test
    void testGetCurrentSlide() {
        presentation.setSlideNumber(0);
        assertSame(slide1, presentation.getCurrentSlide(), "Should return the current slide.");
    }

    @Test
    void testSetSlideNumber() {
        presentation.setSlideNumber(1);
        assertEquals(1, presentation.getSlideNumber(), "Should set the correct slide number.");
        verify(mockViewerComponent).update(eq(presentation), eq(slide2));
    }

    @Test
    void testSetSlideNumberOutOfBounds() {
        presentation.setSlideNumber(10);
        assertEquals(0, presentation.getSlideNumber(), "Should not change slide number if out of bounds.");
    }

    @Test
    void testClear() {
        presentation.clear();
        assertEquals(0, presentation.getSize(), "Should clear all slides.");
        assertNull(presentation.getCurrentSlide(), "Should reset the current slide to null after clear.");
    }

    @Test
    void testNextSlide() {
        presentation.setSlideNumber(0);
        presentation.nextSlide();
        assertSame(slide2, presentation.getCurrentSlide(), "Should move to the next slide.");
        verify(mockViewerComponent).update(eq(presentation), eq(slide2));
    }

    @Test
    void testPrevSlide() {
        presentation.setSlideNumber(1);
        presentation.prevSlide();
        assertSame(slide1, presentation.getCurrentSlide(), "Should move to the previous slide.");
        verify(mockViewerComponent).update(eq(presentation), eq(slide1));
    }

    @Test
    void testAppendSlide() {
        Slide slide3 = new Slide();
        presentation.append(slide3);
        assertEquals(3, presentation.getSize(), "Should append a new slide.");
    }

    @Test
    void testExit() {
        try {
            presentation.exit(0);
            fail("Should throw an exception or exit.");
        } catch (Exception e) {
            assertNotNull(e, "Expected an exception when exiting.");
        }
    }
}
