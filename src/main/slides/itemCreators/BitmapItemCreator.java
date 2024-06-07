package main.slides.itemCreators;

import main.slides.Slide;
import main.slides.itemTypes.BitmapItem;
import main.slides.itemTypes.SlideItem;
import main.style.types.StyleType;

public class BitmapItemCreator implements SlideItemCreator
{
    //TODO check if correct



    @Override
    public SlideItem createItemSlide()
    {
        return new BitmapItem(StyleType.STYLELEVEL1,"default_image_name");
    }
    @Override
    public SlideItem createItemSlide(StyleType styleType, String name)
    {
        return new BitmapItem(styleType,name);
    }
    public void createItemAndAddToSlide(StyleType style, String string, Slide slide)
    {
        slide.append(this.createItemSlide(style, string));
    }
    public void createMultipleItemsAndAppendToSlide(String[] imageNames,StyleType[] imageStyles, Slide slide)
    {
        if (imageStyles.length != imageNames.length)
            throw new IllegalArgumentException("Please provide suitable");
        for (int i = 0;i < imageNames.length;i++)
        {
            this.createItemAndAddToSlide(imageStyles[i], imageNames[i], slide);
        }
    }
}