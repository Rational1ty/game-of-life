package src;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Life {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> buildFrame());
	}

	private static void buildFrame() {
		JFrame frame = new JFrame("The Flower Game");

		try {
			var icon = ImageIO.read(new File("../assets/icon.png"));
			frame.setIconImage(icon);
		} catch (IOException ex) {}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(Constants.BOARD);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.add(new Panel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}