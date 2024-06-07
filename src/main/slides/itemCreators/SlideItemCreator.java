package main.slides.itemCreators;

import main.slides.itemTypes.SlideItem;
import main.style.types.Style;
import main.style.types.StyleType;

public interface SlideItemCreator
{

    public abstract SlideItem createItemSlide();
    public abstract SlideItem createItemSlide(StyleType style, String string);
}