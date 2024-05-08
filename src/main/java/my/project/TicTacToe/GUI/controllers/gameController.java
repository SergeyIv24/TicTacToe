package my.project.TicTacToe.GUI.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import my.project.TicTacToe.Game.GameService;
import my.project.TicTacToe.Game.GamerVGamer;
import my.project.TicTacToe.Gamers.Gamer;

import java.net.URL;
import java.util.Arrays;
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

    private Node[][] arrGridPane = new Node[3][3];

    private void fillGridArray() {
        for (Node node : gameBoard.getChildren()) {
            Integer currentRowIndex = GridPane.getRowIndex(node);
            Integer currentColumnIndex = GridPane.getColumnIndex(node);
            if (currentRowIndex == null) {
                break;
            }
            if (currentColumnIndex == null) {
                break;
            }
            this.arrGridPane[currentRowIndex][currentColumnIndex] = node;
        }
    }

    private String defineCoordinatesNode(Node node) {
        int j = 0;
        int i = 0;
        while ((i != 3) && (j < arrGridPane[i].length)) {
            if (node == arrGridPane[i][j]) {
                System.out.println(arrGridPane[i][j].getId());
                return "" + i + j; //i - строка, j - колонка
            }
            if (j++ == arrGridPane[i].length - 1) {
                i++;
                j = 0;
            }
        }
        return "";
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGridArray();
        GamerVGamer.startGame();
    }



    @FXML
    protected void gamerCourse(MouseEvent event) {

        if (whichGameSymbol().isEmpty()) {
            return;
        }
        Label label = (Label) event.getSource();
        label.setText(whichGameSymbol());
        String nodeCoordinates = defineCoordinatesNode(label);
        System.out.println(nodeCoordinates);
        int line = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(0)));
        int column = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(1)));
        GamerVGamer.game(line, column);

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
