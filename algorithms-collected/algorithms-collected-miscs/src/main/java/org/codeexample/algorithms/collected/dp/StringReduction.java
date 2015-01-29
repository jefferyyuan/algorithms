package org.codeexample.algorithms.collected.dp;

// all 10 test cases passed
import java.util.Arrays;
import java.util.Scanner;

public class StringReduction {

	int minimum = 1;
	int value = 1;

	private boolean reduce(String s1) {
		char[] array = s1.toCharArray();
		int len = array.length;
		minimum = array.length;
		boolean flag;
		String cat;
		if (len == 1) {
			value = 1;
			return true;
		} else if (array[0] == array[1] && len == 2) {
			value = 2;
			return true;
		}

		for (int i = 0; i < len - 1; i++) {
			if (array[i + 1] != array[i]) {
				String new_string = Character.toString(array[i]).concat(
						Character.toString(array[i + 1]));
				char reduce = other(new_string);
				String sub1 = "";
				String sub2 = Character.toString(reduce);
				String sub3 = "";
				if (i == 1) {
					sub1 = Character.toString(array[0]);
				} else if (i > 1) {
					sub1 = s1.substring(0, i);
				}
				if (i + 2 < len - 1) {
					sub3 = s1.substring(i + 2, len);
				} else if (i + 2 == len - 1) {
					sub3 = Character.toString(array[i + 2]);
				}
				cat = sub1 + sub2 + sub3;
				flag = reduce(cat);
				if (flag) {
					minimum = Math.min(minimum, value);
					return flag;
				}
			}
		}
		return false;
	}

	private char other(String s1) {
		char ret_value = 'b';
		if (s1.equalsIgnoreCase("bc") || s1.equalsIgnoreCase("cb")) {
			ret_value = 'a';
		} else if (s1.equalsIgnoreCase("ab") || s1.equalsIgnoreCase("ba")) {
			ret_value = 'c';
		}
		return ret_value;
	}

	public static void main(String[] args) {
		Scanner scan;
		StringReduction obj = new StringReduction();
		scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int i = 0; i < T; i++) {
			String s1 = scan.next();
			obj.reduce(s1);
			System.out.println(obj.minimum);
		}
	}

}