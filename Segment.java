import java.awt.Color;
import java.awt.Graphics2D;

public class Segment {
	
	public static int size = 20;
	
	private int x;
	private int y;
	
	public Segment(int initX, int initY) {
		setPos(initX, initY);
	}
	
	public void setPos(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	/*
	 * spaces can be negative to move down.
	 */
	public void moveUp(int spaces) {
		y -= spaces;
	}
	
	/*
	 * spaces can be negative to move left.
	 */
	public void moveRight(int spaces) {
		x += spaces;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void draw(Graphics2D g2d, Color hsbColor) {
		g2d.setColor(hsbColor);
		g2d.fillRect(x*size, y*size, size, size);
	}
}
