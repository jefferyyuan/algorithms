package org.codeexample.algorithms.collected.wordpuzzle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.POS;

public class WordPuzzleIteratively {

	private RAMDictionary dict;

	public WordPuzzleIteratively(String dictFile) throws IOException,
			InterruptedException {
		init(dictFile);
	}

	private void init(String dictFile) throws IOException, InterruptedException {
		// construct the dictionary object and open it
		dict = new RAMDictionary(new File(dictFile), ILoadPolicy.NO_LOAD);
		dict.open();
		dict.load(true);
	}

	public void close() {
		dict.close();
	}

	public List<String> getAllValidWords(String str, int len) {
		long start = new Date().getTime();
		List<String> result = new ArrayList<>();
		List<String> nStrs = getNChars(str, len);

		for (String aStr : nStrs) {
			result.addAll(getValidWords(aStr));
		}
		System.out.println("getAllValidWords took "
				+ (new Date().getTime() - start) + " mill seconds, found " + result);
		return result;
	}

	public List<String> getMaxLenValidWordThreaded(final String str)
			throws MalformedURLException, IOException, InterruptedException {
		final ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

		if (str == null || str.length() == 0)
			return new ArrayList<>();
		long start = new Date().getTime();

		for (int len = str.length(); len > 0; len--) {
			list.add(new ArrayList<String>());
		}
		ExecutorService executor = Executors.newFixedThreadPool(str.length());
		for (int len = str.length(); len > 0; len--) {
			final int length = len;
			Runnable task = new Runnable() {
				@Override
				public void run() {

					long innerStart = new Date().getTime();
					ArrayList<String> result = (ArrayList<String>) getAllValidWords(str,
							length);
					System.out.println("getMaxLenValidWordThreaded len: " + length
							+ " took " + (new Date().getTime() - innerStart) + ", found "
							+ result.size() + " : " + result);

					list.set(length, result);
				}
			};
			executor.submit(task);
		}

		executor.shutdown();
		boolean terminated = executor.awaitTermination(Long.MAX_VALUE,
				TimeUnit.SECONDS);
		if (!terminated) {
			throw new RuntimeException("Request takes too much time");
		}

		List<String> result = new ArrayList<>();

		int index = str.length() - 1;
		for (; index >= 0; index--) {
			result = list.get(index);
			if (!result.isEmpty())
				break;
		}
		System.out.println("getMaxLenValidWord took "
				+ (new Date().getTime() - start) + ", len: " + (index + 1) + " found "
				+ result);
		return result;
	}

	public List<String> getMaxLenValidWord(String str)
			throws MalformedURLException, IOException {
		long start = new Date().getTime();
		List<String> list = new ArrayList<>();
		for (int len = str.length(); len > 0; len--) {
			long innerStart = new Date().getTime();
			list = getAllValidWords(str, len);
			System.out.println("getMaxLenValidWord len: " + len + " took "
					+ (new Date().getTime() - innerStart));
			if (!list.isEmpty()) {
				break;
			}
		}
		System.out.println("getMaxLenValidWord took "
				+ (new Date().getTime() - start));
		return list;
	}

	/**
	 * Idea comes from book: Cracking code interview.
	 */
	private List<String> getNChars(String strs, int len) {
		// for each char, it can be in the response or not, if
		List<String> nStrs = new ArrayList<String>();
		long max = 1 << strs.length();
		for (int i = 0; i < max; i++) {
			StringBuilder sb = new StringBuilder(len);
			int k = i;
			int index = 0;
			while (k > 0) {
				if ((k & 1) > 0) {
					sb.append(strs.charAt(index));
				}
				k >>= 1;
				index++;
			}

			if (sb.length() == len) {
				nStrs.add(sb.toString());
			}
		}
		return nStrs;
	}

	/**
	 * Ideas comes from: The Counting QuickPerm Algorithm:
	 * http://www.freewebs.com/permute/quickperm.html<br>
	 * http://stackoverflow.com/questions/11915026/permutations-of-a-string-using-
	 * iteration
	 */
	private List<String> getValidWords(String str) {
		List<String> validWords = new ArrayList<>();
		if (isValidWorld(str)) {
			validWords.add(str);
		}
		char[] a = str.toCharArray();
		int n = a.length;
		int[] p = new int[n]; // Index control array initially all zeros
		int i = 1;
		while (i < n) {
			if (p[i] < i) {
				int j = ((i % 2) == 0) ? 0 : p[i];
				swap(a, i, j);
				String word = toString(a);
				if (isValidWorld(word)) {
					validWords.add(word);
				}
				p[i]++;
				i = 1;
			} else {
				p[i] = 0;
				i++;
			}
		}
		return validWords;
	}

	private static String toString(char[] a) {
		StringBuilder builder = new StringBuilder();
		builder.append(a);
		return builder.toString();
	}

	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) throws Exception {
		String str = "oivmwujbecn";
		int len = 7;

		// str = "abcd";
		// len = 2;

		WordPuzzleIteratively instance = new WordPuzzleIteratively(
				"C:/Program Files (x86)/WordNet/2.1/dict");

		List<String> list = null;
		// getAllValidWords took 755 mill seconds, found [combine, newcomb]
		list = instance.getAllValidWords(str, len);
		System.out.println(list.size());
		System.out.println(list);

		// getMaxLenValidWord took 17601, len: 8 found [combine, newcomb]
		list = instance.getMaxLenValidWordThreaded(str);
		System.out.println(list.size());
		System.out.println(list);
		
		instance.close();
	}

	// public List<IWord> getMaxLenValidWord(String str)
	// throws MalformedURLException, IOException {
	// for (int len = str.length(); len > 0; len--) {
	// List<IWord> list = getNCombinationValidWord(str, len);
	// if (!list.isEmpty()) {
	// return list;
	// }
	// }
	//
	// return new ArrayList<>();
	// }

	private boolean isValidWorld(String word) {
		Boolean isWord = false;
		IIndexWord idxWord = dict.getIndexWord(word, POS.NOUN);
		if (idxWord != null && !idxWord.getWordIDs().isEmpty()) {
			isWord = true;
		}
		return isWord;
	}

}
