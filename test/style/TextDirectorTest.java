package style;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import style.builders.*;
import style.types.*;

import java.awt.*;

public class TextDirectorTest {

    private TextDirector director;
    private Stylelevel0Builder mockStyle0Builder;
    private Style mockStyle0;


    @BeforeEach
    void setUp() {
        // Resetting Singleton instance for testing (use reflection or restructure for real tests)
        TextDirector instance = TextDirector.getInstance();
        mockStyle0Builder = mock(Stylelevel0Builder.class);
        mockStyle0 = mock(Style.class);

        when(mockStyle0Builder.createStyle()).thenReturn(mockStyle0);
    }

    @Test
    void testSingleton() {
        TextDirector firstInstance = TextDirector.getInstance();
        TextDirector secondInstance = TextDirector.getInstance();
        assertSame(firstInstance, secondInstance, "Should return the same instance for singleton pattern");
    }

    @Test
    void testConstructStyles() {
        director.constructStyles();
        verify(mockStyle0Builder, times(1)).createStyle();
        Style retrievedStyle = director.getStyle(StyleType.STYLELEVEL0);
        assertSame(mockStyle0, retrievedStyle, "Should retrieve the same style object as constructed");
    }

    @Test
    void testGetStyleUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> director.getStyle(null), "Should throw for unknown style types");
    }
}
