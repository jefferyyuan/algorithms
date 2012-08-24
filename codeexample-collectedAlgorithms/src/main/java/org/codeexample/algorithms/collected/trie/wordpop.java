package org.codeexample.algorithms.collected.trie;

/** ***********************************wordpop.java**************************************
 @author Chris Urso
 @program Program #2: WordPop With Text
 @class CS 340, Spring 2005
 @date Tuesday, 8th of March
 @system java 1.4.2, linux machines 2260 SEO
 Source: http://logos.cs.uic.edu/340/assignments/Solutions/Wordpopup/curso/wordpop.java

 ----------------------------------------------------------

 The Program:
 This program implements a game called WordPop.  36 letters are generated randomly
 and are placed onto a 6x6 board.  The user must then type in adjacent letters to
 form a valid word.  If the word is valid (appears in the dictionary), and the word
 appears on the board, the letters in the word are removed and the letters above
 fall down into the empty spots.  The longer the word the more points the user gets
 for finding it.  The user tries to accumulate as many points as possible before
 the letters are all used up.  If there are no possible valid words left on the board
 the game stops, and the score at that point is the user's final score.

 Besides using his or her mind, the player may use other tools as well.  They may
 press '?' to see the list of commands.  By pressing 'r' the board rotates 1/4 turn
 to the right (90 degrees clockwise).  By pressing 's' the program finds all the
 possible words on the board, and suggests a random one to present to the player.
 Additionally I added some more features, because it was simple to do, but incredibly
 fun to use.  If the user presses 'n' the number of possible words are displayed.
 The final two commands are closer to cheating, so they would not be used in a normal
 game, but are interesting to try out for fun.  By pressing 'l' the longest possible
 word on the board is displayed (It is usually around 8 or 9 letters long!).  By
 pressing 'a' all the possible words on the board are printed (usually around 250
 words).

 ----------------------------------------------------------

 Data structures used:
 Trie - The words in the dictionary are stored in a data type trie.
 In this type of tree, each node can have a maximum of 26 children,
 one for each letter of the alphabet.  Each node is structured like this.

 class node {
 char letter;
 boolean endsWord;
 node down;
 node right;
 }

 Each node stores a char letter, a boolean variable (indicates if the
 letter stored in the node ends a word or not), a pointer to the child
 of the node, and a pointer to the right sibling of the node.  Each
 node can have a maximum of 26 children, one for each letter of the
 alphabet.  All children are arranged in alphabetical order for easy
 searching.  This trie stores each word in the foreward rather than
 backword because the searching must be done from the start of the word.

 Board - Each current position on the board is stored in a Board data
 type.  a Board data type is simply an array of 36 data types called
 Squares.  Squares are composed of two parts as follows

 class square {
 char letter;
 boolean checked;
 }

 Each square stores a char called letter, which is the object displayed
 on the board, as well as a boolean checked, which keeps track if the
 particular square has been check or not in the search algorithms.  This
 is needed because no letter can be used more than once in this game,
 and this makes sure it hasn't been used yet.

 Built-in Data Structures:
 An ArrayList was used during the searching to find all possible words
 on the board.  In the middle of the recursive searching, if a word was
 found it was added to the end of the ArrayList, so at the end each word
 was stored.
 A Stack was used to keep track of the indexes of a word to be deleted.
 The Stack was chosen in the case because often a path ended up leading
 nowhere so backtracking was needed, and the latest element added to the
 Stack was the first to be deleted.

 ----------------------------------------------------------

 Implementing:
 Generating Random Letters - The letters weren't completely random on the
 board.  They were pseudorandom.  Letters such as A and E were meant to
 appear more than X and Q.  In the trie class when inserting each word
 from the dictionary file into the trie, there was a seperate array counting
 the amount of times each word appeared.  The [0] index stored the number
 of A's, the [1] index stored the amount of B's, and so on.  A new array
 was made to store the some of the percentage of the letters before it.  For
 example, the [0] index stored the probability of getting an 'A', the [1]
 index stored the probability of getting an 'A' or a 'B', and so on.  Next
 the Random method in the Math class was used to generate a random floating
 point number between 0 and 1.  The random letter used corresponded to the
 first number in the percentage array that was greater than the random number.
 That number was calculated 36 times for each square on the board.

 The following were the trickiest implementations, because of how complicated
 the recursion is, but they both follow the same basic idea of recursively
 searching each possible word combination on the board starting with the
 square to the left of the current square and continuing clockwise.  The
 searches were conducted in a depth first manner.  The only differences in
 the two methods were the data types they used, and the method of determining
 when or not to search branches.

 Finding All Words Recursively - This used an ArrayList.  Since all possible
 words on the board wee needed, the only way a branch could be eliminated
 was if the letters did not start any word.  A String was sent each recursive
 call to store the letters found so far.  If that String did not start any
 word, then the entire branch could be eliminated.  This was easy to check
 since a trie was used, and each set of starting letters in the trie were
 unique.  It was known that a word was found if the String was part of a word
 and the boolean variable (endsWord) for that node was true.  If a word was
 found, the word was added to an ArrayList.  Since after the method was called
 all the words on the board were stored in an ArrayList, any help the user
 needed was provided easily.  If the ArrayList is empty at any point, there
 are no possible words on the board, and the game is over.

 Finding the Word on the Board Recursively - This was implemented much the
 same as the "Finding All Words" method.  It is different because it uses
 a Stack instead of an ArrayList.  A Stack is better because the path of
 a single word must be stored.  The words are found one letter at a time.
 After each letter in a path matches, that index is pushed onto the Stack.
 If a path turns out to be a dead end, that index is popped from the Stack.
 At the end the Stack will hold all the indexes of the letters in the first
 matching word found.  After that it is easy to remove the letters at those
 indexes.

 /** ***********************************wordpop.java**************************************/

