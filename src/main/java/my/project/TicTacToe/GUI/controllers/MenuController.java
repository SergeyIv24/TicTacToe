package my.project.TicTacToe.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import my.project.TicTacToe.GUI.Constance;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button gamerVsGamer;

    @FXML
    private MenuButton gamerVsComputer;

    @FXML
    protected void goNameAndSymbol() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/gamersNameAndSymbol.fxml"));
        Stage stage = (Stage) gamerVsGamer.getScene().getWindow();
        Parent root = loaderNextScene.load();
        Scene scene = new Scene(root, Constance.windowWeight, Constance.windowHeight);
        stage.setScene(scene);
    }

    @FXML
    protected void goNameAndSymbolCompEasy() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/gamersNameAndSymbol.fxml"));
        Stage stage = (Stage) gamerVsComputer.getScene().getWindow();
        Parent root = loaderNextScene.load();
        Scene scene = new Scene(root, Constance.windowWeight, Constance.windowHeight);
        NamesAndSymbolsController controller = loaderNextScene.getController();
        controller.setIsGameAgainstComputer(true);
        controller.setIsGameHard(false);
        stage.setScene(scene);
    }

    @FXML
    protected void goNameAndSymbolCompHard() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/gamersNameAndSymbol.fxml"));
        Stage stage = (Stage) gamerVsComputer.getScene().getWindow();
        Parent root = loaderNextScene.load();
        Scene scene = new Scene(root, Constance.windowWeight, Constance.windowHeight);
        NamesAndSymbolsController controller = loaderNextScene.getController();
        controller.setIsGameAgainstComputer(true);
        controller.setIsGameHard(true);
        stage.setScene(scene);
    }

    @FXML
    protected void goExit() {
        System.exit(0);
    }
}
