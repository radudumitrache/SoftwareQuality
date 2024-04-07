public class NormalIterator implements PresentationIterator {
    private Presentation presentation;
    private int currentIndex = -1;

    public NormalIterator(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public Slide getNext() {
        if (hasMore()) {
            currentIndex++;
            return presentation.getSlide(currentIndex);
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        return currentIndex < presentation.getSize() - 1;
    }

    @Override
    public Slide getPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            return presentation.getSlide(currentIndex);
        }
        return null;
    }

    @Override
    public Slide getCurrent() {
        if (currentIndex >= 0 && currentIndex < presentation.getSize()) {
            return presentation.getSlide(currentIndex);
        }
        return null;
    }
}