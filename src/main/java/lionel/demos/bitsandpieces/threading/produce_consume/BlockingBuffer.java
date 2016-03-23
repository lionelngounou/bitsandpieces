package lionel.demos.bitsandpieces.threading.produce_consume;

public interface BlockingBuffer {

    public int get();

    public void set(int value);
}
