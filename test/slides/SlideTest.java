package slides;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slides.itemTypes.SlideItem;
import slides.itemTypes.TextItem;
import style.types.StyleType;

public class SlideTest {

    private Slide slide;

    @BeforeEach
    void setUp() {
        slide = new Slide();
    }

    @Test
    void testAppendAndGetItems() {
        SlideItem item1 = new TextItem(StyleType.STYLELEVEL1, "Hello");
        SlideItem item2 = new TextItem(StyleType.STYLELEVEL2, "World");

        slide.append(item1);
        slide.append(item2);

        assertAll("Verify all items are added and retrievable",
                () -> assertEquals(2, slide.getSize(), "Slide should have two items"),
                () -> assertSame(item1, slide.getSlideItem(0), "First item should match"),
                () -> assertSame(item2, slide.getSlideItem(1), "Second item should match")
        );
    }

    @Test
    void testSetTitleAndGetTitle() {
        String title = "Test Slide";
        slide.setTitle(title);
        assertEquals(title, slide.getTitle(), "Slide title should be set and retrieved correctly");
    }

    @Test
    void testAppendTextItemDirectly() {
        slide.append(StyleType.STYLELEVEL1, "Direct Text");
        SlideItem result = slide.getSlideItem(0);
        assertTrue(result instanceof TextItem, "Item should be a TextItem");
        assertEquals("Direct Text", ((TextItem)result).getText(), "Text should match input");
    }
}