import java.io.*;
import java.lang.*;
import java.util.*;

public class wordpop
{
    public class square
    {
        char letter; // store the letter in each square
        boolean checked; // remember squares that were searched before

        // constructor for the square class
        public square()
        {
            letter = ' ';
            checked = false;
        }
    }

    Trie dictionary = new Trie(); // store every word in the dictionary
    square[] board = new square[36]; // store all 36 squares on the board
    ArrayList words = new ArrayList(); // store all valid words on the board
    Stack coordinates = new Stack(); // store the coordinates of the desired
                                     // word
    protected int score = 0; // store the player's score

    // constructor for the wordpop class
    public wordpop()
    {
    }

    /*
     * prints the introduction including author name, program name, class, and
     * semester
     */
    public void printHeading()
    {
        System.out.println("Author: Chris Urso");
        System.out.println("Program: #2, WordPop With Text");
        System.out.println("CS 340, Spring 2005");
    }

    /*
     * prints the directions of the game and an introduction
     */
    public void printIntro()
    {
        System.out.println("Welcome to Wordpop, a word-finding game.  Type in adjacent"
                + " letters to form a valid word.  Letters can only be used"
                + " once in a word.  If the word is found, those letters are"
                + " removed, with the letters above \"falling\" down.");
    }

    /*
     * prints a summary of all the commands in the game
     */
    public void printHelp()
    {
        System.out.println("Points are assigned based on the length of the word formed"
                + " as follows:");
        System.out.println("\t3 letter words: 1 point");
        System.out.println("\t4 letter words: 2 points");
        System.out.println("\t5 letter words: 4 points");
        System.out.println("\t6 letter words: 7 points");
        System.out.println("\t7+ letter words: 12 points");

        System.out.println("Below is a list of possible commands");
        System.out.println("\tx - quit the game, and exit the program");
        System.out.println("\ts - suggest a word");
        System.out.println("\tr - rotate the board 90 degrees clockwise");
        System.out.println("\t? - help");
        System.out.println("\tn - number of possible words on the board");
        System.out.println("\tl - longest word on the board");
        System.out.println("\ta - all possible words on the board");
    }

    /*
     * receives: A String file returns: nothing creates the board one square at
     * a time, by first declaring it, then assigning a pseudorandom letter to
     * it. this is also where the trie class is told to build the dictionary
     * trie.
     */
    public void initialize(
            String file)
    {
        // build the trie using the filename stored in 'file'
        dictionary.setupTrie(file);

        for (int x = 0; x < 36; x++)
        {
            // declare each new square
            board[x] = new square();
            // assign is a random letter
            board[x].letter = randLetter();
        }

    }

