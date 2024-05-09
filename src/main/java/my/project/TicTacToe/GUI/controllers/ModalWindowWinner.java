package my.project.TicTacToe.GUI.controllers;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModalWindowWinner  {

    public void winnerModalWindow(String text) {
        Stage modalWindow = new Stage();
        VBox parentVbox = new VBox();
        Scene scene = new Scene(parentVbox, 200, 200);
        Label title = new Label("Результат игры");
        HBox hBoxText = new HBox();
        Label textResult = new Label();
        Label result = new Label();
        hBoxText.getChildren().addAll(textResult, result);
        parentVbox.getChildren().addAll(title, hBoxText);
        setWinnerText(text, textResult, result);
        modalWindow.setScene(scene);
        modalWindow.showAndWait();
    }

    public void setWinnerText(String text, Label textResult, Label result) {
        if (text.equalsIgnoreCase("Ничья!")) {
            textResult.setText("Победила дружба");
            result.setText("");
        }
        textResult.setText("Победил игрок:");
        result.setText(text);
    }
}
