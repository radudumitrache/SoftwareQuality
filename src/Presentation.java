import java.util.ArrayList;
public class Presentation {
    private String showTitle;
    private ArrayList<Slide> showList;
    private SlideViewerComponent slideViewComponent;
    private PresentationIterator iterator;

    public Presentation() {
        this.showList = new ArrayList<>();
        this.iterator = new NormalIterator(this); // Assuming NormalIterator is the concrete implementation
    }

    public Presentation(SlideViewerComponent slideViewerComponent) {
        this();
        this.slideViewComponent = slideViewerComponent;
    }

    public int getSize() {
        return showList.size();
    }

    public String getTitle() {
        return showTitle;
    }

    public void setTitle(String title) {
        this.showTitle = title;
    }

    public void setShowView(SlideViewerComponent slideViewerComponent) {
        this.slideViewComponent = slideViewerComponent;
    }

    // This method should now use the iterator to get the current slide
    public Slide getCurrentSlide() {
        return iterator.getCurrent();
    }

    public void nextSlide() {
        if (iterator.hasNext()) {
            Slide slide = iterator.getNext();
            if (slideViewComponent != null) {
                slideViewComponent.update(this, slide);
            }
        }
    }

    public void prevSlide() {
        Slide slide = iterator.getPrevious();
        if (slide != null && slideViewComponent != null) {
            slideViewComponent.update(this, slide);
        }
    }

    public void clear() {
        showList.clear();
        iterator = new NormalIterator(this); // Reset the iterator
    }

    public void append(Slide slide) {
        showList.add(slide);
    }

    public Slide getSlide(int number) {
        if (number < 0 || number >= getSize()) {
            return null;
        }
        return showList.get(number);
    }

    public void exit(int n) {
        System.exit(n);
    }