import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ChessBoard extends Pane {

    // Parameters
    private int size;

    public ChessBoard(int size){
        this.size = size;

        drawChessboard();
    }

    private void drawChessboard(){
        ArrayList<Rectangle> squares;
        ArrayList<Text> coordX, coordY;
        Color lightSquareColor = Color.rgb(240, 218, 181);
        Color darkSquareColor = Color.rgb(181, 135, 99);

        // Coordinates Container
        Rectangle coordinatesContanier = new Rectangle(0,0, Constants.BOARD_WIDTH+Constants.SQUARE_SIZE/3, Constants.BOARD_HEIGHT+Constants.SQUARE_SIZE/3);
        coordinatesContanier.setFill(Color.BLACK);
        coordinatesContanier.setStroke(Color.BLACK);
        coordinatesContanier.setStrokeWidth(1);
        coordinatesContanier.setOpacity(0.6);

        // Letters
        coordX = new ArrayList<>();
        for(int j=0; j<size; j++){
            Text newText = new Text(Constants.SQUARE_SIZE*(j+0.5), Constants.BOARD_HEIGHT+Constants.SQUARE_SIZE/4, ""+(char)('A'+j));
            newText.setFill(Color.WHITE);
            newText.setStroke(Color.WHITE);
            newText.setStrokeWidth(1);
            newText.setFont(Font.font("Monaco", 14));
            coordX.add(newText);
        }
        // Numbers
        coordY = new ArrayList<>();
        for(int i=0; i<size; i++){
            Text newText = new Text(Constants.BOARD_WIDTH+Constants.SQUARE_SIZE/10, i*Constants.SQUARE_SIZE+Constants.SQUARE_SIZE/2, ""+(i+1));
            newText.setFill(Color.WHITE);
            newText.setStroke(Color.WHITE);
            newText.setStrokeWidth(1);
            newText.setFont(Font.font("Monaco", 14));
            coordY.add(newText);
        }

        // Squares
        squares = new ArrayList<>();
        Rectangle border = new Rectangle(0,0, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        border.setStroke(darkSquareColor);
        border.setStrokeWidth(1.5);
        squares.add(border);
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++){
                Rectangle newRectangle = new Rectangle(j* Constants.SQUARE_SIZE,i* Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
                newRectangle.setFill(lightSquareColor);
                if((i+j)%2==1)
                    newRectangle.setFill(darkSquareColor);
                squares.add(newRectangle);
            }

        getChildren().add(coordinatesContanier);
        getChildren().addAll(coordY);
        getChildren().addAll(coordX);
        getChildren().addAll(squares);
    }

}
