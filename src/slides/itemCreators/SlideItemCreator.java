package slides.itemCreators;

import slides.itemTypes.SlideItem;
import style.types.Style;
import style.types.StyleType;

public interface SlideItemCreator
{

    public abstract SlideItem createItemSlide();
    public abstract SlideItem createItemSlide(StyleType style, String string);
}