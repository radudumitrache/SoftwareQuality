package slides.itemCreators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import slides.itemTypes.TextItem;
import style.types.StyleType;

class TextItemCreatorTest {

    @Test
    void testCreateItemSlideDefault() {
        TextItemCreator creator = new TextItemCreator();
        TextItem item = (TextItem) creator.createItemSlide();
        assertNotNull(item, "Item should not be null");
        assertEquals(StyleType.STYLELEVEL1, item.getStyle(), "Check default style");
        assertEquals("Default Text", item.getText(), "Check default text");
    }

    @Test
    void testCreateItemSlideWithParameters() {
        TextItemCreator creator = new TextItemCreator();
        TextItem item = (TextItem) creator.createItemSlide(StyleType.STYLELEVEL2, "Hello World");
        assertNotNull(item, "Item should not be null");
        assertEquals(StyleType.STYLELEVEL2, item.getStyle(), "Check specified style");
        assertEquals("Hello World", item.getText(), "Check specified text");
    }
}