import java.awt.Color;
import java.awt.Graphics2D;

public class Segment {
	
	public static final int SIZE = 20; // size in pixels
	
	public int x; // game x-coordinate
	public int y; // game x-coordinate
	
	/**
	 * Creates a Segment with the given location.
	 * 
	 * @param x initial game x-coordinate
	 * @param y initial game y-coordinate
	 */
	public Segment(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	
	/**
	 * Draws the Segment on the given Graphics2D with the given color.
	 * 
	 * @param g2d Graphics2D to draw on
	 * @param hsbColor Color of the Segment
	 */
	public void draw(Graphics2D g2d, Color hsbColor) {
		g2d.setColor(hsbColor);
		g2d.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
