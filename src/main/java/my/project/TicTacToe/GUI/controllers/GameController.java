package my.project.TicTacToe.GUI.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import my.project.TicTacToe.Game.GamerVGamer;
import my.project.TicTacToe.Gamers.Gamer;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Label gamerName;

    @FXML
    private Label currentSymbol;

    @FXML
    private GridPane gameBoard;

    private final Node[][] arrGridPane = new Node[3][3];

    //Заполнить двумерный массив объектами из GridPane для поиска объектов и получения координат в таблице
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
                return "" + i + j; //i - строка, j - колонка //todo лишний объект
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
        gamerName.setText(GamerVGamer.getFirstCourseGamer().getName());
        currentSymbol.setText(String.valueOf(GamerVGamer.getFirstCourseGamer().getGameSymbol()));
    }

    @FXML
    protected void gamerCourse(MouseEvent event) {
        if (GamerVGamer.getCourses() == -1) {
            return;
        }

        if (getGameSymbol().isEmpty()) {
            return;
        }

        Label label = (Label) event.getSource(); //Нажатие на объект Label
        label.setText(getGameSymbol()); //Установка игрового символа
        String nodeCoordinates = defineCoordinatesNode(label); //Координаты хода
        int line = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(0))); //Строка
        int column = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(1))); //Колонка
        Optional<Gamer> winner = GamerVGamer.game(line, column);
        if (winner.isPresent()) {
            ModalWindowWinner windowWinner = new ModalWindowWinner();
            windowWinner.winnerModalWindow(winner.get().getName());
            return;
        }
        if (GamerVGamer.getCourses() == -1) {
            ModalWindowWinner windowWinner = new ModalWindowWinner();
            windowWinner.winnerModalWindow("Ничья!");
            return;
        }

        gamerName.setText(" " + getGamerName());
        currentSymbol.setText(" " + getGameSymbol());
    }

    //Определение текущего символа
    private String getGameSymbol() {
        if (GamerVGamer.whichCourse().isPresent()) {
            return String.valueOf(GamerVGamer.whichCourse().get().getGameSymbol());
        }
        return "";
    }

    private String getGamerName() {
        if (GamerVGamer.whichCourse().isPresent()) {
            return String.valueOf(GamerVGamer.whichCourse().get().getName());
        }
        return "";
    }
}
