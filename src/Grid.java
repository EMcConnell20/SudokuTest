public class Grid {
    // Setup //

    private static byte[][] ZERO_GRID = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    // Variables //

    protected byte[][] grid = ZERO_GRID.clone();
    protected boolean[][][] booleans = new boolean[3][9][9];

    // Public Functions //

    public boolean check_set(byte x, byte y, byte value) {
        if (this.grid[x][y] == 0) {
            if (value == 0) {
                return true;
            } else if (value < 10) {
                byte val_index = (byte) (value - 1);

                if (this.booleans[0][x][val_index]
                        || this.booleans[1][y][val_index]
                        || this.booleans[2][x / 3 * 3 + y / 3][val_index]) {
                    return false;
                } else {
                    this.grid[x][y] = value;
                    this.booleans[0][x][val_index] = true;
                    this.booleans[1][y][val_index] = true;
                    this.booleans[2][x / 3 * 3 + y / 3][val_index] = true;

                    return true;
                }
            } else {
                return true;
            }
        } else {
            byte old_val_index = (byte) (this.grid[x][y] - 1);
            if (value == 0) {
                this.grid[x][y] = 0;
                this.booleans[0][x][old_val_index] = false;
                this.booleans[1][y][old_val_index] = false;
                this.booleans[2][x / 3 * 3 + y / 3][old_val_index] = false;

                return true;
            } else if (value < 10) {
                byte val_index = (byte) (value - 1);
                if (this.booleans[0][x][val_index]
                        || this.booleans[1][y][val_index]
                        || this.booleans[2][x / 3 * 3 + y / 3][val_index]) {
                    return true;
                } else {
                    this.grid[x][y] = value;
                    this.booleans[0][x][old_val_index] = false;
                    this.booleans[1][y][old_val_index] = false;
                    this.booleans[2][x / 3 * 3 + y / 3][old_val_index] = false;
                    this.booleans[0][x][val_index] = true;
                    this.booleans[1][y][val_index] = true;
                    this.booleans[2][x / 3 * 3 + y / 3][val_index] = true;

                    return true;
                }
            } else {
                return true;
            }
        }
    }

    public boolean check_can_be(byte x, byte y, byte value) {
        if (value == 0) {
            return true;
        }

        byte val_index = (byte) (value - 1);

        if (this.grid[x][y] == 0) {
            if (this.booleans[0][x][val_index]
                    || this.booleans[1][y][val_index]
                    || this.booleans[2][x / 3 * 3 + y / 3][val_index]) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public Grid clone() {
        Grid output = new Grid();
        output.grid = this.grid;
        output.booleans = this.booleans;

        return output;
    }

    // Defaults //

    public static Grid default_a() {
        Grid output = new Grid();

        for (byte x = 0; x < 3; ++x) {
            for (byte y = 0; y < 9; ++y) {
                output.check_set(x, y, (byte) ((y + x * 3) % 9 + 1));
            }
        }

        for (byte x = 3; x < 6; ++x) {
            for (byte y = 0; y < 3; ++y) {
                output.check_set(x, y, (byte) ((y + x * 3 + 1) % 9 + 1));
            }
        }

        return output;
    }

    public static Grid default_b() {
        Grid output = new Grid();

        for (byte x = 0; x < 5; ++x) {
            for (byte y = 0; y < 5; ++y) {
                output.check_set((byte) (x * 2), (byte) (y * 2), (byte) ((y * 2 + x % 2 + x * 2 / 3) % 9 + 1));
            }
        }

        return output;
    }
}
