package my.project.TicTacToe.GUI.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import my.project.TicTacToe.GUI.TicTacToeApp;

import java.io.IOException;

public class ModalWindowWinner {

    public void winnerModalWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("/ModalWindow.fxml"));
        Parent rootNode = fxmlLoader.load();
        Stage modalWindow = new Stage();
        Scene scene = new Scene(rootNode, 200, 200);
        modalWindow.initModality(Modality.APPLICATION_MODAL);
        modalWindow.setScene(scene);
        modalWindow.showAndWait();

    }


}
