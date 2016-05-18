package org.acme.epub;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.epub.EpubReader;
import org.acme.epub.book.Paragraph;
import org.acme.epub.book.Section;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.function.Consumer;

/**
 * Created by noel on 16/08/15.
 */
public class EpubTextReader
{

    EpubReader reader = new EpubReader();
    Book book = null;

    public EpubTextReader(FileInputStream fis)
    {
        try
        {
            book = reader.readEpub(fis);
        }
        catch (Exception e)
        {
            book = null;
        }
    }

    public EpubTextReader(File file)
    {
        try
        {
            book = reader.readEpub(new FileInputStream(file));
        }
        catch (Exception e)
        {
            book = null;
        }
    }

    public EpubTextReader(String fileName)
    {
        this(new File(fileName));
    }

    public void forEach(Consumer<String> consumer)
    {
        if (book != null)
        {
            Spine spine = book.getSpine();
            for (int i = 0; i < spine.size(); i++)
            {
                Resource resource = spine.getResource(i);
                try
                {
                    StringBuffer sb = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(resource.getReader());
                    bufferedReader.lines().forEach(sb::append);

                    Document document = Jsoup.parse(sb.toString());
                    Elements elements = document.body().children();
                    for (Element e : elements)
                    {
                        consumer.accept(e.text());
                    }
                }
                catch (Exception e)
                {}

                consumer.accept("");
                consumer.accept("");
            }
        }
    }

    public org.acme.epub.book.Book getBook()
    {
        if (book != null)
        {
            org.acme.epub.book.Book b = new org.acme.epub.book.Book(book.getTitle());

            Spine spine = book.getSpine();
            for (int i = 0; i < spine.size(); i++)
            {
                Resource resource = spine.getResource(i);
                Section section = new Section(resource.getTitle());
                try
                {
                    StringBuffer sb = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(resource.getReader());
                    bufferedReader.lines().forEach(sb::append);

                    Document document = Jsoup.parse(sb.toString());
                    Elements elements = document.body().children();
                    for (Element e : elements)
                    {
                        Paragraph paragraph = new Paragraph(e.text());
                        section.addParagraph(paragraph);
                        //section.addBlankParagraph();
                    }
                }
                catch (Exception e)
                {}

                //section.addBlankParagraph();
                //section.addBlankParagraph();
                b.addSection(section);
            }

            return b;
        }

        return  null;
    }

}
