package my.project.TicTacToe.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import my.project.TicTacToe.GUI.TicTacToeApp;
import my.project.TicTacToe.Game.Constance;
import my.project.TicTacToe.Game.Game;
import my.project.TicTacToe.Gamers.Gamer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Label currentGamerName;

    @FXML
    private Label currentSymbol;

    @FXML
    private GridPane gameBoard;

    @FXML
    private Button menu;

    @FXML
    private Button revenge;

    private final Node[][] arrGridPane = new Node[3][3]; //Массив элементов GridPane. Создан для взаимодействия с
    //каждым элементом GridPane.
    private boolean isGameAgainstComputer; //Режим игры. По умолчанию игра против игрока.
    private boolean isGameHard; //Сложно ПК для игры. По умолчанию простой.

    public void setIsGameAgainstComputer(boolean againstComputer) {
        isGameAgainstComputer = againstComputer;
    }

    public void setIsGameHard(boolean hard) {
        isGameHard = hard;
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

    //Определение координат узла в GridPane
    private String defineCoordinatesNode(Node node) {
        int j = 0;
        int i = 0;
        while ((i != 3) && (j < arrGridPane[i].length)) {
            if (node == arrGridPane[i][j]) {
                return String.valueOf(i) + j; //i - строка, j - колонка
            }
            if (j++ == arrGridPane[i].length - 1) {
                i++;
                j = 0;
            }
        }
        return "";
    }

    //Метод поиска Node(Label) в массиве GridPane
    private Node findLabelByCoordinates(int line, int column) {
        try {
            return arrGridPane[line][column];
        } catch (Exception e) {
            return null;
        }
    }

    @FXML
    protected void goExit() {
        System.exit(0);
    }

    @FXML
    protected void goRevenge() throws IOException {
        FXMLLoader loaderNextScene = new FXMLLoader(this.getClass().getResource("/game.fxml"));
        Stage stage = (Stage) revenge.getScene().getWindow();
        Parent root = loaderNextScene.load();
        GameController controller = loaderNextScene.getController();
        controller.setIsGameAgainstComputer(isGameAgainstComputer);
        controller.setIsGameHard(isGameHard);
        Scene scene = new Scene(root, my.project.TicTacToe.GUI.Constance.windowWeight,
                my.project.TicTacToe.GUI.Constance.windowHeight);
        stage.setScene(scene);
    }

    @FXML
    protected void goMenu() throws IOException {
        NamesAndSymbolsController.resetSettings();
        Game.stopGame();
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("/menu.fxml"));
        Stage stage = (Stage) menu.getScene().getWindow();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, my.project.TicTacToe.GUI.Constance.windowWeight, my.project.TicTacToe.GUI.Constance.windowHeight);
        stage.setScene(scene);
    }

    private boolean checkNotEmptyCell(Label label) {
        return !label.getText().isEmpty();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game.createGame();
        fillGridArray(); //Заполнение массива GridPane объектами
        currentGamerName.setText(Game.getFirstCourseGamer().getName());
        currentSymbol.setText(String.valueOf(Game.getFirstCourseGamer().getGameSymbol()));
        if (Game.getFirstCourseGamer().getComputer()) { //Если игрок компьютер
            computerCourses(); //Ход компьютера
        }
    }

    @FXML
    protected void gamerCourse(MouseEvent event) {
        if (!Game.checkAbilityToContinue()) { //Если игра закончена или не начата.
            return;
        }
        if (getGameSymbol().isEmpty()) {
            return;
        }
        Label label;
        try {
            label = (Label) event.getSource(); //Нажатие на объект Label
        } catch (ClassCastException e) {
            return;
        }

        if (checkNotEmptyCell(label)) {
            return;
        }

        realGamerCourses(label); //Ход игрока

        if (!Game.checkAbilityToContinue()) { //Если игра закончена или не начата.
            return;
        }

        //Если игра не закончена, то следующий ход компьютера
        if (isGameAgainstComputer) {
            computerCourses();
        }
    }

    private void realGamerCourses(Label label) {
        if (Game.getCourses() == -1) {
            return;
        }
        label.setText(getGameSymbol()); //Установка игрового символа на поле
        String nodeCoordinates = defineCoordinatesNode(label); //Координаты хода
        int line = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(0))); //Строка
        int column = Integer.parseInt(String.valueOf(nodeCoordinates.charAt(1))); //Колонка
        Optional<Gamer> winner = Game.game(line, column); //Ход
        defineWinner(winner); //После хода, определение победителя
        Game.increaseCourse();
        switchNameAndSymbol(getCurrentGamerName(), getGameSymbol()); //Смена текущего игрока и символа
    }

    private void computerCourses() {
        if (Game.getCourses() == -1) {
            return;
        }

        Optional<Gamer> winner = Game.game(0, 0);
        setComputerCourseOnBoard();
        defineWinner(winner);
        Game.increaseCourse();
        switchNameAndSymbol(getCurrentGamerName(), getGameSymbol()); //Смена текущего игрока и символа
    }

    //принимает координаты, ставит ход на доску
    private void setComputerCourseOnBoard() {
        String coordinates = Game.getCurrentCourseCoordinates();
        if (coordinates.isEmpty()) {
            return;
        }
        int line = Integer.parseInt(String.valueOf(coordinates.charAt(0))); //Строка
        int column = Integer.parseInt(String.valueOf(coordinates.charAt(1))); //Колонка
        Label label = (Label) findLabelByCoordinates(line, column);
        if (label != null) {
            label.setText(getGameSymbol());
        }
    }

    private void defineWinner(Optional<Gamer> winner) {
        if (winner.isPresent()) {
            ModalWindowWinner windowWinner = new ModalWindowWinner();
            windowWinner.winnerModalWindow(winner.get().getName());
            Game.stopGame();
        }
        if (Game.getCourses() == Constance.EXIT_NUMBER_FOR_DRAW) {
            ModalWindowWinner windowWinner = new ModalWindowWinner();
            windowWinner.winnerModalWindow("Ничья!");
            Game.stopGame();
        }
    }

    private void switchNameAndSymbol(String name, String symbol) {
        currentGamerName.setText(" " + name);
        currentSymbol.setText(" " + symbol);
    }

    //Определение текущего символа
    private String getGameSymbol() {
        if (Game.whoseCourse().isPresent()) {
            return String.valueOf(Game.whoseCourse().get().getGameSymbol());
        }
        return "";
    }

    private String getCurrentGamerName() {
        if (Game.whoseCourse().isPresent()) {
            return String.valueOf(Game.whoseCourse().get().getName());
        }
        return "";
    }
}
