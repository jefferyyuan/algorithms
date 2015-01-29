package org.codeexample.algorithms.collected.number.miscs;
public class DivideTwoIntegers {
 
    // 440ms for 987 test cases
    public int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int quotient = 0;
        // In order to make the Math.abs method work as expected, we need to consider the case
        // when either number is Integer.MIN_VALUE (Math.abs(Integer.MIN_VALUE)=Integer.MIN_VALUE)
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            quotient++;
            dividend += Math.abs(divisor);
        }
 
        int posDividend = Math.abs(dividend), posDivisor = Math.abs(divisor);
        // The maximum number of right shifts that can be applied to posDividend while
        // Making it larger than posDivisor
        int shift = 0;
        while ((posDividend>>(shift+1)) >= posDivisor)
            shift++;
        // Subtract multiples of posDivisor from posDividend and sum up the number
        // of multiples in quotient.
        while (shift >= 0) {
            if (posDividend >= (posDivisor << shift)) {
                quotient += 1 << shift;
                posDividend -= posDivisor << shift;
            }
            shift--;
        }
        // Determine whether quotient should be positive or negative
        return ((dividend^divisor) >>> 31 == 0) ? quotient : -quotient;
    }
}