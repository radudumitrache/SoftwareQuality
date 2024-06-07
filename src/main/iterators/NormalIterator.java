package main.iterators;

import main.slides.Slide;

import java.util.List;

public class NormalIterator implements PresentationIterator
{
    private List<Slide> slides;
    private int currentPosition = 0;

    public NormalIterator(List<Slide> slides)
    {
        this.slides = slides;
        this.currentPosition = -1;
    }

    @Override
    public Slide getNext()
    {
        if (hasMore())
        {
            currentPosition++;
            return slides.get(currentPosition);
        }
        return null;
    }

    @Override
    public boolean hasMore()
    {
        return currentPosition < slides.size() - 1;
    }

    @Override
    public Slide getPrevious()
    {
        if (currentPosition > 0)
        {
            currentPosition--;
            return slides.get(currentPosition);
        }
        return null;
    }

    @Override
    public Slide getCurrent()
    {
        if (currentPosition >= 0 && currentPosition < slides.size())
        {
            return slides.get(currentPosition);
        }
        return null;
    }

    @Override
    public void setPosition(int index)
    {
        if (index >= 0 && index < slides.size())
        {
            currentPosition = index;
        }
        else
        {
            throw new IndexOutOfBoundsException("Invalid index for slide position.");
        }
    }

    @Override
    public int getPosition()
    {
        return currentPosition;
    }
}