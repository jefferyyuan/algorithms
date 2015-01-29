package codebytes.mergesort;
import java.util.Arrays;

public class MergeSort { 
    
    public static void mergeSort(int[] orig){
        int temp[] = new int[orig.length];
        mergeSorter(0, orig.length-1, orig, temp);
    }
    
    private static void mergeSorter(int beg, int end, int[] orig, int[] temp) {
        if (beg == end) {
            return;
        }  
        mergeSorter(beg, (beg + end) / 2, orig, temp); 
        mergeSorter((beg + end) / 2 + 1, end, orig, temp);  
        merge(beg, end, orig, temp); 
        System.arraycopy(temp, beg, orig, beg, (end-beg+1));
    } 
    
    private static void merge(int beg, int end, int[] orig, int[] temp){
        int s1 = beg;           //first half starting position
        int e1 = (beg+end)/2;   //first half ends here
        int s2 = (beg+end)/2+1; //second half starts here
        int e2 = end;           
        
        int i = beg, l = s1, r = s2;
        while(l<=e1 && r<=e2){ 
            if (orig[l] >= orig[r]) {
                temp[i] = orig[r];  
                r++;
            } else if (orig[l] < orig[r]) {
                temp[i] = orig[l];  
                l++;
            } 
            i++;
        } 

        if (l == e1+1) { 
            System.arraycopy(orig, r, temp, i, e2-r+1);  
        } else if (r == e2+1) { 
            System.arraycopy(orig, l, temp, i, e1-l+1); 
        }   
    }
    
    public static void main(String[] args) { 
        int[] input = new int[]{5, 6, 3, 2, 2, 9, 9, 0};    //test array
        MergeSort.mergeSort(input);
        System.out.print(Arrays.toString(input));
    }
}
