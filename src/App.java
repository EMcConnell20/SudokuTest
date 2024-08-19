/*
 * This is still a work in progress.
 * Lots of the code is inconsistent because:
 * 
 * A - I'm still in the prototyping phase.
 * B - Java is just a messy language.
*/

public class App {
    public static void main(String[] args) throws Exception {
        Grid grid = new Grid();
        Grid test_grid = grid.clone();

        Menu menu = new Menu();
        MenuMode mode = MenuMode.Main;

        byte x_focus = 0;
        byte y_focus = 0;
        byte scan_focus = 0;

        char input;
        boolean running = true;

        while (running) {
            switch (mode) {
                case Main: {
                    menu.print_menu_main(grid);
                    input = menu.scan();

                    switch (input) {
                        case 'Q':
                        case 'q': {
                            mode = MenuMode.Quit;
                            break;
                        }

                        case 'F':
                        case 'f': {
                            mode = MenuMode.Fill;
                            x_focus = 0;
                            y_focus = 0;
                            break;
                        }

                        case 'S':
                        case 's': {
                            x_focus = 0;
                            y_focus = 0;
                            mode = MenuMode.Solve;
                            break;
                        }
                    }

                    break;
                }

                case Fill: {
                    menu.print_menu_fill(grid, x_focus, y_focus);
                    input = menu.scan();

                    switch (input) {
                        case 'W':
                        case 'w': {
                            if (y_focus < 1) {
                                y_focus = 9;
                            }
                            --y_focus;
                            break;
                        }

                        case 'A':
                        case 'a': {
                            if (x_focus < 1) {
                                x_focus = 9;
                            }
                            --x_focus;
                            break;
                        }

                        case 'S':
                        case 's': {
                            y_focus++;
                            if (y_focus > 8) {
                                y_focus = 0;
                            }
                            break;
                        }

                        case 'D':
                        case 'd': {
                            x_focus++;
                            if (x_focus > 8) {
                                x_focus = 0;
                            }
                            break;
                        }

                        case 'F':
                        case 'f': {
                            grid = test_grid.clone();
                            mode = MenuMode.Main;
                            x_focus = 0;
                            y_focus = 0;
                            break;
                        }

                        case 'C':
                        case 'c': {
                            mode = MenuMode.Main;
                            x_focus = 0;
                            y_focus = 0;
                            break;
                        }

                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            byte val = Byte.valueOf(String.valueOf(input));

                            // Backwards because reasons
                            if (test_grid.check_set(y_focus, x_focus, val)) {
                                ++x_focus;
                                if (x_focus > 8) {
                                    x_focus = 0;
                                    ++y_focus;

                                    if (y_focus > 8) {
                                        y_focus = 0;
                                    }
                                }
                            } else {
                                mode = MenuMode.FillError;
                            }

                            break;
                        }
                    }

                    break;
                }

                case FillError: {
                    mode = MenuMode.Fill;
                    menu.print_menu_fill_problem(grid, x_focus, y_focus);
                    input = menu.scan();

                    switch (input) {
                        case 'W':
                        case 'w': {
                            if (y_focus < 1) {
                                y_focus = 9;
                            }
                            --y_focus;
                            break;
                        }

                        case 'A':
                        case 'a': {
                            if (x_focus < 1) {
                                x_focus = 9;
                            }
                            --x_focus;
                            break;
                        }

                        case 'S':
                        case 's': {
                            y_focus++;
                            if (y_focus > 8) {
                                y_focus = 0;
                            }
                            break;
                        }

                        case 'D':
                        case 'd': {
                            x_focus++;
                            if (x_focus > 8) {
                                x_focus = 0;
                            }
                            break;
                        }

                        case 'F':
                        case 'f': {
                            grid = test_grid.clone();
                            mode = MenuMode.Main;
                            x_focus = 0;
                            y_focus = 0;
                            break;
                        }

                        case 'C':
                        case 'c': {
                            mode = MenuMode.Main;
                            x_focus = 0;
                            y_focus = 0;
                            break;
                        }

                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            byte val = Byte.valueOf(String.valueOf(input));

                            if (test_grid.check_set(y_focus, x_focus, val)) {
                                ++x_focus;
                                if (x_focus > 8) {
                                    x_focus = 0;
                                    ++y_focus;

                                    if (y_focus > 8) {
                                        y_focus = 0;
                                    }
                                }
                            } else {
                                mode = MenuMode.FillError;
                            }

                            break;
                        }
                    }

                    break;
                }

                case Solve: {
                    menu.print_menu_solve(test_grid, x_focus, y_focus);
                    input = menu.scan();

                    switch (input) {
                        case 'H':
                        case 'h': {
                            mode = MenuMode.Help;
                            break;
                        }

                        case 'B':
                        case 'b': {
                            if (y_focus == 0) {
                                x_focus = 0;
                            } else {
                                y_focus = 0;
                            }

                            break;
                        }

                        case 'M':
                        case 'm':
                        case 'C':
                        case 'c': {
                            mode = MenuMode.Main;
                            break;
                        }

                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            byte val = Byte.valueOf(String.valueOf(input));
                            if (val > 9) {
                                break;
                            }

                            if (x_focus != 0) {
                                if (y_focus != 0) {
                                    --x_focus;
                                    --y_focus;

                                    // Backwards for reasons
                                    if (test_grid.check_set(x_focus, y_focus, val)) {
                                        x_focus = 0;
                                        y_focus = 0;
                                    } else {
                                        mode = MenuMode.SolveError;
                                    }
                                } else {
                                    y_focus = val;
                                }
                            } else {
                                x_focus = val;
                            }
                            break;
                        }
                    }

                    break;
                }

