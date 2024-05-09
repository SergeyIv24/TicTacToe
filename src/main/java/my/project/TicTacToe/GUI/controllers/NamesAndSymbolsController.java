package my.project.TicTacToe.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import my.project.TicTacToe.Game.GameService;
import my.project.TicTacToe.Gamers.ComputerGamerHard;
import my.project.TicTacToe.Gamers.Gamer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NamesAndSymbolsController implements Initializable {

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
    private static boolean isGameAgainstComputer;
    private static boolean isGameHard;
    protected static Gamer firstGamer;
    protected static Gamer secondGamer;
    protected static Gamer computerGamer;

    public void setIsGameAgainstComputer(boolean againstComputer) {
        isGameAgainstComputer = againstComputer;
    }

    public boolean getIsGameAgainstComputer() {
        return isGameAgainstComputer;
    }

    public void setIsGameHard(boolean hard) {
        isGameHard = hard;
    }

    public boolean getIsGameHard() {
        return isGameHard;
    }




    @FXML
    protected void defineGameSymbolsForFirst() {
        if (firstGamerName.getText().isEmpty() || firstGamerName.getText().isBlank()) {
            //todo throw new exception
        }
        if (firstGamerSymbol.getText().contains("X") || firstGamerSymbol.getText().contains("0")) {
            return;
        }
        firstGamer = GameService.createFirstGamer(firstGamerName.getText());
        firstGamerSymbol.setText(firstGamerSymbol.getText() + " " + firstGamer.getGameSymbol());
        firstGamerName.setDisable(true);

        if (isGameAgainstComputer) {
            setComputerName();
            start.setDisable(false);
        }
    }

    @FXML
    protected void defineGameSymbolsForSecond() {
        if (secondGamerName.getText().isEmpty() || secondGamerName.getText().isBlank()) {
            //todo throw new exception
        }
        if (secondGamerSymbol.getText().contains("X") || secondGamerSymbol.getText().contains("0")) {
            return;
        }
        secondGamer = GameService.createSecondGamer(secondGamerName.getText());
        secondGamerSymbol.setText(secondGamerSymbol.getText() + " " + secondGamer.getGameSymbol());
        secondGamerName.setDisable(true);
        start.setDisable(false);
    }

    private void setComputerName() {
        secondGamerName.setDisable(true);
        if (isGameHard) {
            secondGamerName.setText("Компьютер (сложно)");
            return;
        }
        secondGamerName.setText("Компьютер (легко)");
    }

    @FXML
    protected void startGame() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/game.fxml"));
        Stage stage = (Stage) start.getScene().getWindow();
        Parent root = loaderNextScene.load();
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setDisable(true);
    }
}
