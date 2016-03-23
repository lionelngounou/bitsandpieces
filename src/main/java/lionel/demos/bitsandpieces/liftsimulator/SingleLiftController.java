package lionel.demos.bitsandpieces.liftsimulator;

import java.util.Queue;

public abstract class SingleLiftController extends LiftController {

    private final CommonLift lift;

    public SingleLiftController(CommonLift lift, Queue<Call> liftCalls, Queue<Call> destinationCalls) {
        super(liftCalls, destinationCalls);
        this.lift = lift;
    }

    @Override
    public void run() {
        while (true) {
            Call nextLiftCall = this.getLiftCalls().peek();
            Call nextDestinationCall = this.getDestinationCalls().peek();

            if (nextLiftCall != null
                    && (nextDestinationCall == null || nextLiftCall.compareTo(nextDestinationCall) > 1)) {
                this.doLiftCall(nextLiftCall);
                this.getLiftCalls().poll();
            } else if (nextDestinationCall != null) {
                this.doDestinationCall(nextDestinationCall);
                this.getDestinationCalls().poll();
            }
        }
    }

    public CommonLift getLift() {
        return lift;
    }
}
