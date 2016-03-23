package lionel.demos.bitsandpieces.liftsimulator;

public interface LiftUser {
	boolean enterLift();
	boolean exitLift();
	void makeServiceCall(Call call, byte type);
	String getName();
}
