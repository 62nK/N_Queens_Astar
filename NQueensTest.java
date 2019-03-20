import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NQueensTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Objects
        StackPane stackPane;
        BorderPane borderPane;
        ChessBoard chessBoard;
        GuessBoard guessBoard;

        stackPane = new StackPane();
        borderPane = new BorderPane();
        chessBoard = new ChessBoard(Constants.BOARD_SIZE);
        guessBoard = new GuessBoard();

        stackPane.getChildren().addAll(chessBoard, guessBoard);
        borderPane.setCenter(stackPane);
        borderPane.setPadding(new Insets(10));
        guessBoard.setOnMouseClicked(event -> {
            guessBoard.nextGuess();
        });

        // Put the pane on the scene and the scene on the stage
        Scene scene = new Scene(borderPane, Constants.WIDTH, Constants.HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle(Constants.BOARD_SIZE+" Queens with A* - Andrea Pinardi");
        primaryStage.show();
    }
}
