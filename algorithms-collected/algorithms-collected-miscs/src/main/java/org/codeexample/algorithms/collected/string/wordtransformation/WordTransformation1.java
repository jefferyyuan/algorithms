package org.codeexample.algorithms.collected.string.wordtransformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Algorithm to transform one word to another through valid words
 * <p>
 * Java implementation: http://belbesy.wordpress.com/2011/05/31/uva-429-word-transformation/
 * <br>
 * C++ version:
 * http://tausiq.wordpress.com/2011/04/06/uva-429-word-transformation/
 * <p>
 * http://stackoverflow.com/questions/2205540/algorithm-to-transform-one-word-to
 * -another-through-valid-words<br>
 * http://stackoverflow.com/questions/1521958/shortest-path-to-transform-one-
 * word-into-another
 */
public class WordTransformation1
{
    public static void main(
            String[] args) throws NumberFormatException, IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        String s = in.readLine();
        while (T-- != 0)
        {
            ArrayList<String> items = new ArrayList<String>();
            while (!(s = in.readLine()).equals("*"))
                items.add(s);

            HashMap<String, List<String>> graph = generateGraph(items);

            while ((s = in.readLine()) != null && !(s).equals(""))
            {
                String[] tokens = s.split(" ");
                String source = tokens[0];
                String distination = tokens[1];
                Integer length = BFS(source, distination, graph);

                System.out.println(source + " " + distination + " " + length);
            }
            if (T != 0)
                System.out.println();
        }
    }

    public static HashMap<String, List<String>> generateGraph(
            ArrayList<String> words)
    {
        HashMap<String, List<String>> g = new HashMap<String, List<String>>();
        for (int i = 0; i < words.size(); i++)
        {
            List<String> adj = new LinkedList<String>();
            for (int j = 0; j < words.size(); j++)
            {
                if (i == j)
                    continue;
                if (linked(words.get(i), words.get(j)))
                {
                    adj.add(words.get(j));
                }
            }

            g.put(words.get(i), adj);
        }

        return g;
    }

    public static boolean linked(
            String a, String b)
    {
        if (a.length() != b.length())
            return false;

        int difference = 0;
        for (int i = 0; i < a.length(); i++)
            difference += a.charAt(i) != b.charAt(i) ? 1 : 0;

        return difference == 1;
    }

    public static Integer BFS(
            String source, String distination, HashMap<String, List<String>> g)
    {

        HashMap<String, Integer> distance = new HashMap<String, Integer>();
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();

        HashMap<String, List<String>> wordMap = new HashMap<String, List<String>>();

        for (String s : g.keySet())
        {
            distance.put(s, -1);
            visited.put(s, false);
        }

        Queue<String> que = new LinkedList<String>();
        distance.put(source, 0);

        // updateWordMap(wordMap, source, source);

        // List<String> words = new LinkedList<>();
        // wordMap.put(source, words);

        que.add(source);

        while (!que.isEmpty())
        {
            String u = que.poll();
            visited.put(u, true);
            for (String v : g.get(u))
            {
                if (v.equals(distination))
                {
                    // updateWordMap(wordMap, u, v);
                    return distance.get(u) + 1;
                }
                if (!visited.get(v))
                {
                    que.add(v);
                    distance.put(v, distance.get(u) + 1);

                    // updateWordMap(wordMap, u, v);
                }
            }
        }
        return distance.get(distination);
    }

    // doesn't work
    // private static void updateWordMap(
    // HashMap<String, List<String>> wordMap, String oneCharacterDiffWord,
    // String destiniation)
    // {
    // List<String> tmpWords = wordMap.get(destiniation);
    // if (tmpWords == null)
    // {
    // tmpWords = new LinkedList<>();
    // wordMap.put(destiniation, tmpWords);
    // }
    // tmpWords.add(oneCharacterDiffWord);
    // }
}