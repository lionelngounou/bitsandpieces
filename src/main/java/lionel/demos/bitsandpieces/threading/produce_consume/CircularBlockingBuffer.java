package lionel.demos.bitsandpieces.threading.produce_consume;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBlockingBuffer implements BlockingBuffer {

    private static final int CAPACITY = 3;
    private final Integer[] memory = new Integer[CAPACITY];
    private final ReentrantLock reLock = new ReentrantLock();
    private final Condition writeCondition = reLock.newCondition(), readCondition = reLock.newCondition();
    private volatile int writeLocation, readLocation, size;

    public CircularBlockingBuffer() {
        this.writeLocation = 0;
        this.readLocation = 0;
        this.size = 0;
    }
    
    @Override
    public int get() {
        while (!reLock.tryLock());
        try{
            checkReading();
            
            int val = memory[readLocation];
            memory[readLocation] = null;
            if (++readLocation == CAPACITY) {
                readLocation = 0; //go back to beginning
            }
            size--; //decrease size of elements in the memory

            if (reLock.hasWaiters(writeCondition)) {
                writeCondition.signalAll();
            }
            Engine.print("\t\tAfter Consume - state: " + Arrays.toString(memory));
            return val;
        }
        finally{
            reLock.unlock();
        }
    }

    @Override
    public void set(int value) {
        while (!reLock.tryLock());
        try{
            checkWriting();
            
            memory[writeLocation] = value;
            if (++writeLocation == CAPACITY) {
                writeLocation = 0; //go back to beginning
            }
            size++; //increase size of elements in the memory
            
            if (reLock.hasWaiters(readCondition)) {
                readCondition.signalAll();
            }
            Engine.print("\t\tAfter Produce - state: " + Arrays.toString(memory));
        }
        finally{
            reLock.unlock();
        }
    }

    private boolean isFull() {
        return size == CAPACITY;
    }

    private boolean isEmpty() {
        return size <= 0;
    }
    
    private void checkReading(){
        while (this.isEmpty()) { //wait to read, if memory is empty
            try {
                Engine.print("\t\tEmpty buffer. Consumer waiting.......");
                readCondition.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
    private void checkWriting(){
        while (this.isFull()) {//wait available space, if memory is full
            try {
                Engine.print("\t\tFull buffer. Producer waiting.........");
                writeCondition.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
}
