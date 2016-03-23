package lionel.demos.bitsandpieces.liftsimulator;

import java.util.GregorianCalendar;

public interface Call extends Comparable<Call> {

    byte getFloor();

    boolean isServiced();

    void setServiced(boolean serviced);

    GregorianCalendar getTime();
}
