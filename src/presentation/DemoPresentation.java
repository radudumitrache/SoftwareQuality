package presentation;

import accessors.Accessor;
import slides.itemCreators.BitmapItemCreator;
import slides.itemCreators.TextItemCreator;
import slides.Slide;
import slides.itemTypes.SlideItem;
import style.types.StyleType;

public class DemoPresentation extends Accessor
{
    private void make_first_slide(Presentation presentation)
    {
        Slide slide = new Slide();
        slide.setTitle("JabberPoint");
        slide.append(StyleType.STYLELEVEL1, "The Java presentation.Presentation Tool");
        slide.append(StyleType.STYLELEVEL2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append(StyleType.STYLELEVEL2, "Copyright (c) 2000-now:");
        slide.append(StyleType.STYLELEVEL2, "Gert Florijn andn Sylvia Stuurman");
        slide.append(StyleType.STYLELEVEL3, "Starting JabberPoint without a filename");
        slide.append(StyleType.STYLELEVEL3, "shows this presentation");
        slide.append(StyleType.STYLELEVEL1, "Navigate:");
        slide.append(StyleType.STYLELEVEL3, "Next slide: PgDn or Enter");
        slide.append(StyleType.STYLELEVEL3, "Previous slide: PgUp or up-arrow");
        slide.append(StyleType.STYLELEVEL3, "Quit: q or Q");
        presentation.append(slide);
    }
    private void make_second_slide(Presentation presentation)
    {
        Slide slide = new Slide();
        slide.setTitle("Demonstration of levels and stijlen");
        slide.append(StyleType.STYLELEVEL1, "Level 1");
        slide.append(StyleType.STYLELEVEL2, "Level 2");
        slide.append(StyleType.STYLELEVEL1, "Again level 1");
        slide.append(StyleType.STYLELEVEL1, "Level 1 has style number 1");
        slide.append(StyleType.STYLELEVEL2, "Level 2 has style number  2");
        slide.append(StyleType.STYLELEVEL3, "This is how level 3 looks like");
        presentation.append(slide);

    }
    private void make_third_slide(Presentation presentation)
    {
        Slide slide = new Slide();
        slide = new Slide();
        slide.setTitle("The third slide");
        slide.append(StyleType.STYLELEVEL1, "To open a new presentation,");
        slide.append(StyleType.STYLELEVEL2, "use File->Open from the menu.");
        slide.append(StyleType.STYLELEVEL1, " ");
        slide.append(StyleType.STYLELEVEL1, "This is the end of the presentation.");
        BitmapItemCreator bitmapItemCreator = new BitmapItemCreator();
        SlideItem bitmapItem = bitmapItemCreator.createItemSlide();
        slide.append(bitmapItem);
        presentation.append(slide);
    }
    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo presentation.Presentation");
        make_first_slide(presentation);
        make_second_slide(presentation);
        make_third_slide(presentation);
    }

    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Save As->Demo! called");
    }
}
