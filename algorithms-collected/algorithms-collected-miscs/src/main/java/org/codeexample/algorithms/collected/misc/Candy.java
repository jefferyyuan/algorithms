package org.codeexample.algorithms.collected.misc;

import java.util.Arrays;

public class Candy {

	public int candy2(int[] ratings) {
		if (ratings == null || ratings.length == 0) {
			return -1;
		}

		int[] candy = new int[ratings.length];
		int sum = 0;
		Arrays.fill(candy, 1);

		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candy[i] = candy[i - 1] + 1;
			}
		}

		for (int i = ratings.length - 1; i > 0; i--) {
			if (ratings[i - 1] > ratings[i] && candy[i - 1] <= candy[i]) {
				candy[i - 1] = candy[i] + 1;
			}
			sum += candy[i];
		}

		sum += candy[0];
		return sum;
	}

	public int candy(int[] ratings) {
		int[] candies = new int[ratings.length];
		if (ratings.length <= 1)
			return ratings.length;
		candies[0] = 1;
		for (int i = 1; i < candies.length; ++i) {
			if (ratings[i] > ratings[i - 1])
				candies[i] = candies[i - 1] + 1;
			if (ratings[i] == ratings[i - 1])
				candies[i] = 1;
			if (ratings[i] < ratings[i - 1])
				candies[i] = candies[i - 1] - 1;
			if ((i + 1 < ratings.length) && ratings[i] < ratings[i - 1]
					&& ratings[i] <= ratings[i + 1])
				adjust(ratings, candies, i);
		}
		if (ratings[candies.length - 1] < ratings[candies.length - 2])
			adjust(ratings, candies, candies.length - 1);
		int rst = 0;
		for (int i = 0; i < candies.length; ++i) {
			rst += candies[i];
		}
		return rst;
	}

	public void adjust(int[] ratings, int[] candies, int idx) {
		int diff = 1 - candies[idx];
		int i = idx;
		while (i > 0 && ratings[i - 1] > ratings[i]) {
			candies[i] += diff;
			i--;
		}
		if (diff > 0)
			candies[i] += diff; // the top can only be increased;
	}
}
