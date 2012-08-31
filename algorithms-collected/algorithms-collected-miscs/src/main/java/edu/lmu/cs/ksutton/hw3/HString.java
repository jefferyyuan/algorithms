package edu.lmu.cs.ksutton.hw3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A String class that contains methods for using Horspool's algorithm Source:
 * http://www.koders.com/java/fidBD23AA2D86B56AB70E8D4FCB0B6CB52A4AD7612A.aspx
 * <p>
 * 
 * @author Kelly Sutton
 */
public class HString
{

    private HashMap<Character, Integer> shiftTable;

    private String pattern;

    public HString(String s)
    {

        pattern = s;
        shiftTable = computeShiftTable(pattern);

    }

    /**
     * Returns an array filled with the index numbers of the matches of the
     * pattern within the String. The user should determine how many matches
     * there are by taking the length of the array returned
     * 
     * @param The
     *            string to be search through
     * @return An ArrayList<Integer> with each value representing the index of a
     *         match _inside the input string_.
     */
    public ArrayList<Integer> match(
            String s)
    {

        ArrayList<Integer> returnArray = new ArrayList<Integer>();

        int i = 0;// the position of the first letter in our pattern

        // position in the pattern of the character we are looking at
        int j = pattern.length() - 1;

        // We loop and add any matches to our returnArray
        while (i + j <= s.length() - 1)
        {

            // We match
            if (pattern.charAt(j) == (s.charAt(i + j)))
            {

                // We're at the last character! We've got a match!
                if (j == 0)
                {
                    returnArray.add(i);
                    i += pattern.length();
                    j = pattern.length() - 1;
                }

                // We still have characters to go!
                else
                    j--;
            }

            // We don't match, shift by appropriate value
            else
            {
                if (shiftTable.get(s.charAt(i)) != null)
                    i += shiftTable.get(pattern.charAt(j));
                else
                    i += pattern.length() - j;
                j = pattern.length() - 1; // we look at the last character again
            }
        }

        return returnArray;
    }

    @Override
    public String toString()
    {
        String s = "";/* pattern + "\n"; */

        for (int i = 0; i < pattern.length(); i++)
        {

            // We haven't already outputted the letter we're on
            if (s.indexOf(pattern.charAt(i)) == -1)
                s = s + "[" + pattern.charAt(i) + ", " + shiftTable.get(pattern.charAt(i)) + "]\n";
        }

        return pattern + "\n" + s;
    }

    private HashMap<Character, Integer> computeShiftTable(
            String pattern)
    {

        HashMap<Character, Integer> returnMap = new HashMap<Character, Integer>();

        for (int i = 0; i < pattern.length(); i++)
        {
            // Insert the int value into the map at the proper character
            Character curChar = pattern.charAt(i);
            int shiftValue = pattern.lastIndexOf(curChar);

            returnMap.put(curChar, shiftValue);
        }

        return returnMap;
    }

}
