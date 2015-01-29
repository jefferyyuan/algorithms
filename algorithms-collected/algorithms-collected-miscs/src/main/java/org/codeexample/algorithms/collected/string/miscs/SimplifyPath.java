package org.codeexample.algorithms.collected.string.miscs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

public class SimplifyPath {
	public static void main(String[] args) {
		Deque<Integer> deque = new ArrayDeque<Integer>();
		deque.push(1);
		deque.push(2);
		for (Integer i : deque) {
			System.out.println(i);
		}
	}

	public static String simplifyPath2(String path) {
		if (path == null || path.length() == 0)
			return "";
		Deque<String> stack = new ArrayDeque<String>();
		String[] directories = path.split("/"); // Separate path by "/"
		for (String dir : directories) {
			if (dir.length() != 0) {
				if (dir.equals("..")) { // Go up to its parent
					if (!stack.isEmpty()) {
						stack.pop();
					} else {
						//
						stack.push(dir);
					}
				} else if (!dir.equals(".")) {
					stack.push(dir);
				}
			}
			// No redundant "/"
		}
		// Construct simplified path
		if (stack.isEmpty()) // No subdirectory
			return "/";
		StringBuilder result = new StringBuilder();
		for (String s : stack)
			result.append("/" + s);
		return result.toString();
	}

	public static String simplifyPath1(String path) {
		if (path == null)
			return null;
		if (path.length() == 0)
			return "/";
		int n = path.length();
		Stack<String> stack = new Stack<String>();
		String[] directories = path.split("/"); // Separate path by "/"
		for (String dir : directories) {
			if (dir.length() != 0) {
				if (dir.equals("..")) { // Go up to its parent
					if (!stack.empty())
						stack.pop();
				} else if (!dir.equals(".")) { // Enter its subdirectory
					stack.push(dir);
				}
			}
			// No redundant "/"
		}
		// Construct simplified path
		if (stack.empty()) // No subdirectory
			return "/";
		StringBuilder result = new StringBuilder();
		for (String s : stack.toArray(new String[stack.size()]))
			result.append("/" + s);
		return result.toString();
	}
}
