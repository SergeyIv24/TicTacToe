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
import my.project.TicTacToe.Exeptions.ValidationException;
import my.project.TicTacToe.GUI.Constance;
import my.project.TicTacToe.GUI.TicTacToeApp;
import my.project.TicTacToe.Game.GameService;
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

    @FXML
    private Button back;

    protected static boolean isGameAgainstComputer;
    protected static boolean isGameHard;
    private static Gamer firstGamer;
    private static Gamer secondGamer;

    public void setIsGameAgainstComputer(boolean againstComputer) {
        isGameAgainstComputer = againstComputer;
    }

    public void setIsGameHard(boolean hard) {
        isGameHard = hard;
        if (isGameAgainstComputer) {
            secondGamerName.setDisable(true);
            secondGamerName.setPromptText(setComputerName());
        }
    }

    public static void resetSettings() {
        isGameAgainstComputer = false;
        isGameHard = false;
        firstGamer = null;
        secondGamer = null;
    }

    @FXML
    protected void defineGameSymbolsForFirst() {
        try {
            validate(firstGamerName.getText());
        } catch (ValidationException e) {
            return;
        }
        if (firstGamerSymbol.getText().contains("X") || firstGamerSymbol.getText().contains("0")) {
            return;
        }
        firstGamer = GameService.createFirstGamer(firstGamerName.getText());
        firstGamerSymbol.setText(firstGamerSymbol.getText() + " " + firstGamer.getGameSymbol());
        firstGamerName.setDisable(true);

        if (isGameAgainstComputer) {
            createComputerGamer(isGameHard);
        }

        if (isItReadyToStart()) {
            start.setDisable(false);
        }
    }

    @FXML
    protected void defineGameSymbolsForSecond() {
        try {
            validate(secondGamerName.getText());
        } catch (ValidationException e) {
            return;
        }

        if (secondGamerSymbol.getText().contains("X") || secondGamerSymbol.getText().contains("0")) {
            return;
        }
        secondGamer = GameService.createSecondGamer(secondGamerName.getText());
        secondGamerSymbol.setText(secondGamerSymbol.getText() + " " + secondGamer.getGameSymbol());
        secondGamerName.setDisable(true);
        if (isItReadyToStart()) {
            start.setDisable(false);
        }
    }

    private void validate(String gamerName) {
        if (gamerName.isBlank() || gamerName.isEmpty()) {
            throw new ValidationException("Имя не заполнено");
        }
    }

    private boolean isItReadyToStart() {
        return !firstGamerName.getText().isEmpty() && !secondGamerName.getText().isEmpty();
    }

    private String setComputerName() {
        if (isGameHard) {
            secondGamerName.setText(Constance.pcHard);
            return Constance.pcHard;
        }
        secondGamerName.setText(Constance.pcEasy);
        return Constance.pcEasy;
    }

    private void createComputerGamer(boolean isGameHard) {
        setComputerName();
        secondGamer = GameService.createComputerGamer(isGameHard, "Компьютер");
        secondGamer.setComputer(true);
        start.setDisable(false);
        secondGamerSymbol.setText(secondGamerSymbol.getText() + " " + secondGamer.getGameSymbol());
    }

    @FXML
    protected void startGame() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/game.fxml"));
        Stage stage = (Stage) start.getScene().getWindow();
        Parent root = loaderNextScene.load();
        GameController controller = loaderNextScene.getController();
        controller.setIsGameAgainstComputer(isGameAgainstComputer);
        controller.setIsGameHard(isGameHard);
        Scene scene = new Scene(root, Constance.windowWeight, Constance.windowHeight);
        stage.setScene(scene);
    }

    @FXML
    protected void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("/menu.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, Constance.windowWeight, Constance.windowHeight);
        GameService.resetSettings();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setDisable(true);
    }
}
