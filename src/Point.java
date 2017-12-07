
public class Point {
	
	private final double x;
	private final double y;
	private final int ID;
	private static int IDctr = 0;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.ID = IDctr++;
	}
	
	public static double distanceTo(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p2.x - p1.x),2) / Math.pow((p2.y - p1.y),2));
	}
	
	public int getID() {
		return ID;
	}
	
	public boolean equals(Point that) {
		if (this.x != that.x) return false;
		if (this.y != that.y) return false;
		return true;
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(0.0, 0.0);
		Point p2 = new Point(1.0, 1.1);
		System.out.println(p1.getID());
		System.out.println(p2.getID());
	}

}
