package src;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener, KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;

	protected static final Color BG_COLOR   = Color.BLACK;
	protected static final Color FG_COLOR   = new Color(0xC7C7C7);
	protected static final Color GRID_COLOR = new Color(0x0F0F0F);

	public Panel() {
		addKeyListener(this);
		addMouseListener(this);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(BG_COLOR);

		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(FG_COLOR);
		// draw cells

		if (Constants.GRID) {
			g2d.setColor(GRID_COLOR);
			// draw grid
		}

		Toolkit.getDefaultToolkit().sync();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:

				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_SPACE:

				break;
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			int row = e.getY() / Constants.CELL_SIZE;
			int col = e.getX() / Constants.CELL_SIZE;

			
		}
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}