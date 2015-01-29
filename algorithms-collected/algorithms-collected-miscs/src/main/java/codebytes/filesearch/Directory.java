package codebytes.filesearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class PPrint {
	public static String pformat(Collection<?> c) {
		if (c.size() == 0)
			return "[]";
		StringBuilder result = new StringBuilder("[");
		for (Object elem : c) {
			if (c.size() != 1)
				result.append("\n  ");
			result.append(elem);
		}
		if (c.size() != 1)
			result.append("\n");
		result.append("]");
		return result.toString();
	}

	public static void pprint(Collection<?> c) {
		System.out.println(pformat(c));
	}

	public static void pprint(Object[] c) {
		System.out.println(pformat(Arrays.asList(c)));
	}
}

class TextFile extends ArrayList<String> {
	// Read a file as a single string:
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(
					fileName).getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	// Read a file, split by any regular expression:
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		// Regular expression split() often leaves an empty
		// String at the first position:
		if (get(0).equals(""))
			remove(0);
	}

	// Normally read by lines:
	public TextFile(String dir) {
		this(dir, "\n");
	}

}

public final class Directory {
	public static class TreeInfo implements Iterable<File> {
		public List<File> files = new ArrayList<File>();
		public List<File> dirs = new ArrayList<File>();

		public Iterator<File> iterator() {
			return files.iterator();
		}

		void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}

		public String toString() {
			return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: "
					+ PPrint.pformat(files);
		}
	}

	public static TreeInfo walk(String start, String regex) {
		return recurseDirs(new File(start), regex);
	}

	public static TreeInfo walk(File start, String regex) {
		return recurseDirs(start, regex);
	}

	public static TreeInfo walk(File start) {
		return recurseDirs(start, ".*");
	}

	public static TreeInfo walk(String start) {
		return recurseDirs(new File(start), ".*");
	}

	static List<String> words = new ArrayList<String>();

	static TreeInfo recurseDirs(File startDir, String regex) {
		TreeInfo result = new TreeInfo();
		for (File item : startDir.listFiles()) {
			if (item.isDirectory()) {
				result.dirs.add(item);
				result.addAll(recurseDirs(item, regex));
			} else if (item.getName().matches(regex)) {
				result.files.add(item);
			}

			if (item.isFile()) {

				TextFile tf = new TextFile(item.getAbsolutePath(), "\\W+");
				if (!Collections.disjoint(tf, words)) {
					if (!result.files.contains(item))
						result.files.add(item);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("Usage: java Directory <dir to search in> <regex pattern to match in file names> <word to look for in files> <more words> <more...>");
		} else {
			for (int i = 2; i < args.length; ++i) {
				words.add(args[i]);
			}
			System.out.println(walk(args[0], args[1]));
		}
	}
}