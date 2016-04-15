import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP && Surface.dirY == 0) {
			Surface.dirX = 0;
			Surface.dirY = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && Surface.dirY == 0) {
			Surface.dirX = 0;
			Surface.dirY = -1;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && Surface.dirX == 0) {
			Surface.dirX = -1;
			Surface.dirY = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && Surface.dirX == 0) {
			Surface.dirX = 1;
			Surface.dirY = 0;
		}
	}
}