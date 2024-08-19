public class Solver {

    // Region - Number - Count
    private byte[][][] confirmed = new byte[3][9][9];

    /**
     * Checks empty space in the grid and
     * 
     */
    public void scan(Grid grid) {
        // Numbers
        for (byte i = 1; i <= 9; ++i) {
            // x
            for (byte j = 0; j < 9; ++j) {
                // y
                for (byte k = 0; k < 9; ++k) {
                    if (grid.check_can_be(j, k, i)) {
                        // Row
                        if (confirmed[0][i - 1][j] == 0) {
                            confirmed[0][i - 1][j] = (byte) (k + 1);
                        } else {
                            confirmed[0][i - 1][j] = 10;
                        }

                        // Col
                        if (confirmed[1][i - 1][k] == 0) {
                            confirmed[1][i - 1][k] = (byte) (j + 1);
                        } else {
                            confirmed[1][i - 1][k] = 10;
                        }

                        // Box
                        if (confirmed[2][i - 1][j / 3 * 3 + k / 3] == 0) {
                            confirmed[2][i - 1][j / 3 * 3 + k / 3] = (byte) (j % 3 * 3 + k % 3 + 1);
                        } else {
                            confirmed[2][i - 1][j / 3 * 3 + k / 3] = 10;
                        }
                    }
                }
            }
        }

        for (byte i = 0; i < 9; ++i) {
            for (byte j = 0; j < 9; ++j) {
                byte index = confirmed[0][i][j];

                if (index != 0 && index != 10) {
                    grid.check_set(j, (byte) (index - 1), (byte) (i + 1));
                }

                index = confirmed[1][i][j];

                if (index != 0 && index != 10) {
                    grid.check_set((byte) (index - 1), j, (byte) (i + 1));
                }

                index = confirmed[2][i][j];

                if (index != 0 && index != 10) {
                    grid.check_set((byte) (j / 3 * 3 + (index - 1) / 3), (byte) (j % 3 * 3 + (index - 1) % 3),
                            (byte) (i + 1));
                }
            }
        }

        confirmed = new byte[3][9][9];
    }
}
