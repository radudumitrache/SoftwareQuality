package style.builders;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import style.types.Style;

class ConcreteStyleBuilderTest {
    private StyleBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new ConcreteStyleBuilder();  // Assuming there's an implementing class
    }

    @Test
    void testSetIndent() {
        builder.setIndent(10);
        Style style = builder.createStyle();
        assertEquals(10, style.getIndent(), "Indent should be set to 10.");
    }

    @Test
    void testSetColor() {
        Color testColor = Color.BLUE;
        builder.setColor(testColor);
        Style style = builder.createStyle();
        assertEquals(testColor, style.getColor(), "Color should be set to BLUE.");
    }

    @Test
    void testSetFontSize() {
        builder.setFontSize(16);
        Style style = builder.createStyle();
        assertEquals(16, style.getFontSize(), "Font size should be set to 16.");
    }

    @Test
    void testSetLeading() {
        builder.setLeading(20);
        Style style = builder.createStyle();
        assertEquals(20, style.getLeading(), "Leading should be set to 20.");
    }

    @Test
    void testCreateStyle() {
        builder.setIndent(5);
        builder.setColor(Color.RED);
        builder.setFontSize(14);
        builder.setLeading(18);
        Style style = builder.createStyle();

        assertAll("Style should incorporate all set properties",
                () -> assertEquals(5, style.getIndent(), "Indent should match"),
                () -> assertEquals(Color.RED, style.getColor(), "Color should match"),
                () -> assertEquals(14, style.getFontSize(), "Font size should match"),
                () -> assertEquals(18, style.getLeading(), "Leading should match")
        );
    }
}