package src;

import static src.Constants.COLS;
import static src.Constants.ROWS;

import java.util.Arrays;

public class Board {
	public static final int RANDOM = 1;
	public static final int LINES  = 2;
	public static final int EMPTY  = 3;

	protected final boolean[][] cells = new boolean[ROWS][COLS];
	private final boolean[][] next = new boolean[ROWS][COLS];

	public Board(int initialConfig) {
		switch (initialConfig) {
			case RANDOM:
				for (int r = 0; r < ROWS; r++) {
					for (int c = 0; c < COLS; c++) {
						cells[r][c] = Math.random() < Constants.BIAS;
					}
				}
				break;
			case LINES:
				for (int r = 0; r < ROWS; r++) {
					for (int c = 0; c < COLS; c++) {
						cells[r][c] = (r % 2 == 0);
					}
				}
				break;
			case EMPTY:
				// Don't need to do anything since all cells are already false
				break;
		}
	}

	public void evolve() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				int livingAdj = nLivingAdjacent(r, c);

				if (cells[r][c]) {
					next[r][c] = (livingAdj == 2 || livingAdj == 3);
				} else {
					next[r][c] = (livingAdj == 3);
				}
			}
		}

		// copy next board into current board
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				cells[r][c] = next[r][c];
			}
		}
	}

	private int nLivingAdjacent(int row, int col) {
		int r, c;		// local row and column
		int num = 0;	// number of adjacent cells that are alive

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;

				r = row + i;
				c = col + j;

				// bounds checking; wrap if necessary
				if (r < 0) r = ROWS - 1;
				if (c < 0) c = COLS - 1;
				if (r > ROWS - 1) r = 0;
				if (c > COLS - 1) c = 0;

				if (cells[r][c]) num++;
			}
		}

		return num;
	}

	public void clear() {
		for (int i = 0; i < ROWS; i++) {
			Arrays.fill(cells[i], false);
			Arrays.fill(next[i], false);
		}
	}
}