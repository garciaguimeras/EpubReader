package org.acme;

import org.acme.epub.EpubTextFileWriter;
import org.acme.epub.EpubTextReader;
import org.acme.epub.book.BookReader;

import java.io.*;

public class Main
{

    private void processFile(File f)
    {
        System.out.println("Processing file " + f.getName());

        EpubTextFileWriter writer = new EpubTextFileWriter(f.getName() + ".txt");

        EpubTextReader textReader = new EpubTextReader(f);
        textReader.forEach(writer::writeLine);

        /*
        BookReader bookReader = textReader.getBook().getBookReader();
        while (bookReader.hasNext())
            System.out.println(bookReader.getNext());
        */

        writer.close();
    }

    private void processDirectory(File dir)
    {
        File[] files = dir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if (pathname.getName().toLowerCase().endsWith(".epub"))
                    return true;
                return false;
            }
        });

        for (File f: files)
            processFile(f);
    }

    public void processParams(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Need at least an epub file as first param");
            return;
        }

        File in = new File(args[0]);
        if (!in.exists())
        {
            System.out.println("File does not exist");
            return;
        }

        if (in.isFile())
            processFile(in);

        if (in.isDirectory())
            processDirectory(in);
    }

    public static void main(String[] args)
    {
        new Main().processParams(args);
    }
}
