import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameDriver extends JFrame {
	
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 600;
	
	private static final int NUM_SNAKES = 1; // use 2 for multiplayer, cannot be more than 3

	
	public GameDriver() {
		init();
	}
	
	private void init() {
		add(new Surface(NUM_SNAKES));
		
		setResizable(false);
		pack();
		
		setTitle("Snake game");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setFocusable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(new KeyInputHandler());
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameDriver game = new GameDriver();
                game.setVisible(true);
            }
        });

	}

}
