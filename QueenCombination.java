import java.util.ArrayList;
import java.util.Comparator;

public class QueenCombination {

    // Parameters
    private ArrayList<Square> queensCombination;
    private int f, g, h;
    private boolean conflict;
    private boolean solution;

    public QueenCombination(ArrayList<Square> queensCombination){
        this.queensCombination = new ArrayList<>();
        solution = false;
        conflict = false;
        for(Square square: queensCombination) {
            this.queensCombination.add(new Square(square.getCoordX(), square.getCoordY()));
        }
    }

    // Getters
    public ArrayList<Square> getQueensCombination() {
        return queensCombination;
    }
    public int getF() {
        return f;
    }
    public int getG() {
        return g;
    }
    public int getH() {
        return h;
    }
    public int getSize(){
        return queensCombination.size();
    }
    public boolean isSolution() {
        return solution;
    }
    public boolean hasConflict() {
        return conflict;
    }

    public ArrayList<QueenCombination> generateNeighborStates(){
        ArrayList<QueenCombination> neighborStates = new ArrayList<>();
        for(char index='A'; index<'A'+Constants.BOARD_SIZE; index++){
            QueenCombination tempQueensCombination = new QueenCombination(queensCombination);
            Square tempSquare = new Square(index, queensCombination.size()+1);
            if(tempSquare.isValid()) {
                tempQueensCombination.getQueensCombination().add(tempSquare);
                Astar.f(tempQueensCombination);
                if(Constants.PRUNE_IMPOSSIBLE_GUESSES) {
                    if (!tempQueensCombination.hasConflict())
                        neighborStates.add(tempQueensCombination);
                }else
                    neighborStates.add(tempQueensCombination);
            }
        }
        return neighborStates;
    }

    // Setters
    public void setF(int f) {
        this.f = f;
    }
    public void setG(int g) {
        this.g = g;
    }
    public void setH(int h) {
        this.h = h;
    }
    public void setSolution(boolean solution) {
        this.solution = solution;
    }
    public void setConflict(boolean conflict) {
        this.conflict = conflict;
    }

    // To String
    public String toString(){
        String string = "";
        for(Square s: queensCombination)
            string+= s.toString();
        return string+"f("+f+")"+"="+g+"+"+h;
    }
    public String toString(String title){
        return title +": "+ toString();
    }
}
class SortByFValue implements Comparator<QueenCombination> {
    public int compare(QueenCombination a, QueenCombination b) {
        return (b.getF() - a.getF());
    }
}