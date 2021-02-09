package src;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Life {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				buildFrame();
			}
		});
	}

	private static void buildFrame() {
		JFrame frame = new JFrame("The Flower Game");

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