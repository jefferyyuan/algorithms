package org.codeexample.algorithms.collected.dp;

/**
 ** Java Program to Implement Knapsack Algorithm
 **/

import java.util.ArrayList;

/** Class Knapsack **/
public class Knapsack2 {
	void ksUnbounded(int targetWeight, int nItems, int[] iWeights, int[] iValues) {
		// Algo: Dynamic Programming/Memoizing
		// Step1: Maitain a two-dimensional array,
		// Array[0...nItems][0...targetWeight]
		// Note: Weight increses upto targetWeight
		// Step2: Also maintain a ksTrackArray[0...nItems-1][0...targetWeight]
		// to track which items are
		// part of the solution satisfying the weight value constraint
		// Step3: At any point in dynamic programming find: maximum combined
		// weight of any subsets of items, o...currentItem
		// of weight atmost iw (the ith weight in the 2-dimensional array)

		int[][] ksItemCapacity = new int[nItems + 1][targetWeight + 1];
		int[][] ksTrack = new int[nItems + 1][targetWeight + 1];

		for (int w = 0; w <= targetWeight; w++) {
			ksItemCapacity[0][w] = 0;
		}

		for (int item = 1; item < nItems; item++) {
			for (int w = 0; w <= targetWeight; w++) {
				// last known Maximum value of KS contents s.t. their weight
				// totals to atmost w-iWeights[item]
				int eItemValue = (iWeights[item] <= w) ? ksItemCapacity[item - 1][w
						- iWeights[item]]
						: 0;
				if ((iWeights[item] <= w)
						&& (iValues[item] + eItemValue) > ksItemCapacity[item - 1][w]) {
					ksItemCapacity[item][w] = eItemValue + iValues[item];
					// current item included
					ksTrack[item][w] = 1;
				} else {
					ksItemCapacity[item][w] = ksItemCapacity[item - 1][w];
					// current item not included
					ksTrack[item][w] = 0;
				}
			}
		}

		// Print KS contents
		ArrayList<Integer> ksContents = new ArrayList<Integer>();
		int tW = targetWeight;
		for (int item = nItems; item >= 0; item--) {
			if (ksTrack[item][tW] == 1) {
				tW -= iWeights[item];
				ksContents.add(item);
			}
		}

		System.out.println("Items choosen are:");
		int W = 0, V = 0;
		for (Integer e : ksContents) {
			W += iWeights[e];
			V += iValues[e];
			System.out.println("Weight: " + iWeights[e] + ", Value: "
					+ iValues[e]);
		}
		System.out.println("Total weight: " + W + " Total value: " + V);
	} // ksUnbounded
}