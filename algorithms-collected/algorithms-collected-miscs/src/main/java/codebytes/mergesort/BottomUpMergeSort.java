package codebytes.mergesort;

import java.util.Arrays;

public class BottomUpMergeSort {

	public static void merge(int[] orig, int[] aux, int start, int mid, int end) {
		int i, j, z = start;

		if (orig[mid] <= orig[mid + 1])
			return;

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
		System.out.println(Arrays.toString(orig));
		System.out.println("start = " + start + " mid = " + mid + " end = "
				+ end);
		System.out.println(Arrays.toString(aux) + "\n");
		System.arraycopy(aux, start, orig, start, end - start + 1);
	}

	public static void sort(int[] orig, int[] aux, int start, int end) {
		int N = orig.length;
		for (int sz = 1; sz < N; sz *= 2) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(orig, aux, lo, lo + sz - 1,
						Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}

	public static void main(String[] args) {
		int array[] = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int aux[] = new int[array.length];
		sort(array, aux, 0, array.length - 1);
	}
}