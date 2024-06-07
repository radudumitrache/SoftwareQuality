package iterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import slides.Slide;
import java.util.Arrays;
import java.util.List;

public class NormalIteratorTest {

    private NormalIterator iterator;
    private List<Slide> slides;

    @BeforeEach
    void setUp() {
        Slide slide1 = new Slide();  // Assume Slide is a simple class
        Slide slide2 = new Slide();
        Slide slide3 = new Slide();
        slides = Arrays.asList(slide1, slide2, slide3);
        iterator = new NormalIterator(slides);
    }

    @Test
    void testGetNext() {
        iterator.setPosition(0);
        assertSame(slides.get(1), iterator.getNext(), "Should return the next slide.");
    }

    @Test
    void testGetNextAtEnd() {
        iterator.setPosition(2);  // Set to the last slide
        assertNull(iterator.getNext(), "Should return null when no more slides are available.");
    }

    @Test
    void testGetPrevious() {
        iterator.setPosition(1);
        assertSame(slides.get(0), iterator.getPrevious(), "Should return the previous slide.");
    }

    @Test
    void testGetPreviousAtStart() {
        iterator.setPosition(0);
        assertNull(iterator.getPrevious(), "Should return null when no previous slides are available.");
    }

    @Test
    void testGetCurrent() {
        iterator.setPosition(1);
        assertSame(slides.get(1), iterator.getCurrent(), "Should return the current slide.");
    }

    @Test
    void testGetCurrentOutOfBounds() {
        iterator.setPosition(-1);
        assertNull(iterator.getCurrent(), "Should return null when position is out of bounds.");
    }

    @Test
    void testHasMore() {
        iterator.setPosition(1);
        assertTrue(iterator.hasMore(), "Should return true if there are more slides after the current one.");
    }

    @Test
    void testHasNoMore() {
        iterator.setPosition(2);  // Last slide
        assertFalse(iterator.hasMore(), "Should return false if there are no more slides after the current one.");
    }

    @Test
    void testSetPositionWithinBounds() {
        assertDoesNotThrow(() -> iterator.setPosition(1), "Should not throw when setting position within bounds.");
    }

    @Test
    void testSetPositionOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> iterator.setPosition(-1), "Should throw when setting position out of bounds.");
        assertThrows(IndexOutOfBoundsException.class, () -> iterator.setPosition(slides.size()), "Should also throw when position is equal to size of the slides list.");
    }
}