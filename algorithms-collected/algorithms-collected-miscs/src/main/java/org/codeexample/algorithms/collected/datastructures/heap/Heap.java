package org.codeexample.algorithms.collected.datastructures.heap;

/**
 * Source:
 * http://eranle.blogspot.co.il/2012/08/min-max-heap-java-implementation.html
 * 
 */
public class Heap
{

    private int heapSize;
    private TreeNode[] maxHeap = null;
    private TreeNode[] minHeap = null;

    /**
     * Constructor Initiate the heapsize to 0.
     */
    public Heap()
    {
        this.heapSize = 0;
    }

    /**
     * Build maximum and minimum heaps using the given array of integer numbers.
     * The heaps are built as TreeNode[] array objects and a TreeNode POJO. The
     * Build heap action time complexity is: O(n).
     * 
     * @param arr
     *            - the array provided by the user.
     */
    public void buildHeap(
            int[] arr)
    {
        this.heapSize = arr.length - 1;

        // build max and min heaps using the given array
        maxHeap = convertArrToTreeNode(arr);
        minHeap = convertArrToTreeNode(arr);

        // heapify max heap
        for (int i = (arr.length / 2) - 1; i >= 0; i--)
        {
            maxHeapify(maxHeap, i);
        }

        // heapify min heap
        for (int i = (arr.length / 2) - 1; i >= 0; i--)
        {
            minHeapify(minHeap, i);
        }
    }

    /**
     * Find Max value. Time complexity is: O(1)
     * 
     * @return the max heap value
     */
    public int findMax()
    {
        return maxHeap[0].getValue();
    }

    /**
     * Find Min value. Time complexity is: O(1)
     * 
     * @return the min heap value
     */
    public int findMin()
    {
        return minHeap[0].getValue();
    }

    /**
     * Insert a new value to the heap.
     * 
     * @param x
     *            the value to add into the heaps.
     */
    public void insert(
            int x)
    {
        // increase size
        heapSize = heapSize + 1;
        // created a new TreeNode for the given value
        TreeNode newNode = new TreeNode();
        newNode.setValue(x);

        // insert to max heap
        insertMaxHeap(newNode);
        // insert to min heap
        insertMinHeap(newNode);
    }

    /**
     * Exract Max. This action extracts the max of this heap and update the
     * references to the min heap and change the min heap accordingly. Time
     * Complexity is: O(lgn).
     * 
     * @return int the max number extracted
     */
    public int deleteMax()
    {
        int max = 0;
        if (heapSize < 1)
        {
            System.out.println("Heap underflow");
        }
        else
        {
            // set the max value to extract
            max = maxHeap[0].getValue();
            int minIndexOfMax = maxHeap[0].getMinArrIndex();

            // create a new node consists of the last element details in max
            // heap
            TreeNode newNode = new TreeNode();
            newNode.setValue(maxHeap[heapSize].getValue());
            newNode.setMaxArrIndex(maxHeap[heapSize].getMaxArrIndex());
            newNode.setMinArrIndex(maxHeap[heapSize].getMinArrIndex());

            // replace the max node with the new node
            maxHeap[0] = newNode;
            minHeap[newNode.getMinArrIndex()].setMaxArrIndex(0);
            // clear references
            maxHeap[heapSize] = null;

            // in case that this is the last element, just update references and
            // heapify
            if (minIndexOfMax == heapSize)
            {
                minHeap[heapSize] = null;
                heapSize = heapSize - 1;
                maxHeapify(maxHeap, 0);
                minHeapify(minHeap, 0);
            }
            else
            {
                newNode = new TreeNode();
                newNode.setValue(minHeap[heapSize].getValue());
                newNode.setMaxArrIndex(minHeap[heapSize].getMaxArrIndex());
                newNode.setMinArrIndex(minHeap[heapSize].getMinArrIndex());

                minHeap[minIndexOfMax] = newNode;
                maxHeap[newNode.getMaxArrIndex()].setMinArrIndex(minIndexOfMax);

                minHeap[heapSize] = null;

                heapSize = heapSize - 1;

                maxHeapify(maxHeap, 0);
                minHeapify(minHeap, minIndexOfMax);
            }

        }
        return max;
    }

