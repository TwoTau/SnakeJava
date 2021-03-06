import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Segment> snake;
	
	public boolean dead;
	
	// snake starts out going right
	private int dirX; // can be either -1 (left), 0 (no hor. movement) or 1 (right)
	private int dirY; // can be either -1 (down), 0 (no vert. movement) or 1 (up)
	
	private float hueOffset;
	
	/**
	 * Constructs a Snake with the given position, direction, and size.
	 * Either dirX or dirY must be 0.
	 * 
	 * @param  x     initial game x-coordinate of the head
	 * @param  y     initial game y-coordinate of the head
	 * @param  dirX  initial x-direction of the snake, either -1 (left), 0, or 1 (right)
	 * @param  dirY  initial y-direction of the snake, either -1 (down), 0, or 1 (up)
	 * @param  size  initial length of the snake
	 * @param  color offset index to calculate the color of the Snake
	 * @throws IllegalArgumentException if x < 0, y < 0, or size < 1
	 */
	public Snake(int x, int y, int dirX, int dirY, int size, int color) {
		if (x < 0 || y < 0 || size < 1) {
			throw new IllegalArgumentException();
		}
		
		setDirection(dirX, dirY);
		
		dead = false;
		
		snake = new LinkedList<Segment>();
		snake.addFirst(new Segment(x, y));
		for (int i = 1; i < size; i++) {
			snake.add(new Segment(x + dirX * i, y + dirY * i));
		}
		
		hueOffset = (float) color * 5 / 18;
	}
	
	/**
	 * Sets the new direction of the Snake. Either dirX or dirY must be 0 and -1 <= dir <= 1.
	 * 
	 * @param  dirX new x-direction (-1 is left, 1 is right)
	 * @param  dirY new y-direction (-1 is down, 1 is up)
	 * @throws IllegalArgumentException if dirX or dirY are invalid
	 */
	public void setDirection(int dirX, int dirY) {
		if (dirX < -1 || dirX > 1 || dirY < -1 || dirY > 1) {
			throw new IllegalArgumentException();
		}
		if (!(this.dirX == -dirX && dirX != 0) && !(this.dirY == -dirY && dirY != 0)) {
			this.dirX = dirX;
			this.dirY = dirY;
		}
	}
	
	public boolean isFoodInSnake(Food food) {
		for (Segment seg : snake) {
			if (food.isInPoint(seg.x, seg.y)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the first element in the snake list.
	 * 
	 * @returns head Segment of the snake
	 */
	public Segment getHead() {
		return snake.getFirst();
	}
	
	/**
	 * Updates the entire snake one step according to the current direction.
	 */
	public void updateNormal() {
		snake.removeLast();
		addSegment();
	}
	
	/**
	 * Adds a Segment to the snake at the head and updates the snake one step.
	 */
	public void addSegment() {
		snake.addFirst(new Segment(getHead().x, getHead().y));
		updateHead();
	}
	
	/**
	 * Updates only the head to move according to the current direction
	 */
	private void updateHead() {
		getHead().y -= dirY;
		getHead().x += dirX;
	}
	
	/**
	 * Returns whether the snake has looped or hit the wall
	 * 
	 * @returns whether the snake is dead
	 */
	public boolean isDead() {
		if (dead) {
			return true;
		}
		
		int headX = getHead().x;
		int headY = getHead().y;
		
		// return whether the snake has gone out of bounds
		int maxX = GameDriver.WINDOW_WIDTH / Segment.SIZE - 1;
		int maxY = GameDriver.WINDOW_HEIGHT / Segment.SIZE - 1;
		if (headX == -1 || headY == -1 || headX == maxX || headY == maxY) {
			dead = true;
			return true;
		}
		
		// return true if the snake just looped
		for (int i = 1; i < snake.size(); i++) {
			Segment seg = snake.get(i);
			if (headX == seg.x && headY == seg.y) {
				dead = true;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns a Color between red and yellow corresponding to the given position ratio.
	 * 
	 * @param segPos index of the Segment
	 * @param totalSegments number of total Segments
	 * @returns Color corresponding to ratio of segPos to totalSegments
	 */
	private Color getSegmentColor(int segPos, int totalSegments) {
		if (segPos == 0) { // the head
			return Color.getHSBColor(0f, 0f, 0.1f); // almost black
		}
		// divide by 6 to get a hue between 0.0 + offset and 0.167 + offset
		float hue = (float) segPos / totalSegments / 6 + hueOffset;
		return Color.getHSBColor(hue, 0.98f, 1f);
	}
	
	/**
	 * Draws the entire snake on the given Graphics.
	 * 
	 * @param g2d Graphics2D object to draw on.
	 */
	public void draw(Graphics2D g2d) {
		for (int i = snake.size() - 1; i >= 0; i--) {
			snake.get(i).draw(g2d, getSegmentColor(i, snake.size() - 1));
		}
	}
		
}