                case SolveError: {
                    mode = MenuMode.Solve;
                    menu.print_menu_solve_problem(test_grid, x_focus, y_focus);
                    input = menu.scan();

                    switch (input) {
                        case 'H':
                        case 'h': {
                            mode = MenuMode.Help;
                            break;
                        }

                        case 'B':
                        case 'b': {
                            if (y_focus == 0) {
                                x_focus = 0;
                            } else {
                                y_focus = 0;
                            }

                            break;
                        }

                        case 'M':
                        case 'm':
                        case 'C':
                        case 'c': {
                            mode = MenuMode.Main;
                            break;
                        }

                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            byte val = Byte.valueOf(String.valueOf(input));
                            if (val > 9) {
                                break;
                            }

                            x_focus = val;
                            y_focus = 0;
                            break;
                        }
                    }

                    break;
                }

                case Help: {
                    menu.print_menu_help(test_grid);
                    input = menu.scan();

                    switch (input) {
                        case 'S':
                        case 's': {
                            Solver solver = new Solver();
                            solver.scan(grid);

                            break;
                        }

                        case 'C':
                        case 'c': {
                            mode = MenuMode.Solve;
                            break;
                        }

                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            byte val = Byte.valueOf(String.valueOf(input));
                            // > 9 might be redundant...
                            if (val > 9 || val == 0) {
                                break;
                            }

                            scan_focus = val;

                            mode = MenuMode.SearchResult;
                            break;
                        }
                    }

                    break;
                }

                case Quit: {
                    menu.print_menu_quit();
                    input = menu.scan();

                    switch (input) {
                        case 'Y':
                        case 'y': {
                            running = false;
                            break;
                        }

                        case 'N':
                        case 'n': {
                            mode = MenuMode.Main;
                            break;
                        }
                    }

                    break;
                }

                case SearchResult: {
                    mode = MenuMode.Solve;
                    menu.print_menu_scan_result(test_grid, scan_focus);
                    input = menu.scan();

                    break;
                }
            }
        }
    }
}

/*
 * Menus
 * 0 - Main
 * 1 - Fill
 * 2 - Solve
 * 3 - Help
 * 4 - Quit
 */

/*
 * Options 0
 * Q - Quit
 * F - Fill
 * S - Solve
 */

/*
 * Options 1
 * 0..=9 - Write
 * 
 * W - Up
 * A - Left
 * S - Down
 * D - Left
 * 
 * F - Finish
 * C - Cancel
 */

/*
 * Options 2
 * 0..=9 - Write
 * 
 * H - Help
 * 
 * C - Cancel
 * M - Main Menu
 */

/*
 * Options 3
 * 1..=9 - Check Valid Spaces
 * 
 * S - Search For Singles
 * 
 * C - Cancel
 */

/*
 * Options 4
 * Y - Quit
 * N - Main Menu
 */
