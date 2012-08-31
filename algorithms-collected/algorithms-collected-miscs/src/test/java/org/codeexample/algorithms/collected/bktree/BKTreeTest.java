package org.codeexample.algorithms.collected.bktree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;


public class BKTreeTest {
        private static final int THRESHOLD = 2;
        @Test
        public void distance() {
                assertEquals(1, BKTree.distance("book", "nook"));
                assertEquals(2, BKTree.distance("book", "rooks"));
                assertEquals(3, BKTree.distance("book", "drooks"));
        }
        @Test
        public void query() {
                BKTree tree = new BKTree();
                tree.add("Book");
                tree.add("Nook");
                tree.add("Rooks");
                tree.add("Drooks");
                tree.add("Boon");
                tree.add("Man");
                tree.add("Women");
                assertEquals(2,tree.search("Boo",1).size());
                assertEquals(5,tree.search("Poo",3).size());
                assertEquals("Book",tree.search("Book"));
        }
        @Test
        public void performance() throws Throwable {
                InputStream inputStream = getClass().getResourceAsStream("dict.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),1024 * 10 * 70);
                String line;
                BKTree tree = new BKTree();
                long start = System.nanoTime();
                while((line = reader.readLine()) != null) {
                        tree.add(line);
                }
                long diff = System.nanoTime() - start;
                System.out.printf("Dictionary indexed in %f msec.%n",diff / (1000f * 1000f));
                start = System.nanoTime();
                String search = "nirav";
                List<String> query = tree.search(search, THRESHOLD);
                assertNotNull(query);
                assertEquals(11,query.size());
                diff = System.nanoTime() - start;
                System.out.printf("Fuzzy matches (threshold=%d) for '%s' took %f msec.%n%s",THRESHOLD,search,diff / (1000f * 1000f),query);
        }
}

