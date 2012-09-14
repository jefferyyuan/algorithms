package org.codeexample.algorithms.collected.string.regularexpression;

/**
 * Source: http://www.leetcode.com/2010/11/best-time-to-buy-and-sell-stock.html
 * <p>
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * <p>
 * If you were only permitted to buy one share of the stock and sell one share
 * of the stock, design an algorithm to find the best times to buy and sell.
 * <p>
 * The question is equivalent to the following: Find i and j that maximizes Aj –
 * Ai, where i < j.
 * <p>
 * There is an obvious O(N2) solution, but in fact we can do better in just
 * O(N).
 * <p>
 * 
 */
public class BestTimeToBuySellStock
{

    /**
     * To solve this problem efficiently, you would need to track the minimum
     * value’s index. As you traverse, update the minimum value’s index when a
     * new minimum is met. Then, compare the difference of the current element
     * with the minimum value. Save the buy and sell time when the difference
     * exceeds our maximum difference (also update the maximum difference).
     */
    public static void getBestTime(
            int stocks[], int sz, Integer buy, Integer sell)
    {
        int min = 0;
        int maxDiff = 0;
        buy = sell = 0;
        for (int i = 0; i < sz; i++)
        {
            if (stocks[i] < stocks[min])
                min = i;
            int diff = stocks[i] - stocks[min];
            if (diff > maxDiff)
            {
                buy = min;
                sell = i;
                maxDiff = diff;
            }
        }
    }
}
