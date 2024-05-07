package my.project.TicTacToe.GUI.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class gameController implements Initializable {

    @FXML
    GridPane gameBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard.add(new Label("X"), 0, 0);
    }

    @FXML
    protected void test(MouseEvent event) {

        System.out.println(gameBoard.getCellBounds(1,0));
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            gameBoard.add(new Label("X"), 1, 0);
            //System.out.println(event.getSource());

        }
    }


}
