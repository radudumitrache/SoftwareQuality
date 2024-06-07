package main.style;

import main.style.types.Style;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextDirectorTest {

    private TextDirector textDirector;

    @BeforeEach
    void setUp() {
        textDirector = TextDirector.getInstance();
    }

    @Test
    void getInstance() {
        TextDirector instance1 = TextDirector.getInstance();
        TextDirector instance2 = TextDirector.getInstance();
        assertSame(instance1, instance2, "Instances should be the same (singleton pattern)");
    }

    @Test
    void constructStyles() {
        textDirector.constructStyles();
        assertNotNull(textDirector.getStyle(StyleType.STYLELEVEL0), "Stylelevel0 should be constructed and not null");
        assertNotNull(textDirector.getStyle(StyleType.STYLELEVEL1), "Stylelevel1 should be constructed and not null");
        assertNotNull(textDirector.getStyle(StyleType.STYLELEVEL2), "Stylelevel2 should be constructed and not null");
        assertNotNull(textDirector.getStyle(StyleType.STYLELEVEL3), "Stylelevel3 should be constructed and not null");
    }

    @Test
    void getStyle() {
        textDirector.constructStyles();

        Style stylelevel0 = textDirector.getStyle(StyleType.STYLELEVEL0);
        assertNotNull(stylelevel0, "Stylelevel0 should not be null");

        Style stylelevel1 = textDirector.getStyle(StyleType.STYLELEVEL1);
        assertNotNull(stylelevel1, "Stylelevel1 should not be null");

        Style stylelevel2 = textDirector.getStyle(StyleType.STYLELEVEL2);
        assertNotNull(stylelevel2, "Stylelevel2 should not be null");

        Style stylelevel3 = textDirector.getStyle(StyleType.STYLELEVEL3);
        assertNotNull(stylelevel3, "Stylelevel3 should not be null");

        assertThrows(IllegalArgumentException.class, () -> textDirector.getStyle(null), "Requesting a null style should throw IllegalArgumentException");
    }
}
