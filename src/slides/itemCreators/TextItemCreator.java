package slides.itemCreators;

import slides.itemTypes.SlideItem;
import slides.itemTypes.TextItem;

public class TextItemCreator implements SlideItemCreator
{
    @Override
    public SlideItem createItemSlide()
    {
        return new TextItem("Some default text");
    }
}