package my.project.TicTacToe.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import my.project.TicTacToe.Game.GameService;

import java.io.IOException;

public class menuController {

    @FXML
    private Button gamerVsGamer;

    @FXML
    private Button gamerVsComputer;

    @FXML
    protected void goToGamersNameAndSymbol() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/gamersNameAndSymbol.fxml"));
        Stage stage = (Stage) gamerVsGamer.getScene().getWindow();
        Parent root = loaderNextScene.load();
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);

    }

}
