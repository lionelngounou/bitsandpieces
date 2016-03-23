package lionel.demos.bitsandpieces.threading.produce_consume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * for getting and setting a single value.
 */
public class SingleBlockingBuffer implements BlockingBuffer {

    private Integer value = null;
    private final Lock lock = new ReentrantLock();
    private final Condition readCondition = lock.newCondition(), writeCondition = lock.newCondition();

    @Override
    public int get() {
        while (!lock.tryLock());
        while (isEmpty()) {
            try {
                Engine.print("\t\tconsumer waiting for lock to get value>>>");
                readCondition.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        int val = value;
        value = null; //empty buffer
        writeCondition.signal();
        lock.unlock();
        return val;
    }

    @Override
    public void set(int value) {
        while (!lock.tryLock());
        while (!isEmpty()) {
            try {
                Engine.print("\t\tproducer waiting for lock to set value " + value);
                writeCondition.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        this.value = value;
        readCondition.signal();
        lock.unlock();
    }

    private boolean isEmpty() {
        return this.value == null;
    }

}
