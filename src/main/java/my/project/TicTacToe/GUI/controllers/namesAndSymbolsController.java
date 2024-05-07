package my.project.TicTacToe.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import my.project.TicTacToe.Game.Game;
import my.project.TicTacToe.Game.GameService;
import my.project.TicTacToe.Game.GamerVsGamer;

import java.io.IOException;

public class namesAndSymbolsController {

    @FXML
    private TextField firstGamerName;

    @FXML
    private TextField secondGamerName;

    @FXML
    private Label firstGamerSymbol;

    @FXML
    private Label secondGamerSymbol;

    @FXML
    private Button start;

    @FXML
    protected void defineGameSymbolsForFirst() {
        if (firstGamerName.getText().isEmpty() || firstGamerName.getText().isBlank()) {
            //todo throw new exception
        }
        if (firstGamerSymbol.getText().contains("X") || firstGamerSymbol.getText().contains("0")) {
            return;
        }
        firstGamerSymbol.setText(firstGamerSymbol.getText()
                + " " + GameService.createFirstGamer(firstGamerName.getText()).getGameSymbol());
        firstGamerName.setDisable(true);
    }

    @FXML
    protected void defineGameSymbolsForSecond() {
        if (secondGamerName.getText().isEmpty() || secondGamerName.getText().isBlank()) {
            //todo throw new exception
        }
        if (secondGamerSymbol.getText().contains("X") || secondGamerSymbol.getText().contains("0")) {
            return;
        }
        secondGamerSymbol.setText(secondGamerSymbol.getText()
                + " " + GameService.createSecondGamer(secondGamerName.getText()).getGameSymbol());
        secondGamerName.setDisable(true);
    }

    @FXML
    protected void startGame() throws IOException {
        //todo дизейблить кнопку пока не заполнены имена
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/game.fxml"));
        Stage stage = (Stage) start.getScene().getWindow();
        Parent root = loaderNextScene.load();
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
    }








}
