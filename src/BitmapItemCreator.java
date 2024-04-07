public class BitmapItemCreator implements SlideItemCreator {
    @Override
    public SlideItem createItemSlide() {
        return new BitmapItem("default_name");
    }
}