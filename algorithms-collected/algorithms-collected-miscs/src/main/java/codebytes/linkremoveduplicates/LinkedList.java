package codebytes.linkremoveduplicates;
public class LinkedList <T>{
    
    Node head;
    
    class Node{
        Node next;
        T value;
        
        Node(T value){this.value = value;}
    }
    
    public void add(T value){
        if(head==null){head = new Node(value); return;}
        Node t = head;
        while(t.next!=null){t = t.next;}
        t.next = new Node(value); 
    }
    
    public boolean isEmpty(){
        return head==null;
    }
    
    @Override
    public String toString(){
        StringBuilder bf = new StringBuilder();
        Node t = head;
        while(t!=null){
            bf.append(t.value);
            t = t.next;
        }
        return bf.toString();
    }
}




import java.util.HashSet; 

