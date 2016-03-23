package lionel.demos.bitsandpieces.threading.bouncingball;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class GeometryUtil {
	/** Returns value of the angle at point B , given a line from point A to B and from point B to C*/
	public static final double angle(Point2D pointA, Point2D pointB, Point2D pointC){
		return angle(pointA.getX(),pointA.getY(),pointB.getX(),pointB.getY(),pointC.getX(),pointC.getY());
	}
	
	/** Returns value of the angle at vertice B(x,y) , given a line from vertice A(x,y) to vertice B(x,y) and from vertice B(x,y) to C(x,y)*/
	public static final double angle(double Ax, double Ay,double Bx, double By,double Cx, double Cy){
		Vector BA = new Vector(Bx,Ax,By,Ay);
		Vector BC = new Vector(Bx,Cx,By,Cy);
		double cosB = Vector.scalarProduct(BA, BC) / (BA.getMagnitude() * BC.getMagnitude());
		return Math.acos(cosB);
	}
	
	public static double gradient (Line2D line){
		return gradient (line.getP1(), line.getP2());
	}
	
	public static double gradient (Point2D p1, Point2D p2){
		return gradient (p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	public static double gradient (double x1, double y1, double x2, double y2){
		return (y2 - y1) / (x2 - x1);
	}
	
	/** This returns the value of x from the equation of this line given y*/
	public static double getX(Line2D line, double y){
		return ((y - line.getP1().getY()) / gradient(line)) + line.getP1().getX();
	}
	
	/** This returns the value of y from the equation of this line given x*/
	public static double getY(Line2D line, double x){
		return (gradient(line) * (x - line.getP1().getX())) + line.getP1().getY();
	}
	
	/** This returns the midpoint of the given line*/
	public static Point2D.Double midPoint(Line2D line){
		return midPoint(line.getP1(),line.getP2());
	}
	
	/** This returns the midpoint of the given points*/
	public static Point2D.Double midPoint(Point2D point1, Point2D point2){
		return midPoint(point1.getX(),point1.getY(),point2.getX(),point2.getY());
	}
	
	/** This returns the midpoint of the given points' coordinates*/
	public static Point2D.Double midPoint(double x1, double y1, double x2, double y2){
		return new Point2D.Double((x1 + x2)/2, (y1 + y2)/2);
	}
	
	private GeometryUtil(){}
	
}