    /**
     * Exract Min. This action extracts the min of this heap and update the
     * references to the max heap and change the max heap accordingly. Time
     * Complexity is: O(lgn).
     * 
     * @return int the min number extracted
     */
    public int deleteMin()
    {
        int min = 0;
        if (heapSize < 1)
        {
            System.out.println("Heap underflow");
        }
        else
        {
            // set the min value
            min = minHeap[0].getValue();
            int maxIndexOfMax = minHeap[0].getMaxArrIndex();

            // create a new node with all last element min heap details
            TreeNode newNode = new TreeNode();
            newNode.setValue(minHeap[heapSize].getValue());
            newNode.setMaxArrIndex(minHeap[heapSize].getMaxArrIndex());
            newNode.setMinArrIndex(minHeap[heapSize].getMinArrIndex());

            minHeap[0] = newNode;
            maxHeap[newNode.getMaxArrIndex()].setMinArrIndex(0);

            minHeap[heapSize] = null;
            // in case that this is the last element, clear references and
            // heapify the heaps
            if (maxIndexOfMax == heapSize)
            {
                maxHeap[heapSize] = null;

                heapSize = heapSize - 1;

                minHeapify(minHeap, 0);
                maxHeapify(maxHeap, 0);
            }
            else
            {
                newNode = new TreeNode();
                newNode.setValue(maxHeap[heapSize].getValue());
                newNode.setMaxArrIndex(maxHeap[heapSize].getMaxArrIndex());
                newNode.setMinArrIndex(maxHeap[heapSize].getMinArrIndex());

                maxHeap[maxIndexOfMax] = newNode;
                minHeap[newNode.getMinArrIndex()].setMaxArrIndex(maxIndexOfMax);

                maxHeap[heapSize] = null;

                heapSize = heapSize - 1;

                minHeapify(minHeap, 0);
                maxHeapify(maxHeap, maxIndexOfMax);
            }
        }
        return min;
    }

    /**
     * Insert a new node to the Max heap.
     * 
     * @param newNode
     *            the node to insert
     */
    private void insertMaxHeap(
            TreeNode newNode)
    {
        int i = heapSize;

        // insert to Max heap
        while (i > 0 && (maxHeap[(i - 1) / 2].getValue() < newNode.getValue()))
        {
            TreeNode node = new TreeNode();
            node.setValue(maxHeap[(i - 1) / 2].getValue());
            node.setMinArrIndex(maxHeap[(i - 1) / 2].getMinArrIndex());
            node.setMaxArrIndex(maxHeap[(i - 1) / 2].getMaxArrIndex());

            maxHeap[i] = node;
            // update reference in Min heap
            minHeap[maxHeap[i].getMinArrIndex()].setMaxArrIndex(i);
            // change to parent(i)
            i = (i - 1) / 2;
        }
        newNode.setMaxArrIndex(i);
        maxHeap[i] = newNode;
    }

    /**
     * Insert a new node to the min heap.
     * 
     * @param newNode
     *            the new node to insert.
     */
    private void insertMinHeap(
            TreeNode newNode)
    {
        int i = heapSize;

        // insert to Max heap
        while (i > 0 && (minHeap[(i - 1) / 2].getValue() > newNode.getValue()))
        {
            TreeNode node = new TreeNode();
            node.setValue(minHeap[(i - 1) / 2].getValue());
            node.setMinArrIndex(minHeap[(i - 1) / 2].getMinArrIndex());
            node.setMaxArrIndex(minHeap[(i - 1) / 2].getMaxArrIndex());

            minHeap[i] = node;
            // update reference in Min heap
            maxHeap[minHeap[i].getMaxArrIndex()].setMinArrIndex(i);
            // set to parent(i)
            i = (i - 1) / 2;
        }
        newNode.setMinArrIndex(i);
        minHeap[i] = newNode;
    }

