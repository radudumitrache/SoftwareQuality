package accessors;

import static org.junit.jupiter.api.Assertions.*;

import accessors.XMLAccessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLAccessorTest {
    private XMLAccessor xmlAccessor;

    @BeforeEach
    void setup() {
        xmlAccessor = new XMLAccessor();
    }

    @Test
    void loadValidXMLFile() {
        assertDoesNotThrow(() -> xmlAccessor.loadFile("valid.xml"));
    }

    @Test
    void failToLoadInvalidXMLFile() {
        assertThrows(SAXException.class, () -> xmlAccessor.loadFile("invalid.xml"));
    }

    @Test
    void correctlyExtractsData() {
        xmlAccessor.loadFile("valid.xml");
        assertAll("Data Extraction",
                () -> assertEquals("Expected Title", xmlAccessor.getTitle()),
                () -> assertEquals(5, xmlAccessor.getNumberOfSlides())
        );
    }
}