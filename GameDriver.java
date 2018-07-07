import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameDriver extends JFrame {
	
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 600;

	
	public GameDriver() {
		init();
	}
	
	private void init() {
		add(new Surface());
		
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
