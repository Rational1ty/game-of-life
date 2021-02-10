package src;

import static src.Constants.BOARD;
import static src.Constants.ENV_CONSOLE;
import static src.Constants.OUTPUT_ENV;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Life {
	private static final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();

	public static void main(String[] args) throws ClassNotFoundException {
		// load Constants class first
		Class.forName("src.Constants");
		// run program on event queue (separate thread)
		EventQueue.invokeLater(OUTPUT_ENV == ENV_CONSOLE ? Life::startConsole : Life::buildFrame);
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

	private static void startConsole() {
		cls();
	}

	private static void cls() {
		try {
			pb.start().waitFor();
		} catch (IOException | InterruptedException ex) {
			System.err.println("Error occurred during terminal interface");
			System.exit(0);
		}
	}
}