package org.acme.epub.book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noel on 16/08/15.
 */
public class Section
{

    String name;
    List<Paragraph> paragraphs = new ArrayList<>();

    public Section(String name)
    {
        this.name = name;
    }

    public void addParagraph(Paragraph paragraph)
    {
        paragraphs.add(paragraph);
    }

    public void addBlankParagraph()
    {
        paragraphs.add(new Paragraph(""));
    }

    public String getName()
    {
        return name;
    }

    public List<Paragraph> getParagraphs()
    {
        return paragraphs;
    }

    public int getSize()
    {
        return paragraphs.size();
    }

    public Paragraph getParagraph(int i)
    {
        if (i >= paragraphs.size())
            return null;
        return paragraphs.get(i);
    }

}
