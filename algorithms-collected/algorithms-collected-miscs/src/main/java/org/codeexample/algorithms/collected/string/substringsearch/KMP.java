package org.codeexample.algorithms.collected.string.substringsearch;

/*  
 Name:   Ritika A. Muraraka
 Roll no: 45
 Batch: TE Comps
 Problem Statement: Given a string, check whether the pattern is present in the string or not using Knuth Morris Pratt Algorithm.
 */

import java.util.Scanner;

class KMP
{
    static String str, pat;

    public static void table(
            String s, String p)
    {
        // char s[]=str.length();
        // char p[]=pat.length();
        char mat[][] = new char[s.length()][p.length() + 1];
        for (int k = 0; k < p.length(); k++)
        {
            for (int i = 0; i < s.length(); i++)
            {
                for (int j = 0; j < p.length(); j++)
                {
                    if (s.charAt(i) == p.charAt(j) && j < p.length())
                    {
                        mat[k][j] = p.charAt(j + 1);
                    }
                    mat[k][j] = p.charAt(j);
                }

            }
        }
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = 0; j < p.length(); j++)
            {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static int[] failure(
            String s)
    {
        char c[] = s.toCharArray();
        int a[] = new int[c.length];
        int i, j;
        j = 0;
        i = 1;
        while (j < c.length)
        {
            if (c[i] == c[j])
            {
                i++;
                j++;
            }
            else if (i > 0)
                j++;
            else
                i = a[i - 1];
        }
        return a;

    }

    public static void main(
            String args[])
    {

        Scanner cin = new Scanner(System.in);
        System.out.println("Enter the string from which you want to search:");
        str = cin.next();
        System.out.println("Enter the pattern to be searched:");
        pat = cin.next();
        int a[] = failure(pat);
        int i = 0, j = 0;
        while (j < pat.length() && j < str.length())
        {
            if (str.charAt(i) == pat.charAt(j))
            {
                i++;
                j++;
            }
            else if (j > 0)
                j = a[j - 1];
            else
            {
                i++;
            }
        }
        if (j == pat.length())
        {
            System.out.println("The string is found");
            System.out.println("The position is:" + (i - j));
            table(str, pat);
        }
        else
            System.out.println("The string is not found. Please enter another string");
    }
}