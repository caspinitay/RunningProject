
public class Road extends Edge {
	private Point a;
	private Point b;
	private String name;

	public Road(Point u, Point v, int weight, String name) {
		super(u.getID(), v.getID(), weight);
		this.name = name;
	}
		

}
