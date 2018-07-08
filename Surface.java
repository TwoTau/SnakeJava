import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Surface extends JPanel implements ActionListener {
	
	private static final int DELAY = 80;
	private static Timer timer;
	
	private static Food food;
	
	private static int aliveSnakes;
	
	private static ArrayList<Snake> snakes;
	
	/**
	 * Creates a new Surface.
	 * 
	 * @param numSnakes number of Snakes to start with
	 */
	public Surface(int numSnakes) {
		snakes = new ArrayList<Snake>();
		for (int i = 0; i < numSnakes; i++) {
			snakes.add(new Snake(4 + i*10, 1, 0, -1, 5));
		}
		aliveSnakes = numSnakes;
		
		food = new Food();
		
		timer = new Timer(DELAY, this);
		timer.start();
		
		// automatically give focus to window so that keyboard input can be taken immediately
		requestFocus();
	}
	
	
	/**
	 * Draws all elements (the Snakes and the food) on the given Graphics.
	 * 
	 * @param g Graphics to draw on
	 */
	private static void drawAll(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (Snake snake : snakes) {
			snake.draw(g2d);
		}
		
		food.draw(g2d);
	}
	
	
	/**
	 * Moves the food to a random non-edge non-occupied location
	 */
	private static void moveFood() {
		food.moveToRandom();
		
		// ensure that food is not in the snake
		boolean isFoodInSnake = false;
		for (Snake snake : snakes) {
			if (snake.isFoodInSnake(food)) {
				isFoodInSnake = true;
				break;
			}
		}
		if (isFoodInSnake) {
			moveFood();
		}
	}
	
	/**
	 * Sets the direction of the Snake with the given index.
	 * 
	 * @param  snakeNum index of the Snake
	 * @param  dirX new x-direction of Snake
	 * @param  dirY new y-direction of Snake
	 * @throws IllegalArgumentException if the given indexed Snake is dead or does not exist
	 */
	public static void setDirection(int snakeNum, int dirX, int dirY) {
		if (snakeNum >= snakes.size() || snakes.get(snakeNum).dead) {
			throw new IllegalArgumentException();
		}
		
		snakes.get(snakeNum).setDirection(dirX, dirY);
	}
	
	/**
	 * Returns the x-direction (-1, 0, or 1) of the Snake with the given index.
	 * If the Snake does not exist, returns -2.
	 * 
	 * @param snakeNum index of the Snake
	 * @returns x-direction of the Snake or -2 if it does not exist
	 */
	public static int getDirX(int snakeNum) {
		return (snakeNum < snakes.size()) ? snakes.get(snakeNum).getDirX() : -2; 
	}
	
	/**
	 * Returns the y-direction (-1, 0, or 1) of the Snake with the given index.
	 * If the Snake does not exist, returns -2.
	 * 
	 * @param snakeNum index of the Snake
	 * @returns y-direction of the Snake or -2 if it does not exist
	 */
	public static int getDirY(int snakeNum) {
		return (snakeNum < snakes.size()) ? snakes.get(snakeNum).getDirY() : -2;
	}
	
	/**
	 * Returns whether the Snake with the given index is alive
	 * 
	 * @param snakeNum index of the Snake
	 * @returns whether the given indexed Snake is alive or false if it does not exist
	 */
	public static boolean isAlive(int snakeNum) {
		return (snakeNum < snakes.size() && !snakes.get(snakeNum).dead);
	}
	
	/**
	 * Returns the total number of snakes, both alive and dead.
	 * 
	 * @returns total number of Snakes
	 */
	public static int getNumSnakes() {
		return snakes.size();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (aliveSnakes == 0) {
			return;
		}
		for (Snake snake : snakes) {
			if (!snake.dead) {
				if (snake.isDead()) {
					aliveSnakes--;
				} else {
					if (snake.isFoodInSnake(food)) {
						snake.addSegment();
						moveFood();
					} else {				
						snake.updateNormal();
					}
				}
			}
		}
		
		repaint();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawAll(g);
	}

}
