package main.slides.itemCreators;

import main.slides.Slide;
import main.slides.itemTypes.SlideItem;
import main.slides.itemTypes.TextItem;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextItemCreatorTest {

    private TextItemCreator textItemCreator;
    private Slide mockSlide;

    @BeforeEach
    void setUp() {
        textItemCreator = new TextItemCreator();
        mockSlide = new Slide();
    }

    @Test
    void createItemSlide() {
        SlideItem slideItem = textItemCreator.createItemSlide();
        assertNotNull(slideItem, "SlideItem should not be null");
        assertTrue(slideItem instanceof TextItem, "SlideItem should be an instance of TextItem");
        assertEquals("No Text Given", ((TextItem) slideItem).getText(), "Text should be 'No Text Given'");
        assertEquals(StyleType.STYLELEVEL0, ((TextItem) slideItem).getStyleType(), "StyleType should be STYLELEVEL0");
    }

    @Test
    void testCreateItemSlide() {
        SlideItem slideItem = textItemCreator.createItemSlide(StyleType.STYLELEVEL1, "Test Text");
        assertNotNull(slideItem, "SlideItem should not be null");
        assertTrue(slideItem instanceof TextItem, "SlideItem should be an instance of TextItem");
        assertEquals("Test Text", ((TextItem) slideItem).getText(), "Text should be 'Test Text'");
        assertEquals(StyleType.STYLELEVEL1, ((TextItem) slideItem).getStyleType(), "StyleType should be STYLELEVEL1");
    }

    @Test
    void createIemAndAddToSlide() {
        textItemCreator.createIemAndAddToSlide(StyleType.STYLELEVEL1, "Test Text", mockSlide);
        assertEquals(1, mockSlide.getSize(), "Slide should contain one item");
        SlideItem slideItem = mockSlide.getSlideItem(0);
        assertNotNull(slideItem, "SlideItem should not be null");
        assertTrue(slideItem instanceof TextItem, "SlideItem should be an instance of TextItem");
        assertEquals("Test Text", ((TextItem) slideItem).getText(), "Text should be 'Test Text'");
        assertEquals(StyleType.STYLELEVEL1, ((TextItem) slideItem).getStyleType(), "StyleType should be STYLELEVEL1");
    }

    @Test
    void createItemsAndAddToSlide() {
        String[] textList = {"Text1", "Text2", "Text3"};
        StyleType[] styleList = {StyleType.STYLELEVEL1, StyleType.STYLELEVEL2, StyleType.STYLELEVEL3};
        textItemCreator.createItemsAndAddToSlide(textList, styleList, mockSlide);
        assertEquals(3, mockSlide.getSize(), "Slide should contain three items");
        for (int i = 0; i < textList.length; i++) {
            SlideItem slideItem = mockSlide.getSlideItem(i);
            assertNotNull(slideItem, "SlideItem should not be null");
            assertTrue(slideItem instanceof TextItem, "SlideItem should be an instance of TextItem");
            assertEquals(textList[i], ((TextItem) slideItem).getText(), "Text should be '" + textList[i] + "'");
            assertEquals(styleList[i], ((TextItem) slideItem).getStyleType(), "StyleType should be " + styleList[i]);
        }
    }

    @Test
    void createItemsAndAddToSlide_InvalidInput() {
        String[] textList = {"Text1", "Text2"};
        StyleType[] styleList = {StyleType.STYLELEVEL1};
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                textItemCreator.createItemsAndAddToSlide(textList, styleList, mockSlide)
        );
        assertEquals("Please provide suitable", exception.getMessage(), "Exception message should match");
    }
}
