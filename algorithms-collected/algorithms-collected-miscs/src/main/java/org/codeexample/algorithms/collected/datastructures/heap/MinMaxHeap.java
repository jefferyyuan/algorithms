package org.codeexample.algorithms.collected.datastructures.heap;

/*
 @(#) MinMaxHeap.java
 * @author Joseph Sisson 4/11/03
 */

/**
 * The interface class MinMax provides an interface to class MinMaxHeap for the
 * consistant implementation of methods that insert integers, delete the minimum
 * and maximum integer, as well as show the minimum and maximum integer.
 * <p>
 * Source:http://josephsisson.com/images/MinMaxHeap.html
 */
interface MinMax
{
    public void enqueue(
            Comparable comparable);

    public Comparable min();

    public Comparable max();

    public Comparable deleteMin();

    public Comparable deleteMax();
}

/**
 * Class MinMaxHeap provides methods that insert integers, delete the minimum
 * and maximum integer, as well as show the minimum and maximum integer. This
 * class has global variables count, which records how many elements are in the
 * heap, elements which records all elements in heap order, and max, which
 * records the current max integer in the heap. In addition, class MinMaxHeap
 * provides a constructor that creates and intitalizes its instance variable
 * elements.
 */
public class MinMaxHeap implements MinMax
{
    /* To hold all the elements of the heap */
    private Object[] elements;
    /* To record the number of elements in the heap */
    private int count;
    /* To hold the maximnm value in the heap */
    private Comparable max = new Integer(0);

    /**
     * Creates a MinMaxHeap object with the capacity that is desired by and
     * passed by the user.
     * 
     * @param capacity
     *            the initial capacity
     * @precondition capacity is not negative
     * @postcondition initialCapacity is initialized.
     * @throws nothing
     */
    public MinMaxHeap(int capacity)
    {
        elements = new Object[capacity];
    }

    /**
     * Returns the parent element of the right child element.
     * 
     * @param one
     *            .
     * @returns the parent element in the heap of the child param
     * @precondition none
     * @postcondition nothing
     * @throws nothing
     */
    int parent(
            int child)
    {
        return (child - 1) / 2;
    }

