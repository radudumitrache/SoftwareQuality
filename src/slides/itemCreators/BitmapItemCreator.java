package slides.itemCreators;

import slides.itemTypes.BitmapItem;
import slides.itemTypes.SlideItem;

public class BitmapItemCreator implements SlideItemCreator
{
    @Override
    public SlideItem createItemSlide()
    {
        return new BitmapItem("default_name");
    }
}