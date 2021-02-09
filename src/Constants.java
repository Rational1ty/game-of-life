package src;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public final class Constants {
	public static final Dimension BOARD;
	public static final int CELL_SIZE;
	public static final int DELAY;
	public static final int ROWS;
	public static final int COLS;
	public static final double BIAS;
	public static final boolean GRID;

	static {
		Properties props = new Properties();
		Path path = Paths.get("../life.properties");
		String[] keys     = {"board_width", "board_height", "cell_size", "delay", "bias", "grid"};
		String[] defaults = {"1920", "1080", "20", "50", "0.25", "1"};

		try {
			if (!Files.exists(path)) {
                // Pair each key with its default value, formatted and separated by a colon
                var lines = new ArrayList<String>(keys.length);
                for (int i = 0; i < keys.length; i++) {
                    lines.add(String.format("%-14s %s", keys[i] + ":", defaults[i]));
                }

                // Create life.properties in root directory and write default k:v pairs
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
		GRID = parseInt(props.getProperty("board_width")) > 0;

		ROWS = BOARD.height / CELL_SIZE;
		COLS = BOARD.width / CELL_SIZE;
	}

	private Constants() {}

	public static void displayConstants() {
		System.out.println(BOARD.width);
		System.out.println(BOARD.height);
		System.out.println(CELL_SIZE);
		System.out.println(DELAY);
		System.out.println(ROWS);
		System.out.println(COLS);
		System.out.println(BIAS);
		System.out.println(GRID);
	}
}