public class TextItemCreator implements SlideItemCreator
{
    @Override
    public SlideItem createItemSlide()
    {
        return new TextItem("Some default text");
    }
}