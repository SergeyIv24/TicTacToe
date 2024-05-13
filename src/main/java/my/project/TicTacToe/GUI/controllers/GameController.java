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

    private boolean isGameAgainstComputer = false;
    private boolean isGameHard = false;

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

    private Node findLabelByCoordinates(int line, int column) {
        try {
            return arrGridPane[line][column];
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGridArray();
        gamerName.setText(GamerVGamer.getFirstCourseGamer().getName());
        currentSymbol.setText(String.valueOf(GamerVGamer.getFirstCourseGamer().getGameSymbol()));
        if (gamerName.getText().contains("Компьютер")) {
            GamerVGamer.startGame();
            computerCourses();
            switchNameAndSymbol(getGamerName(), getGameSymbol());
        } else {
            GamerVGamer.startGame();
        }

    }

    @FXML
    protected void gamerCourse(MouseEvent event) {
        if (GamerVGamer.getCourses() == -1) { //Игра не начала или закончена
            return;
        }
        if (getGameSymbol().isEmpty()) {
            return;
        }

        Label label = (Label) event.getSource(); //Нажатие на объект Label
        realGamerCourses(label);
        if (GamerVGamer.getCourses() == -1) { //Игра не начала или закончена
            return;
        }
        GamerVGamer.increaseCourse();

        //Переключение имени и символа
        switchNameAndSymbol(getGamerName(), getGameSymbol());
        if (isItComputerCourse() && GamerVGamer.getCourses() != -1) {
            computerCourses();
            switchNameAndSymbol(getGamerName(), getGameSymbol());
        }
    }

    private void realGamerCourses(Label label) {
        label.setText(getGameSymbol()); //Установка игрового символа
        String nodeCoordinates = defineCoordinatesNode(label); //Координаты хода
        int line = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(0))); //Строка
        int column = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(1))); //Колонка
        Optional<Gamer> winner = GamerVGamer.game(line, column);
        defineWinner(winner);
    }

    private void computerCourses() {
        Optional<Gamer> winner = GamerVGamer.game(0, 0);
        setComputerCourseOnBoard();
        defineWinner(winner);
    }

    //принимает координаты, ставит ход на доску
    private void setComputerCourseOnBoard() {
        String coordinates = GamerVGamer.getCurrentCourseCoordinates();
        if (coordinates.isEmpty()) {
            return;
        }
        int line = Integer.parseInt(String.valueOf(coordinates.charAt(0))); //Строка
        int column = Integer.parseInt(String.valueOf(coordinates.charAt(1))); //Колонка
        Label label =  (Label) findLabelByCoordinates(line, column);
        if (label != null) {
            label.setText(getGameSymbol());
        }
        GamerVGamer.increaseCourse();
    }

    private void defineWinner(Optional<Gamer> winner) {
        if (winner.isPresent()) {
            ModalWindowWinner windowWinner = new ModalWindowWinner();
            windowWinner.winnerModalWindow(winner.get().getName());
            GamerVGamer.stopGame();
            return;
        }
        if (GamerVGamer.getCourses() == 100) { //todo заменить константой
            ModalWindowWinner windowWinner = new ModalWindowWinner();
            windowWinner.winnerModalWindow("Ничья!");
            GamerVGamer.stopGame();
        }

    }


    private void switchNameAndSymbol(String name, String symbol) {
        gamerName.setText(" " + name);
        currentSymbol.setText(" " + symbol);
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

    private boolean isItComputerCourse() {
        return isGameAgainstComputer;
    }
}
