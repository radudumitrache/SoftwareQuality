package main.accessors;

import main.presentation.Presentation;
import main.slides.Slide;
import main.slides.itemTypes.TextItem;
import main.style.types.StyleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class XMLAccessorTest {

    private XMLAccessor xmlAccessor;
    private Presentation mockPresentation;

    @BeforeEach
    void setUp() {
        xmlAccessor = new XMLAccessor();
        mockPresentation = mock(Presentation.class);
    }

    @Test
    void loadFile() throws Exception {
        // Prepare the mock presentation
        doNothing().when(mockPresentation).setTitle(anyString());
        doNothing().when(mockPresentation).append(any(Slide.class));

        // Call the method with a test XML file
        xmlAccessor.loadFile(mockPresentation, "testfiles/test_presentation.xml");

        // Verify that methods were called correctly
        verify(mockPresentation, times(1)).setTitle(anyString());
        verify(mockPresentation, atLeastOnce()).append(any(Slide.class));
    }

    @Test
    void loadSlideItem() {
        Slide mockSlide = mock(Slide.class);
        Element mockElement = mock(Element.class);
        NamedNodeMap mockAttributes = mock(NamedNodeMap.class);
        Node mockLevelNode = mock(Node.class);
        Node mockKindNode = mock(Node.class);

        when(mockElement.getAttributes()).thenReturn(mockAttributes);
        when(mockAttributes.getNamedItem("level")).thenReturn(mockLevelNode);
        when(mockAttributes.getNamedItem("kind")).thenReturn(mockKindNode);
        when(mockLevelNode.getTextContent()).thenReturn("1");
        when(mockKindNode.getTextContent()).thenReturn("text");
        when(mockElement.getTextContent()).thenReturn("Sample Text");

        xmlAccessor.loadSlideItem(mockSlide, mockElement);

        verify(mockSlide, times(1)).append(any(TextItem.class));
    }

    @Test
    void saveFile() throws Exception {
        Presentation presentation = new Presentation();
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        TextItem textItem = new TextItem(StyleType.STYLELEVEL1, "Test Text");
        slide.append(textItem);
        presentation.append(slide);

        XMLAccessor xmlAccessor = new XMLAccessor();
        String filename = "testfiles/test_save.xml";
        xmlAccessor.saveFile(presentation, filename);

        File file = new File(filename);
        assertTrue(file.exists(), "File should be created");

        // Verify the content of the file
        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.contains("<slide>"));
        assertTrue(lines.contains("<title>Test Slide</title>"));
        assertTrue(lines.contains("<item kind=\"text\" style=\"default_style\">Test Text</item>"));
        assertTrue(lines.contains("</slide>"));
    }
}
