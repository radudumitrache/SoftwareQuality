package main.accessors;

import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.slides.itemTypes.BitmapItem;
import main.slides.Slide;
import main.slides.itemTypes.SlideItem;
import main.slides.itemTypes.TextItem;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import main.presentation.Presentation;
import main.style.types.StyleType;


/**
 * main.accessors.XMLAccessor, reads and writes XML files
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor
{

    /**
     * Default API to use.
     */
    protected static final String DEFAULT_API_TO_USE = "dom";

    /**
     * namen van xml tags of attributen
     */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    /**
     * tekst van messages
     */
    //TODO check error types and maybe write them separately
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";


    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();

    }
    private NodeList getSlides(Presentation presentation, String filename) throws Exception
    {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new File(filename)); // Create a JDOM document
        Element doc = document.getDocumentElement();
        presentation.setTitle(getTitle(doc, SHOWTITLE));

        return doc.getElementsByTagName(SLIDE);
    }
    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        //TODO check if good`
        int slideNumber, itemNumber, max = 0, maxItems = 0;
        try
        {
            NodeList slides = getSlides(presentation,filename);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++)
            {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++)
                {
                    Element item = (Element) slideItems.item(itemNumber);
                    loadSlideItem(slide, item);
                }
            }
        }
        catch (IOException iox)
        {
            System.err.println(iox.toString());
        }
        catch (SAXException sax)
        {
            System.err.println(sax.getMessage());
        }
        catch (ParserConfigurationException pcx)
        {
            System.err.println(PCE);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    private int getItemLevel(NamedNodeMap attributes)
    {
        String levelText = attributes.getNamedItem(LEVEL).getTextContent();
        if (levelText!= null)
        {
            return Integer.parseInt(levelText);
        }
        return 1;
    }
    protected void loadSlideItem(Slide slide, Element item)
    {
        NamedNodeMap attributes = item.getAttributes();
        try
        {
            int level = getItemLevel(attributes);
        }
        catch (NumberFormatException e)
        {
            System.err.println(NFE);
        }
        String type = attributes.getNamedItem(KIND).getTextContent();

        switch (type)
        {
            case TEXT :
            {
                slide.append(new TextItem(item.getTextContent()));
                break;
            }
            case IMAGE:
            {
                slide.append(new BitmapItem(StyleType.STYLELEVEL1,item.getTextContent()));
                break;
            }
            default: {
                System.err.println(type);

            }
        }
    }
    private void print_heading(Presentation presentation,PrintWriter out)
    {
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
        out.println("<presentation>");
        out.print("<showtitle>");
        out.print(presentation.getTitle());
        out.println("</showtitle>");
    }

    public void saveFile(Presentation presentation, String filename) throws IOException
    {//TODO check if this is ok
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        print_heading(presentation,out);
        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++)
        {
            Slide slide = presentation.getSlide(slideNumber);
            out.println("<slide>");
            out.println("<title>" + slide.getTitle() + "</title>");
            Vector<SlideItem> slideItems = slide.getSlideItems();
            for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++)
            {
                SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
                out.println(slideItem.getTagContent());
            }
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }
}
