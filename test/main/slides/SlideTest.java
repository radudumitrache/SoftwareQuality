package main.slides;

import main.slides.itemTypes.SlideItem;
import main.slides.itemTypes.TextItem;
import main.style.TextDirector;
import main.style.types.Style;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideTest {

    private Slide slide;
    private TextDirector mockTextDirector;
    private Style mockStyle;

    @BeforeEach
    void setUp() {
        slide = new Slide();
        mockTextDirector = mock(TextDirector.class);
        mockStyle = mock(Style.class);

        when(mockTextDirector.getStyle(any(StyleType.class))).thenReturn(mockStyle);
        TextDirector.setInstance(mockTextDirector);

        // Set up mock style properties
        when(mockStyle.getFont(anyFloat())).thenReturn(mock(Font.class));
        when(mockStyle.getIndent()).thenReturn(10);
        when(mockStyle.getLeading()).thenReturn(10);
        when(mockStyle.toString()).thenReturn("mockStyle");
    }

    @Test
    void append() {
        SlideItem slideItem = mock(SlideItem.class);
        slide.append(slideItem);
        assertEquals(1, slide.getSize(), "Slide size should be 1 after appending one item");
        assertEquals(slideItem, slide.getSlideItem(0), "Appended slide item should match the retrieved item");
    }

    @Test
    void getTitle() {
        slide.setTitle("Test Title");
        assertEquals("Test Title", slide.getTitle(), "Title should be 'Test Title'");
    }

    @Test
    void setTitle() {
        slide.setTitle("New Title");
        assertEquals("New Title", slide.getTitle(), "Title should be 'New Title'");
    }

    @Test
    void testAppend() {
        slide.append(StyleType.STYLELEVEL1, "Test Message");
        SlideItem item = slide.getSlideItem(0);
        assertTrue(item instanceof TextItem, "Appended item should be of type TextItem");
        assertEquals(StyleType.STYLELEVEL1, ((TextItem) item).getStyleType(), "StyleType should be STYLELEVEL1");
        assertEquals("Test Message", ((TextItem) item).getText(), "Text should be 'Test Message'");
    }

    @Test
    void getSlideItem() {
        SlideItem slideItem = mock(SlideItem.class);
        slide.append(slideItem);
        assertEquals(slideItem, slide.getSlideItem(0), "Retrieved slide item should match the appended item");
    }

    @Test
    void getSlideItems() {
        SlideItem slideItem1 = mock(SlideItem.class);
        SlideItem slideItem2 = mock(SlideItem.class);
        slide.append(slideItem1);
        slide.append(slideItem2);
        Vector<SlideItem> items = slide.getSlideItems();
        assertEquals(2, items.size(), "Slide should contain two items");
        assertEquals(slideItem1, items.elementAt(0), "First item should match the first appended item");
        assertEquals(slideItem2, items.elementAt(1), "Second item should match the second appended item");
    }

    @Test
    void getSize() {
        assertEquals(0, slide.getSize(), "Initial slide size should be 0");
        slide.append(mock(SlideItem.class));
        assertEquals(1, slide.getSize(), "Slide size should be 1 after appending one item");
    }
}