    /*
     * recieves: nothing returns: a random char returns a pseudorandom character
     * based on the values in the array ammountList. the higher the number at
     * each index, the greater probabily that number and that corresponding
     * character will be chosen and returned.
     */
    char randLetter()
    {
        float total = 0;
        float runningSum = 0;
        float[] probList = new float[26];

        Random rand = new Random();

        // set total equal to the total number of letters in the dictionary
        for (int x = 0; x < 26; x++)
        {
            total = total + dictionary.amountList[x];
        }
        // set each index of probList to store the sums of the probabilities
        // of each letter before it occuring
        for (int x = 0; x < 26; x++)
        {
            probList[x] = dictionary.amountList[x] / total + runningSum;
            runningSum = probList[x];
        }

        // use Math.random to generate a random float between 0 and 1
        float randFloat = (float) Math.random();
        runningSum = 0;
        for (int x = 0; x < 26; x++)
        {
            // once a value in probList is greater than randFloat return
            // the corresponding character (index offset by 97)
            if (randFloat < probList[x])
            {
                return (char) (x + 97);
            }
        }
        // this will never happen. it makes the compiler happy
        return ' ';
    }

    /*
     * prints the main menus for the user to select a command, and calls the
     * correct function based on the command. the main menus is printed until
     * the user enters and x to exit.
     */
    public void menu()
    {
        // holds the entered command
        String command = new String();
        // holds the split command and arguement
        String[] commandArgs = new String[2];
        // status shows which error occured
        int status;

        // prompt for command and read it in
        while (true)
        {
            // display the score and board
            printBoard();

            findAll();
            if (words.size() == 0)
            {
                System.out.println("game over: there are no more words left");
                System.out.println("Final Score: " + score);
                System.exit(0);
            }

            // store the entered command in command
            command = getCommand();
            // test the command and stores it in commandArgs
            status = checkCommand(command);

            // based on the command call the appropriate function
            // or inform the user that the command is invalid
            if (status < 0)
                continue;
            else if (command.compareTo("s") == 0)
                randomWord();
            else if (command.compareTo("r") == 0)
                rotateBoard();
            else if (command.compareTo("n") == 0)
                numWords();
            else if (command.compareTo("a") == 0)
                allWords();
            else if (command.compareTo("l") == 0)
                longestWord();
            else if (command.compareTo("?") == 0)
                printHelp();
            else if (command.compareTo("x") == 0)
            {
                System.out.println("game over: you have given up too soon");
                System.out.println("Final Score: " + score);
                System.exit(0);
            }
            // if status is 1 then no errors happened
            else if (status == 1)
                removeWord(command);
            // clear the ArrayList holding all possible words
            // the board has changed and they must be found again
            words.clear();
        }
    }

    /*
     * prints the letters of all scares on the board in order
     */
    public void printBoard()
    {
        int index = 0;
        System.out.println("Score: " + score);
        // go through each row
        for (int x = 0; x < 6; x++)
        {
            // go through each column
            for (int y = 0; y < 6; y++)
            {
                // print the letter at the current index and increment the index
                System.out.print(board[index++].letter + " ");
            }
            // print the space after each row
            System.out.println();
        }
    }

    /*
     * receives: nothing returns: a String entered by the user asks for a
     * command, and reads in the String entered by the user
     */
    public String getCommand()
    {
        // declare a BufferedReader to obtain input
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        // variable for holding the command
        String command = new String();

        // prompt for a command
        System.out.println();
        System.out.print("Your word: ");
        try
        {
            // store the entered line in command
            command = stdin.readLine();
        }
        catch (IOException e)
        {
            // catch exception e
        }
        System.out.println("");

        // return the command
        return command;
    }

