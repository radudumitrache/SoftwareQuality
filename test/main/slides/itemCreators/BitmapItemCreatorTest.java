package main.slides.itemCreators;

import main.slides.Slide;
import main.slides.itemTypes.BitmapItem;
import main.slides.itemTypes.SlideItem;
import main.style.types.Style;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BitmapItemCreatorTest {

    private BitmapItemCreator bitmapItemCreator;
    private Slide mockSlide;
    private Style mockStyle;

    @BeforeEach
    void setUp() {
        bitmapItemCreator = new BitmapItemCreator();
        mockSlide = new Slide();

        // Mock the Style class
        mockStyle = mock(Style.class);
        when(mockStyle.toString()).thenReturn("mockStyle");
    }

    @Test
    void createItemSlide() {
        SlideItem slideItem = bitmapItemCreator.createItemSlide();
        assertNotNull(slideItem, "SlideItem should not be null");
        assertTrue(slideItem instanceof BitmapItem, "SlideItem should be an instance of BitmapItem");
        assertEquals("default_image_name", ((BitmapItem) slideItem).getName(), "Image name should be 'default_image_name'");
    }

    @Test
    void testCreateItemSlide() {
        SlideItem slideItem = bitmapItemCreator.createItemSlide(StyleType.STYLELEVEL2, "TestImage.png");
        assertNotNull(slideItem, "SlideItem should not be null");
        assertTrue(slideItem instanceof BitmapItem, "SlideItem should be an instance of BitmapItem");
        assertEquals("TestImage.png", ((BitmapItem) slideItem).getName(), "Image name should be 'TestImage.png'");
    }

    @Test
    void createItemAndAddToSlide() {
        bitmapItemCreator.createItemAndAddToSlide(StyleType.STYLELEVEL2, "TestImage.png", mockSlide);
        assertEquals(1, mockSlide.getSize(), "Slide should contain one item");
        SlideItem slideItem = mockSlide.getSlideItem(0);
        assertNotNull(slideItem, "SlideItem should not be null");
        assertTrue(slideItem instanceof BitmapItem, "SlideItem should be an instance of BitmapItem");
        assertEquals("TestImage.png", ((BitmapItem) slideItem).getName(), "Image name should be 'TestImage.png'");
    }

    @Test
    void createMultipleItemsAndAppendToSlide() {
        String[] imageNames = {"Image1.png", "Image2.png", "Image3.png"};
        StyleType[] imageStyles = {StyleType.STYLELEVEL1, StyleType.STYLELEVEL2, StyleType.STYLELEVEL3};
        bitmapItemCreator.createMultipleItemsAndAppendToSlide(imageNames, imageStyles, mockSlide);
        assertEquals(3, mockSlide.getSize(), "Slide should contain three items");
        for (int i = 0; i < imageNames.length; i++) {
            SlideItem slideItem = mockSlide.getSlideItem(i);
            assertNotNull(slideItem, "SlideItem should not be null");
            assertTrue(slideItem instanceof BitmapItem, "SlideItem should be an instance of BitmapItem");
            assertEquals(imageNames[i], ((BitmapItem) slideItem).getName(), "Image name should be '" + imageNames[i] + "'");
        }
    }

    @Test
    void createMultipleItemsAndAppendToSlide_InvalidInput() {
        String[] imageNames = {"Image1.png", "Image2.png"};
        StyleType[] imageStyles = {StyleType.STYLELEVEL1};
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                bitmapItemCreator.createMultipleItemsAndAppendToSlide(imageNames, imageStyles, mockSlide)
        );
        assertEquals("Please provide suitable", exception.getMessage(), "Exception message should match");
    }
}
