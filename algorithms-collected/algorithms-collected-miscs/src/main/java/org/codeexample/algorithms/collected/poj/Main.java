package org.codeexample.algorithms.collected.poj;

//* @author:
import java.util.*;

class Cloth {// 表示一件要洗的衣服
	int time;// 洗这件衣服的时间
	String color;// 这件衣服的颜色

	public Cloth() {
		this.time = 0;
		this.color = null;
	}

	public Cloth(int tiem, String color) {
		this.time = time;
		this.color = color;
	}
}

public class Main {
	private int n;// 颜色种类数
	private int m;// 衣服件数
	private String colors[];// 颜色数组，保存所有的颜色
	private Cloth cloths[];// 衣服数组，保存所有要洗的衣服
	private int res;// 洗完全部衣服所需的最少时间

	public Main(int n, int m, String colors[], Cloth cloths[]) {
		this.n = n;
		this.m = m;
		this.colors = colors;
		this.cloths = cloths;
		res = 0;
	}

	public void dp() {
		int r[] = new int[n];// r[j]表示一个人洗colors[j]这种颜色的衣服的总的洗衣时间
		int dp[] = new int[50000];
		/*
		 * 
		 * dp[j]表示在时间j内, 一个人洗某种颜色的衣服，一件件的洗. 最大洗衣时间。
		 * 
		 * 总的衣服数< 100,每件衣服的洗衣时间< 1000，100*1000/2=50000
		 */
		for (int i = 0; i < m; i++) {// 对每一件衣服
			for (int j = 0; j < n; j++) {// 看它是哪一种的颜色
				if (cloths[i].color.equals(colors[j])) {
					r[j] += cloths[i].time;// 计算这种颜色的衣服总的洗衣时间
					break;
				}
			}
		}
		for (int i = 0; i < n; i++) {// 对每一种颜色（n个01背包问题）
			for (int j = 0; j <= r[i] / 2; j++)
				dp[j] = 0;
			for (int j = 0; j < m; j++) {// 在所有衣服中找这种颜色的衣服
				if (cloths[j].color.equals(colors[i])) {
					for (int k = r[i] / 2; k >= cloths[j].time; k--) {
						if (dp[k] < dp[k - cloths[j].time] + cloths[j].time)
							dp[k] = dp[k - cloths[j].time] + cloths[j].time;
					}
				}
			}
			res += r[i] - dp[r[i] / 2];// 洗完全部衣服所需的最少时间=洗各种颜色衣服所需最少时间的和。
		}
		System.out.println(res);
	}

	public static void main(String args[]) {
		String colors[];
		Cloth cloths[];
		Scanner in = new Scanner(System.in);
		while (true) {
			int n = in.nextInt();
			int m = in.nextInt();
			if (n == 0 && m == 0)
				break;
			colors = new String[n];
			cloths = new Cloth[m];
			for (int i = 0; i < n; i++)
				colors[i] = in.next();
			for (int i = 0; i < m; i++) {
				cloths[i] = new Cloth();
				cloths[i].time = in.nextInt();
				cloths[i].color = in.next();
			}
			Main mm = new Main(n, m, colors, cloths);
			mm.dp();
		}
	}
}
