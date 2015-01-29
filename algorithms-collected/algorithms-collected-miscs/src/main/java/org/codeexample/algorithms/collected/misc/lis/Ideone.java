package org.codeexample.algorithms.collected.misc.lis;

/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
	public static int ceil(int[] a, int l, int r, int key) {
		int m;
		while (r - l > 1) {
			m = l + (r - l) / 2;
			if (a[m] > key)
				r = m;
			else
				l = m;
		}
		return r;
	}

	public static int lis(int[] a, int n) {
		int[] lis = new int[n];
		int[] lds = new int[n];
		int[] top = new int[n];
		int i, curr, max, len;
		top[0] = a[0];
		len = 1;
		for (i = 0; i < n; i++) {
			if (a[i] > top[len - 1]) {
				top[len++] = a[i];
				lis[i] = len;
			} else {
				lis[i] = ceil(top, -1, len - 1, a[i]) + 1;
				top[ceil(top, -1, len - 1, a[i])] = a[i];
			}
		}
		top[0] = a[n - 1];
		len = 1;
		for (i = n - 2; i >= 0; i--) {
			if (a[i] > top[len - 1]) {
				top[len++] = a[i];
				lds[i] = len;
			} else {
				lds[i] = ceil(top, -1, len - 1, a[i]) + 1;
				top[ceil(top, -1, len - 1, a[i])] = a[i];
			}
		}
		max = 0;
		for (i = 0; i < n; i++) {
			curr = lis[i] + lds[i] - 1;
			if (curr > max)
				max = curr;
		}
		return max;
	}

	public static void main(String args[]) {
		int arr[] = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		int n = arr.length;
		System.out.println(lis(arr, n));

	}
}