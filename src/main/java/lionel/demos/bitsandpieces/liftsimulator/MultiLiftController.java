package lionel.demos.bitsandpieces.liftsimulator;

import java.util.Queue;

public abstract class MultiLiftController extends LiftController {

    private final CommonLift[] lifts;

    public MultiLiftController(CommonLift[] lifts, Queue<Call> liftCalls, Queue<Call> destinationCalls) {
        super(liftCalls, destinationCalls);
        this.lifts = lifts;
    }

    @Override
    public void run() {
    }

    public CommonLift[] getLift() {
        return lifts;
    }
}
