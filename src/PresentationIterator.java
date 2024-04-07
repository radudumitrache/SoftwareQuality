public interface PresentationIterator {
    Slide getNext();
    boolean hasMore();
    Slide getPrevious();
    Slide getCurrent();
    void setPosition(int index); // Method to set the current position
    int getPosition(); // Method to get the current position
}