package org.acme.epub.book;

import java.util.*;

/**
 * Created by noel on 16/08/15.
 */
public class Paragraph
{

    List<String> sentences = new ArrayList<>();

    public Paragraph(String paragraph)
    {
        sentences = Arrays.asList(paragraph.split("."));
    }

    public List<String> getSentences()
    {
        return sentences;
    }

    public int getSize()
    {
        return sentences.size();
    }

    public String getSentence(int i)
    {
        if (i >= sentences.size())
            return null;
        return sentences.get(i);
    }

}
