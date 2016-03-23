package lionel.demos.bitsandpieces.liftsimulator;

import java.util.Random;

public class Building {

    public static final byte FLOORS = 10;

    public static void main(String[] args) {
        LiftController controller = LiftActiveController.getInstance();
        Thread engine = new Thread(controller, "Lift Server");
        engine.start();
        while (true) {
            int floor = (int) new Random().nextDouble();
            while (floor < 1 || floor > 10) {
                floor = (int) new Random().nextDouble();
            }
            try {
                controller.service(new LiftCall((byte) floor), LiftController.LIFTCALLTYPE);
                Thread.currentThread();
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("Had a problem servicing lift call");
            }
        }
    }
}
