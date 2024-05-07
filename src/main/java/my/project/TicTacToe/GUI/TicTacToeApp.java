package my.project.TicTacToe.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class TicTacToeApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("/menu.fxml"));
        Parent rootNode = fxmlLoader.load();
        Scene sceneMenu = new Scene(rootNode, 600, 600);
        stage.setTitle("Крестики - нолики");
        stage.setScene(sceneMenu);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        //System.out.println(Arrays.deepToString(new char[3][3]));
    }


}
