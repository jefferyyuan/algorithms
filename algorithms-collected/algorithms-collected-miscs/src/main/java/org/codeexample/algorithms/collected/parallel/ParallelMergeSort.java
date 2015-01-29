package org.codeexample.algorithms.collected.parallel;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Random;
 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
 
 
class ParallelMergeSort<T> extends SerialMergeSort<T> {
 
   private static ExecutorService executor;
 
   protected int depth;
   
   public ParallelMergeSort(List<T> list, Comparator<T> comp){
      super(list, comp);
      if(executor == null) executor = Executors.newCachedThreadPool();
      this.depth = 0;
   }
 
   protected ParallelMergeSort(List<T> list, Comparator<T> comp, int depth){
      this(list, comp);
      this.depth = depth;
   }
 
   @Override
   public void run(){
      if(list.size() < 2) return;
 
      ParallelMergeSort<T> a = new ParallelMergeSort<T>(list.subList(0, list.size()/2), comp, depth + 1);
      ParallelMergeSort<T> b = new ParallelMergeSort<T>(list.subList(list.size()/2, list.size()), comp, depth + 1);
 
      // only be parallel if we're high up in the recursion.
      if(depth < 2){
         Future<?> fa = executor.submit(a);
         Future<?> fb = executor.submit(b);
 
         try{
            fa.get();
            fb.get();
         }catch(Exception e){
            // If some exception happens, then we fall back to working serially.
            System.out.println("Exception while sorting, falling back to serial operation.");
            e.printStackTrace();
            if(!a.isSorted()) a.run();
            if(!b.isSorted()) b.run();
         }
      }else{
         a.run();
         b.run();
      }
 
      merge(a.get(), b.get());
      this.sorted = true;
   }
 
   public static void main(String[] argv) {
      final int LENGTH = 100000;
      Random rand = new Random();
      ArrayList<Integer> array = new ArrayList<Integer>(LENGTH);
 
      for(int i = 0; i < LENGTH; ++i){
         array.add(rand.nextInt());
      }
 
      Comparator<Integer> comp = new Comparator<Integer>() {
         @Override
         public int compare(Integer a, Integer b){
            return a.compareTo(b);
         }
         @Override
         public boolean equals(Object a){
            return this == a;
         }
      };
      
      ParallelMergeSort<Integer> sort = new ParallelMergeSort<Integer>(array, comp);
      System.out.println("About to sort.");
      long ellapsedNanos = System.nanoTime();
      sort.run();
      ellapsedNanos = System.nanoTime() - ellapsedNanos;
      System.out.println("Done sorting.");
 
      Iterator<Integer> a = array.iterator();
      Iterator<Integer> b = array.iterator();
 
      b.next();
 
      while(b.hasNext()){
         if(a.next().compareTo(b.next()) > 0){
            System.out.println("Sort failed!");
            return;
         }
      }
      System.out.println("Success, sorting took " + ellapsedNanos + " nanoseconds.");
 
      if (executor != null) executor.shutdown();
   }
}