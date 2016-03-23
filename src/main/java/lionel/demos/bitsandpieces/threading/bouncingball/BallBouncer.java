package lionel.demos.bitsandpieces.threading.bouncingball;

import javax.swing.SwingUtilities;

public class BallBouncer {

	public static void main(String[] args) throws Exception {
		/*
		double a = Ball.angle(3,5,-1,-1,6,0);
		System.out.println("Radian: " + a);
		System.out.println("Degrees: " + Math.toDegrees(a));
		*/
		final Frame frame = Frame.getInstance();
		SwingUtilities.invokeAndWait(
			new Runnable(){
				public void run(){
					frame.init();
					new Thread(frame).start();
				}
			}
		);
	}

}
