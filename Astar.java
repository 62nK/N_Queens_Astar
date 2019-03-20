import java.util.ArrayList;

public class Astar {

    public static int f(QueenCombination state){
        int f=g(state)+h(state);
        state.setF(f);
        hasConflict(state);
        return f;
    }
    // g function returns cost of the state. The cost of adding one queen equals to (Constants.BOARD_SIZE+1)
    public static int g(QueenCombination state){
        int g=state.getSize()*(Constants.BOARD_SIZE+1);
        state.setG(g);
        return g;
    }
    // h function returns heuristic of the state. Heuristic counts number of available diagonals of a state
    public static int h(QueenCombination state){
        int h=0;
        int[][] filledChessBoard = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        for(Square s: state.getQueensCombination()){
            fillRow(s.getStandardCoordY(), filledChessBoard);
            fillColumn(s.getStandardCoordX(), filledChessBoard);
            fillDiagonals(s.getStandardCoordY(), s.getStandardCoordX(), filledChessBoard);
        }
        h+=neswDiagonal(filledChessBoard)+nwseDiagonal(filledChessBoard)-100*countPairsOfConflict(state);
        state.setH(h);
        return h;
    }

    public static int countPairsOfConflict(QueenCombination state){
        int h=0;
        int vertical=0, horizontal=0, backDiagonal=0, forwardDiagonal=0;
        ArrayList<Integer> visited = new ArrayList<>();
        for(Square s: state.getQueensCombination()){
            if(!visited.contains(s.getIndex())) {
                visited.add(s.getIndex());
                for(Square t: state.getQueensCombination()) {
                    if(!s.equals(t) && !visited.contains(t.getIndex())){
                        if(s.isOnSameVertical(t))
                            vertical++;
                        if(s.isOnSameHorizontal(t))
                            horizontal++;
                        if(s.isOnSameForwardDiagonal(t))
                            forwardDiagonal++;
                        if(s.isOnSameBackDiagonal(t))
                            backDiagonal++;
                    }
                }
            }
        }
        h=vertical+horizontal+backDiagonal+forwardDiagonal;
        state.setH(h);
        if(h==0 && state.getSize()==Constants.BOARD_SIZE)
            state.setSolution(true);
        return h;
    }
    public static boolean hasConflict(QueenCombination state){
        boolean conflict = false;
        ArrayList<Integer> visited = new ArrayList<>();
        for(Square s: state.getQueensCombination()){
            if(!visited.contains(s.getIndex())) {
                visited.add(s.getIndex());
                for(Square t: state.getQueensCombination()) {
                    if(!s.equals(t) && !visited.contains(t.getIndex())){
                        if(s.isOnSameVertical(t))
                           conflict = true;
                        if(s.isOnSameHorizontal(t))
                            conflict = true;
                        if(s.isOnSameForwardDiagonal(t))
                            conflict = true;
                        if(s.isOnSameBackDiagonal(t))
                            conflict = true;
                    }
                }
            }
        }
        state.setConflict(conflict);
        return conflict;
    }

