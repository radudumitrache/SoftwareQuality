package slides.itemCreators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slides.Slide;
import slides.itemTypes.BitmapItem;
import slides.itemTypes.SlideItem;
import style.types.StyleType;

public class BitmapItemCreatorTest {

    private BitmapItemCreator itemCreator;
    private Slide mockSlide;

    @BeforeEach
    void setUp() {
        itemCreator = new BitmapItemCreator();
        mockSlide = mock(Slide.class);
    }

    @Test
    void testCreateItemSlideDefault() {
        SlideItem result = itemCreator.createItemSlide();
        assertNotNull(result);
        assertTrue(result instanceof BitmapItem);
        assertEquals("default_image_name", ((BitmapItem)result).getName());
        assertEquals(StyleType.STYLELEVEL1, ((BitmapItem)result).getStyle());
    }

    @Test
    void testCreateItemSlideWithParameters() {
        String imageName = "test_image.png";
        SlideItem result = itemCreator.createItemSlide(StyleType.STYLELEVEL2, imageName);
        assertNotNull(result);
        assertTrue(result instanceof BitmapItem);
        assertEquals(imageName, ((BitmapItem)result).getName());
        assertEquals(StyleType.STYLELEVEL2, ((BitmapItem)result).getStyle());
    }

    @Test
    void testCreateItemAndAddToSlide() {
        String imageName = "add_image.png";
        itemCreator.createItemAndAddToSlide(StyleType.STYLELEVEL1, imageName, mockSlide);
        verify(mockSlide, times(1)).append(any(BitmapItem.class));
    }

    @Test
    void testCreateMultipleItemsAndAppendToSlide() {
        String[] names = {"image1.png", "image2.png"};
        StyleType[] styles = {StyleType.STYLELEVEL1, StyleType.STYLELEVEL2};
        itemCreator.createMultipleItemsAndAppendToSlide(names, styles, mockSlide);
        verify(mockSlide, times(names.length)).append(any(BitmapItem.class));
    }

    @Test
    void testCreateMultipleItemsAndAppendToSlideMismatch() {
        String[] names = {"only_one.png"};
        StyleType[] styles = {StyleType.STYLELEVEL1, StyleType.STYLELEVEL2}; // Mismatch in length
        assertThrows(IllegalArgumentException.class, () ->
                itemCreator.createMultipleItemsAndAppendToSlide(names, styles, mockSlide));
    }
}
