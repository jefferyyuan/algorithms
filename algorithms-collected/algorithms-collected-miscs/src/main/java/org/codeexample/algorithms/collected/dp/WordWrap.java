package org.codeexample.algorithms.collected.dp;

import java.util.Arrays;
import java.util.ArrayList;

public class WordWrap {
	public WordWrap() {
	}

	public static final char CHAR_EMPTY = ' ';
	public static final String BLANK_SPACE = " ";

	public static void main(String[] args) {
		String wodehouse = "aaa bb cc ddddd";

		ArrayList<String> lines = formatLineBreak(wodehouse, 6);
		for (String line : lines) {
			System.out.println(line);
		}

		System.out.println();
		wodehouse = "Old Mr MacFarland (_said Henry_) started the place fifteen years ago. He was a widower with one son and what you might call half a daughter. That's to say, he had adopted her. Katie was her name, and she was the child of a dead friend of his. The son's name was Andy. A little freckled nipper he was when I first knew him--one of those silent kids that don't say much and have as much obstinacy in them as if they were mules. Many's the time, in them days, I've clumped him on the head and told him to do something; and he didn't run yelling to his pa, same as most kids would have done, but just said nothing and went on not doing whatever it was I had told him to do. That was the sort of disposition Andy had, and it grew on him. Why, when he came back from Oxford College the time the old man sent for him--what I'm going to tell you about soon--he had a jaw on him like the ram of a battleship. Katie was the kid for my money. I liked Katie. We all liked Katie.";

		lines = formatLineBreak(wodehouse, 120);
		for (String line : lines) {
			System.out.println(line);
		}
	}

	/**
	 * Overloaded method called when just the fullText and line size are known
	 * 
	 * @param strFullText
	 *            String
	 * @param iLineSize
	 *            int
	 * @return ArrayList
	 */
	public static ArrayList formatLineBreak(String strFullText, int iLineSize) {
		return formatLineBreak(strFullText, iLineSize, -1);
	}

	/**
	 * Overloaded method called when just the fullText, line size and
	 * totalbodylength are known
	 * 
	 * @param strFullText
	 *            String
	 * @param iLineSize
	 *            int
	 * @param iTotalBodyLen
	 *            int
	 * @return ArrayList
	 */
	public static ArrayList formatLineBreak(String strFullText, int iLineSize,
			int iTotalBodyLen) {
		return formatLineBreak(strFullText, -1, null, false, iLineSize,
				iTotalBodyLen);
	}

