phonetic matching algorithms such as the Soundex and Metaphone and string similarity types such as the Dynamic Programming algorithm.
Aspell algorithm
http://www.ibm.com/developerworks/java/library/j-jazzy/
Can't beat Jazzy
 Levenshtein algorithm
 
Phonetic matching algorithms
It aims to solve the problem of "which names match those that sound like x." 
Essentially, Soundex algorithms function by mapping each letter of a given alphabet to a numerical code representing its phonetic group. In this scheme, letters such as d and t are in the same group since they sound alike (in fact each letter is vocalized by a similar mechanism) and vowels are omitted altogether. By applying this mapping to a whole word a phonetic "key" for the word is produced. Words that sound alike will usually have the same key. For example, the Soundex key for both Smith and Smyth is S530.

Soundex for spell checking
Unfortunately, the Soundex algorithm is a poor candidate for spell checking. For a start, words that sound distinct may have the same soundex.
This isn't surprising, since the Soundex algorithm was designed to group names that sound alike, not just those that sound identical.
It is not useful for a spell-checking application, since it produces far too many matches. The Soundex algorithm also truncates each soundex code to four characters, further boosting the number of matches by ignoring the ends of long words.

The homophone problem
Just as different sounding words may have the same soundex, the reverse situation can also occur: words that sound identical, called homophones, may have different codes. This is the result of some letters being silent.
In a similar vein, the initial letter of a word may vary without affecting its sound, such as the c in Carr (C600) versus the k of Karr (K600). The Soundex algorithm brings this problem on itself by failing to map the initial letter of each word to a phonetic digit.

The Metaphone algorithm
The idea behind the Metaphone algorithm is to explicitly code common rules of English pronunciation that the Soundex doesn't attempt to address. For example, the Metaphone algorithm includes an explicit rule that drops the letter b where it occurs after the letter m at the end of a word. This rule ensures that lam and lamb will have the same encoding (LM), thus enabling a spell check application to offer the correct replacement for lam.

The Metaphone algorithm uses 16 consonant classes represented by the following characters:
B X S K J T F H L M N P R 0 W Y

The 0 is a zero, used to represent the th sound. As in the Soundex algorithm, the first letter is retained and the final code is truncated to four characters, although it is not padded if shorter. Repeated letters and vowels are generally dropped, as are vowels. The bulk of the Metaphone algorithm is a set of rules that map letter combinations into the consonant classes.

String similarity algorithms
Distance is the number of steps it takes to transform one word into another, provided that you change only one letter at a time and use actual dictionary words for each step
The puzzle distance defined above is unsuitable for at least one good reason: A misspelled word is not always one letter away from a correctly spelled one. For instance, there are no "stepping stones" from the misspelling puzzel to any correctly spelled English word.

The Dynamic Programming algorithm
The Dynamic Programming algorithm is essentially a brute-force method that considers all the different ways of transforming a source word to a target to find the least cost, or distance between the two. Levenshtein distance, a particular implementation of the Dynamic Programming algorithm, permits three types of operation for transforming the source word x to the target word y: substitution, deletion, insertion.

Each operation has a certain cost, and the total distance is the smallest total cost for transforming word x to word y. It is intuitively plausible that an algorithm based on these operations would work well for spelling correction, since typos are nothing more than these operations interpreted as keying errors. (In fact, a Levenshtein distance is also known as an edit distance.) 

To start the algorithm you fill in the first row, which corresponds to an empty source word, so it is the cost of inserting 0, 1, ..., j letters. Similarly the first column corresponds to an empty target word, so it is the cost of deleting 0, 1, ..., i letters. 

Next, you calculate the values in each remaining cell by considering its three neighbors: above, to the left, and diagonally upward and to the left.
Left=Min(
  Diagonal + substitution cost,
  Above + delete cost,
  Left + insert cost
)

A limitation of both of these implementations is that they do not scale to large strings, since the storage requirements are O(mn), where m and n are the length of the source and target words, respectively. If you need only to compute the distance and not alignments, as is usually the case, it is easy to reduce this to O(n), since only the previous row is required to compute the next.

Introducing Jazzy
The magic behind [Aspell] comes from merging Lawrence Philips excellent metaphone algorithm and Ispell's near miss strategy which is inserting a space or hyphen, interchanging two adjacent letters, changing one letter, deleting a letter, or adding a letter.

The Aspell algorithm and Jazzy
If the word being spell checked is not in the dictionary, then the Aspell algorithm assumes it is misspelled. In this case, the algorithm uses the following steps to build a ranked list of suggested corrections:
Add close phonetic matches of the misspelling: Add all dictionary words that have the same phonetic code as the misspelled word and whose edit distance from the misspelled word is less than a given threshold.

Add close phonetic matches of the misspelling's near misses: Find all phonetic codes for words that are one edit operation from the misspelled word. For these codes, add all dictionary words that have the same phonetic code as the misspelled word and whose edit distance from the misspelled word is less than a given threshold.

Best guess: If no suggestions have been found, add all dictionary words that have the same phonetic code as the misspelled word and with the smallest edit distance from the misspelled word.

Sort: Sort the words by edit distance, keeping words found at each step together.
The strength of the Aspell algorithm is the way it uses edit distance at both the word level and the phonetic code level. In practice, this introduces enough fuzziness to produce good suggestions for misspelled words.

A note about edit distance

The edit distance used in Jazzy differs from the Levenshtein distance defined earlier. As well as substitutions, deletions, and insertions, Jazzy includes operations to swap adjacent letters and to change the case of a letter. The cost of the operations is configurable. The default phonetic encoding is Metaphone, although it is possible to use a phonetic transformations rules file (see Resources), which is a table-driven way of defining transformations like Metaphone. The table-driven approach makes it easy to configure a Jazzy-based spell checker to support other languages. 