    /*
     * receives: a String - command returns: an int - status tests to see if the
     * command entered by the user is valid or not. a valid command is either a
     * valid word (a word stored in the dictionary trie), or one of the single
     * character commands. if it is not a valid command, a negative number is
     * returned, otherwise 1 is returned.
     */
    public int checkCommand(
            String command)
    {
        // check if the command is a valid single character command
        if (command.compareTo("s") == 0 || command.compareTo("r") == 0
                || command.compareTo("n") == 0 || command.compareTo("a") == 0
                || command.compareTo("x") == 0 || command.compareTo("?") == 0
                || command.compareTo("l") == 0)
        {
            // return 1 meaning success
            return 1;
        }

        // take out all characters except alphabetic characters
        command = dictionary.fixString(command);

        // check if the word is of length 3 or greater
        if (command.length() < 3)
        {
            // if not print an error message and return -1 meaning failure
            System.out.println("invalid: word lengths must be greater than 3");
            return -1;
        }

        // check if th word is valid (in the trie)
        if (dictionary.wordInTrie(command) == false)
        {
            // if not print an error message and return -2 meaning failure
            System.out.println(command + " is not a word");
            return -2;
        }
        // at this point the word is valid and must be removed so return 1
        // meaning success
        return 1;
    }

    /*
     * receives: nothing returns: nothing - prints a random word from all the
     * words on the board
     */
    public void randomWord()
    {
        // use the Random class to create a random integer
        Random rand = new Random();
        int max = words.size();
        // get the next random integer less than or equal to max
        int index = rand.nextInt(max);

        System.out.println("I suggest " + words.get(index));
    }

    /*
     * receives: nothing returns: nothing - prints the number of all words left
     * on the board
     */
    public void numWords()
    {
        // the length of the ArrayList words is the number of possible words
        System.out.println("there are " + words.size() + " words remaining");
    }

    /*
     * receives: nothing returns: nothing - prints all the words left on the
     * board less than 7 characters long
     */
    public void allWords()
    {
        String str = new String();
        // print each word less than length 7
        for (int x = 0; x < words.size(); x++)
        {
            str = (String) words.get(x);
            if (str.length() <= 7)
            {
                System.out.print(str + " ");
            }
        }
        System.out.println();
    }

    /*
     * receives: nothing returns: nothing - prints the longest word left on the
     * board
     */
    public void longestWord()
    {
        String longest = (String) words.get(0);
        String tempWord = new String();
        // go through each word
        for (int x = 1; x < words.size(); x++)
        {
            tempWord = (String) words.get(x);
            // the longest word will be replaced if a longer word is found
            if (tempWord.length() > longest.length())
            {
                longest = tempWord;
            }
        }
        System.out.println("the longest word is " + longest);
    }

    /*
     * receives: a String str to find returns: nothing - stores the indexes of
     * the letters making up the word. fills the Stack coordinates with the
     * coordinates of the letters in the String str. If the stack is empty at
     * the end of the method, the word is not on the board
     */
    public void findWordPath(
            String str)
    {
        boolean[] stackFull = new boolean[1];
        stackFull[0] = false;
        // loop through each square once
        for (int x = 0; x < 36; x++)
        {
            // reset current dictionary position to start a new search
            resetSquares();

            // only call the recursive function if the word has not been found
            // yet
            if (stackFull[0] == false)
            {
                findWordPath(x, 0, str, stackFull);
            }
        }
    }

    /*
     * recursive part of the findWordPath. str holds the desired word to be
     * found. when stackFull is true, the desired word has been found and no
     * more recursive calls must be made. index keeps track of the current
     * position on the board, depth keeps track of how deep into the recursion
     * the method currently is (the first call is 0, second is 1, etc.).
     */
    void findWordPath(
            int index, int depth, String str, boolean[] stackFull)
    {
        // continue the search if the square at this index has not been checked
        // yet,
        // the depth is not greater than the word to be found, and the letter at
        // the
        // current square on the board matches the corresponding letter in str
        if (board[index].checked == false && str.length() > depth
                && board[index].letter == str.charAt(depth))
        {
            // this is a potentially correct path, so save it in the stack
            coordinates.push(new Integer(index));
            // if the number of correct indexes in the path is equal to the size
            // of the desired word, then the word is found, and stop all other
            // recursive calls
            if (coordinates.size() == str.length())
            {
                stackFull[0] = true;
            }
            if (index % 6 != 0)
            { // check square to the left
                findWordPathIfStatements(index, depth, str, -1, stackFull);
            }
            if (index % 6 != 0 && index > 5)
            { // check square to the upper left
                findWordPathIfStatements(index, depth, str, -7, stackFull);
            }
            if (index > 5)
            { // check square above
                findWordPathIfStatements(index, depth, str, -6, stackFull);
            }
            if (index % 6 != 5 && index > 5)
            { // check square to the upper right
                findWordPathIfStatements(index, depth, str, -5, stackFull);
            }
            if (index % 6 != 5)
            { // check square to the right
                findWordPathIfStatements(index, depth, str, 1, stackFull);
            }
            if (index % 6 != 5 && index < 30)
            { // check square to the lower right
                findWordPathIfStatements(index, depth, str, 7, stackFull);
            }
            if (index < 30)
            { // check square below
                findWordPathIfStatements(index, depth, str, 6, stackFull);
            }
            if (index % 6 != 0 && index < 30)
            { // check square to the lower left
                findWordPathIfStatements(index, depth, str, 5, stackFull);
            }
            // if the word has not been found, the latest index is a dead end,
            // and the index should not be saved and must back up once
            if (stackFull[0] == false)
            {
                coordinates.pop();
            }
        }
    }

