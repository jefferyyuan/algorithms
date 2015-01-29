package org.codeexample.algorithms.collected.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiset implemented using two lists (list of values, list of occurrences)
 * @author Pavel Micka
 * @param <ENTITY> type parameter of the contained value
 */
public class Multiset<VALUE> {

    private List<VALUE> values;
    private List<Integer> occurences;

    /**
     * Constructor
     * @param initialCapacity initial capacity of underlying lists
     */
    public Multiset(int initialCapacity) {
        values = new ArrayList<VALUE>(initialCapacity);
        occurences = new ArrayList<Integer>(initialCapacity);
    }

    /**
     * Inserts a value into the multiset
     * @param val value
     * @return number of occurences of the value in the set after the addition
     */
    public int put(VALUE val) {
        int index = values.indexOf(val);
        if (index == -1) {
            values.add(val);
            occurences.add(1);
            return 1;
        } else {
            int currCount = occurences.get(index);
            occurences.set(index, currCount + 1);
            return currCount + 1;
        }
    }

    /**
     * Returns and deletes (decrements number of uccurences) some value from the multiset
     * @return value, null if the multiset is empty
     */
    public VALUE pick() {
        if (values.size() == 0) {
            return null;
        }
        if (occurences.get(0) == 1) {
            VALUE v = values.remove(0);
            occurences.remove(0);
            return v;
        } else {
            VALUE v = values.get(0);
            occurences.set(0, occurences.get(0) - 1);
            return v;
        }
    }

    /**
     * Deletes a given value from the multiset (removes one occurrence)
     * @param val value
     * @return number of occurences of the value in the set after the deletion
     */
    public int remove(VALUE e) {
        int index = values.indexOf(e);
        int curr = occurences.get(index);
        if (curr != 1) {
            occurences.set(index, curr - 1);
            return curr - 1;
        } else {
            values.remove(index);
            occurences.remove(index);
            return 0;
        }
    }

    /**
     * Query, if the multiset contains a given value
     * @param val value
     * @return number of occurences of the given value in the set
     */
    public int contains(VALUE e) {
        int index = values.indexOf(e);
        if(index == -1) return 0;
        return occurences.get(index);
    }

    /**
     * Size of the multiset (including all multiplicities)
     * @return number of stored entities (values*occurences)
     */
    public int size() {
        int count = 0;
        for(Integer i : occurences){
            count += i;
        }
        return count;
    }
}
