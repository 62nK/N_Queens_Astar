import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class GuessBoard extends Pane {

    // Parameters
    private QueenCombination currentQueensCombination;
    private ArrayList<QueenCombination> visitedQueenCombinationsList;
    private ArrayList<QueenCombination> openQueenCombinationsList;

    // Constructor
    public GuessBoard(){
        visitedQueenCombinationsList = new ArrayList<>();
        openQueenCombinationsList = new ArrayList<>();
        currentQueensCombination = new QueenCombination(Constants.QUEENS_INITIAL_PLACEMENT);
        Astar.f(currentQueensCombination);

        drawCurrentState();
    }

    public void nextGuess(){
        visit();
        open();
        drawCurrentState();
        if(Constants.DRAW_GUESSES)
            drawGuess();
        if(Constants.DEBUG) {
            System.out.println(currentQueensCombination.toString("Current combination"));
            System.out.println("Open combinations:"+openQueenCombinationsList.toString());
            System.out.println("Visited combinations:"+visitedQueenCombinationsList.toString());
        }
    }
    private void open(){
        ArrayList<QueenCombination> currentNeighborStates = currentQueensCombination.generateNeighborStates();
        for(QueenCombination q: currentNeighborStates){
            openQueenCombinationsList.add(q);
        }
        if(Constants.RANDOMIZE_PATH)
            Collections.shuffle(openQueenCombinationsList);
        Collections.sort(openQueenCombinationsList, new SortByFValue());
    }
    private void visit(){
        if(!openQueenCombinationsList.isEmpty()){
            QueenCombination tempQueensCombination = openQueenCombinationsList.remove(0);
            if(!currentQueensCombination.isSolution()) {
                currentQueensCombination = tempQueensCombination;
                visitedQueenCombinationsList.add(currentQueensCombination);
            }
        }
    }

    private void drawCurrentState(){
        getChildren().clear();
        ArrayList<ImageView> queens = new ArrayList<>();
        for(Square s: currentQueensCombination.getQueensCombination()){
            ImageView queen = null;
            try{
                Image whiteQueenImage;
                whiteQueenImage = new Image(new FileInputStream("src/images/wQueen.png"));
                queen = new ImageView(whiteQueenImage);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            queen.setX(Constants.SQUARE_SIZE*(s.getStandardCoordX()+0.05));
            queen.setY(Constants.SQUARE_SIZE*(s.getStandardCoordY()+0.05));
            queen.setFitWidth(Constants.SQUARE_SIZE*0.9);
            queen.setPreserveRatio(true);
            queen.setSmooth(true);
            queen.setCache(true);
            queens.add(queen);
        }

        Text fgh = new Text( Constants.BOARD_WIDTH*0.95, 20,
                "f="+currentQueensCombination.getF()+"\ng="+currentQueensCombination.getG()+"\nh="+currentQueensCombination.getH());
        fgh.setFont(Font.font("Arial", Constants.SQUARE_SIZE/3));
        fgh.setFill(Color.ORANGERED);
        fgh.setStrokeWidth(1);
        fgh.setStroke(Color.ORANGERED);
        getChildren().addAll(queens);
        getChildren().addAll(fgh);
    }
    private void drawGuess(){
        ArrayList<Rectangle> squares = new ArrayList<>();
        ArrayList<Text> textOverlay = new ArrayList<>();
        if(Constants.DRAW_PATHS) {
            ArrayList<Line> path = new ArrayList<>();
            for (QueenCombination list : openQueenCombinationsList) {
                int r = (int) (Math.random() * 256), g = (int) (Math.random() * 256), b = (int) (Math.random() * 256);
                for (int squareIndex = 0; squareIndex < list.getSize() - 1; squareIndex++) {
                    Line line = new Line(
                            (list.getQueensCombination().get(squareIndex).getStandardCoordX() + 0.5) * Constants.SQUARE_SIZE,
                            (list.getQueensCombination().get(squareIndex).getStandardCoordY() + 0.5) * Constants.SQUARE_SIZE,
                            (list.getQueensCombination().get(squareIndex + 1).getStandardCoordX() + 0.5) * Constants.SQUARE_SIZE,
                            (list.getQueensCombination().get(squareIndex + 1).getStandardCoordY() + 0.5) * Constants.SQUARE_SIZE
                    );
                    line.setStroke(Color.rgb(r, g, b));
                    line.setStrokeWidth(2);
                    path.add(line);
                }
            }
            getChildren().addAll(path);
        }
        if(Constants.DRAW_GUESSES) {
            for(QueenCombination list: openQueenCombinationsList){
                for(Square s: list.getQueensCombination()){
                    Rectangle square = new Rectangle(s.getStandardCoordX()*Constants.SQUARE_SIZE, s.getStandardCoordY()*Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
                    square.setFill(Color.TRANSPARENT);
                    square.setStroke(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
                    square.setStrokeWidth(2);
                    squares.add(square);

                    // F
                    Text fFunction = new Text(Constants.SQUARE_SIZE*(s.getStandardCoordX()+0.1), Constants.SQUARE_SIZE*(s.getStandardCoordY()+0.25), ""+list.getF());
                    fFunction.setFill(Color.BLACK);
                    fFunction.setFont(Font.font("Arial", Constants.SQUARE_SIZE/5));
                    textOverlay.add(fFunction);

                    // G
                    Text gFunction = new Text(Constants.SQUARE_SIZE*(s.getStandardCoordX()+0.1), Constants.SQUARE_SIZE*(s.getStandardCoordY()+0.90), ""+list.getG());
                    gFunction.setFill(Color.BLACK);
                    gFunction.setTextAlignment(TextAlignment.RIGHT);
                    gFunction.setFont(Font.font("Arial", Constants.SQUARE_SIZE/5));
                    textOverlay.add(gFunction);

                    // H
                    Text hFunction = new Text(Constants.SQUARE_SIZE*(s.getStandardCoordX()+0.65), Constants.SQUARE_SIZE*(s.getStandardCoordY()+0.90), ""+list.getH());
                    hFunction.setFill(Color.BLACK);
                    hFunction.setFont(Font.font("Arial", Constants.SQUARE_SIZE/5));
                    textOverlay.add(hFunction);
                }
            }
            getChildren().addAll(squares);
            getChildren().addAll(textOverlay);
        }
    }
}
