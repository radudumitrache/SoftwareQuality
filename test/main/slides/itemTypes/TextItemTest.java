package main.slides.itemTypes;

import main.style.TextDirector;
import main.style.types.Style;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextItemTest {

    private TextItem textItem;
    private TextDirector mockTextDirector;
    private Style mockStyle;

    @BeforeEach
    void setUp() {
        mockTextDirector = mock(TextDirector.class);
        mockStyle = mock(Style.class);
        when(mockTextDirector.getStyle(any(StyleType.class))).thenReturn(mockStyle);

        // Replace the singleton instance with our mock
        TextDirector.setInstance(mockTextDirector);

        textItem = new TextItem(StyleType.STYLELEVEL1, "Test Text");
    }

    @Test
    void getText() {
        assertEquals("Test Text", textItem.getText(), "Text should be 'Test Text'");
    }

    @Test
    void getStyleType() {
        assertEquals(StyleType.STYLELEVEL1, textItem.getStyleType(), "StyleType should be STYLELEVEL1");
    }

    @Test
    void setStyleType() {
        textItem.setStyleType(StyleType.STYLELEVEL2);
        assertEquals(StyleType.STYLELEVEL2, textItem.getStyleType(), "StyleType should be STYLELEVEL2");
    }

    @Test
    void draw() {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        ImageObserver observer = mock(ImageObserver.class);

        when(mockStyle.getFont(anyFloat())).thenReturn(new Font("Serif", Font.PLAIN, 12));
        when(mockStyle.getColor()).thenReturn(Color.BLACK);
        when(mockStyle.getIndent()).thenReturn(10);
        when(mockStyle.getLeading()).thenReturn(10);

        textItem.draw(10, 10, 1.0f, g2d, mockStyle, observer);

        verify(mockStyle, atLeastOnce()).getFont(anyFloat());
        verify(mockStyle, atLeastOnce()).getColor();
    }

    @Test
    void getBoundingBox() {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        ImageObserver observer = mock(ImageObserver.class);

        when(mockStyle.getFont(anyFloat())).thenReturn(new Font("Serif", Font.PLAIN, 12));
        when(mockStyle.getIndent()).thenReturn(10);
        when(mockStyle.getLeading()).thenReturn(10);

        Rectangle bbox = textItem.getBoundingBox(g2d, observer, 1.0f, mockStyle);

        assertNotNull(bbox, "BoundingBox should not be null");
        assertTrue(bbox.width > 0, "BoundingBox width should be greater than 0");
        assertTrue(bbox.height > 0, "BoundingBox height should be greater than 0");
    }

    @Test
    void getStyle() {
        Style style = textItem.getStyle();
        assertNotNull(style, "Style should not be null");
        verify(mockTextDirector, times(1)).getStyle(StyleType.STYLELEVEL1);
    }

    @Test
    void getTagContent() {
        when(mockStyle.toString()).thenReturn("style");
        String expectedTagContent = "<item kind=\"text\" style=\"style\">Test Text</item>";
        String tagContent = textItem.getTagContent();
        assertEquals(expectedTagContent, tagContent, "Tag content should match expected format");
    }

    @Test
    void testToString() {
        String expectedToString = "Slides.TextItem[STYLELEVEL1,Test Text]";
        assertEquals(expectedToString, textItem.toString(), "toString should match expected format");
    }
}
