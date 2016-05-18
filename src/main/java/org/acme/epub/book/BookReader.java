package org.acme.epub.book;

/**
 * Created by noel on 16/08/15.
 */
public class BookReader
{

    Book book;
    int currentSection;
    int currentParagraph;
    int currentSentence;

    public BookReader(Book book)
    {
        this.book = book;
        currentSection = 0;
        currentParagraph = 0;
        currentSentence = 0;
    }

    public void nextSection()
    {
        if (currentSection < book.getSize() - 1)
        {
            currentSection++;
            currentParagraph = 0;
            currentSentence = 0;
        }
    }

    public void prevSection()
    {
        if (currentSection > 0)
        {
            currentSection--;
            currentParagraph = 0;
            currentSentence = 0;
        }
    }

    public void nextParagraph()
    {
        Section section = book.getSection(currentSection);
        if (currentParagraph >= section.getSize() - 1)
            nextSection();
        else if (currentParagraph < section.getSize() - 1)
        {
            currentParagraph++;
            currentSentence = 0;
        }
    }

    public void prevParagraph()
    {
        Section section = book.getSection(currentSection);
        if (currentParagraph <= 0)
            prevSection();
        else if (currentParagraph > 0)
        {
            currentParagraph--;
            currentSentence = 0;
        }
    }

    public void nextSentence()
    {
        Section section = book.getSection(currentSection);
        Paragraph paragraph = section.getParagraph(currentParagraph);
        if (currentSentence >= paragraph.getSize() - 1)
            nextParagraph();
        else if (currentSentence < paragraph.getSize() - 1)
        {
            currentSentence++;
        }
    }

    public void prevSentence()
    {
        Section section = book.getSection(currentSection);
        Paragraph paragraph = section.getParagraph(currentParagraph);
        if (currentSentence <= 0)
            prevParagraph();
        else if (currentSentence > 0)
        {
            currentSentence--;
        }
    }

    public boolean hasNext()
    {
        Section lastSection = book.getSection(book.getSize() - 1);
        Paragraph lastParagraph = lastSection.getParagraph(lastSection.getSize() - 1);

        if (currentSection == book.getSize() -1 && currentParagraph == lastSection.getSize() - 1 && currentSentence == lastParagraph.getSize() - 1)
            return false;
        return true;
    }

    public String getNext()
    {
        System.out.println("sect " + currentSection + " para " + currentParagraph + " sent " + currentSentence);

        Section section = book.getSection(currentSection);
        Paragraph paragraph = section.getParagraph(currentParagraph);
        String sentence = paragraph.getSentence(currentSentence);
        nextSentence();
        return sentence;
    }
}
