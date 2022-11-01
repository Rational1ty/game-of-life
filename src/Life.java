package src;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Life {
	public static void main(String[] args) throws ClassNotFoundException {
		// load Constants class first
		Class.forName("src.Constants");
		// run program on event queue (separate thread)
		EventQueue.invokeLater(Constants.WINDOW ? Life::buildFrame : Life::startConsole);
	}

	private static void buildFrame() {
		JFrame frame = new JFrame("The Flower Game");

		// set window icon
		try {
			var icon = ImageIO.read(new File("../assets/icon.png"));
			frame.setIconImage(icon);
		} catch (IOException ex) {}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(Constants.BOARD);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		// frame.setOpacity(0.5f);		// TODO: opacity option
		frame.add(new Panel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private static void startConsole() {
		new ConsoleDisplayHandler();
	}
}