package src;

import static src.Constants.COLS;
import static src.Constants.ROWS;
import static src.Constants.get;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

public class ConsoleDisplayHandler implements ActionListener {
	private Timer timer;
	private Board board;
	private BufferedOutputStream os;
	private ProcessBuilder pb;
	private ProcessBuilder cls;

	protected final String FG = get("fg_color");
	protected final String BG = get("bg_color");

	public ConsoleDisplayHandler() {
		board = new Board(Constants.INITIAL_CONFIG);
		timer = new Timer(Constants.DELAY, this);

		os = new BufferedOutputStream(System.out);

		pb  = new ProcessBuilder().inheritIO();
		cls = new ProcessBuilder("cmd", "/c", "cls").inheritIO();

		system("title The Flower Game");
		system("mode", "con:", String.format("cols=%d", COLS), String.format("lines=%d", ROWS + 1));

		if (FG.matches("[0-9A-Fa-f]") && BG.matches("[0-9A-Fa-f]")) {
			system("color", String.format("%s%s", BG, FG));
		}

		timer.start();
	}

	public void drawBoard() throws IOException {
		cls();

		// draw living cells
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				os.write(board.cells[r][c] ? Constants.CELL_CHAR : ' ');
			}

			if (r < ROWS - 1) {
				os.flush();
				System.out.println();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		board.evolve();
		try {
			drawBoard();
		} catch (IOException ex) {
			System.exit(0);
		}
	}

	private int cls() {
		try {
			return cls.start().waitFor();
		} catch (IOException | InterruptedException ex) {
			System.exit(0);
			return 1;
		}
	}

	private int system(String... command) {
		var cmd = new ArrayList<String>();

		cmd.add("cmd");
		cmd.add("/c");
		for (String s : command) {
			cmd.add(s);
		}

		pb.command(cmd);

		try {
			return pb.start().waitFor();
		} catch (IOException | InterruptedException ex) {
			return 1;
		}
	}
}