package src;

import static src.Constants.BOARD;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Life {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("src.Constants");				// load Constants class first
		EventQueue.invokeLater(Life::buildFrame);	// run program on event queue (separate thread)
	}

	private static void buildFrame() {
		JFrame frame = new JFrame("The Flower Game");

		// set window icon
		try {
			var icon = ImageIO.read(new File("../assets/icon.png"));
			frame.setIconImage(icon);
		} catch (IOException ex) {}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(BOARD);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(BOARD.width == 1920 && BOARD.height == 1080);
		frame.add(new Panel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}