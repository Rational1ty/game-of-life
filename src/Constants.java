package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public final class Constants {
	// life.properties constants
	public static final boolean WINDOW;
	public static final Dimension BOARD;
	public static final int CELL_SIZE;
	public static final char CELL_CHAR;
	public static final int DELAY;
	public static final double BIAS;
	public static final boolean GRID;
	public static final int INITIAL_CONFIG;
	public static final Color BG_COLOR;
	public static final Color FG_COLOR;
	public static final Color GRID_COLOR;

	// internal constants
	public static final int ROWS;	// calculated based on cell size and board dimensions
	public static final int COLS;
	public static final String BG;	// required for compatibility with console
	public static final String FG;

	public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

	// underlying properties object
	private static final Properties props = new Properties(12);

	static {
		Path path = Paths.get("../life.properties");
		List<String> lines = List.of(
			"window:          1",
			"board_width:     0",
			"board_height:    0",
			"cell_size:       10",
			"cell_char:       #",
			"delay:           50",
			"bias:            0.25",
			"grid:            1",
			"initial_config:  random",
			"bg_color:        0x000000",
			"fg_color:        0xC7C7C7",
			"grid_color:      0x0F0F0F"
		);

		try {
			if (!Files.exists(path)) {
				// create life.properties in root directory and write default entries
				Files.createFile(path);
				Files.write(path, lines);
			}
			props.load(Files.newInputStream(path));
		} catch (IOException ex) {
			System.err.println("Error while accessing file \"life.properties\".");
			System.exit(0);
		}

		WINDOW = parseBool("window");

		BOARD = getAdjustedBoardDim("board_width", "board_height");
		
		CELL_SIZE = parseInt("cell_size");
		CELL_CHAR = parseChar("cell_char");

		DELAY = parseInt("delay");
		BIAS = parseDouble("bias");
		GRID = parseBool("grid");

		INITIAL_CONFIG = switch (props.getProperty("initial_config")) {
			case "blank"  -> Board.BLANK;
			case "lined"  -> Board.LINED;
			case "center" -> Board.CENTER;
			case "border" -> Board.BORDER;
			default -> Board.RANDOM;
		};

		BG_COLOR = parseColor("bg_color");
		FG_COLOR = parseColor("fg_color");
		GRID_COLOR = parseColor("grid_color");

		// if window is on, calculate rows and cols; otherwise, use clamped board dimensions
		ROWS = WINDOW ? BOARD.height / CELL_SIZE : Math.min(BOARD.height, 66);
		COLS = WINDOW ? BOARD.width / CELL_SIZE  : Math.min(BOARD.width, 237);

		BG = props.getProperty("bg_color");
		FG = props.getProperty("fg_color");
	}

	private Constants() {}

	private static boolean parseBool(String key) {
		return Integer.parseInt(props.getProperty(key)) > 0;
	}

	private static char parseChar(String key) {
		return props.getProperty(key).charAt(0);
	}

	private static int parseInt(String key) {
		return Integer.parseInt(props.getProperty(key));
	}

	private static double parseDouble(String key) {
		return Double.parseDouble(props.getProperty(key));
	}

	private static Color parseColor(String key) {
		return Color.decode(props.getProperty(key));
	}

	private static Dimension getAdjustedBoardDim(String widthKey, String heightKey) {
		Dimension board = new Dimension(parseInt(widthKey), parseInt(heightKey));

		if (board.width <= 0 || board.width > SCREEN.width) {
			board.width = SCREEN.width;
		}
		if (board.height <= 0 || board.height > SCREEN.height) {
			board.height = SCREEN.height;
		}
		
		return board;
	}
}