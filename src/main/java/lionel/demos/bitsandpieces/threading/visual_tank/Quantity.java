package lionel.demos.bitsandpieces.threading.visual_tank;

public class Quantity {

    private volatile int value = 0;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
