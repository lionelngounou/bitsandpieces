package lionel.demos.bitsandpieces.threading.bouncingball;

import java.util.Set;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import static java.awt.geom.Line2D.Double;

import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable{
	private Set<Ball> balls;
	private static final int BALL_COUNT = 3;
	private static final int MAX_X = 800, MAX_Y = 500;
	public static final Point MAX_POINT = new Point(MAX_X, MAX_Y); 
	
	public static final Double TOP_LINE = new Double(0,0,MAX_X,0);
	public static final Double BOTTOM_LINE = new Double(0,MAX_Y,MAX_X,MAX_Y);
	public static final Double RIGHT_LINE = new Double(0,0,0,MAX_Y);
	public static final Double LEFT_LINE = new Double(MAX_X,0,MAX_X,MAX_Y);	
	
	private Frame() {
		 balls = new java.util.HashSet<Ball>(5);
	}
	
	public void init(){
		setTitle("Loosed Ball(s)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container =  getContentPane();
		container.setLayout(null); 
		container.setBackground(Color.BLACK);
		setSize(MAX_X, MAX_Y);
		setVisible(true);
		launchBalls();
	}
	
	public void run(){
		try{Thread.sleep(2500);} catch(InterruptedException ie){ie.printStackTrace();}
		for(;;){
			for(Ball ball: balls)
				ball.run(null);
			repaint();
			try{Thread.sleep(50);} catch(InterruptedException ie){ie.printStackTrace();}
		}
	}
	private void launchBalls(){
		for(int c=0; c<BALL_COUNT; c++){
			Ball ball = new Ball();
			balls.add(ball);
			//new Thread(ball).start();
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Ball ball: balls){
			//if(ball.hasChangedLocation()){
				g2.setPaint(ball.getBorder());
				g2.draw(ball);
				g2.setPaint(ball.getBackground());
				g2.fill(ball);
		//	}
		}	
	}
	
	public boolean hasBeenTouched(Point p){
		return hasBeenTouched(p.x,p.y);
	}
	
	public boolean hasBeenTouched(double x, double y){
		return x<=0 || x>= MAX_X || y<=0 || y>=MAX_Y;
	}

	private static final class Holder{
		private static final Frame INSTANCE = new Frame();
	}
	public static Frame getInstance(){
		return Holder.INSTANCE;
	}

	public Set<Ball> getBalls() {
		return balls;
	}
	private static final long serialVersionUID = -454544781L;
}
