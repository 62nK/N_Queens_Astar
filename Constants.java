import java.util.ArrayList;

public interface Constants {

    // Parameters
    public static final boolean RANDOMIZE_PATH = true;
    public static final boolean PRUNE_IMPOSSIBLE_GUESSES = true;
    public static final boolean DEBUG = true;
    public static final boolean DRAW_GUESSES = false;
    public static final boolean DRAW_PATHS = false;

    // Problem size
    public static final int BOARD_SIZE = 8;
    public static final int BOARD_SIZE_X = BOARD_SIZE;
    public static final int BOARD_SIZE_Y = BOARD_SIZE;

    // JavaFX
    public static final double BOARD_HEIGHT = 500; // Pixels
    public static final double BOARD_WIDTH = BOARD_HEIGHT;  // Pixels
    public static final double SQUARE_SIZE = BOARD_HEIGHT/BOARD_SIZE;    // Pixels
    public static final double WIDTH = BOARD_WIDTH+SQUARE_SIZE;    // Pixels
    public static final double HEIGHT = BOARD_HEIGHT+SQUARE_SIZE;   // Pixels

    // Initial State
    public static ArrayList<Square> QUEENS_INITIAL_PLACEMENT = new ArrayList<>();

    // Squares
    public static final int QUEEN = 1;
    public static final int EMPTY = 0;
    public static final int NOT_ALLOWED = 2;
}
