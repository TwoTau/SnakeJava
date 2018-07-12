import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {
	
	private static final int[][] keys = {
			{KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT},
			{KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D},
			{KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L}
	};
	
	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < Surface.getNumSnakes(); i++) {
			if (Surface.isAlive(i)) {
				if (e.getKeyCode() == keys[i][0]) {
					Surface.setDirection(i, 0, 1);
				} else if (e.getKeyCode() == keys[i][1]) {
					Surface.setDirection(i, 0, -1);
				} else if (e.getKeyCode() == keys[i][2]) {
					Surface.setDirection(i, -1, 0);
				} else if (e.getKeyCode() == keys[i][3]) {
					Surface.setDirection(i, 1, 0);
				}
			}
		}
	}
}