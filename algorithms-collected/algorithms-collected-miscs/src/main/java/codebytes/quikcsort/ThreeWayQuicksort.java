package codebytes.quikcsort;
import java.util.Arrays;

/**
 * http://www.codebytes.in/2014/10/3-way-quicksort-with-improvements.html
 *
 */
public class ThreeWayQuicksort { 
    
    public static void qSort(int[] a){
        qSorter(a, 0, a.length-1);
    }
    
    private static void qSorter(int[] a, int lo, int hi){
        
        if(lo>=hi) return;
        
        /*
          If the sub array is of size 10 or less
          it is better to use insertion sort instead
        */
        if (hi - lo <= 10) { 
            InsertionSort.insertionSort(a, lo, hi);
            return;
        }

        if(hi - lo <= 25) {
            /*
              If the sub array is small, we can just use the middle
              element as the element to compare with
            */
            swap(a, lo, (lo+hi)/2); 
        }else if(hi - lo <= 50){ 
            /*
              If the sub array is medium in size, we can use the median
              of three elements lo, (mid), and hi and set it as the
              element to compare with.
            */
            int m = median(a, lo, (lo + hi) / 2, hi); 
            swap(a, m, lo); 
        }else{  
            
            //Tukey's ninther 
            int a1[] = new int[3];
            int a2[] = new int[3];
            int a3[] = new int[3]; 
            
            int gap = (hi-lo+1)/9;  
            if(((hi-lo+1)+gap)%9<gap)gap++; //Tricky logic!
            
            //System.out.println("Gap = "+gap);
            
            //Indexes
            int a1I = 0, a2I = 0, a3I = 0, arrayNo = 0;
            for(int i=lo;arrayNo<9;i+=gap){ 
               if(arrayNo < 3)      a1[a1I++] = i; 
               else if(arrayNo < 6) a2[a2I++] = i;
               else                 a3[a3I++] = i;
               
               arrayNo++;
            }
            
            /* Debug code
            System.out.println("Median of "+Arrays.toString(a1));
            System.out.println("Median of "+Arrays.toString(a2));
            System.out.println("Median of "+Arrays.toString(a3));
            */
            
            int median = median(a, median(a, a1[0], a1[1], a1[2]),
                                   median(a, a2[0], a2[1], a2[2]),
                                   median(a, a3[0], a3[1], a3[2]));
            swap(a, median, lo);
            //System.out.println("Ninther = "+a[lo]);
        }
        
        int lt = lo, gt = hi, i = lo;
        int v = a[lo];
        
        while(i<=gt){
            if(a[i]<v){ swap(a, i++, lt++);}
            else if(a[i]>v){swap(a, i, gt--);}
            else i++;
            //System.out.println(Arrays.toString(a));
        }
        
        qSorter(a, lo, lt-1); 
        qSorter(a, gt+1, hi);
    }
    
    public static int median(int[] x, int a, int b, int c) {
        if (x[a] > x[b] && x[a] > x[c]) { if (x[b] > x[c]) return b; else return c; }
        else if (x[b] > x[a] && x[b] > x[c]) { if (x[a] > x[c]) return a; else return c; } 
        else if (x[c] > x[a] && x[c] > x[b]) { if (x[a] > x[b]) return a; }
        return b;
    }
    
    private static void swap(int[] array, int a, int b){ 
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public static void main(String[] args){
        //Two arrays to test
        //int array[] = {2,3,5,7,8,3,5,6,8,4,3,2,5,6,7,8};
        int array[] = {2,3,4,1,5,6,7,8,9,10,11,2,3,4,5,2,3,14,19,20,23,22,1,4,3,2,5};
        qSort(array);
        System.out.println(Arrays.toString(array));
    }
}

class InsertionSort {

    public static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; ++i) {
            int j = i;
            while (a[j] < a[j - 1]) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
                if (--j == 0) {
                    break;
                }
            }
        }
    }
}