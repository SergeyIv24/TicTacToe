package my.project.TicTacToe.GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {
    @Override
    public void start(Stage stage) {
        FlowPane rootNode = new FlowPane();
        Scene scene = new Scene(rootNode, 500, 500);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}
