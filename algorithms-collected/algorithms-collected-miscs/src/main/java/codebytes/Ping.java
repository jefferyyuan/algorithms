package codebytes;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.*;
import java.net.*;

class Worker implements Runnable {

    static int id = 0;
    final int iden = ++id;
    static volatile String ip = "192.168.80.1";
    static Pattern pattern = Pattern.compile(".\\d+$");
    static Matcher m = null;
    static volatile int i = 1;
    static volatile boolean cancel = false;
    static volatile List<String> reachables = new ArrayList<>();

    public static synchronized void ping(int id, String ipLocal) throws UnknownHostException, IOException {
        InetAddress adr = null;
        if (i < 255) {

            m = pattern.matcher(ip);
            if (m.find()) {
                ip = m.replaceFirst("." + Integer.toString(++i));
                System.out.print("\nThread #" + id + " testing IP: " + ip);

                adr = InetAddress.getByName(ip);
                ipLocal = ip;
            }
        } else {
            cancel = true;
            return;
        }

        if (adr.isReachable(2000)) {
            System.out.print("\nAddress " + ipLocal + " is reachable!");
            reachables.add(ipLocal);
        } else {
            System.out.print("\nAddress " + ipLocal + " not reachable!");
        }
    }

    public void run() {
        String ipLocal = "";
        while (!cancel) {
            try {
                ping(iden, ipLocal);
                Thread.yield();
            } catch (IOException ex) {
                System.out.println("IOException caught!");
            }

        }
    }
}

public class Ping {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int j = 0; j < 10; ++j) {
            exec.execute(new Worker());
        }

        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                System.out.println("\n\nReachable IPs = ");
                for (String reach : Worker.reachables) {
                    System.out.println(reach);
                }
                break;
            }
        }
    }
}