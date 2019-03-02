public class Point {
	
	private final int x;
	private final int y;
	
	public Point (int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public double distanceTo (Point p){
		return (Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y)));
	}

}