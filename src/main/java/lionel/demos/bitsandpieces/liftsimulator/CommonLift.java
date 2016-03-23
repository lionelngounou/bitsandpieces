package lionel.demos.bitsandpieces.liftsimulator;

import java.util.Set;

public interface CommonLift {

    void open();

    void close();

    void move(byte floorDestination) throws Exception;

    void stop();

    byte getCurrentFloor();

    Set<LiftUser> getUsers();

    double getMaximumCapacity();

    byte getMaximumSize();

    boolean isOpened();
}
