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
	public static final Dimension BOARD;
	public static final int CELL_SIZE;
	public static final int DELAY;
	public static final int ROWS;
	public static final int COLS;
	public static final int INITIAL_CONFIG;
	public static final double BIAS;
	public static final boolean GRID;

	public static final Properties props = new Properties();

	static {
		Path path = Paths.get("../life.properties");
		List<String> lines = List.of(
			"board_width:     1920",
			"board_height:    1080",
			"cell_size:       10",
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

		BOARD = new Dimension(
			parseInt(props.getProperty("board_width")),
			parseInt(props.getProperty("board_height"))
		);

		CELL_SIZE = parseInt(props.getProperty("cell_size"));
		DELAY     = parseInt(props.getProperty("delay"));

		BIAS = parseDouble(props.getProperty("bias"));
		GRID = parseInt(props.getProperty("grid")) > 0;

		INITIAL_CONFIG = props.getProperty("initial_config")
			.equals("lined") ? Board.LINED : Board.RANDOM;

		ROWS = BOARD.height / CELL_SIZE;
		COLS = BOARD.width / CELL_SIZE;
	}

	private Constants() {}

	public static String get(String key) {
		return props.getProperty(key);
	}
}