package org.codeexample.algorithms.collected.number.miscs;

public class IsBinaryPalindrome {

	public static void main(String[] args) {
		System.out.println(isPalindrome(5));
		System.out.println(isPalindrome(6));

		System.out.println(isPalindrome(-1));
		System.out.println(isPalindrome(-2));

		System.out.println(getBitSizeOfInteger(1));

		System.out.println(getBitSizeOfInteger(-1));
		// System.err.println(isPalindrome2(0));
		// System.err.println(isPalindrome2(-1));

	}

	public static int getBitSizeOfInteger(int num) {
		int size = 0;
		// shift the number untill it becomes negative(1 followed by 0's)
		while (num != 0) {
			num = num >>> 1;
			size++;
		}
		return size;
	}

	public static boolean isPalindrome(int x) {
		int original = x, reverse = 0;
		while (x != 0) {
			reverse <<= 1;
			reverse += x & 1;
			x >>>= 1;
		}
		return reverse == original;
	}

public static boolean isPalindrome2(int n) {

	int count = 32;
	int right = 1;
	int left = 1 << (32 - 1);
	while (count > 0) {
			int leftBitValue = n & left, rightBitValue = n
				& right;
		if (!((leftBitValue == 0 && rightBitValue == 0) || (leftBitValue != 0 && rightBitValue != 0))) {
			return false;
		}
		left = left >> 1;
		right = right << 1;
		count -= 2;
	}
	return true;
}

	public static int getSizeOfInteger() {
		int size = 1;
		int num = 1;
		// shift the number untill it becomes negative(1 followed by 0's)
		while (num > 0) {
			num = num << 1;
			size++;
		}
		return size;
	}

}
