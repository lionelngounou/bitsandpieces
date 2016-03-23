package lionel.demos.bitsandpieces.liftsimulator;

import java.util.Queue;

public abstract class LiftController implements Runnable {

    public static final byte LIFTCALLTYPE = 1;
    public static final byte DESTINATIONCALLTYPE = 2;

    private final Queue<Call> liftCalls, destinationCalls;

    public LiftController(Queue<Call> liftCalls, Queue<Call> destinationCalls) {
        this.liftCalls = liftCalls;
        this.destinationCalls = destinationCalls;
    }

    public final void service(Call call, byte callType) throws Exception {
        if (callType == LIFTCALLTYPE) {
            if (!this.getLiftCalls().contains(call)) {
                this.getLiftCalls().offer(call);
            }
            //doLiftCall(call);
        } else if (callType == DESTINATIONCALLTYPE) {
            if (!this.getDestinationCalls().contains(call)) {
                this.getDestinationCalls().offer(call);
            }
            //doDestinationCall(call);
        } else {
            throw new Exception("The Call Type: " + callType + " is not part of the calls serviced.");
        }
    }

    public abstract void doLiftCall(Call liftCall);

    public abstract void doDestinationCall(Call destinationCall);

    public Queue<Call> getLiftCalls() {
        return liftCalls;
    }

    public Queue<Call> getDestinationCalls() {
        return destinationCalls;
    }
}
