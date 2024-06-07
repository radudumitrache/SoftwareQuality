package presentation;

import accessors.Accessor;
import slides.itemCreators.BitmapItemCreator;
import slides.itemCreators.TextItemCreator;
import slides.Slide;
import slides.itemTypes.SlideItem;
import style.types.StyleType;

public class DemoPresentation extends Accessor
{
    private TextItemCreator textItemCreator;
    private BitmapItemCreator bitmapItemCreator;
    private void make_first_slide(Presentation presentation)
    {
        Slide slide = new Slide();
        slide.setTitle("JabberPoint");
        String[] slideTexts = new String[] {"The Java presentation.Presentation Tool",
                "Copyright (c) 1996-2000: Ian Darwin",
                "Copyright (c) 2000-now:",
                "Gert Florijn andn Sylvia Stuurman",
                "Starting JabberPoint without a filename",
                "shows this presentation",
                "Navigate:",
                "Next slide: PgDn or Enter",
                "Previous slide: PgUp or up-arrow",
                "Quit: q or Q"
        };
        StyleType[] slideStyles = new StyleType[] {StyleType.STYLELEVEL1, StyleType.STYLELEVEL2,
                StyleType.STYLELEVEL2,StyleType.STYLELEVEL2,StyleType.STYLELEVEL3,
                StyleType.STYLELEVEL3,StyleType.STYLELEVEL1,StyleType.STYLELEVEL3,
                StyleType.STYLELEVEL3,StyleType.STYLELEVEL3};
        textItemCreator.createItemsAndAddToSlide(slideTexts,slideStyles,slide);
        presentation.append(slide);
    }
    private void make_second_slide(Presentation presentation)
    {
        Slide slide = new Slide();
        slide.setTitle("Demonstration of levels and stijlen");
        String[] slideTexts = new String[] {"Level 1",
                "Level 2",
                "Again Level 1",
                "Level 1 has style number 1",
                "Level 2 has style number  2",
                "This is how level 3 looks like"
        };
        StyleType[] slideStyles = new StyleType[] {StyleType.STYLELEVEL1,
                StyleType.STYLELEVEL2,
                StyleType.STYLELEVEL1,
                StyleType.STYLELEVEL1,
                StyleType.STYLELEVEL2,
                StyleType.STYLELEVEL3
        };
        textItemCreator.createItemsAndAddToSlide(slideTexts,slideStyles,slide);
        presentation.append(slide);

    }
//    Trying to see if tests trigger
    private void make_third_slide(Presentation presentation)
    {
        Slide slide = new Slide();
        slide = new Slide();
        slide.setTitle("The third slide");
        String[] slideTexts = new String[] {"To open a new presentation,",
                "use File->Open from the menu.",
                " ",
                "This is the end of the presentation."
        };
        StyleType[] slideStyles = new StyleType[] {StyleType.STYLELEVEL1,
                StyleType.STYLELEVEL2,
                StyleType.STYLELEVEL1,
                StyleType.STYLELEVEL1,
        };
        textItemCreator.createItemsAndAddToSlide(slideTexts,slideStyles,slide);
        bitmapItemCreator.createItemAndAddToSlide(StyleType.STYLELEVEL0,"JabberPoint1.0",slide);
        slide.append(StyleType.STYLELEVEL1, "To open a new presentation,");
        slide.append(StyleType.STYLELEVEL2, "use File->Open from the menu.");
        slide.append(StyleType.STYLELEVEL1, " ");
        slide.append(StyleType.STYLELEVEL1, "This is the end of the presentation.");
        SlideItem bitmapItem = bitmapItemCreator.createItemSlide(StyleType.STYLELEVEL0,"JabberPoint1.0");
        slide.append(bitmapItem);
        presentation.append(slide);
    }
    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo presentation.Presentation");
        bitmapItemCreator = new BitmapItemCreator();
        textItemCreator = new TextItemCreator();
        make_first_slide(presentation);
        make_second_slide(presentation);
        make_third_slide(presentation);
    }

    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Save As->Demo! called");
    }
}
