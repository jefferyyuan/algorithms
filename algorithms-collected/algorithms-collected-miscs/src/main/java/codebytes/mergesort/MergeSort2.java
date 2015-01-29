package codebytes.mergesort;

import java.util.Arrays;

public class MergeSort2 {

	public static void merge(int[] orig, int[] aux, int start, int mid, int end) {
		int i, j, z = start;

		if (orig[mid] <= orig[mid + 1])
			return; // Point #1

		for (i = start, j = mid + 1; i != mid + 1 || j != end + 1;) {
			if (i == mid + 1)
				while (j != end + 1) {
					aux[z++] = orig[j++];
				}
			else if (j == end + 1)
				while (i != mid + 1) {
					aux[z++] = orig[i++];
				}
			else if (orig[i] <= orig[j])
				aux[z++] = orig[i++];
			else
				aux[z++] = orig[j++];
		}
	}

	public static void sort(int[] orig, int[] aux, int start, int end) {
		if (start >= end)
			return;
		int mid = (start + end) / 2;
		sort(aux, orig, start, mid); // Point #2
		sort(aux, orig, mid + 1, end);
		merge(orig, aux, start, mid, end);
	}

	public static void main(String[] args) {
		int array[] = { 5, 4, 3, 2, 1 };
		int aux[] = new int[array.length];
		System.arraycopy(array, 0, aux, 0, array.length); // Be careful, both
															// arrays must be
															// the same
															// initially!
		sort(array, aux, 0, array.length - 1);
		System.out.println(Arrays.toString(aux));
	}
}