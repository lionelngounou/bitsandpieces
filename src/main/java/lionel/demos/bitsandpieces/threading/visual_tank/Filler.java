package lionel.demos.bitsandpieces.threading.visual_tank;

import javax.swing.JProgressBar;

/**Filler responsible for filling the tank*/
public class Filler extends Thread {

    private final Quantity quantity;
    private final JProgressBar tank;

    public Filler(JProgressBar tank, Quantity quantity) {
        super("Filler");
        this.tank = tank;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (quantity) {
                if (quantity.getValue() == 0) {
                    try {
                        quantity.wait();
                    } catch (InterruptedException ie) {
                        System.err.println(this.getName() + " Quantity Wait Interrupted!");
                    }
                }
                try {
                    this.sleep(500);
                } catch (InterruptedException ie) {
                    System.err.println(this.getName() + " Sleep Interrupted!");
                }
                if (tankFull()) {
                    tank.setValue(tank.getMinimum());
                } else {
                    tank.setValue(tank.getValue() + quantity.getValue());
                }
                quantity.setValue(0);
                quantity.notify();
            }
        }
    }

    private boolean tankFull() {
        return getTankCapacity() == 100;
    }

    private Integer getTankCapacity() {
        return ((tank.getValue() * 100) / tank.getMaximum());
    }
}
