import java.awt.Color;
import java.awt.Graphics2D;

public class Segment {
	
	public static final int SIZE = 20; // size in pixels
	
	private int x; // game x-coordinate
	private int y; // game x-coordinate
	
	/**
	 * Creates a Segment with the given location.
	 * 
	 * @param x initial game x-coordinate
	 * @param y initial game y-coordinate
	 */
	public Segment(int x, int y) {
		setPos(x, y);
	}
	
	/**
	 * Sets the game coordinates of the Segment.
	 * 
	 * @param x new x-coordinate of Segment
	 * @param y new y-coordinate of Segment
	 */
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Moves the Segment up or down by the given number of game coordinates.
	 * Moves up if spaces > 0 and moves down if spaces < 0.
	 * 
	 * @param spaces number of game coordinates to move up
	 */
	public void moveUp(int spaces) {
		y -= spaces;
	}
	
	/**
	 * Moves the Segment right or left by the given number of game coordinates.
	 * Moves right if spaces > 0 and moves left if spaces < 0.
	 * 
	 * @param spaces number of game coordinates to move right
	 */
	public void moveRight(int spaces) {
		x += spaces;
	}
	
	/**
	 * Returns the game coordinate of the Segment's x position
	 * 
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the game coordinate of the Segment's y position
	 * 
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
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
