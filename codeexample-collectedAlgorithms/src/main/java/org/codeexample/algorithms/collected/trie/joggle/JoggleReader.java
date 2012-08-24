package org.codeexample.algorithms.collected.trie.joggle;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * read words with Trie support
 * 
 * @author Owen Astrachan
 * @version $Id: JoggleReader.java,v 1.2 1996/12/01 00:08:39 ola Exp ola $
 */

public class JoggleReader
{
    public JoggleReader()
    {
        // nothing to create
    }

    public void ReadWords(
            Trie trie)
    {
        String s;
        FileInputStream f = null;
        DataInputStream stream = null;
        try
        {
            f = new FileInputStream("bogdict.txt");
            stream = new DataInputStream(f);
            int count = 0;
            while ((s = stream.readLine()) != null)
            {
                trie.addString(s);
                count++;
                if (count % 1000 == 0)
                {
                    System.out.println("read " + count + " words");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("error reading dictionary");
            System.err.println(e.getMessage());
        }
        finally
        {
            if (f != null)
            {
                try
                {
                    f.close();
                    stream.close();
                }
                catch (IOException e)
                {
                    // nothing here
                }
            }
        }
    }
}