package src;

import java.awt.*;
import javax.swing.JFrame;

public class Life {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			buildFrame();
		});
	}

	private static void buildFrame() {
		JFrame frame = new JFrame("The Flower Game");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(Constants.BOARD.width, Constants.BOARD.height));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.add(new Panel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}