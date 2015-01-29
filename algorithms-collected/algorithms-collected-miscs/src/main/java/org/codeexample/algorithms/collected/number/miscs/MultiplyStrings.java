package org.codeexample.algorithms.collected.number.miscs;
public class MultiplyStrings {
 
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 ||
                num2 == null || num2.length() == 0)
            return "";
        if (num1.equals("0") || num2.equals("0"))   // Either one is 0
            return "0";
        int m = num1.length(), n = num2.length();
        // Multiply single digit of each number and add up products at each position
        int[] prods = new int[m+n];
        for (int i = n-1; i >= 0; i--)
            for (int j = m-1; j >= 0; j--)
                prods[i+j+1] += (num2.charAt(i)-'0') * (num1.charAt(j)-'0');
        // Keep a single digit at each position and add carry to a higher position
        StringBuilder result = new StringBuilder();
        for (int i = n+m-1; i >= 0; i--) {
            result.insert(0, prods[i]%10);
            if (i > 0)
                prods[i-1] += prods[i] / 10;    // Carry
        }
        // Get rid of one leaing "0" (if any)
        if (result.charAt(0) == '0')
            result.deleteCharAt(0);
 
        return result.toString();
    }
}