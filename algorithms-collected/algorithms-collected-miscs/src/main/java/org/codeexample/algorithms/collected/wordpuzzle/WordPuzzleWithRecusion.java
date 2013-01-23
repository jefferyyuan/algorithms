package org.codeexample.algorithms.collected.wordpuzzle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.POS;

public class WordPuzzleWithRecusion {

	private RAMDictionary dict;

	public WordPuzzleWithRecusion(String dictFile) throws IOException,
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

	/**
	 * @return Not null.
	 */
	public List<String> getAllValidWords(String str, int length)
			throws MalformedURLException, IOException {
		List<String> lists = new ArrayList<String>();
		long start = new Date().getTime();
		HashSet<String> result = getNCombination(str, length);
		System.out.println("getAllValidWords took "
				+ (new Date().getTime() - start) + " mill seconds.");

		for (String word : result) {
			if (isValidWorld(word)) {
				lists.add(word);
			}
		}
		System.out.println("getAllValidWords took "
				+ (new Date().getTime() - start) + " mill seconds.");
		return lists;
	}

	public List<String> getMaxLenValidWord(String str)
			throws MalformedURLException, IOException {
		long start = new Date().getTime();
		List<String> list = new ArrayList<>();
		for (int len = str.length(); len > 0; len--) {
			list = getAllValidWords(str, len);
			if (!list.isEmpty()) {
				return list;
			}
		}
		System.out.println("getMaxLenValidWord took "
				+ (new Date().getTime() - start) + " mill seconds, found " + list);
		return list;
	}

	/**
	 * Solve the problem using recursion, need a lot of memory.
	 */
	private HashSet<String> getNCombination(String strs, int len) {
		HashSet<String> result = new HashSet<String>();
		if (strs == null) { // error case
			return result;
		}
		if (len == 0 || len > strs.length())
			return result;

		if (strs.length() == 1 && len == 1) { // base case
			result.add(strs);
			return result;
		}

		HashSet<String> result1 = getNCombination(strs.substring(1), len);

		result.addAll(result1);

		String firstStr = strs.substring(0, 1);
		if (firstStr.length() == len) {
			result.add(firstStr);
		} else {
			HashSet<String> result2 = getNCombination(strs.substring(1), len - 1);
			for (String tmp : result2) {
				for (int i = 0; i < tmp.length(); i++) {
					String tmpResult = tmp.substring(0, i) + firstStr + tmp.substring(i);
					result.add(tmpResult);
				}

				result.add(tmp + firstStr);
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		String str = "oivmwujbecn";
		int length = 7;

		// getAllValidWords took 5095 mill seconds.
		WordPuzzleWithRecusion instance = new WordPuzzleWithRecusion(
				"C:/Program Files (x86)/WordNet/2.1/dict");
		System.out.println(instance.getAllValidWords(str, length));

		// This will throw OOM
		System.out.println(instance.getMaxLenValidWord(str));
		instance.close();
	}

	private boolean isValidWorld(String word) {
		Boolean isWord = false;
		IIndexWord idxWord = dict.getIndexWord(word, POS.NOUN);
		if (idxWord != null && !idxWord.getWordIDs().isEmpty()) {
			isWord = true;
		}
		return isWord;
	}
}
