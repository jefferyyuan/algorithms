
/* Copyright 2003 by Lawrence Kesteloot */

package org.codeexample.algorithms.collected.markov;

import java.io.FileReader;
import java.io.Reader;
import java.util.Random;

/**
 * Creates a Markov graph from an input file and generates text
 * based on it.  Given two input files, generates two graphs
 * and interpolates between them.
 */

public class A {
    private static final int DEFAULT_PREFIX_LENGTH = 4;
    private static final int LINE_WIDTH = 65;
    private static final int TOTAL_CHARACTERS = 300;

    private static Markov loadMarkov(String filename, int prefixLength) {
        Reader input;

        try {
            input = new FileReader(filename);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: \"" + filename +
                "\" (" + e + ")");
            return null;
        }

        Markov markov;

        try {
            markov = new Markov(input, prefixLength);
        } catch (java.io.IOException e) {
            System.out.println("Cannot read from \"" + filename +
                "\" (" + e + ")");
            return null;
        }

        return markov;
    }

    private static void usage() {
        System.out.println(
                "Usage: A [--prefix n] [--html] input.txt [other.txt]");
    }

    public static void main(String[] args) {
        int prefixLength = DEFAULT_PREFIX_LENGTH;
        boolean highlightHTML = false;
        Markov markov1 = null;
        Markov markov2 = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--prefix")) {
                try {
                    prefixLength = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    usage();
                    return;
                }
                i++;
            } else if (args[i].equals("--html")) {
                highlightHTML = true;
            } else if (markov1 == null) {
                markov1 = loadMarkov(args[i], prefixLength);
                if (markov1 == null) {
                    return;
                }
            } else if (markov2 == null) {
                markov2 = loadMarkov(args[i], prefixLength);
                if (markov2 == null) {
                    return;
                }
            } else {
                usage();
                return;
            }
        }

        if (markov1 == null) {
            usage();
            return;
        }

        Random random = new Random();
        CharQueue queue = new CharQueue(prefixLength);
        float weight = 0;

        queue.set(markov1.getBootstrapPrefix());
        System.out.print(queue.toString());
        int width = prefixLength;
        int c;

        do {
            boolean cameFromSecond = false;

            String prefix = queue.toString();

            // get a character from each chain
            c = markov1.get(prefix, random);
            int c2 = -1;
            if (markov2 != null) {
                c2 = markov2.get(prefix, random);
            }
            if (c == -1 && c2 == -1) {
                break;
            }

            // choose one if we can
            if (markov2 != null) {
                if (c == -1) {
                    c = c2;
                    cameFromSecond = true;
                } else if (c2 != -1 && random.nextFloat() < weight) {
                    c = c2;
                    cameFromSecond = true;
                }
            }

            if (cameFromSecond && highlightHTML) {
                System.out.print("<font color='red'>" + (char)c + "</font>");
            } else {
                System.out.print((char)c);
            }
            queue.put((char)c);
            width++;

            // line wrap
            if (c == ' ' && width > LINE_WIDTH) {
                System.out.println();
                width = 0;
            }

            // go towards second markov chain
            weight += 1.0/TOTAL_CHARACTERS;
        } while (weight < 1 || c != '.');

        System.out.println();
    }
}

