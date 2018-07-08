import java.awt.Color;
import java.awt.Graphics2D;

public class Food {
	private int x; // game x-coordinate
	private int y; // game y-coordinate
	
	private static final int BORDER_SIZE = 4; // border width in pixels
	private static final Color BORDER_COLOR = Color.BLUE;
	private static final Color INNER_COLOR = Color.GREEN;
	
	/**
	 * Constructs a new Food object and moves it to a random location.
	 */
	public Food() {
		moveToRandom();
	}
	
	/**
	 * Moves the food to an almost random location.
	 * The food never moves to the outside edges of the game.
	 */
	public void moveToRandom() {
		x = (int) (Math.random() * (GameDriver.WINDOW_WIDTH / Segment.SIZE - 3) + 1);
		y = (int) (Math.random() * (GameDriver.WINDOW_HEIGHT / Segment.SIZE - 3) + 1);
	}
	
	/**
	 * Draws the food cell on the given Graphics2D. The cell has an inside border.
	 * 
	 * @param g2d Graphics2D to draw on
	 */
	public void draw(Graphics2D g2d) {
		int coorX = x * Segment.SIZE;
		int coorY = y * Segment.SIZE;
		
		g2d.setColor(BORDER_COLOR);
		g2d.fillRect(coorX, coorY, Segment.SIZE, Segment.SIZE);
		
		g2d.setColor(INNER_COLOR);
		int innerWidth = Segment.SIZE - BORDER_SIZE * 2;
		g2d.fillRect(coorX + BORDER_SIZE, coorY + BORDER_SIZE, innerWidth, innerWidth);
	}
	
	/**
	 * Returns whether the Food is at the given game coordinates.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @returns whether the Food is in the given point
	 */
	public boolean isInPoint(int x, int y) {
		return (this.x == x && this.y == y);
	}
}
