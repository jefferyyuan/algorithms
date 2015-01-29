
/* Copyright 2003 by Lawrence Kesteloot */

package org.codeexample.algorithms.collected.markov;

/**
 * Keeps a fixed-length queue of characters.  There are only three
 * operations on the queue: set the whole thing; append a character
 * (dropping the first); and retreive the whole thing.  This is useful
 * as a moving window on a text stream.
 */

class CharQueue {
    private int length;
    private char[] queue;
    private int count;

    /**
     * Create the queue with a fixed length.  The queue will be
     * filled with the value 0, so don't use the toString()
     * method until the queue has been filled with either
     * set() or put().
     */

    public CharQueue(int length) {
        this.length = length;
        queue = new char[length];
        count = 0;
    }

    /**
     * Sets the contents of the queue.  The length of the string
     * must be the same as the length passed to the constructor.
     */

    public void set(String s) {
        if (s.length() != length) {
            System.out.println("Lengths don't match");
            return;
        }

        queue = s.toCharArray();
        count = length;
    }

    /**
     * Appends the character to the queue.  If the resulting queue
     * would be longer than the length set in the constructor, then
     * the first character is dropped.
     */

    public void put(char c) {
        if (count == length) {
            System.arraycopy(queue, 1, queue, 0, length - 1);
            count--;
        }

        queue[count++] = c;
    }

    /**
     * Returns the contents of the queue as a string.  This does
     * not take into account the number of characters that have
     * been put into the queue.  The returned string's length
     * is always the length passed to the constructor.
     */

    public String toString() {
        return new String(queue);
    }
}

