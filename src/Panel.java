package src;

import static src.Constants.BOARD;
import static src.Constants.CELL_SIZE;
import static src.Constants.COLS;
import static src.Constants.ROWS;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Panel extends JPanel implements ActionListener, KeyListener, MouseInputListener {
	private static final long serialVersionUID = 1L;

	private Timer timer;
	private Board board;
	private boolean active = false;

	public Panel() {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		setBorder(BorderFactory.createLineBorder(Constants.BG_COLOR));
		setBackground(Constants.BG_COLOR);
		setFocusable(true);

		board = new Board(Constants.INITIAL_CONFIG);
		timer = new Timer(Constants.DELAY, this);

		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// draw living cells
		g2d.setColor(Constants.FG_COLOR);
		for (int r = 0; r < ROWS; r++) {
			int y = r * CELL_SIZE;

			for (int c = 0; c < COLS; c++) {
				int x = c * CELL_SIZE;

				if (board.cells[r][c]) {
					g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
				}
			}
		}

		if (Constants.GRID) {
			// draw grid
			g2d.setColor(Constants.GRID_COLOR);

			for (int x = 0; x < BOARD.width; x += CELL_SIZE) {
				g2d.drawLine(x, 0, x, BOARD.height);
			}
			for (int y = 0; y < BOARD.height; y += CELL_SIZE) {
				g2d.drawLine(0, y, BOARD.width, y);
			}
		}

		Toolkit.getDefaultToolkit().sync();
	}

	public void actionPerformed(ActionEvent e) {
		if (active) {
			board.evolve();
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_SPACE:
				active = !active;
				break;
			case KeyEvent.VK_ENTER:
				if (!active) {
					board.evolve();
				}
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (!active) {
					board.clear();
				}
				break;
		}
	}

	public void mousePressed(MouseEvent e) {
		if (active) return;
		
		int row = e.getY() / CELL_SIZE;
		int col = e.getX() / CELL_SIZE;

		if (row < 0 || row >= ROWS) return;
		if (col < 0 || col >= COLS) return;

		if (SwingUtilities.isLeftMouseButton(e)) {
			board.cells[row][col] = true;
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			board.cells[row][col] = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		mousePressed(e);
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}
}