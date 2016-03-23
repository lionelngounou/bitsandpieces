package lionel.demos.bitsandpieces.threading.visual_tank;

import javax.swing.JProgressBar;

/**Drainer responsible for draining the tank*/
public class Drainer extends Thread {

    private final Quantity quantity;
    private final JProgressBar tank;

    public Drainer(JProgressBar tank, Quantity quantity) {
        super("Drainer");
        this.tank = tank;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (quantity) {
                if (quantity.getValue() != 0) {
                    try {
                        quantity.wait();
                    } catch (InterruptedException ie) {
                        System.err.println(this.getName() + " Quantity Wait Interrupted!");
                    }
                }
                quantity.setValue(10);
                if (tankEmpty()) {
                    tank.setValue(tank.getMaximum());
                } else {
                    tank.setValue(tank.getValue() - quantity.getValue());
                }
                try {
                    this.sleep(500);
                } catch (InterruptedException ie) {
                    System.err.println(this.getName() + " Sleep Interrupted!");
                }
                quantity.notify();
            }
        }
    }

    private boolean tankEmpty() {
        return getTankCapacity() == 0;
    }

    private Integer getTankCapacity() {
        return ((tank.getValue() * 100) / tank.getMaximum());
    }
}
