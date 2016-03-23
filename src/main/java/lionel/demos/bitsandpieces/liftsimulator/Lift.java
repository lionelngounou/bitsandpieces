package lionel.demos.bitsandpieces.liftsimulator;

import java.util.HashSet;
import java.util.Set;

public class Lift implements CommonLift {

    private Set<LiftUser> users;
    private byte currentFloor;
    private boolean closed;
    private boolean moving;

    public Lift() {
        users = new HashSet<>(getMaximumSize());
        currentFloor = (byte) 1;
        this.closed = true;
    }

    @Override
    public void open() {
        if (this.isOpened()) {
            return;
        }
        while (this.isMoving()) {
            System.out.println("Waiting for Lift To Stop Moving...");
            try {
                Thread.currentThread();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Opening time interrupted...");
                e.printStackTrace();
            }
        }
        System.out.println("Door Opening...");
        this.setClosed(false);
        try {
            Thread.currentThread();
            Thread.sleep(3000);//stay opened for 3 secs
        } catch (InterruptedException e) {
            System.out.println("Opening time interrupted...");
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (this.isClosed()) {
            return;
        }
        System.out.println("Door Closing...");
        try {
            Thread.currentThread();
            Thread.sleep(1000);//take a second to close;
        } catch (InterruptedException e) {
            System.out.println("Closing interrupted...");
            e.printStackTrace();
        }
        this.setClosed(true);
    }

    @Override
    public void move(byte floorDestination) throws Exception {
        while (this.isOpened()) {
            this.close();
        }
        String where = floorDestination > this.getCurrentFloor() ? "UP"
                : floorDestination < this.getCurrentFloor() ? "Down"
                        : "NoWhere";
        int dir = floorDestination > this.getCurrentFloor() ? 1
                : floorDestination < this.getCurrentFloor() ? -1
                        : 0;
        System.out.println("Lift going " + where + "... To Floor: " + floorDestination);
        if (dir != 0) {
            this.setMoving(true);
            while (this.getCurrentFloor() != floorDestination) {
                System.out.println("Lift moving... Floor " + this.getCurrentFloor());
                Thread.currentThread().sleep(1000);
                this.setCurrentFloor((byte) (this.getCurrentFloor() + dir));
            }
            this.stop();
        }
        this.open();
        this.close();
    }

    @Override
    public void stop() {
        System.out.println("Lift Stopping... Floor " + this.getCurrentFloor());
        setMoving(false);
    }

    @Override
    public final double getMaximumCapacity() {
        return getMaximumSize() * 100.00;
    }

    @Override
    public final byte getMaximumSize() {
        return 5;
    }

    @Override
    public Set<LiftUser> getUsers() {
        return users;
    }

    public void setUsers(Set<LiftUser> users) {
        this.users = users;
    }

    @Override
    public byte getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(byte currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    public boolean isOpened() {
        return !closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
