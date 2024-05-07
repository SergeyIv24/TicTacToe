package my.project.TicTacToe.GUI.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import my.project.TicTacToe.Game.GameService;
import my.project.TicTacToe.Game.GamerVGamer;
import my.project.TicTacToe.Gamers.Gamer;

import java.net.URL;
import java.util.ResourceBundle;

public class gameController implements Initializable {
    //private char[][] gameBoardArr;

    @FXML
    private GridPane gameBoard;

    @FXML
    private Label coord00;

    @FXML
    private Label coord01;

    @FXML
    private Label coord02;

    @FXML
    private Label coord10;

    @FXML
    private Label coord11;

    @FXML
    private Label coord12;

    @FXML
    private Label coord20;

    @FXML
    private Label coord21;

    @FXML
    private Label coord22;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GamerVGamer.game(namesAndSymbolsController.firstGamer, namesAndSymbolsController.secondGamer);

    }

    @FXML
    protected void gamerCourse(MouseEvent event) {
        if (whichGameSymbol().isEmpty()) {
            return;
        }

        Label label = (Label) event.getSource();
        label.setText(whichGameSymbol());
        GamerVGamer.setCourses((short) (GamerVGamer.getCourses() + 1));
    }

    //Определение какой символ ставить
    private String whichGameSymbol() {
        if (GamerVGamer.whichCourse().isPresent()) {
            return String.valueOf(GamerVGamer.whichCourse().get().getGameSymbol());
        }
        return "";
    }

}



/*        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {




        }*/