	/**
	 *
	 * @param strFullText
	 *            String - this includes header and msg body
	 *
	 * @param iHeaderLen
	 *            int -(optional) if there is no header pass value as -1
	 *
	 * @param strSeparator
	 *            String - (optional) if there is no header(indicated by
	 *            iHeaderLen=-1) anything passed here is ignored. If there is a
	 *            header pass the separator in form on String, ex: ":" or "-" to
	 *            indicate end of header
	 *
	 * @param bAddSeparatorToHeader
	 *            boolean - if true separator is added to end of header else
	 *            separator is removed from the header
	 *
	 * @param iEachLineInBodyLen
	 *            int - the text will be wrapped to n number of chars as
	 *            mentioned by this argument. Words will be wrapped to next line
	 *            if the complete word doesn't fit in to this line.
	 *
	 * @param iTotalBodyLen
	 *            int - (optional) Required total length of msg body - if there
	 *            is no requirement for constraining total length pass this as
	 *            -1
	 *
	 * @return ArrayList - the first entry will be msg header. If there is no no
	 *         requirement for msg header (indicated by iHeaderLen=-1) then the
	 *         first entry in ArrayList will be an empty string.
	 */
	public static ArrayList formatLineBreak(String strFullText, int iHeaderLen,
			String strSeparator, boolean bAddSeparatorToHeader, int iLineSize,
			int iTotalBodyLen) {
		ArrayList<String> lstResultStrings = new ArrayList<String>();
		if (strFullText == null || iLineSize <= 0) {
			System.out.println("The text is null or iLineSize is <= 0 ");
			return lstResultStrings;
		}
		String msgHdr = "";
		String msgBody = "";
		int posColon = -1;

		if (strFullText != null) {
			// If there is a header
			if (iHeaderLen > 0) {

				if (strSeparator == null) {
					System.out
							.println("The text includes a header but no separator has"
									+ " been provided to identify the header");
					return lstResultStrings;
				}
				// determining the position of the column which separates the
				// header and body
				posColon = (strFullText.indexOf(strSeparator));
				// extracting header till the position of the colon
				msgHdr = strFullText.substring(0, posColon + 1).trim();
				if (msgHdr.length() >= iHeaderLen && bAddSeparatorToHeader) {
					// if header is greater than iHeaderLen trim the header to a
					// length of
					// iHeaderLen-1 and add separator
					msgHdr = lengthenStr(msgHdr, CHAR_EMPTY, iHeaderLen - 1,
							false);
					msgHdr = msgHdr + strSeparator;
				} else {
					// lengthen/shorten the header to iHeaderLen characters
					msgHdr = lengthenStr(msgHdr, CHAR_EMPTY, iHeaderLen, false);
				}
			}
			lstResultStrings.add(msgHdr);

			msgBody = strFullText.substring(posColon + 1).trim();

			// truncating or lengthening the message body to iTotalBodyLen
			// characters
			if (iTotalBodyLen > 0) {
				msgBody = lengthenStr(msgBody, CHAR_EMPTY, iTotalBodyLen, false);
			}
			// Number of chars in the body
			int iBodyLength = msgBody.trim().length();
			int iStartIndex = 0;
			int iEndIndex = iLineSize;
			String strTemp = null;
			boolean bLoop = true;

			while (bLoop) {
				// If endIndex greater than body length put rest of String in
				// ArrayList
				// and exit loop
				if (iEndIndex >= iBodyLength) {
					lstResultStrings.add(msgBody.substring(iStartIndex).trim());
					bLoop = false;
					continue;
				}

				if (msgBody.charAt(iStartIndex) == ' ') {
					iStartIndex++;
					iEndIndex++;
				}

				strTemp = msgBody.substring(iStartIndex, iEndIndex);

				int iLastSpaceIndex = strTemp.lastIndexOf(BLANK_SPACE);

				// if last char in current string or first char of next line is
				// ' '
				// then add string to arraylist
				// Also if there is no space in the current line then blindly
				// add it
				if (iLastSpaceIndex == -1
						|| msgBody.charAt(iEndIndex - 1) == ' '
						|| msgBody.charAt(iEndIndex) == ' ') {
					lstResultStrings.add(msgBody.substring(iStartIndex,
							iEndIndex).trim());
					iStartIndex = iEndIndex;
					iEndIndex = iEndIndex + iLineSize;
				}
				// if word is being cut then exclude that word from current line
				// so that it can be included in the next line
				else {
					lstResultStrings.add(msgBody.substring(iStartIndex,
							iStartIndex + iLastSpaceIndex + 1).trim());
					iStartIndex = iStartIndex + iLastSpaceIndex + 1;
					iEndIndex = iStartIndex + iLineSize;
				}

				// If startIndex is greater than body length then exit loop
				if (iStartIndex > iBodyLength) {
					bLoop = false;
					continue;
				}
			}

		}
		return lstResultStrings;
	}

	/**
	 * Method to lengthen or shorten a given String. If the parameter String is
	 * longer than the required length, then this methods cuts the String from
	 * right. If the parameter String is shorter than required length then it
	 * adds the passed character to left or right to fit the required length.
	 * 
	 * @param str
	 *            String - Given String
	 * @param addChar
	 *            char - Character to add
	 * @param length
	 *            int - Required length of String
	 * @param addLeft
	 *            boolean - True if characters are to be added to the left,
	 *            false otherwise
	 * @return String - The String of required length
	 */
	public static String lengthenStr(String str, char cAddChar, int iLength,
			boolean bAddLeft) {
		StringBuffer strBuffResult = new StringBuffer(iLength);
		if (str != null) {
			if (str.length() > iLength) {
				strBuffResult.append(str.substring(0, iLength));
			} else {
				strBuffResult.append(str);
			}
		}
		int countAddChars = iLength - strBuffResult.length();
		if (countAddChars > 0) {
			char[] addedChars = new char[countAddChars];
			Arrays.fill(addedChars, cAddChar);
			if (bAddLeft) {
				strBuffResult.insert(0, addedChars);
			} else {
				strBuffResult.append(addedChars);
			}
		}
		return strBuffResult.toString();
	}

}
