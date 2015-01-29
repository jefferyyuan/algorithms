package org.codeexample.algorithms.collected.sort;

import java.util.Collections;
import java.util.Scanner;

import com.google.common.primitives.Ints;

/** Class RadixSort **/
public class RadixSort1 {

	public static int[] radixSort(int[] old) {

		for (int shift = Integer.SIZE - 1; shift > -1; shift--) { // Loop for
																	// every bit
																	// in the
																	// integers

			int[] tmp = new int[old.length]; // the array to put the partially
												// sorted array into
			int j = 0; // The number of 0s
			for (int i = 0; i < old.length; i++) { // Move the 0s to the new
													// array, and the 1s to the
													// old one
				boolean move = old[i] << shift >= 0; // If there is a 1 in the
														// bit we are testing,
														// the number will be
														// negative
				if (shift == 0 ? !move : move) { // If this is the last bit,
													// negative numbers are
													// actually lower
					tmp[j] = old[i];
					j++;
				} else { // It's a 1, so stick it in the old array for now
					old[i - j] = old[i];
				}
			}
			for (int i = j; i < tmp.length; i++) { // Copy over the 1s from the
													// old array
				tmp[i] = old[i - j];
			}
			old = tmp; // And now the tmp array gets switched for another round
						// of sorting
		}
		return old;
	}

	/** Radix Sort function **/
	public static void sort(int[] a) {
		int m = Collections.max(Ints.asList(a)), exp = 1;
		while (m / exp > 0) {
			countSort(a, exp);
			exp *= 10;
		}
	}

	public static void countSort(int[] a, int exp) {
		int[] bucket = new int[10];
		int i, n = a.length;
		int[] b = new int[10];
		for (i = 0; i < n; i++)
			bucket[(a[i] / exp) % 10]++;
		for (i = 1; i < 10; i++)
			bucket[i] += bucket[i - 1];
		for (i = n - 1; i >= 0; i--)
			b[--bucket[(a[i] / exp) % 10]] = a[i];
		for (i = 0; i < n; i++)
			a[i] = b[i];
	}

	/** Main method **/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Radix Sort Test\n");
		int n, i;
		/** Accept number of elements **/
		System.out.println("Enter number of integer elements");
		n = scan.nextInt();
		/** Create integer array on n elements **/
		int arr[] = new int[n];
		/** Accept elements **/
		System.out.println("\nEnter " + n + " integer elements");
		for (i = 0; i < n; i++)
			arr[i] = scan.nextInt();
		/** Call method sort **/
		sort(arr);
		/** Print sorted Array **/
		System.out.println("\nElements after sorting ");
		for (i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}