    /*
     * this is meant to save space, and make findWordPath more legible. this
     * holds what is in each if statement.
     */
    void findWordPathIfStatements(
            int index, int depth, String str, int iAdd, boolean[] stackFull)
    {
        if (board[index + iAdd].letter != ' ')
        {
            // the currect position has just been checked
            board[index].checked = true;

            // only make a recursive call if the word has not been found yet
            if (stackFull[0] == false)
            {
                // make the recursive call
                findWordPath(index + iAdd, depth + 1, str, stackFull);
            }

            // uncheck for future searches (this search is over)
            board[index].checked = false;
        }
    }

    /*
     * receives: a String - str returns: nothing - removes a word from the board
     * to delete a word all that is needed is to replace the letter of the
     * square at each index with a space ' '. later on the spaces will be
     * removed
     */
    public void removeWord(
            String str)
    {
        // first find the path of the word to remove
        findWordPath(str);

        // if the path has not been found, the word is not on the board, so do
        // nothing
        if (coordinates.size() == 0)
        {
            System.out.println(str + " is not on the board");
            return;
        }

        int numToRemove;
        // get the values from coordinates and use the Integer class to get
        // integers
        Integer i = new Integer(0);
        // go to each index on the board that is stored in the Stack coordinates
        while (!coordinates.empty())
        {
            // replace the letter at each position with a space ' '
            i = (Integer) coordinates.pop();
            numToRemove = i.intValue();
            board[numToRemove].letter = ' ';
        }
        ;

        // find the correct amount of points to add to the score using a switch
        // statement
        int points = 0;
        switch (str.length())
        {
        case 3:
            points = 1;
            break;
        case 4:
            points = 2;
            break;
        case 5:
            points = 4;
            break;
        case 6:
            points = 7;
            break;
        default:
            points = 12;
        }

        // add the points to the score and inform the user
        score = score + points;
        System.out.println(str + " found,  points added: " + points);
        // call dropSquares method to get rid of the spaces
        dropSquares();
    }

    /*
     * moves all the spaces to the top of the board, operating very similar to
     * bubble sort
     */
    public void dropSquares()
    {
        // go through each 6 times in case the space is on the bottom row
        for (int x = 0; x < 6; x++)
        {
            // go through each square on the board
            for (int y = 35; y > 5; y--)
            {
                // check the letter for a space
                if (board[y].letter == ' ')
                {
                    // switch the square with the one above it
                    board[y].letter = board[y - 6].letter;
                    board[y - 6].letter = ' ';
                }
            }
        }
    }

    /*
     * rotate the board 90 degrees clockwise and drop any floating squares
     */
    public void rotateBoard()
    {
        System.out.println("rotating 90 degrees...");
        // declare a temporary square array
        square[][] tempBoard = new square[6][6];
        int index = 0;

        // store the elements in board into tempBoard
        for (int x = 0; x < 6; x++)
        {
            for (int y = 0; y < 6; y++)
            {
                tempBoard[x][y] = board[index++];
            }
        }

        // store a rotated board back into the main board
        index = 0;
        for (int x = 0; x < 6; x++)
        {
            for (int y = 5; y >= 0; y--)
            {
                board[index++] = tempBoard[y][x];
            }
        }

        // drop any squares that are floating
        dropSquares();
    }

