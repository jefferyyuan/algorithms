package org.codeexample.algorithms.collected.misc;

public class LongestAP {
	public static void main(String[] args) {
		int sortedArr[] = new int[] { 3, 4, 5, 7, 8, 9, 11, 13, 14, 15, 16, 17,
				18 };
		int n = sortedArr.length;
		int lookup[][] = new int[n][n];
		int maxAP = 2;

		int apTerm = 0;
		int apStart = 0;

		// Any 2-letter series is an AP
		// Here we initialize only for the last column of lookup
		for (int i = 0; i < n; i++)
			lookup[i][n - 1] = 2;

		// Loop over the array and find two elements 'i' and 'k' such that i+k =
		// 2*j
		for (int j = n - 2; j >= 1; j--) {
			int i = j - 1; // choose i to the left of j
			int k = j + 1; // choose k to the right of j

			while (i >= 0 && k <= n - 1) {
				int isAP = (sortedArr[i] + sortedArr[k]) - 2 * sortedArr[j];

				if (isAP < 0) {
					k++;
				} else if (isAP > 0) {
					i--;
				} else {
					lookup[i][j] = lookup[j][k] + 1;

					maxAP = Math.max(maxAP, lookup[i][j]);
					if (maxAP == lookup[i][j]) {
						// Store the Arithmetic Progression's term
						// And the start point of the AP
						apTerm = sortedArr[j] - sortedArr[i];
						apStart = i;
					}

					k++;
					i--;
				}
			}
		}

		System.out.print("Max AP length = " + maxAP + "\n" + sortedArr[apStart]
				+ ", ");
		for (int i = apStart + 1; i < n; i++) {
			if ((sortedArr[i] - sortedArr[apStart]) % apTerm == 0)
				System.out.print(sortedArr[i] + ", ");
		}
	}
}