package systemdesign.codebytes.producerconsumer;
import java.util.Arrays;
import java.util.concurrent.*;
 
class Consumer implements Runnable {
 
    private final Scenario scenario;
 
    Consumer(Scenario s) {
        scenario = s;
        Arrays.fill(buffer, 0);
    }
 
    int buffer[] = new int[10];
 
    volatile int current = 0;
    volatile int total = 0;
 
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (buffer[current] == 0) {
                        wait();
                    }
                }
                synchronized (scenario.producer) {
                    System.out.println("Consuming item: " + current);
                    buffer[current++] = 0;
 
                    ++total;
                    scenario.producer.notifyAll();
                }
                if (current == 10) {
                    current = 0;
                }
                Thread.yield();
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted!");
 
        }
    }
}
 
class Producer implements Runnable {
 
    volatile int total = 0;
    private final Scenario scenario;
 
    Producer(Scenario s) {
        scenario = s;
    }
    volatile int current = 0;
 
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (scenario.consumer.buffer[(current == 9 ? -1 : current) + 1] == 1) {
                        wait();
                    }
                }
                synchronized (scenario.consumer) {
                    System.out.println("Producing item: " + current);
                    scenario.consumer.buffer[current++] = 1;
                    scenario.consumer.notifyAll();
 
                    ++total;
                }
                if (current == 10) {
                    current = 0;
                }
                Thread.yield();
            }
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted!");
        }
    }
}
 
public class Scenario {
 
    Consumer consumer = new Consumer(this);
    Producer producer = new Producer(this);
 
    ExecutorService exec = Executors.newCachedThreadPool();
 
    Scenario() {
        exec.execute(consumer);
        exec.execute(producer);
    }
 
    public static void main(String[] args) throws InterruptedException {
        Scenario sc = new Scenario();
        TimeUnit.MILLISECONDS.sleep(5);
        sc.exec.shutdownNow();
        System.out.println("Total items produced: " + sc.producer.total);
        System.out.println("Total items consumed: " + sc.consumer.total + "\n\n");
    }
}
