package org.codeexample.algorithms.collected.misc;

public class IsOneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if (Math.abs(s.length() - t.length()) > 1)
			return false;
		if (s.length() == t.length())
			return isOneSameLength(s, t);
		if (s.length() > t.length())
			return isOneDifLength(t, s);
		else
			return isOneDifLength(s, t);
	}

	private boolean isOneDifLength(String s, String l) {
		int i = 0;
		while (i < s.length() && s.charAt(i) == l.charAt(i)) {
			++i;
		}
		if (i == s.length())
			return true;
		return s.substring(i).equals(l.substring(i + 1));
	}

	private boolean isOneSameLength(String s, String t) {
		int dif = 0;
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) != t.charAt(i))
				++dif;
		}
		return dif == 1;
	}
}
