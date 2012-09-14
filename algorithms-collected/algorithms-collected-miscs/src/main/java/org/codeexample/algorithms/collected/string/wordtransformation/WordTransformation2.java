package org.codeexample.algorithms.collected.string.wordtransformation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class WordTransformation2 {

	public static HashMap<String, Integer> hm;
	public static boolean v[];
	public static LinkedList<Integer> matrix[];
	public static Queue<Integer> q;
	public static String org[];

	public static boolean check(String a, String b) {

		int count = 0;
		count += Math.abs(a.length() - b.length());
		if (b.length() >= a.length()) {
			for (int i = 0; i < a.length(); i++)
				if (a.charAt(i) != b.charAt(i))
					count++;
		} else
			for (int i = 0; i < b.length(); i++)
				if (a.charAt(i) != b.charAt(i))
					count++;

		return count == 1;
	}

	public static int bfs(int i, int end) {
		q.add(i);
		q.add(0);
		v[i] = true;
		int current = 0;
		int cost = 0;
		while (!q.isEmpty()) {

			current = q.poll();
			cost = q.poll();
			for (int ver : matrix[current])
				if (!v[ver]) {
					if (ver == end)
						return cost + 1;
					v[ver] = true;
					q.add(ver);
					q.add(cost + 1);
				}
		}
		return 0;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		matrix = new LinkedList[205];
		for (int i = 0; i < 205; i++)
			matrix[i] = new LinkedList<Integer>();
		q = new LinkedList<Integer>();
		hm = new HashMap<String, Integer>();
		org = new String[205];
		v = new boolean[205];

		int k = Integer.parseInt(reader.readLine());
		String st;
		String s[];
		int i = 0;
		String temp = reader.readLine();
		boolean ff = false;
		while (k-- > 0) {
			i = 0;
			if (ff)
				System.out.println();
			ff = true;
			hm.clear();
			while (!(st = reader.readLine()).equals("*")) {

				hm.put(st, i);
				org[i] = st;
				for (int j = 0; j < i; j++) {
					if (check(st, org[j])) {
						matrix[i].add(j);
						matrix[j].add(i);
					}

				}
				i++;
			}

			while (((st = reader.readLine()) != null) && (!st.equals(temp))) {

				q.clear();
				for(int l=0;l<=i;l++)
					v[l]=false;
				s = st.split(" ");
				System.out.println(s[0] + " " + s[1] + " "
						+ bfs(hm.get(s[0]), hm.get(s[1])));
			}
			for (int j = 0; j < i; j++) {
				
				matrix[j].clear();

			}
		}

	}

}

