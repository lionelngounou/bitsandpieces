package lionel.demos.bitsandpieces.liftsimulator;

import java.util.concurrent.ConcurrentLinkedQueue;

public final class LiftActiveController extends SingleLiftController {

    private static LiftController instance;

    public static LiftController getInstance() {
        if (instance == null) {
            instance = new LiftActiveController();
        }
        return instance;
    }

    private LiftActiveController() {
        super(new Lift(), new ConcurrentLinkedQueue<Call>(), new ConcurrentLinkedQueue<Call>());
    }

    @Override
    public void doLiftCall(Call liftCall) {
        try {
            this.getLift().move(liftCall.getFloor());
            liftCall.setServiced(true);
        } catch (Exception e) {
            System.out.println(" Had a problem moving for lift call");
            e.printStackTrace();
        }
    }

    @Override
    public void doDestinationCall(Call destinationCall) {
        try {
            this.getLift().move(destinationCall.getFloor());
            destinationCall.setServiced(true);
        } catch (Exception e) {
            System.out.println(" Had a problem moving for destination call");
            e.printStackTrace();
        }
    }
}