    /*
     * receives: nothing returns: nothing - ArrayList words is filled with all
     * words on the board fills ArrayList words with every valid word found on
     * the board. word in str if the word is found legally on the board and
     * returns true. if the word is not found, false is returned
     */
    public void findAll()
    {
        // loop through each square once
        for (int x = 0; x < 36; x++)
        {
            // reset current dictionary position to start a new search
            resetSquares();
            findAll(x, 0, Character.toString(board[x].letter));
        }
    }

    /*
     * recursive part of the findWordPath. str is concatenated with each letter
     * found that has the possibility of making up a word. if a string is found
     * that is not the start of any word stored in the trie, then that
     * combination of letters is never tested again. index keeps track of the
     * current position on the board, depth keeps track of how deep into the
     * recursion the method currently is (the first call is 0, second is 1,
     * etc.).
     */
    void findAll(
            int index, int depth, String str)
    {
        // only continue if the square at the current index has not been
        // checked, if
        // str can be found in the trie, and if the depth is less than 20
        if (board[index].checked == false && dictionary.stringInTrie(str) && depth < 20)
        {
            // if word (not just the string) is in the trie, the word has not
            // been
            // found already, and the length of the word is 3 characters or
            // greater
            if (dictionary.wordInTrie(str) && words.indexOf(str) == -1 && str.length() >= 3)
            {
                // add the valid word to the ArrayList words
                words.add(str);
            }
            if (index % 6 != 0)
            { // check square to the left
                findAllIfStatements(index, depth, str, -1);
            }
            if (index % 6 != 0 && index > 5)
            { // check square to the upper left
                findAllIfStatements(index, depth, str, -7);
            }
            if (index > 5)
            { // check square above
                findAllIfStatements(index, depth, str, -6);
            }
            if (index % 6 != 5 && index > 5)
            { // check square to the upper right
                findAllIfStatements(index, depth, str, -5);
            }
            if (index % 6 != 5)
            { // check square to the right
                findAllIfStatements(index, depth, str, 1);
            }
            if (index % 6 != 5 && index < 30)
            { // check square to the lower right
                findAllIfStatements(index, depth, str, 7);
            }
            if (index < 30)
            { // check square below
                findAllIfStatements(index, depth, str, 6);
            }
            if (index % 6 != 0 && index < 30)
            { // check square to the lower left
                findAllIfStatements(index, depth, str, 5);
            }
        }
    }

    /*
     * this is meant to save space, and make findWordPath more legible. this
     * holds what is in each if statement.
     */
    void findAllIfStatements(
            int index, int depth, String str, int iAdd)
    {
        // at the end of the game there will be a lot of spaces on the board
        // which can be skipped
        if (board[index + iAdd].letter != ' ')
        {
            // the current position has just been checked
            board[index].checked = true;

            // make the recursive call
            findAll(index + iAdd, depth + 1,
                    str.concat(Character.toString(board[index + iAdd].letter)));

            // uncheck for future searches (this search is over)
            board[index].checked = false;
        }
    }

    // reset all the squares on the board (make their checked false)
    public void resetSquares()
    {
        for (int x = 0; x < 36; x++)
        {
            board[x].checked = false;
        }
    }

    /*
     * receives: an array of Strings returns: nothing - controls the game
     * receives the command line arguement from the user (which will open the
     * correct dictionary file), and runs calls each major part of the program.
     */
    public static void main(
            String args[])
    {
        // exit if the number of arguements sent to the program is not one
        // if (args.length != 1)
        // {
        // System.out.println("invalid: takes only 1 arguement");
        // System.exit(1);
        // }

        // create an instance of wordpop
        wordpop game = new wordpop();

        // print the heading, introduction, heading, and help
        game.printHeading();
        game.printIntro();
        game.printHelp();

        // build the dictionary and create the random letters
        // game.initialize(args[0]);
        game.initialize("C:\\Jeffrey\\my-projects\\github\\algorithms\\codeexample-collectedAlgorithms\\src\\main\\java\\org\\codeexample\\algorithms\\collected\\trie\\joggle\\bogdict.txt");

        // call the menu to allow the user to play the game
        game.menu();
    }
}
