package lionel.demos.bitsandpieces.threading.produce_consume;

public class Consumer implements Runnable {

    private final BlockingBuffer blockingBuffer;
    private final long rate; 
    
    public Consumer(BlockingBuffer blockingBuffer) {
        this(blockingBuffer, 500);
    }
    
    public Consumer(BlockingBuffer blockingBuffer, long rate) {
        this.blockingBuffer = blockingBuffer;
        this.rate = rate;
    }

    @Override
    public void run() {
        for (;;) {
            Engine.sleep(rate);
            Engine.print("...consumer - get from buffer [Start]");
            int value = blockingBuffer.get();
            Engine.print(String.format("...consumer - get ---> [%d] [End]", value));
            if (value == -1) {
                break; // stop when -1 is produced
            }
        }
    }
}
