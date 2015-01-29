package codebytes.stacksorter;
class OrderedStack<T extends Comparable<T>> extends Stack<T>{ 
    public OrderedStack(int size) {
        super(0); 
        elements = (T[])new Comparable[size];  
    } 
}

class StackSorter{
    
    public static <T extends Comparable<T>> OrderedStack<T> sort(Stack<T> unsorted){
        OrderedStack<T> sorted = new OrderedStack<>(unsorted.size());
        if(unsorted.isEmpty())return sorted;
        
        sorted.push(unsorted.pop());
        while(!unsorted.isEmpty()){
            T temp = unsorted.pop(); 
            int i=0; 
            while(!sorted.isEmpty() && sorted.peek().compareTo(temp)>0){
                unsorted.push(sorted.pop());i++;
            }
            sorted.push(temp);
            while(i--!=0)sorted.push(unsorted.pop());
        }
        return sorted;
    } 
}

public class Code{
    public static void main(String[] args){
        Integer test[] = {2,3,4,4,2,22,34,5,6,7,8,867,1,5};
        Stack<Integer> stack = new Stack<>(test.length);

        for(int i:test)stack.push(i);
        stack = StackSorter.sort(stack);
        while(!stack.isEmpty())System.out.print(stack.pop()+ " ");
    }
}


