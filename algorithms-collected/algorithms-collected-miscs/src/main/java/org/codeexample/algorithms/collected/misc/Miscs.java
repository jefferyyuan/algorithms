package org.codeexample.algorithms.collected.misc;

import java.util.Stack;
import static java.lang.System.out;

public class Miscs {
	int largestRectangle(int barHeights[], int numBars) {
		if (numBars <= 0)
			return 0;
		int maxArea = 0;

		Stack<Integer> stk = new Stack<>();
		stk.push(0);

		for (int i = 1; i < numBars; i++) {
			int currEle = barHeights[i];

			while (stk.empty() == false) {
				int stackTop = stk.peek(); // peep gets stack top without
											// removing

				if (currEle > barHeights[stackTop]) {
					stk.push(i);
					break;
				}

				stackTop = stk.pop(); // pop gets stack top with removal

				// Since stack only stores elements in increasing order, any
				// element
				// just below an element must give the leftmost boundary of the
				// rectangle
				// containing current element

				// if stack is empty, then the popped element is minimum so far
				int leftBoundary = stk.empty() ? 0 : stk.peek();
				int rightBoundary = (barHeights[stackTop] == currEle) ? i
						: i - 1;

				int area = (rightBoundary - leftBoundary)
						* barHeights[stackTop];
				if (area > maxArea)
					maxArea = area;
			}

			if (stk.empty())
				stk.push(i);
		}

		// empty the remaining stack
		int rightBoundary = numBars;
		while (stk.empty() == false) {
			int stackTop = stk.pop();
			int leftBoundary = stk.empty() ? 0 : stk.peek();
			int area = (rightBoundary - leftBoundary) * barHeights[stackTop];
			if (area > maxArea)
				maxArea = area;
		}

		return maxArea;
	}

	void printNextLarger(int arr[]) {
		if (arr.length <= 0)
			return;

		Stack<Integer> stack = new Stack<>();
		stack.push(arr[0]);

		for (int i = 1; i < arr.length; i++) {
			int curr = arr[i];

			while (stack.empty() == false) {
				int leftEle = stack.pop();
				if (leftEle < curr) {
					out.print(leftEle + " -- next larger element -- >" + curr);
					// This loop prints and discards all local pairs whose next
					// larger has been found.
				} else {
					stack.push(leftEle);
					break;
				}
			}

			// current element's next larger element is yet to be seen, so
			// current element always goes into the stack.
			stack.push(curr);
		}

		// The elements left in the stack have no next larger element.
		// Infact, the leftovers are arranged in sorted order in the stack due
		// to this.
		while (stack.empty() == false) {
			int leftoverEle = stack.pop();
			print(leftoverEle + " -- next larger element -- > None");
		}
	}

	int maxPathSum(int arr1[], int arr2[]) {
		int i = 0;
		int j = 0;
		int sum1 = 0;
		int sum2 = 0;
		int result = 0;
		int m = arr1.length;
		int n = arr2.length;

		// This part is very similar to merge sort
		while (i < m && j < n) {
			if (arr1[i] < arr2[j])
				sum1 += arr1[i++];

			else if (arr1[i] > arr2[j])
				sum2 += arr2[j++];

			else // Found a common point
			{
				result += Math.max(sum1, sum2);
				sum1 = 0;
				sum2 = 0;

				while (i < m && j < n && arr1[i] == arr2[j]) {
					result = result + arr1[i];
					i++;
					j++;
				}
			}
		}

		// The loop ends when one of the array reached its end.
		// That means we still have to choose the array whose elements will end
		// the
		// maximum-sum-path (since last pair of equals)
		while (i < m)
			sum1 += arr1[i++];
		while (j < n)
			sum2 += arr2[j++];

		result += Math.max(sum1, sum2);

		return result;
	}

	// void findMaxDistanceApart(int arr[]) {
	// if (arr.length <= 1)
	// return;
	//
	// int indexOfLeftMin[] = new int[arr.length];
	// int indexOfRightMax[] = new int[arr.length];
	//
	// // below loop fills first array such that value at every index
	// // tells the position of the minimum element present in the left of that
	// // index
	// indexOfLeftMin[0] = 0;
	// for (int i = 1; i < arr.length; i++) {
	// int currMin = arr[indexOfLeftMin[i - 1]];
	// if (arr[i] < currMin) {
	// indexOfLeftMin[i] = i;
	// } else {
	// indexOfLeftMin[i] = indexOfLeftMin[i - 1];
	// }
	// }
	//
	// // below loop fills second array such that value at every index
	// // tells the position of the maximum element present in the right of
	// // that index
	// indexOfRightMax[arr.length - 1] = arr.length - 1;
	// for (int i = arr.length - 2; i >= 0; i--) {
	// int currMax = arr[indexOfRightMax[i + 1]];
	// if (arr[i] > currMax) {
	// indexOfRightMax[i] = i;
	// } else {
	// indexOfRightMax[i] = indexOfRightMax[i + 1];
	// }
	// }
	//
	// // find k such that difference between indexOfRightMax[k] and
	// // indexOfLeftMin[k] is maximum
	//
	// int maxDiff = -1;
	// int i = -1;
	// int j = -1;
	// for (int k = 0; k < arr.length; k++) {
	// int distance = indexOfRightMax[k] - indexOfLeftMin[k];
	// if (distance > maxDiff) {
	// i = indexOfLeftMin[k];
	// j = indexOfRightMax[k];
	// }
	// }
	//
	// if (i == j) {
	// print("No such pair exists");
	// return;
	// }
	//
	// print("Two sorted elements maximum distance apart are " + arr[i]
	// + " and " + arr[j]);
	// print("And maximum distance is " + (j - i));
	//
	// }
}
