package lionel.demos.bitsandpieces.threading.bouncingball;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class Ball extends Ellipse2D.Double  implements Runnable {
	private Line2D path;
	private double direction;
	private Color background, border;
	private static final Random random = new Random();
	
	public Ball() {
		//this(random.nextInt(100),random.nextInt(50),40,40);
		this(605,5,40,40);
	}
	
	public Ball(double w,double h){
		this(0, 0, w, h);
	}
	
	public Ball(double x,double y,double w,double h){
		super(x, w, w, h);
		setBorder(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		//path = new Line2D.Double(x, y, Frame.MAX_POINT.x - 150 , Frame.MAX_POINT.y);
		path = new Line2D.Double(x, y, random.nextInt(Frame.MAX_POINT.x) , random.nextInt(Frame.MAX_POINT.y) );
		direction = +5.0;//moving positively in the x-axis, with magnitude 5
		resetDirection();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof Ball) {
			Ball other = (Ball)obj;
			return this.background.equals(other.background) && this.border.equals(other.border)
				&& this.getHeight() == other.getHeight(); 
		}
		return false;
	}
	
	public void run(Object o) {
		if(this.hasHitASurface()){
			resetPath();
			//resetCurrentPosition();
			resetDirection();
		}
		computeNextLocation();
	}
	
	public void run() {
		for(;;){
			if(this.hasHitASurface()){
				resetPath();
				//resetCurrentPosition();
				resetDirection();
			}
			computeNextLocation();
			synchronized(Frame.getInstance()){
				Frame.getInstance().repaint();
				//Frame.getInstance().repaint((int)Math.floor(x), (int)Math.floor(y), (int)Math.ceil(getWidth()), (int)Math.ceil(getHeight()));
			}
			try{Thread.sleep(50);} catch(InterruptedException ie){ie.printStackTrace();}
		}
	}
	
	private void resetPath(){
		calibratePathEndPoint();
		boolean reflectOnXAxis = true;
		Point2D endPoint = path.getP2();
		int fD = 2; //friction distance
		if(Frame.RIGHT_LINE.ptLineDist(endPoint)<=fD || Frame.LEFT_LINE.ptLineDist(endPoint)<=fD)
			reflectOnXAxis = false;
		Point2D midPoint = GeometryUtil.midPoint(path);
		double newEndPoint_X = midPoint.getX(), newEndPoint_Y = midPoint.getY();
		if(reflectOnXAxis) 
			newEndPoint_X = midPoint.getX() + (2 * (path.getX2() -midPoint.getX()));
			//newEndPoint_X = (2 * path.getP2().getX()) -  midPoint.getX();
		else
			newEndPoint_Y = midPoint.getY() + (2 * (path.getY2() -midPoint.getY()));
			//newEndPoint_Y = (2 * path.getP2().getY()) -  midPoint.getY();
		
		if(newEndPoint_X<=0) newEndPoint_X=0;
		else if(newEndPoint_X>=Frame.MAX_POINT.x) newEndPoint_X=Frame.MAX_POINT.x;
		if(newEndPoint_Y<=0) newEndPoint_X=0;
		else if(newEndPoint_Y>=Frame.MAX_POINT.y) newEndPoint_X=Frame.MAX_POINT.y;
		
		path.setLine(path.getX2(), path.getY2(), newEndPoint_X,newEndPoint_Y);
	}
	
	private void calibratePathEndPoint(){
		if(x<=0 || x>=Frame.MAX_POINT.x){
			x = (x<=0)? 0 : Frame.MAX_POINT.x;
			y = GeometryUtil.getY(path, x);
		}
		else if (y<=0 || y>=Frame.MAX_POINT.y){
			y = (y<=0)? 0 : Frame.MAX_POINT.y;
			x = GeometryUtil.getY(path, y);
		}
		path.setLine(path.getX1(), path.getY1(), x, y);
	}
	
	private boolean hasHitASurface(){
		return hasTouchedFrame();
	}

	private boolean hasTouchedFrame(){
		return x<=0 || x>=Frame.MAX_POINT.x || y<=0 || y>=Frame.MAX_POINT.y;
	}
	
	private void resetCurrentPosition(){
		x = path.getX1(); 
		y = path.getY1();
	}
	
	private void resetDirection(){
		direction = Math.abs(direction);
		if(path.getX2() - path.getX1() < 0)
			direction *= -1;//inverse the direction
	}
	
	private void computeNextLocation(){
		x += direction ; 
		y = GeometryUtil.getY(path, x); 
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getBackground() {
		return background;
	}

	public void setBorder(Color border) {
		this.border = border;
	}

	public Color getBorder() {
		return border;
	}

	private static final long serialVersionUID = -4545441L;
}
