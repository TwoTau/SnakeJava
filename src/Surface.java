import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Surface extends JPanel implements ActionListener {
	
	private final int DELAY = 80;
	private Timer timer;
	
	private Food food = new Food();
	
	private boolean dead = false;
	
	private static LinkedList<Segment> snake = new LinkedList<Segment>();
	
	// snake starts out going right
	public static int dirX = 1; // can be either -1 (left), 0 (no hor. movement) or 1 (right)
	public static int dirY = 0; // can be either -1 (down), 0 (no vert. movement) or 1 (up)
	
	public Surface() { // initialization
		// snake starts out with 5 segments arranged vertically
		snake.add(new Segment(4, 1));
		snake.add(new Segment(4, 2));
		snake.add(new Segment(4, 3));
		snake.add(new Segment(4, 4));
		snake.add(new Segment(4, 5));
		
		moveFood();
		
		timer = new Timer(DELAY, this);
		timer.start();
		
		requestFocus(); // automatically gives focus to window so that keyboard input can be taken immediately
	}
	
	private Segment getHead() {
		return snake.getFirst();
	}
	
	/*
	 * returns a Color between red and yellow corresponding to the segPos
	 */
	private Color getSegmentColor(int segPos, int totalSegments) {
		if(segPos == 0) return Color.getHSBColor(0f, 0f, 0.1f); // almost black
		float hue = (float) segPos / totalSegments / 6; // divide by 6 to get a hue between 0.0 (red) and 0.167 (yellow)
		return Color.getHSBColor(hue, 0.98f, 1f);
	}
	
	private void drawAll(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g2d, getSegmentColor(i, snake.size() - 1));
		}
		
		food.draw(g2d);
	}
	
	/*
	 * Normal movement update of the snake
	 */
	private void updateNormalSnake() {
		Segment last = snake.removeLast();
		last.setPos(getHead().getX(), getHead().getY());
		snake.addFirst(last);
		updateHead();
	}
	
	/*
	 * Adds a segment to the snake at the head. Used when food has been eaten.
	 */
	private void addSegment() {
		Segment newHead = new Segment(getHead().getX(), getHead().getY());
		snake.addFirst(newHead);
		updateHead();
	}
	
	/*
	 * Updates the position of the head
	 */
	private void updateHead() {
		getHead().moveUp(dirY);
		getHead().moveRight(dirX);
	}
	
	
	/*
	 * Moves the food to a random non-edge non-occupied location
	 */
	private void moveFood() {
		food.moveToRandom();
		
		// make sure that food is not in the snake
		boolean isFoodInSnake = false;
		for(Segment seg : snake) {
			if(food.isInPoint(seg.getX(), seg.getY())) {
				isFoodInSnake = true;
				break; // No need to check all segments
			}
		}
		if(isFoodInSnake) moveFood();
	}
	
	private boolean isFoodEaten() {
		return food.isInPoint(getHead().getX(), getHead().getY());
	}
	
	/*
	 * Has the snake looped or gone out of bounds?
	 */
	private boolean isDead() {
		// return true if the snake just looped
		for(int i = 1; i < snake.size(); i++) { // loop through all segments in snake except the head
			Segment seg = snake.get(i);
			if(getHead().getX() == seg.getX() && getHead().getY() == seg.getY()) {
				return true;
			}
		}
		
		// return true if the snake has gone out of bounds, false if everything is okay
		return (getHead().getX() == -1 || getHead().getY() == -1);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(!dead) { // is alive
			if(isFoodEaten()) {
				addSegment();
				moveFood();
			} else {
				updateNormalSnake();
			}
			if(isDead()) dead = true;
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawAll(g);
	}
}
