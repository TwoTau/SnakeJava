import java.awt.Color;
import java.awt.Graphics2D;

public class Food {
	
	private int x = 0;
	private int y = 0;
	
	private static int borderWidth = 4;
	
	public Food() {
		
	}
	
	public void moveToRandom() {
		x = (int) (Math.random() * (GameDriver.WINDOW_WIDTH / Segment.size - 3) + 1);
		y = (int) (Math.random() * (GameDriver.WINDOW_HEIGHT / Segment.size - 3) + 1);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		g2d.fillRect(x*Segment.size, y*Segment.size, Segment.size, Segment.size);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x*Segment.size + borderWidth, y*Segment.size + borderWidth, Segment.size - borderWidth * 2, Segment.size - borderWidth * 2);
	}
	
	public boolean isInPoint(int xVal, int yVal) {
		return (x == xVal && y == yVal);
	}
}
