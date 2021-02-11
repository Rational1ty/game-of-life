package src;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public final class Constants {
	public static final boolean WINDOW;
	public static final Dimension BOARD;
	public static final int CELL_SIZE;
	public static final int ROWS;
	public static final int COLS;
	public static final int DELAY;
	public static final int INITIAL_CONFIG;
	public static final double BIAS;
	public static final boolean GRID;
	public static final char CELL_CHAR;

	private static final Properties props = new Properties();

	static {
		Path path = Paths.get("../life.properties");
		List<String> lines = List.of(
			"window:          1",
			"board_width:     1920",
			"board_height:    1080",
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

		WINDOW = parseInt(get("window")) > 0;

		BOARD = new Dimension(
			parseInt(get("board_width")),
			parseInt(get("board_height"))
		);

		CELL_SIZE = parseInt(get("cell_size"));
		DELAY     = parseInt(get("delay"));

		BIAS = parseDouble(get("bias"));
		GRID = parseInt(get("grid")) > 0;

		INITIAL_CONFIG = switch (get("initial_config")) {
			case "blank"  -> Board.BLANK;
			case "lined"  -> Board.LINED;
			case "center" -> Board.CENTER;
			default -> Board.RANDOM;
		};

		CELL_CHAR = get("cell_char").charAt(0);

		// if window is on, calculate rows and cols; otherwise, use clamped board dimensions
		ROWS = WINDOW ? BOARD.height / CELL_SIZE : Math.min(BOARD.height, 66);
		COLS = WINDOW ? BOARD.width / CELL_SIZE  : Math.min(BOARD.width, 237);
	}

	private Constants() {}

	public static String get(String key) {
		return props.getProperty(key);
	}
}