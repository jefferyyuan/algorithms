package org.codeexample.algorithms.collected.string.permutation;

public class PermutationsIter {

	public static void main(String[] args) throws Exception {
		getPerms("abc");
	}

	/**
	 * The Counting QuickPerm Algorithm:
	 * http://www.freewebs.com/permute/quickperm.html<br>
	 * http://stackoverflow.com/questions/11915026/permutations-of-a-string-using-
	 * iteration
	 */
	public static void getPerms(String s) {
		// Print initial string, as only the alterations will be printed later
		System.out.println(s);
		char[] a = s.toCharArray();
		int n = a.length;
		int[] p = new int[n]; // Index control array initially all zeros
		int i = 1;
		while (i < n) {
			if (p[i] < i) {
				int j = ((i % 2) == 0) ? 0 : p[i];
				swap(a, i, j);
				// Print current
				System.out.println(toString(a));
				p[i]++;
				i = 1;
			} else {
				p[i] = 0;
				i++;
			}
		}
	}

	private static String toString(char[] a) {
		StringBuilder builder = new StringBuilder();
		builder.append(a);
		return builder.toString();
	}

	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
