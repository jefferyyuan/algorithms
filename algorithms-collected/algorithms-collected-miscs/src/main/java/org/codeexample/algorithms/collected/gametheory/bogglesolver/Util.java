package org.codeexample.algorithms.collected.gametheory.bogglesolver;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util
{
    private final static Logger logger = Logger.getLogger(Util.class);
    private static Trie trie;
    private static int size = Constants.DEFAULT_PUZZLE_SIZE;

    /**
     Returns the trie built from the dictionary.  The size is used to eliminate words that are too long.

     @param size the size of puzzle.  The maximum lenght of words in the returned trie is (size * size)
     @return the trie that can be used for puzzle of that size
     */
    public static Trie getTrie(int size)
    {
        if ((trie != null) && size == Util.size)
            return trie;

        trie = new Trie();
        Util.size = size;

        logger.info("Reading the dictionary");
        final File file = new File("E:\\jeffery\\code\\github\\algorithms\\algorithms-collected\\algorithms-collected-miscs\\src\\main\\java\\org\\codeexample\\algorithms\\collected\\gametheory\\boggle\\enable1.txt");
        try
        {
            Scanner scanner = new Scanner(file);
            final int maxSize = size * size;
            while (scanner.hasNext())
            {
//                String line = scanner.nextLine().replaceAll("[^\\p{Alpha}]", "");

                String line = scanner.nextLine().trim();
                if (line.length() <= maxSize)
                    trie.insert(line);
            }
        }
        catch (FileNotFoundException e)
        {
            logger.error("Cannot open file", e);
        }

        logger.info("Finish reading the dictionary");
        return trie;
    }

    static boolean[] connectivityRow(int x, int y, int size)
    {
        boolean[] squares = new boolean[size * size];
        for (int offsetX = -1; offsetX <= 1; offsetX++)
        {
            for (int offsetY = -1; offsetY <= 1; offsetY++)
            {
                final int calX = x + offsetX;
                final int calY = y + offsetY;
                if ((calX >= 0) && (calX < size) && (calY >= 0) && (calY < size))
                    squares[calY * size + calX] = true;
            }
        }

        squares[y * size + x] = false;//the current x, y is false

        return squares;
    }

    /**
     Returns the matrix of connectivity between two points.  Point i can go to point j iff matrix[i][j] is true
     Square (x, y) is equivalent to point (size * y + x).  For example, square (1,1) is point 5 in a puzzle of size 4

     @param size the size of the puzzle
     @return the connectivity matrix
     */
    public static boolean[][] connectivityMatrix(int size)
    {
        boolean[][] matrix = new boolean[size * size][];
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                matrix[y * size + x] = connectivityRow(x, y, size);
            }
        }
        return matrix;
    }
}