package lionel.demos.bitsandpieces.threading.produce_consume;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Engine {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        //BlockingBuffer blockingBuffer = new SingleBlockingBuffer();
        BlockingBuffer blockingBuffer = new CircularBlockingBuffer();
        exec.execute(new Consumer(blockingBuffer));
        exec.execute(new Producer(blockingBuffer));
        exec.shutdown();
    }

    public static final synchronized void print(String msg) {
        //maybe print to a concurrent queue and retrieve with one thread?
        System.out.println(msg);
    }

    public static final void sleep(long millis) {
        try{
            Thread.sleep(millis);
        }	
        catch(InterruptedException ie){	
            ie.printStackTrace();
        }
    }
}