    // Count available diagonals along NorthEast - SouthWest axis
    private static int neswDiagonal(int[][] chessBoard){
        int temp_i, temp_j, h=0;
        boolean thereIsAQueen, thereIsOneAvailableSquare;
        for(int j=0; j<Constants.BOARD_SIZE; j++){
            temp_i=0;
            temp_j=j;
            thereIsAQueen = false;
            thereIsOneAvailableSquare = false;
            while(temp_j>=0){
                if(chessBoard[temp_i][temp_j]==Constants.QUEEN){
                    thereIsAQueen = true;
                    break;
                }
                if(chessBoard[temp_i][temp_j]!=Constants.NOT_ALLOWED){
                    thereIsOneAvailableSquare = true;
                }
                temp_j--;
                temp_i++;
            }
            if(!thereIsAQueen && thereIsOneAvailableSquare)
                h++;
        }
        for(int i=1; i<Constants.BOARD_SIZE; i++){
            temp_i=i;
            temp_j=Constants.BOARD_SIZE-1;
            thereIsAQueen = false;
            thereIsOneAvailableSquare = false;
            while(temp_i<Constants.BOARD_SIZE){
                if(chessBoard[temp_i][temp_j]==Constants.QUEEN){
                    thereIsAQueen = true;
                    break;
                }
                if(chessBoard[temp_i][temp_j]!=Constants.NOT_ALLOWED){
                    thereIsOneAvailableSquare = true;
                }
                temp_j--;
                temp_i++;
            }
            if(!thereIsAQueen && thereIsOneAvailableSquare)
                h++;
        }
        return h;
    }
    // Count available diagonals along NorthWest - SouthEast axis
    private static int nwseDiagonal(int[][] chessBoard){
        int temp_i, temp_j, h=0;
        boolean thereIsAQueen, thereIsOneAvailableSquare;
        for(int j=0; j<Constants.BOARD_SIZE; j++){
            temp_i=0;
            temp_j=j;
            thereIsAQueen = false;
            thereIsOneAvailableSquare = false;
            while(temp_j<Constants.BOARD_SIZE){
                if(chessBoard[temp_i][temp_j]==Constants.QUEEN){
                    thereIsAQueen = true;
                    break;
                }
                if(chessBoard[temp_i][temp_j]!=Constants.NOT_ALLOWED){
                    thereIsOneAvailableSquare = true;
                }
                temp_j++;
                temp_i++;
            }
            if(!thereIsAQueen && thereIsOneAvailableSquare)
                h++;
        }
        for(int i=1; i<Constants.BOARD_SIZE; i++){
            temp_i=i;
            temp_j=0;
            thereIsAQueen = false;
            thereIsOneAvailableSquare = false;
            while(temp_i<Constants.BOARD_SIZE){
                if(chessBoard[temp_i][temp_j]==Constants.QUEEN){
                    thereIsAQueen = true;
                    break;
                }
                if(chessBoard[temp_i][temp_j]!=Constants.NOT_ALLOWED){
                    thereIsOneAvailableSquare = true;
                }
                temp_j++;
                temp_i++;
            }
            if(!thereIsAQueen && thereIsOneAvailableSquare)
                h++;
        }
        return h;
    }
    private static void fillRow(int row, int[][] chessBoard){
        for(int column=0; column<Constants.BOARD_SIZE; column++)
            if(chessBoard[row][column]!=Constants.QUEEN)
                chessBoard[row][column] = Constants.NOT_ALLOWED;
    }
    private static void fillColumn(int column, int[][] chessBoard){
        for (int row=0; row<Constants.BOARD_SIZE; row++)
            if(chessBoard[row][column]!=Constants.QUEEN)
                chessBoard[row][column] = Constants.NOT_ALLOWED;
    }
    private static void fillDiagonals(int y, int x, int[][] chessBoard) {
        for (int i = y + 1, j = x + 1; i < Constants.BOARD_SIZE && j < Constants.BOARD_SIZE; i++, j++)
            if(chessBoard[i][j]!=Constants.QUEEN)
                chessBoard[i][j] = Constants.NOT_ALLOWED;
        for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--)
            if(chessBoard[i][j]!=Constants.QUEEN)
                chessBoard[i][j] = Constants.NOT_ALLOWED;
        for (int i = y + 1, j = x - 1; i < Constants.BOARD_SIZE && j >= 0; i++, j--)
            if(chessBoard[i][j]!=Constants.QUEEN)
                chessBoard[i][j] = Constants.NOT_ALLOWED;
        for (int i = y - 1, j = x + 1; i >= 0 && j < Constants.BOARD_SIZE; i--, j++)
            if(chessBoard[i][j]!=Constants.QUEEN)
                chessBoard[i][j] = Constants.NOT_ALLOWED;
    }
}
