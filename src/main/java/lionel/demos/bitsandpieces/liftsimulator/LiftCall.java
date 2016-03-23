package lionel.demos.bitsandpieces.liftsimulator;

import java.util.GregorianCalendar;

public class LiftCall implements Call {

    private byte floor;
    private boolean serviced;

    private GregorianCalendar time;

    public LiftCall(byte floor) {
        this(floor, new GregorianCalendar());
    }

    public LiftCall(byte floor, GregorianCalendar time) {
        this.floor = floor > Building.FLOORS ? Building.FLOORS : floor;
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LiftCall) || (obj != this)) {
            return false;
        }
        LiftCall lc = (LiftCall) obj;
        return lc.getFloor() == this.getFloor() && lc.isServiced() == this.isServiced();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.floor;
        hash = 67 * hash + (this.serviced ? 1 : 0);
        return hash;
    }

    @Override
    public int compareTo(Call lc) {
        return this.getTime().before(lc.getTime()) ? 1
                : this.getTime().after(lc.getTime()) ? -1
                        : 0;
    }

    @Override
    public byte getFloor() {
        return floor;
    }

    public void setFloor(byte floor) {
        this.floor = floor;
    }

    @Override
    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    @Override
    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }

    @Override
    public boolean isServiced() {
        return serviced;
    }
}
