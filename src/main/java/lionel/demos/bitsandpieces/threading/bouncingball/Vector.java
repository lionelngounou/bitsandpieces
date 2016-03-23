package lionel.demos.bitsandpieces.threading.bouncingball;

import java.awt.Point;

public class Vector {
	private double x, y,unitX,unitY, magnitude;
	
	/** This construct an immutable vector given the direction from point A to point B*/
	public Vector(Point pointA,Point pointB) {
		this(pointA.getX(), pointB.getX(), pointA.getY(), pointB.getY());
	}
	
	/** This construct an immutable vector given the direction from point(x1,y1) to point(x2,y2) */
	public Vector(double x1,double x2,double y1,double y2) {
		x = x2 - x1;		
		y = y1 - y2;
		magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		unitX = x / magnitude;
		unitY = y / magnitude;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getUnitX() {
		return unitX;
	}

	public double getUnitY() {
		return unitY;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public static double scalarProduct(Vector v1, Vector v2){
		return (v1.getX() * v2.getX()) + (v1.getY() * v2.getY());
	}
}
