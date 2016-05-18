package org.acme.epub.book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noel on 16/08/15.
 */
public class Book
{

    String title;
    List<Section> sections = new ArrayList<>();

    public Book(String title)
    {
        this.title = title;
    }

    public void addSection(Section section)
    {
        sections.add(section);
    }

    public String getTitle()
    {
        return title;
    }

    public List<Section> getSections()
    {
        return sections;
    }

    public int getSize()
    {
        return sections.size();
    }

    public Section getSection(int i)
    {
        if (i >= sections.size())
            return null;
        return sections.get(i);
    }

    public BookReader getBookReader()
    {
        return new BookReader(this);
    }

}
