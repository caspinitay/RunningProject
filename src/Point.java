
public class Point {
	
	private final double x;
	private final double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static double distanceTo(Point p1, Point p2) {
		
		return Math.sqrt(Math.pow((p2.x - p1.x),2) / Math.pow((p2.y - p1.y),2));
	}

}
