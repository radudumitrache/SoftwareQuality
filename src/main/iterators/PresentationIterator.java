package main.iterators;

import main.slides.Slide;

public interface PresentationIterator
{
    Slide getNext();

    boolean hasMore();

    Slide getPrevious();

    Slide getCurrent();

    void setPosition(int index);

    int getPosition();
}