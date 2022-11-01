# Life

A Java implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
that can run in either a window or the terminal.

---

## Usage

Two scripts, `build.cmd` and `launch.cmd`, are provided to simplify running the program. These 
should be run from the root directory of the project.

Note: The `build` and `launch` scripts must be run in Windows Command Prompt (`cmd.exe`).

```cmd
> build
> launch
```

However, you can also build the project manually using `javac` and run it using `java` if you want.
Refer to the commands in `build.cmd` and `launch.cmd` for more information.

## Settings

The `life.properties` file contains settings that control the behavior of the game. This file is
created for you, with the default options, the first time the program is launched.

Some properties have extra limitations or are disabled when `window=0` (console mode):
- `board_width` and `board_height` are limited to the dimensions of the console
  - Maximum console dimensions are typically around 240&times;70
- `cell_size`, `grid`, and `grid_color` have no effect
- `bg_color` and `fg_color` are limited to single-digit hex values

Similarly, some properties have different effects when `window=1` (window mode):
- `cell_char` has no effect

Here is a list of all properties, along with their types, default values, and descriptions:

| Property       | Type   | Default  | Description                                                                   |
|----------------|--------|----------|-------------------------------------------------------------------------------|
| window         | bool   | 1        | If true, game is run in a dedicated window; otherwise, the console is used    |
| board_width    | int    | 0        | Width of the board in pixels; defaults to screen width if set to `0`          |
| board_height   | int    | 0        | Height of the board in pixels; defaults to screen height if set to `0`        |
| cell_size      | int    | 10       | Size (side length) of each cell in pixels                                     |
| cell_char      | char   | #        | Character used to represent living cells when running in a console            |
| delay          | int    | 50       | Minimum frame time in milliseconds (lower value means faster animation)       |
| bias           | float  | 0.25     | If `initial_config=random`, the probability of each cell starting as living   |
| grid           | bool   | 1        | Whether or not to display gridlines                                           |
| initial_config | string | random   | Starting configuration; can be `random \| blank \| lined \| center \| border` |
| bg_color       | hex    | 0x000000 | Background color in `0xRRGGBB` format                                         |
| fg_color       | hex    | 0xC7C7C7 | Foreground (cell) color in `0xRRGGBB` format                                  |
| grid_color     | hex    | 0x0F0F0F | Gridline color in `0xRRGGBB` format                                           |
