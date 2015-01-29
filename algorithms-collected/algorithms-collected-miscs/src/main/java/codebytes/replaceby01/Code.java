package codebytes.replaceby01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Code {
	public static void print(String s, char[] a) {
		System.out.println();

		int j = 0;
		for (int i = 0; i < s.length(); ++i)
			if (s.charAt(i) == '?')
				System.out.print(a[j++]);
			else
				System.out.print(s.charAt(i));
	}

	public static void process(String string, char[] a, int len, int cIndex) {
		if (cIndex == len) {
			print(string, a);
			return;
		}

		a[cIndex] = '0';
		process(string, a, len, cIndex + 1);
		a[cIndex] = '1';
		process(string, a, len, cIndex + 1);
	}

	public static void input(String string) {
		int l = 0;
		for (int i = 0; i < string.length(); ++i)
			if (string.charAt(i) == '?')
				l++;
		char a[] = new char[l];
		process(string, a, l, 0);
	}

	public static List<String> replaceBy0Or1(String src) {
		// store the ? positions
		Objects.requireNonNull(src, "scr can'tt be null");
		List<Integer> replaceIndex = new ArrayList<>();

		for (int i = 0; i < src.length(); i++) {
			if (src.charAt(i) == '?') {
				replaceIndex.add(i);
			}
		}

		StringBuilder sb = new StringBuilder(src);
		List<String> result = new ArrayList<>();
		helper(sb, replaceIndex, 0, result);

		return result;
	}

	public static void helper(StringBuilder sb, List<Integer> replaceIndex,
			int idx, List<String> result) {
		if (idx == replaceIndex.size()) {
			result.add(sb.toString());
			return;
		}

		sb.setCharAt(replaceIndex.get(idx), '0');
		helper(sb, replaceIndex, idx + 1, result);
		sb.setCharAt(replaceIndex.get(idx), '1');
		helper(sb, replaceIndex, idx + 1, result);
	}

	public static void main(String[] args) {
		replaceBy0Or1("?c?o?d?e??").stream().forEach(
				str -> System.out.println(str));
		replaceBy0Or1("???").stream().forEach(str -> System.out.println(str));
		replaceBy0Or1("?").stream().forEach(str -> System.out.println(str));
		replaceBy0Or1("a").stream().forEach(str -> System.out.println(str));
		replaceBy0Or1("").stream().forEach(str -> System.out.println(str));
	}
}