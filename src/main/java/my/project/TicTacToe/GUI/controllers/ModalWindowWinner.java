package my.project.TicTacToe.GUI.controllers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import my.project.TicTacToe.GUI.Constance;
import my.project.TicTacToe.GUI.TicTacToeApp;

import java.io.InputStream;
import java.util.Optional;

public class ModalWindowWinner  {

    public void winnerModalWindow(String text) {
        Stage modalWindow = new Stage();
        VBox parentVbox = new VBox();
        Scene scene = new Scene(parentVbox, Constance.modalWindowWeight, Constance.modalWindowHeight);
        Label title = new Label("Результат игры:");

        HBox hBoxText = new HBox();
        Label textResult = new Label();
        Label result = new Label();
        hBoxText.setPrefSize(100, 20);
        title.setAlignment(Pos.CENTER);

        title.setId("title");
        textResult.setId("textResult");
        result.setText("result");

        parentVbox.setPadding(new Insets(10, 10, 10, 10));
        hBoxText.getChildren().addAll(textResult, result);
        parentVbox.getChildren().addAll(title, hBoxText);
        setWinnerText(text, textResult, result);

        parentVbox.getStylesheets().add("style.css");
        modalWindow.getIcons().add(findAndSetIcon().get());
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

    private Optional<Image> findAndSetIcon() {
        InputStream iconStream = TicTacToeApp.class.getResourceAsStream("/toe.png");
        if (iconStream != null) {
            return Optional.of(new Image(iconStream));
        }
        return Optional.empty();
    }
}
