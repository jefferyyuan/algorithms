package org.codeexample.algorithms.collected.bit.textmining;

public class Levenshtein {
 
    // from http://rosettacode.org/wiki/Levenshtein_distance#Java

 
    public static void main(String [] args) {
        String [] data = { "kitten", "sitting", "saturday", "sunday", "rosettacode", "raisethysword" };
        for (int i = 0; i < data.length; i += 2)
            System.out.println("distance(" + data[i] + ", " + data[i+1] + ") = " + distance(data[i], data[i+1]));
    }
}