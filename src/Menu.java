import java.util.Scanner;

public class Menu {
    /// Variables ///

    private Scanner scanner = new Scanner(System.in);

    /// Scanning ///

    public char scan() {
        return scanner.next().charAt(0);
    }

    /// Printing ///

    private void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void print_menu_main(Grid grid) {
        clear_screen();

        System.out.println("Main Menu");
        System.out.println();

        System.out.println("F - Open Fill Menu");
        System.out.println("S - Open Solving Menu");
        System.out.println("Q - Quit Application");
        System.out.println();

        this.print_grid(grid);
        System.out.println();

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_fill(Grid grid, byte x, byte y) {
        clear_screen();

        System.out.println("Fill Menu");
        System.out.println();

        System.out.println("F - Finalize Changes");
        System.out.println("C - Cancel Changes");
        System.out.println();

        System.out.println("W - Move Cursor Up");
        System.out.println("S - Move Cursor Down");
        System.out.println("A - Move Cursor Left");
        System.out.println("D - Move Cursor Right");
        System.out.println("# - Write Value");
        System.out.println();

        this.print_grid_marker(grid, 'x', x, y);

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_fill_problem(Grid grid, byte x, byte y) {
        clear_screen();

        System.out.println("Fill Menu");
        System.out.println();

        System.out.println("F - Finalize Changes");
        System.out.println("C - Cancel Changes");
        System.out.println();

        System.out.println("W - Move Cursor Up");
        System.out.println("S - Move Cursor Down");
        System.out.println("A - Move Cursor Left");
        System.out.println("D - Move Cursor Right");
        System.out.println("# - Set Space Value");
        System.out.println();

        this.print_grid_marker(grid, '!', x, y);
        System.out.println();

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_solve(Grid grid, byte row_select, byte col_select) {
        clear_screen();

        System.out.println("Write Menu");
        System.out.println();

        System.out.println("H - Help Menu (Auto Tools)");
        System.out.println("M - Return to Main Menu");
        System.out.println();

        if (row_select == 0) {
            System.out.println("# - Select Row");
        } else if (col_select == 0) {
            System.out.println("# - Select Column");
        } else {
            System.out.println("# - Write Value");
        }
        System.out.println();

        this.print_grid(grid);
        System.out.println();

        if (row_select != 0) {
            System.out.println("Selected Row: " + row_select);
        }
        if (col_select != 0) {
            System.out.println("Selected Column: " + col_select);
        }

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_solve_problem(Grid grid, byte x, byte y) {
        clear_screen();

        System.out.println("Write Menu");
        System.out.println();

        System.out.println("H - Help Menu (Auto Tools)");
        System.out.println("M - Return to Main Menu");
        System.out.println();

        System.out.println("# - Select Row");
        System.out.println();

        this.print_grid_marker(grid, '!', x, y);
        System.out.println();

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_help(Grid grid) {
        clear_screen();

        System.out.println("Help Menu");
        System.out.println();

        System.out.println("S - Search For Solutions");
        System.out.println("C - Cancel Help");
        System.out.println();

        System.out.println("# - Highlight Possible Spaces");
        System.out.println();

        this.print_grid(grid);
        System.out.println();

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_quit() {
        clear_screen();

        System.out.println("Close Application?");
        System.out.println();

        System.out.println("Y - Yes");
        System.out.println("N - No");
        System.out.println();

        System.out.print("Input: ");
        System.out.flush();
    }

    public void print_menu_scan_result(Grid grid, byte scan_focus) {
        clear_screen();

        System.out.println("Scan Result Page");
        System.out.println();

        System.out.println("Any Key - Continue");
        System.out.println();

        this.print_grid_scan(grid, scan_focus);
        System.out.println();

        System.out.print("Input: ");
        System.out.flush();
    }

    /// Print Grid ///

    public void print_grid(Grid grid) {
        System.out.println("┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓");

        for (byte x = 0; x < 8; ++x) {
            for (byte y = 0; y < 9; ++y) {
                var val = grid.grid[x][y];

                if (val == 0 || val > 9) {
                    System.out.print("┃   ");
                } else {
                    System.out.print("┃ " + val + " ");
                }
            }

            System.out.println("┃");
            System.out.println("┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫");
        }

        for (byte y = 0; y < 9; ++y) {
            var val = grid.grid[8][y];

            if (val == 0 || val > 9) {
                System.out.print("┃   ");
            } else {
                System.out.print("┃ " + val + " ");
            }
        }

        System.out.println("┃");
        System.out.println("┗━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┛");
    }

    public void print_grid_marker(Grid grid, char marker, byte px, byte py) {
        byte real_x = py;
        byte real_y = px;

        System.out.println("┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓");

        for (byte x = 0; x < 8; ++x) {
            for (byte y = 0; y < 9; ++y) {
                var val = grid.grid[x][y];

                if (x == real_x && y == real_y) {
                    System.out.print("┃ " + marker + " ");
                } else if (val == 0 || val > 9) {
                    System.out.print("┃   ");
                } else {
                    System.out.print("┃ " + val + " ");
                }
            }

            System.out.println("┃");
            System.out.println("┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫");
        }

        for (byte y = 0; y < 9; ++y) {
            var val = grid.grid[8][y];

            if (8 == real_x && y == real_y) {
                System.out.print("┃ " + marker + " ");
            } else if (val == 0 || val > 9) {
                System.out.print("┃   ");
            } else {
                System.out.print("┃ " + val + " ");
            }
        }

        System.out.println("┃");
        System.out.println("┗━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┛");
    }

    public void print_grid_scan(Grid grid, byte value) {
        System.out.println("┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓");

        for (byte x = 0; x < 8; ++x) {
            for (byte y = 0; y < 9; ++y) {
                var val = grid.grid[x][y];

                if (val == 0) {
                    if (grid.check_can_be(x, y, value)) {
                        System.out.print("┃ ? ");
                    } else {
                        System.out.print("┃   ");
                    }
                } else {
                    System.out.print("┃ " + val + " ");
                }
            }

            System.out.println("┃");
            System.out.println("┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫");
        }

        for (byte y = 0; y < 9; ++y) {
            var val = grid.grid[8][y];

            if (val == 0 || val > 9) {
                if (grid.check_can_be((byte) 8, y, value)) {
                    System.out.print("┃ ? ");
                } else {
                    System.out.print("┃   ");
                }
            } else {
                System.out.print("┃ " + val + " ");
            }
        }

        System.out.println("┃");
        System.out.println("┗━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┛");
    }
}
