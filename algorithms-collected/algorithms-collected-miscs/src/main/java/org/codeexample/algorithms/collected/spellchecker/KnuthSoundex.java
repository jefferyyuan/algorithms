package org.codeexample.algorithms.collected.spellchecker;

/**
 * Source: http://www.ibm.com/developerworks/java/library/j-jazzy/
 * <P>
 * Phonetic matching algorithms It aims to solve the problem of
 * "which names match those that sound like x." Essentially, Soundex algorithms
 * function by mapping each letter of a given alphabet to a numerical code
 * representing its phonetic group. In this scheme, letters such as d and t are
 * in the same group since they sound alike (in fact each letter is vocalized by
 * a similar mechanism) and vowels are omitted altogether. By applying this
 * mapping to a whole word a phonetic "key" for the word is produced. Words that
 * sound alike will usually have the same key. For example, the Soundex key for
 * both Smith and Smyth is S530.
 * <P>
 * Soundex for spell checking Unfortunately, the Soundex algorithm is a poor
 * candidate for spell checking. For a start, words that sound distinct may have
 * the same soundex. This isn't surprising, since the Soundex algorithm was
 * designed to group names that sound alike, not just those that sound
 * identical. It is not useful for a spell-checking application, since it
 * produces far too many matches. The Soundex algorithm also truncates each
 * soundex code to four characters, further boosting the number of matches by
 * ignoring the ends of long words.
 * <P>
 * The homophone problem Just as different sounding words may have the same
 * soundex, the reverse situation can also occur: words that sound identical,
 * called homophones, may have different codes. This is the result of some
 * letters being silent. In a similar vein, the initial letter of a word may
 * vary without affecting its sound, such as the c in Carr (C600) versus the k
 * of Karr (K600). The Soundex algorithm brings this problem on itself by
 * failing to map the initial letter of each word to a phonetic digit.
 */
public class KnuthSoundex
// implements PhoneticEncoder
{
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    private static final String SOUNDEX_DIGITS = "01230120022455012623010202";

    public String calculateCode(
            String string)
    {
        // normalize the input to capital letters and drop all other characters.
        String word = string.toUpperCase(); // 01 ASHCROFT
        word = word.replaceAll("[^A-Z]", ""); // 02
        if (word.length() == 0)
        { // 03
            return ""; // 04
        } // 05
          // ensures the first character of the word is unchanged.
        char first = word.charAt(0); // 06
        // drops subsequent H or W characters.
        word = first + word.substring(1).replaceAll("[HW]", ""); // 07 ASCROFT
        // Lines 08 to 11 replace each letter in the word with its phonetic
        // code.
        StringBuffer sndx = new StringBuffer(); // 08
        for (int i = 0; i < word.length(); i++)
        { // 09
            sndx.append(SOUNDEX_DIGITS.charAt((int) (word.charAt(i) - 'A'))); // 10
        } // 11
          // Line 12 removes adjacent phonetic codes that are the same. (Note
          // that this means, unlike vowels, intervening H and W characters do
          // not act as a barrier to combining runs of letters with the same
          // code.)
        word = sndx.toString().replaceAll("(.)\\1+", "$1"); // 12 026013
        // Like line 06, line 13 ensures the first character of the word is
        // unchanged.
        word = first + word.substring(1); // 13 A26013
        // Line 14 drops all vowels.
        word = word.replaceAll("0", ""); // 14 A2613
        // Line 15 constructs the Soundex by truncating the word to four
        // characters (possibly padded with 0 characters).
        return (word + "000").substring(0, 4); // 15 A261
    }
}
