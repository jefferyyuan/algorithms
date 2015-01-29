package codebytes;
public class Code{
    public static void main(String[] args){
        Integer[] input = {11,3,7,4,1,3,-1,2}; 
        MinStack<Integer> s = new MinStack<>(input.length+4);
        for(int i:input)s.push(i); 
        //System.out.println(s);
         
        System.out.println("Popping "+s.pop());
        System.out.println("Min = "+s.min()+"\n\n");
        s.pop();  
        s.push(44);
        s.push(-4);
        s.push(23);
        s.push(33);
        //System.out.println(s);
        while(!s.isEmpty()){
            System.out.println("Min: "+s.min());
            System.out.println("Popping: "+s.pop());
        } 
    }
}
