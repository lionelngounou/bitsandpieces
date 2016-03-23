package lionel.demos.bitsandpieces.liftsimulator;

public class User implements LiftUser, Runnable {

    private static int count = 0;
    private byte floor;
    private String name;
    private LiftController liftController;

    public User(LiftCall liftCall) {
        this(String.valueOf(++count), liftCall);
    }

    public User(String name, LiftCall liftCall) {
        this.name = name;
        this.floor = liftCall.getFloor();
        this.liftController = LiftActiveController.getInstance();
    }

    @Override
    public void run() {
		// TODO Auto-generated method stub

    }

    @Override
    public boolean enterLift() {
        /*
         CommonLift lift = liftController.getLift();
         if(lift.isOpened()){
			
         }
         System.out.println(this.name + " entering Lift at Floor " + this.getFloor() + "..." );
         */
        return false;
    }

    @Override
    public boolean exitLift() {/*
         System.out.println(this.name + " exiting Lift at Floor " 
         + this.liftController.getLift().getCurrentFloor() + "..." );*/

        return false;
    }

    @Override
    public void makeServiceCall(Call call, byte type) {
        System.out.println(this.name + " making lift service call...");
    }

    public void setFloor(byte floor) {
        this.floor = floor;
    }

    public byte getFloor() {
        return floor;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isInLift() {
        return true;//liftController.getLift().getUsers().contains(this);
    }
}
