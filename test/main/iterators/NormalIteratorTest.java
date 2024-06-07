package main.iterators;

import main.slides.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NormalIteratorTest {

    private NormalIterator normalIterator;
    private List<Slide> slides;

    @BeforeEach
    void setUp() {
        slides = new ArrayList<>();
        slides.add(new Slide());
        slides.add(new Slide());
        slides.add(new Slide());
        normalIterator = new NormalIterator(slides);
    }

    @Test
    void getNext() {
        assertEquals(slides.get(0), normalIterator.getNext(), "First call to getNext should return the first slide because initial position is -1");
        assertEquals(slides.get(1), normalIterator.getNext(), "getNext should return the second slide when current position is 0");
        assertEquals(slides.get(2), normalIterator.getNext(), "getNext should return the third slide when current position is 1");
        assertNull(normalIterator.getNext(), "getNext should return null when there are no more slides");
    }

    @Test
    void hasMore() {
        assertTrue(normalIterator.hasMore(), "hasMore should return true when initial position is -1 and there are slides");
        normalIterator.setPosition(2);
        assertFalse(normalIterator.hasMore(), "hasMore should return false when current position is at the last slide");
    }

    @Test
    void getPrevious() {
        normalIterator.setPosition(1);
        assertEquals(slides.get(0), normalIterator.getPrevious(), "getPrevious should return the first slide when current position is 1");
        normalIterator.setPosition(2);
        assertEquals(slides.get(1), normalIterator.getPrevious(), "getPrevious should return the second slide when current position is 2");
        normalIterator.setPosition(0);
        assertNull(normalIterator.getPrevious(), "getPrevious should return null when current position is 0");
    }

    @Test
    void getCurrent() {
        assertNull(normalIterator.getCurrent(), "getCurrent should return null when initial position is -1");
        normalIterator.setPosition(0);
        assertEquals(slides.get(0), normalIterator.getCurrent(), "getCurrent should return the first slide when current position is 0");
        normalIterator.setPosition(1);
        assertEquals(slides.get(1), normalIterator.getCurrent(), "getCurrent should return the second slide when current position is 1");
        normalIterator.setPosition(2);
        assertEquals(slides.get(2), normalIterator.getCurrent(), "getCurrent should return the third slide when current position is 2");
    }

    @Test
    void setPosition() {
        normalIterator.setPosition(1);
        assertEquals(1, normalIterator.getPosition(), "setPosition should correctly set the position to 1");
        normalIterator.setPosition(2);
        assertEquals(2, normalIterator.getPosition(), "setPosition should correctly set the position to 2");
        assertThrows(IndexOutOfBoundsException.class, () -> normalIterator.setPosition(3), "setPosition should throw IndexOutOfBoundsException for invalid index");
    }

    @Test
    void getPosition() {
        assertEquals(-1, normalIterator.getPosition(), "Initial position should be -1");
        normalIterator.setPosition(0);
        assertEquals(0, normalIterator.getPosition(), "getPosition should return 0 after setting position to 0");
        normalIterator.setPosition(1);
        assertEquals(1, normalIterator.getPosition(), "getPosition should return 1 after setting position to 1");
    }
}
