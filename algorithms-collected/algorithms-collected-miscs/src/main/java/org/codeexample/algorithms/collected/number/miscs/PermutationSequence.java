package org.codeexample.algorithms.collected.number.miscs;

import java.util.LinkedList;
import java.util.List;

public class PermutationSequence {
	public static void main(String[] args) {
		System.err.println(getPermutationLeetCode(5, 20));
	}
	public static String getPermutationLeetCode(int n, int k) {
        // Create a list of 1, 2, ..., n, and compute the number of permutations as "groupSize"
        List<Integer> list = new LinkedList<Integer>();
        int groupSize = 1;
        for (int i = n; i >= 1; i--) {
            list.add(0, i);
            groupSize *= i;
        }
        // Invalid k
        if (k > groupSize)
            return null;
        // Construct the k-th permutation with a list of n numbers
        // Idea: group all permutations according to their first number (so n groups, each of
        // (n-1)! numbers), find the group where the k-th permutation belongs, remove the common
        // first number from the list and append it to the resulting string, and iteratively
        // construct the (((k-1)%(n-1)!)+1)-th permutation with the remaining n-1 numbers
        StringBuilder builder = new StringBuilder();
        while (n > 0) {
            groupSize /= n;
            int groupIndex = (k-1) / groupSize;
            builder.append(list.remove(groupIndex));
            n--;
            k = ((k-1) % groupSize) + 1;
        }

        return builder.toString();
    }
	
	public static String getPermutation3(int n, int k) {
		boolean[] output = new boolean[n];
		StringBuilder buf = new StringBuilder("");
 
		int[] res = new int[n];
		res[0] = 1;
 
		for (int i = 1; i < n; i++)
			res[i] = res[i - 1] * i;
 
		for (int i = n - 1; i >= 0; i--) {
			int s = 1;
 
			while (k > res[i]) {
				s++;
				k = k - res[i];
			}
 
			for (int j = 0; j < n; j++) {
				if (j + 1 <= s && output[j]) {
					s++;
				}
			}
 
			output[s - 1] = true;
			buf.append(Integer.toString(s));
		}
 
		return buf.toString();
	}

	public static String getPermutation2(int n, int k) {
		char[] arr = new char[n];
		int pro = 1;
		for (int i = 0; i < n; ++i) {
			arr[i] = (char) ('1' + i);
			pro *= (i + 1);
		}
		k = k - 1;
		k %= pro;
		pro /= n;
		for (int i = 0; i < n - 1; ++i) {
			int selectI = k / pro;
			k = k % pro;
			pro /= (n - i - 1);
			int temp = arr[selectI + i];
			for (int j = selectI; j > 0; --j) {
				arr[i + j] = arr[i + j - 1];
			}
			arr[i] = (char) temp;
		}
		return String.valueOf(arr);
	}

	public static String getPermutation(int n, int k) {
		// get permutation num
		int[] permutation = new int[n];
		permutation[0] = 1;
		for (int i = 1; i < n; ++i) {
			permutation[i] = permutation[i - 1] * (i + 1);
		}

		// num list that can be used
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= n; ++i) {
			list.add(i);
		}

		// process
		StringBuilder sb = new StringBuilder();
		int pos = n - 1;
		k -= 1;
		while (pos > 0) {
			int index = k / permutation[pos - 1];
			sb.append(list.get(index));
			list.remove(index);
			k = k % permutation[pos];
			--pos;
		}
		sb.append(list.get(0));
		return sb.toString();
	}
}