import java.util.ArrayList;

public class Presentation
{
    private String showTitle;
    private ArrayList<Slide> showList;
    private SlideViewerComponent slideViewComponent;
    private PresentationIterator iterator;

    public Presentation()
    {
        this.showList = new ArrayList<>();
        this.iterator = new NormalIterator(this.showList);
        this.showTitle = "";
    }

    public Presentation(SlideViewerComponent slideViewerComponent)
    {
        this();
        this.slideViewComponent = slideViewerComponent;
    }

    public int getSize()
    {
        return showList.size();
    }

    public String getTitle()
    {
        return showTitle;
    }

    public void setTitle(String title)
    {
        this.showTitle = title;
    }

    public SlideViewerComponent getSlideViewComponent()
    {
        return slideViewComponent;
    }

    public void setSlideViewComponent(SlideViewerComponent slideViewerComponent)
    {
        this.slideViewComponent = slideViewerComponent;
    }

    public Slide getCurrentSlide()
    {
        return iterator.getCurrent();
    }

    public void setSlideNumber(int number)
    {
        if (number >= 0 && number < showList.size())
        {
            iterator.setPosition(number);
            if (slideViewComponent != null)
            {
                slideViewComponent.update(this, getCurrentSlide());
            }
        }
    }

    public int getSlideNumber()
    {
        return iterator.getPosition();
    }

    public void clear()
    {
        showList.clear();
        iterator = new NormalIterator(this.showList);
    }

    public void append(Slide slide)
    {
        showList.add(slide);
    }

    public Slide getSlide(int number)
    {
        return (number >= 0 && number < showList.size()) ? showList.get(number) : null;
    }

    public void nextSlide()
    {
        if (iterator.hasMore())
        {
            iterator.getNext();
            if (slideViewComponent != null)
            {
                slideViewComponent.update(this, getCurrentSlide());
            }
        }
    }

    public void prevSlide()
    {
        Slide slide = iterator.getPrevious();
        if (slide != null && slideViewComponent != null)
        {
            slideViewComponent.update(this, slide);
        }
    }

    public void exit(int n)
    {
        System.exit(n);
    }
}