    /**
     * Max Heapify
     * 
     * @param heap
     *            the heap to perform this action
     * @param index
     *            to perform this action
     */
    private void maxHeapify(
            TreeNode[] heap, int index)
    {
        int largest;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        if (left <= heapSize && heap[left].getValue() > heap[index].getValue())
        {
            largest = left;
        }
        else
        {
            largest = index;
        }

        if (right <= heapSize && heap[right].getValue() > heap[largest].getValue())
        {
            largest = right;
        }
        if (largest != index)
        {
            maxExchange(heap, index, largest);
            maxHeapify(heap, largest);
        }
    }

    /**
     * Swap the given heap indexes
     * 
     * @param heap
     *            the heap to perform this action at.
     * @param index
     *            the index of first element
     * @param largest
     *            the index of the second element
     */
    private void maxExchange(
            TreeNode[] heap, int index, int largest)
    {
        int tempval = heap[index].getValue();
        int tempMinIndex = heap[index].getMinArrIndex();

        // swap values A[i]<->A[largest]
        heap[index].setValue(heap[largest].getValue());
        heap[index].setMinArrIndex(heap[largest].getMinArrIndex());

        heap[largest].setValue(tempval);
        heap[largest].setMinArrIndex(tempMinIndex);

        // swap max array reference in Minimum heap
        this.minHeap[heap[largest].getMinArrIndex()].setMaxArrIndex(largest);
        this.minHeap[heap[index].getMinArrIndex()].setMaxArrIndex(index);
    }

    /**
     * Min Heapify
     * 
     * @param heap
     *            the heap to perform this action at
     * @param index
     *            the element index
     */
    private void minHeapify(
            TreeNode[] heap, int index)
    {
        int smallest;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        if (left <= heapSize && heap[left].getValue() < heap[index].getValue())
        {
            smallest = left;
        }
        else
        {
            smallest = index;
        }

        if (right <= heapSize && heap[right].getValue() < heap[smallest].getValue())
        {
            smallest = right;
        }
        if (smallest != index)
        {
            minExchange(heap, index, smallest);
            minHeapify(heap, smallest);
        }
    }

    /**
     * Swap the given heap elements
     * 
     * @param heap
     * @param index
     * @param smallest
     */
    private void minExchange(
            TreeNode[] heap, int index, int smallest)
    {
        int tempval = heap[index].getValue();
        int tempMaxIndex = heap[index].getMaxArrIndex();

        // swap values A[i]<->A[smallest]
        heap[index].setValue(heap[smallest].getValue());
        heap[index].setMaxArrIndex(heap[smallest].getMaxArrIndex());

        heap[smallest].setValue(tempval);
        heap[smallest].setMaxArrIndex(tempMaxIndex);

        // swap max array reference in Minimum heap
        this.maxHeap[heap[smallest].getMaxArrIndex()].setMinArrIndex(smallest);
        this.maxHeap[heap[index].getMaxArrIndex()].setMinArrIndex(index);
    }

    public TreeNode[] getMaxHeap()
    {
        return maxHeap;
    }

    public TreeNode[] getMinHeap()
    {
        return minHeap;
    }
}

class TreeNode
{
    private int value;
    private int minArrIndex;
    private int maxArrIndex;

    public int getMaxArrIndex()
    {
        return maxArrIndex;
    }

    public void setMaxArrIndex(
            int maxArrIndex)
    {
        this.maxArrIndex = maxArrIndex;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(
            int value)
    {
        this.value = value;
    }

    public int getMinArrIndex()
    {
        return minArrIndex;
    }

    public void setMinArrIndex(
            int minArrIndex)
    {
        this.minArrIndex = minArrIndex;
    }

}
