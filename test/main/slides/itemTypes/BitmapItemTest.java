package main.slides.itemTypes;

import main.style.TextDirector;
import main.style.types.Style;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BitmapItemTest
{

    private BitmapItem bitmapItem;
    private TextDirector mockTextDirector;
    private Style mockStyle;

    @BeforeEach
    void setUp()
    {
        mockTextDirector = mock(TextDirector.class);
        mockStyle = mock(Style.class);
        when(mockTextDirector.getStyle(any(StyleType.class))).thenReturn(mockStyle);

        // Replace the singleton instance with our mock
        TextDirector.setInstance(mockTextDirector);

        // Initialize BitmapItem
        bitmapItem = new BitmapItem(StyleType.STYLELEVEL1, "TestImage.jpg");

        // Set up mock style properties
        when(mockStyle.getIndent()).thenReturn(10);
        when(mockStyle.getLeading()).thenReturn(10);
        when(mockStyle.toString()).thenReturn("mockStyle");
    }

    @Test
    void getName()
    {
        assertEquals("TestImage.jpg", bitmapItem.getName(), "Image name should be 'TestImage.jpg'");
    }

    @Test
    void getBoundingBox()
    {
        BufferedImage image = mock(BufferedImage.class);
        when(image.getWidth(any(ImageObserver.class))).thenReturn(100);
        when(image.getHeight(any(ImageObserver.class))).thenReturn(100);

        bitmapItem = new BitmapItem(StyleType.STYLELEVEL1, "TestImage.jpg");
        Graphics g = mock(Graphics.class);
        ImageObserver observer = mock(ImageObserver.class);

        Rectangle bbox = bitmapItem.getBoundingBox(g, observer, 1.0f, mockStyle);

        assertNotNull(bbox, "BoundingBox should not be null");
        assertEquals(10, bbox.x, "BoundingBox x should match style indent");
        assertEquals(0, bbox.y, "BoundingBox y should be 0");
        assertEquals(100, bbox.width, "BoundingBox width should match image width");
        assertEquals(110, bbox.height, "BoundingBox height should match image height plus leading");
    }

    @Test
    void draw()
    {
        BufferedImage image = mock(BufferedImage.class);
        when(image.getWidth(any(ImageObserver.class))).thenReturn(100);
        when(image.getHeight(any(ImageObserver.class))).thenReturn(100);

        bitmapItem = new BitmapItem(StyleType.STYLELEVEL1, "TestImage.jpg");
        Graphics g = mock(Graphics.class);
        ImageObserver observer = mock(ImageObserver.class);

        bitmapItem.draw(10, 10, 1.0f, g, mockStyle, observer);

        verify(g, atLeastOnce()).drawImage(any(BufferedImage.class), eq(20), eq(20), eq(100), eq(100), eq(observer));
    }

    @Test
    void testToString()
    {
        assertEquals("Slides.BitmapItem[TestImage.jpg]", bitmapItem.toString(), "toString should match expected format");
    }

    @Test
    void getStyle()
    {
        Style style = bitmapItem.getStyle();
        assertNotNull(style, "Style should not be null");
        verify(mockTextDirector, times(1)).getStyle(StyleType.STYLELEVEL1);
    }
}
