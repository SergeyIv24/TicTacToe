package my.project.TicTacToe.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class TicTacToeApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("/menu.fxml"));
        Parent rootNode = fxmlLoader.load();
        Scene sceneMenu = new Scene(rootNode, Constance.windowWeight, Constance.windowHeight);
        stage.setMaxWidth(Constance.maxWindowWeight);
        stage.setMaxHeight(Constance.maxWindowHeight);
        stage.setMinWidth(Constance.minWindowWeight);
        stage.setMinHeight(Constance.minWindowHeight);
        stage.setTitle(Constance.gameName);
        if (findAndSetIcon().isPresent()) {
            stage.getIcons().add(findAndSetIcon().get());
        }
        stage.setScene(sceneMenu);
        stage.show();
    }

    private Optional<Image> findAndSetIcon() {
        InputStream iconStream = TicTacToeApp.class.getResourceAsStream("/toe.png");
        if (iconStream != null) {
            return Optional.of(new Image(iconStream));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        launch();
    }
}
