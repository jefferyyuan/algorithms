
/* Copyright 2003 by Lawrence Kesteloot */

package org.codeexample.algorithms.collected.markov;

import java.io.Reader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

/**
 * A Markov chain for characters.  For each set of prefix strings,
 * keeps track of possible next characters and the probability
 * of going to each.
 */

class Markov {
    /**
     * Map from the prefix string (String) to list of characters
     * (Chain).
     */

    private Map map;
    private String bootstrapPrefix;

    /**
     * Creates a chain based on the Reader with a prefix of
     * length "length".  Reads the entire input stream and
     * creates the Markov chain.
     */

    public Markov(Reader in, int length) throws java.io.IOException {
        map = new HashMap();

        CharQueue queue = new CharQueue(length);
        int c;

        for (int i = 0; i < length; i++) {
            c = in.read();
            if (c == -1) {
                System.out.println("Input is too short");
                return;
            }

            queue.put((char)c);
        }

        bootstrapPrefix = queue.toString();

        // for collapsing whitespace
        boolean wasWhitespace = false;

        while ((c = in.read()) != -1) {
            if (Character.isWhitespace((char)c)) {
                if (wasWhitespace) {
                    // collapse continuous whitespace
                    continue;
                }

                c = ' ';
                wasWhitespace = true;
            } else {
                wasWhitespace = false;
            }

            String prefix = queue.toString();

            Chain chain = (Chain)map.get(prefix);
            if (chain == null) {
                chain = new Chain(prefix);
                map.put(prefix, chain);
            }

            chain.add((char)c);
            queue.put((char)c);
        }
    }

    /**
     * Returns the first "length" characters that were read.
     */

    public String getBootstrapPrefix() {
        return bootstrapPrefix;
    }

    /**
     * Returns the next character to print given the prefix.
     * Returns -1 when there are no possible next characters.
     */

    public int get(String prefix, Random random) {
        Chain chain = (Chain)map.get(prefix);

        if (chain == null) {
            return -1;
        }

        int index = random.nextInt(chain.getTotal());

        return chain.get(index);
    }

    /**
     * Prints the contents of the Markov graph.
     */

    public void dump() {
        Set keys = map.keySet();

        Iterator i = keys.iterator();
        while (i.hasNext()) {
            String prefix = (String)i.next();
            Chain chain = (Chain)map.get(prefix);

            chain.dump();
        }
    }

    /**
     * List of possible next characters and their probabilities.
     */

    private static class Chain {
        private String prefix;
        private int total;
        private List list;

        public Chain(String prefix) {
            this.prefix = prefix;
            total = 0;
            list = new LinkedList();
        }

        public String getPrefix() {
            return prefix;
        }

        public int getTotal() {
            return total;
        }

        public char get(int index) {
            Iterator i = list.iterator();

            while (i.hasNext()) {
                Link link = (Link)i.next();
                int count = link.getCount();

                if (index < count) {
                    return link.getChar();
                }

                index -= count;
            }

            // weird
            return '@';
        }

        public void add(char c) {
            Iterator i = list.iterator();
            boolean found = false;

            while (i.hasNext()) {
                Link link = (Link)i.next();

                if (c == link.getChar()) {
                    link.increment();
                    found = true;
                    break;
                }
            }

            if (!found) {
                Link link = new Link(c);
                list.add(link);
            }

            total++;
        }

        public void dump() {
            System.out.println(prefix + ": (" + total + ")");

            Iterator i = list.iterator();

            while (i.hasNext()) {
                Link link = (Link)i.next();

                System.out.println("    " + link.getChar() + " (" +
                        link.getCount() + ")");
            }
        }

        /**
         * Possible next character and the number of times we've
         * seen it.
         */

        private static class Link {
            private char c;
            private int count;

            public Link(char c) {
                this.c = c;
                count = 1;
            }

            public void increment() {
                count++;
            }

            public int getCount() {
                return count;
            }

            public char getChar() {
                return c;
            }
        }
    }
}

