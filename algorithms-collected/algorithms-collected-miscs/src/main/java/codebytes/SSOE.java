package codebytes;

import java.util.*;

class SSOE {

	public static ArrayList<Integer> getPrimes(int sqrt) {
		int q = sqrt;
		ArrayList<Integer> primes = new ArrayList<>();
		if (q < 2) {
			return primes;
		}
		if (q % 2 == 0) {
			q--;
		}
		boolean check[] = new boolean[q + 1];
		for (int i = 3; i <= check.length - 1; i += 2) {
			check[i] = true;
		}
		for (int i = 1; i <= check.length - 1; i += 2) {
			if (check[i]) {
				primes.add(i);
				for (int j = i * i; j <= check.length - 1; j += i) {
					check[j] = false;
				}
			}
		}
		return primes;
	}

	static List<Integer> getPrimes(int p, int q) {
		ArrayList<Integer> primeList = new ArrayList<>();
		if (p <= 2) {
			if (q >= 2) {
				primeList.add(2);
			}
			p = 3;
		}
		if (p % 2 == 0) {
			p++;
		}
		if (q % 2 == 0) {
			q--;
		}
		if (q >= p) {

			int sqrt = (int) Math.sqrt(q);
			ArrayList<Integer> primes = getPrimes(sqrt);
			boolean check[] = new boolean[q - p + 1];
			for (int i = 0; i <= check.length - 1; i += 2) {
				check[i] = true;
			}
			for (int prime : primes) {
				int index, rem = p % prime;
				if (rem == 0) {
					index = rem;
				} else {
					index = prime - rem;
				}
				if (index + p != prime && index < check.length) {
					check[index] = false;
				} else {
					index += prime;
				}
				while (index <= check.length - 1) {
					check[index] = false;
					index += prime;
				}
			}

			for (int i = 0; i <= check.length - 1; ++i) {
				if (check[i]) {
					primeList.add(i + p);
				}
			}
		}
		return primeList;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getPrimes(1, 100));
	}
}