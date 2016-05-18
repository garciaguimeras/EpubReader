package org.acme.epub;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by noel on 16/08/15.
 */
public class EpubTextFileWriter
{

    FileOutputStream fos;

    public EpubTextFileWriter(String fileName)
    {
        try
        {
            File out = new File(fileName);
            fos = new FileOutputStream(out);
        }
        catch (Exception e)
        {
            fos = null;
        }
    }

    public void writeLine(String text)
    {
        if (fos != null)
        {
            try
            {
                fos.write(text.getBytes());
                fos.write("\r\n".getBytes());
            }
            catch (Exception e)
            {}
        }
    }

    public void close()
    {
        try
        {
            fos.close();
        }
        catch (Exception e)
        {}
    }
}
