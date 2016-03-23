package lionel.demos.bitsandpieces.threading.produce_consume;

import java.util.Random;

public class Producer implements Runnable {

    private final BlockingBuffer blockingBuffer;
    private final Random random;
    private final long rate; 
    private static final int MAX = 13;

    public Producer(BlockingBuffer blockingBuffer) {
        this(blockingBuffer, 550);
    }
    
    public Producer(BlockingBuffer blockingBuffer, long rate) {
        this.blockingBuffer = blockingBuffer;
        this.rate = rate;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i <= MAX; i++) {
            Engine.sleep(rate);
            int value = -1;

            if (i != MAX) {
                value = random.nextInt(100);
            }
            Engine.print(String.format("...producer set <--- [%d] [Start]", value));
            blockingBuffer.set(value);
            Engine.print(String.format("...producer set <--- [%d] [End]", value));
        }
    }
}
