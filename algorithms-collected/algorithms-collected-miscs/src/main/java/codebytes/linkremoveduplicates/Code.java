package codebytes.linkremoveduplicates;

import java.util.HashSet;

public class Code{
    
    public static LinkedList<Character> buildList(String s){ 
        LinkedList<Character> ll = new LinkedList<>();
        for(int i=0;i<s.length();++i)ll.add(s.charAt(i)); 
        return ll;
    }
    
    public static void removeDuplicatesUsingBuffer(LinkedList<Character> ll){ 
        LinkedList.Node t1 = ll.head;
        LinkedList.Node t2 = null;
         
        HashSet buffer = new HashSet();
        while(t1!=null){
            if(!buffer.contains(t1.value)){
                buffer.add(t1.value);
                t2 = t1;
                t1 = t1.next;
            }else{ 
                while(buffer.contains(t1.value)){
                    t1 = t1.next;
                    if(t1==null)break;
                }
                t2.next = t1; 
            }
        }
    }
    
    public static void removeDuplicatesWihoutBuffer(LinkedList<Character> ll){  
        LinkedList.Node t0 = ll.head;
        LinkedList.Node t1;
        LinkedList.Node t2;
        
        while(t0!=null){
            t1 = t0;
            t2 = t1.next;
            
            Object value = t1.value; 
            while(t2!=null){
                if(t2.value == value){ 
                    while(t2.value == value){
                        t2 = t2.next;
                        if(t2==null)break;
                    } 
                    t1.next = t2; 
                }else{
                    t1.next = t2;
                } 
                t1 = t2;
                if(t2!=null) t2 = t2.next; 
            }
            t0 = t0.next;  
        }
    }
    
    public static void testWithBuffer(String s){
        LinkedList<Character> ll = buildList(s);
        removeDuplicatesUsingBuffer(ll);
        System.out.println(ll.toString()); 
    }
    
    public static void testWithoutBuffer(String s){
        LinkedList<Character> ll = buildList(s); 
        removeDuplicatesWihoutBuffer(ll);
        System.out.println(ll.toString()); 
    }
    public static void main(String[] args){
        LinkedList<Character> ll = new LinkedList<>();
        
        System.out.println("Using a Buffer: ");
        testWithBuffer("a");
        testWithBuffer("aa");
        testWithBuffer("abbcdeeeeeffffaaadeee");
        testWithBuffer("azzzxxxa");
        testWithBuffer("abcdef"); 
        testWithBuffer("FOLLOW UP");
        testWithBuffer("www.codebytes.in");
        testWithBuffer("");
        
        System.out.println("\nWithout using a buffer: ");
        testWithoutBuffer("a");
        testWithoutBuffer("aa");
        testWithoutBuffer("abbcdeeeeeffffaaadeeee");
        testWithoutBuffer("azzzxxxa");
        testWithoutBuffer("abcdef"); 
        testWithoutBuffer("FOLLOW UP"); 
        testWithoutBuffer("www.codebytes.in");
        testWithoutBuffer(""); 
    }
}
