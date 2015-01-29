/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codeexample.algorithms.collected.dp;

/**
 *
 * @author shrikantk
 */
public class WordWrap2 {

	void wrapthis(String para, int w) {
		String c[] = para.split(" ");
		int n = c.length;
		int cost[] = new int[n];
		int[][] espace = new int[n][n];
		int line[] = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				int t = 0;
				if (i == j) {
					t = c[i].length();
				} else {
					if (i > j) {
						for (int k = j; k <= i; k++) {
							t = t + c[k].length();
						}
					} else {
						for (int k = i; k <= j; k++) {
							t = t + c[k].length();
						}
					}

				}

				// System.out.print(" t:"+t);
				if (t > w) {
					espace[i][j] = -1;
					espace[j][i] = -1;
				} else {
					espace[i][j] = w - t - (i - j);
					espace[j][i] = w - t - (i - j);
				}
				System.out.print(" " + espace[i][j]);
			}
			System.out.println();
		}
		int es = w - c[0].length();
		cost[0] = es * es * es;
		for (int j = 1; j < n; j++) {
			int t;
			int tl = c[j].length();
			cost[j] = Integer.MAX_VALUE;
			for (int i = 1; i <= j; i++) {
				if (espace[i][j] != -1) {
					t = cost[i - 1] + espace[i][j] * espace[i][j]
							* espace[i][j];
					// System.out.println("t:"+t);
					if (t < cost[j]) {
						cost[j] = t;
						line[j] = i;
					}
				}
			}
			System.out.println("optimal line" + line[j]);
		}
		System.out.println("optimal cost" + cost[n - 1]);
		int pre;
		System.out.print(" " + c[0]);
		pre = 0;
		for (int i = 1; i < n; i++) {

			if (line[i] == pre) {
				System.out.print(" " + c[i]);
			} else {
				System.out.println();
				System.out.print(" " + c[i]);
				pre = line[i];
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		String para = "aaa bb cc ddddd";
		WordWrap2 o = new WordWrap2();
		o.wrapthis(para, 6);

//		String wodehouse = "Old Mr MacFarland (_said Henry_) started the place fifteen years ago. He was a widower with one son and what you might call half a daughter. That's to say, he had adopted her. Katie was her name, and she was the child of a dead friend of his. The son's name was Andy. A little freckled nipper he was when I first knew him--one of those silent kids that don't say much and have as much obstinacy in them as if they were mules. Many's the time, in them days, I've clumped him on the head and told him to do something; and he didn't run yelling to his pa, same as most kids would have done, but just said nothing and went on not doing whatever it was I had told him to do. That was the sort of disposition Andy had, and it grew on him. Why, when he came back from Oxford College the time the old man sent for him--what I'm going to tell you about soon--he had a jaw on him like the ram of a battleship. Katie was the kid for my money. I liked Katie. We all liked Katie.";
//
//		o.wrapthis(wodehouse, 120);

	}
}