    /**
     * Method swap exchanges the index of two heap lelements with each other's
     * posistion in the heap.
     * 
     * @param two
     *            .
     * @returns nothing
     * @precondition none
     * @postcondition the elements indexes are exchanged with each other
     * @throws nothing
     */
    public void swap(
            int index1, int index2)
    {
        Object temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    /**
     * Method enqueue inserts elements into the heap in the proper heap order.
     * This heap order is a min heap which has the minimum value at element[0]
     * and subsquently assends in proper heap order. This heap does not allow
     * for duplicate values.
     * 
     * @param one
     *            .
     * @returns nothing
     * @precondition positive integers only
     * @postcondition the inserted element is placed in the heap in the proper
     *                location to maintain the heap properties.
     * @throws nothing
     */
    public void enqueue(
            Comparable value)
    {
        /* Used to determine if the inserted value is greater than max */
        if (value.compareTo(max) > 0)
        {
            max = value;
        }
        /* Check if heap is full */
        if (count == elements.length)
        {
            /* Notify the user if the heap is full */
            System.out.print(" The Heap is full! ");
            return;
        }
        else
        {
            /* To add one to the index of elements */
            int index = count++;
            /* Find where to insert the input value */
            while (index > 0 && value.compareTo(elements[parent(index)]) < 0)
            {
                elements[index] = elements[parent(index)];
                index = parent(index);
            }
            /* Put the input value in the heap here */
            elements[index] = value;
            return;
        }
    }

    /**
     * Method deleteMin deletes the minimum value from the heap and then reoders
     * the heap elements in the propers order to maintain the heap properties
     * 
     * @param none
     * @returns the minimum heap element that is deleted.
     * @precondition none
     * @postcondition the heap is in the proper order without the deleted minmum
     *                element.
     * @throws nothing
     */
    public Comparable deleteMin()
    {
        /* check to ensure heap is not empty */
        if (count == 0)
        {
            return null;
        }
        else
        {
            /* Pass the value to local variable element to return */
            Comparable element = (Comparable) elements[0];
            /* Put the last element in the heap at first */
            elements[0] = elements[--count];
            /* To hold the index value while traversing the heap */
            int smallest = 1;
            /* To hold value for child and parent comparisons */
            int index = 0;
            /* Check values of left child with the right */
            while (smallest <= count)
            {
                if (smallest < count
                        && ((Comparable) elements[smallest]).compareTo(elements[smallest + 1]) > 0)
                {
                    smallest++;
                }
                /* Check values of child and parent */
                if (((Comparable) elements[index]).compareTo(elements[smallest]) > 0)
                {
                    swap(index, smallest);
                    index = smallest;
                    smallest = index * 2 + 1;

                }
                else
                {
                    break;
                }
            }
            /* returns the minimum element */
            return element;
        }
    }

    /**
     * Method min() check if the heap is empty and returns null if it is empty
     * and the minimum value in the heap if not empty.
     * 
     * @param none
     * @returns null or the minimum value depending on heap condition
     * @precondition none
     * @postcondition The heap is not changed
     * @throws nothing
     */
    public Comparable min()
    {
        return count == 0 ? null : (Comparable) elements[0];
    }

    /**
     * Method max() check if the heap is empty and returns null if it is empty
     * and the maximum value in the heap if not empty.
     * 
     * @param none
     * @returns null or the maximum value depending on heap condition
     * @precondition none
     * @postcondition The heap is not changed
     * @throws nothing
     */
    public Comparable max()
    {
        return count == 0 ? null : max;
    }

    /**
     * Method deleteMax deletes the maximum value from the heap and then reoders
     * the heap elements in the propers order to maintain the heap properties.
     * In addition, this method finds the new max value after deleting the old.
     * This method makes an effort to be as efficient as possible by staring at
     * the bottom of the heap first and only checking the leaves when searching
     * for the max value.
     * 
     * @param none
     * @returns the maximum heap element that is deleted.
     * @precondition none
     * @postcondition the heap is in the proper order without the deleted
     *                maximum element and a new value is assigned to max.
     * @throws nothing
     */
    public Comparable deleteMax()
    {
        /* hold the value removed from the heap */
        Comparable deletedMax = new Integer(0);
        /* Check to ensure the heap is not empty */
        if (count == 0)
        {
            return null;
        }
        else
        {
            /* Pass the maximum value to local element for return */
            Comparable element = max;
            /* To hold the maximum index value */
            int indexmax = count - 1;
            /* To hold the greatest element value */
            int biggest = count - 1;
            /* To hold the lowest element value */
            int smallest = count - 1;
            /* Began by making the end of heap the max value */
            max = (Comparable) elements[biggest];
            /* Check only the leaves because they contain max */
            while (elements[((smallest) * 2)] == null)
            {
                if (elements[((smallest) * 2)] == null)
                {
                    if (((Comparable) elements[biggest - 1]).compareTo(max) > 0)
                    {
                        /* Change max to the next */
                        max = (Comparable) elements[--biggest];
                    }
                    else
                    {
                        /* Skipp it & go to the next */
                        --biggest;
                    }
                    /* Continue traverse the heap */
                    --smallest;

                }
                /* Find the maximum index in the heap */
                if (((Comparable) elements[indexmax]).compareTo(max) < 0)
                {
                    indexmax = indexmax - 1;
                }

            }
            /* First see if the max is at the bottom of heap */
            if (((Comparable) elements[count - 1]).equals(max))
            {
                elements[--count] = null;
            }
            else
            {
                /* To hold the value at the bottom of the heap */
                Object temp;
                temp = elements[count - 1];
                /* Pass the max value to the bottom of the heap */
                elements[count - 1] = elements[indexmax];
                /* Pass the bootom heap value to the old max location */
                elements[indexmax] = temp;
                /* Delete the max value at the bottom of heap */
                elements[--count] = null;
                /* Place where the max value was */
                Comparable value = (Comparable) elements[indexmax];
                /*
                 * Check if new position of the old largest value is in the
                 * correct place with regard to its parants
                 */
                int index = indexmax;
                /*
                 * Start at old max location and check parant on up the parental
                 * chain for a greater value
                 */
                while (index > 0 && value.compareTo(elements[parent(index)]) < 0)
                {
                    elements[index] = elements[parent(index)];
                    index = parent(index);
                }
                /* Insert the value in the correct location */
                elements[index] = value;

            }
            /* Check to ensure heap is not empty */
            if (count == 0)
            {
                return null;
            }
            else
            {
                /* Pass the max to the return value */
                deletedMax = max;
                /* To hold largest value */
                biggest = count - 1;
                /* To hold a value for comparing with the max value */
                smallest = count - 1;
                /* Set the max value to the value at bottom of heap */
                max = (Comparable) elements[biggest];
                /* Began by making the end of heap the max value */
                /* Check only the leaves because they contain max */
                while (elements[((smallest) * 2)] == null)
                {
                    if (elements[((smallest) * 2)] == null)
                    {
                        if (((Comparable) elements[biggest - 1]).compareTo(max) > 0)
                        {
                            /* Change max to the next */
                            max = (Comparable) elements[--biggest];
                        }
                        else
                        {
                            /* Skipp it & go to the next */
                            --biggest;
                        }
                        /* Continue traverse the heap */
                        --smallest;
                    }
                }
            }
            /* Return the max number that was deleted */
            return element = deletedMax;
        }
    }

    /**
     * Method main provides a place to instantiate a MinMax object to access the
     * methods of class MinMaxHeap via the MinMax Interface. This main method
     * does relitively no work compared to the rest of the class methods.
     * 
     * @param none
     * @returns results of heap depending on user input
     * @precondition none
     * @postcondition a heap instantiated depending on and according to user
     *                input and in the proper order.
     * @throws nothing
     */
    public static void main(
            String[] args)
    {

        /* The folowing code for testing only, remove if desired */
        /* Instantiate a MinMaxHeap object via MinMax Interface */
        MinMax myHeap = new MinMaxHeap(50);
        /* Insert some integers that create a dificult situation */
        myHeap.enqueue(new Integer(2));
        myHeap.enqueue(new Integer(13));
        myHeap.enqueue(new Integer(11));
        myHeap.enqueue(new Integer(30));
        myHeap.enqueue(new Integer(20));
        myHeap.enqueue(new Integer(15));
        myHeap.enqueue(new Integer(12));
        /* Call min() and max() method to check min and max heap value */
        Integer min = (Integer) myHeap.min();
        Integer max = (Integer) myHeap.max();
        System.out.print(" Max " + max + " Min " + min + " ");
        /*
         * Call deletedMax() and deleteMin() method to delete max and min heap
         * value
         */
        myHeap.deleteMax();
        myHeap.deleteMin();
        /* Call min() and max() method to check min and max heap value */
        min = (Integer) myHeap.min();
        max = (Integer) myHeap.max();
        System.out.print(" Max " + max + " Min " + min + " ");

        /* Keep deleting until heap is empty for test */
        myHeap.deleteMax();
        myHeap.deleteMin();

        min = (Integer) myHeap.min();
        max = (Integer) myHeap.max();
        System.out.print(" Max " + max + " Min " + min + " ");

        myHeap.deleteMax();
        myHeap.deleteMin();

        min = (Integer) myHeap.min();
        max = (Integer) myHeap.max();
        System.out.print(" Max " + max + " Min " + min + " ");
        myHeap.deleteMax();
        myHeap.deleteMin();

        min = (Integer) myHeap.min();
        max = (Integer) myHeap.max();
        System.out.print(" Max " + max + " Min " + min + " ");

    }
}
