package main.slides.itemCreators;

import main.slides.Slide;
import main.slides.itemTypes.SlideItem;
import main.slides.itemTypes.TextItem;
import main.style.types.StyleType;

import java.util.ArrayList;

public class TextItemCreator implements SlideItemCreator
{


    @Override
    public SlideItem createItemSlide() {
        return new TextItem();
    }

    @Override
    public SlideItem createItemSlide(StyleType style, String string) {

        return new TextItem(style, string);
    }
    public void createIemAndAddToSlide(StyleType style, String string,Slide slide)
    {
        slide.append(this.createItemSlide(style, string));
    }
    public void createItemsAndAddToSlide(String[] textlist, StyleType[] styleList, Slide slide)
    {
        if (textlist.length != styleList.length)
            throw new IllegalArgumentException("Please provide suitable");
        for (int it = 0 ;it < textlist.length; it++)
            {
                slide.append(this.createItemSlide(styleList[it], textlist[it]));
            }
    }
}