package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.*;

import java.util.Optional;
import java.util.Random;

public class GameService {
    protected static Gamer[] arrayOfGamers = new Gamer[2]; //Массив игроков для рандома
    protected static char[] gameSymbol = new char[]{'X', '0'}; //Массив для рандома игрового символа
    protected static final char[] winX = new char[3]; //Массив для определения победителя X
    protected static final char[] win0 = new char[3]; //Массив для определения победителя 0
    protected static Random rdn = new Random();
    private static char[][] gameBoard;

    public static void setGameBoard(int line, int column, char symbol) {
        gameBoard[line][column] = symbol;
    }

    public static char[][] getGameBoard() {
        return gameBoard;
    }

    //Создание игрока 1
    public static Gamer createFirstGamer(String name) {
        char firstGamerSymbol = defineRandomGameSymbol();
        FirstGamer firstGamer = new FirstGamer(name, firstGamerSymbol);
        arrayOfGamers[0] = firstGamer;
        return firstGamer;
    }

    //Создание второго игрока
    public static Gamer createSecondGamer(String name) {
        char secondGamerSymbol = defineRandomGameSymbol();
        if ((arrayOfGamers[0] != null) && (secondGamerSymbol == arrayOfGamers[0].getGameSymbol())
                && (secondGamerSymbol == gameSymbol[0])) {
            secondGamerSymbol = '0';
        } else if ((arrayOfGamers[0] != null) && (secondGamerSymbol == arrayOfGamers[0].getGameSymbol())
                && (secondGamerSymbol == gameSymbol[1])) {
            secondGamerSymbol = 'X';
        }
        SeckondGamer seckondGamer = new SeckondGamer(name, secondGamerSymbol);
        arrayOfGamers[1] = seckondGamer;
        return seckondGamer;
    }

    public static Gamer createComputerGamer(boolean isGameHard, String name) {
        char computerEasySymbol = defineRandomGameSymbol();
        if ((arrayOfGamers[0] != null) && (computerEasySymbol == arrayOfGamers[0].getGameSymbol())
                && (computerEasySymbol == gameSymbol[0])) {
            computerEasySymbol = '0';
        } else if ((arrayOfGamers[0] != null) && (computerEasySymbol == arrayOfGamers[0].getGameSymbol())
                && (computerEasySymbol == gameSymbol[1])) {
            computerEasySymbol = 'X';
        }
        Gamer computerGamer;

        if (isGameHard) {
            computerGamer = new ComputerGamerHard(name, computerEasySymbol, new Random());
        } else {
            computerGamer = new ComputerGamerEasy(name, computerEasySymbol, new Random());
        }
        computerGamer.setComputer(true);
        arrayOfGamers[1] = computerGamer;
        return computerGamer;
    }

    //Определение кто каким символом играет
    private static char defineRandomGameSymbol() {
        int indexOfSymbol = rdn.nextInt(2);
        return gameSymbol[indexOfSymbol];
    }

    //Определение кто первый ходит
    public static Gamer defineWhoFirst(int index) {
        return arrayOfGamers[index];
    }

    //Определение кто первый ходит
    public static int defineWhoFirstIndex() {
        return rdn.nextInt(2);
    }

    //Массив игровая доска
    protected static void prepareGameBoard() {
        gameBoard = new char[3][3];
    }

    protected static void clearGameBoard() {
        gameBoard = null;
    }

    //Проверка наличия победителя по диагоналям
    public static char[] checkWinIfSymInCentralPosition() {
        for (char[] chars : gameBoard) {
            for (int h = 0; h < chars.length; h++) { //Цикл по элементам
                if (gameBoard[1][1] == 'X') { //Если в центре Х
                    winX[0] = 'X';
                    if (gameBoard[0][2] == 'X') { //Проверка диагональных ячеек
                        winX[1] = 'X';
                        if (gameBoard[2][0] == 'X') {
                            winX[2] = 'X';
                            return winX;
                        }
                    } else if (gameBoard[0][0] == 'X') { //Проверка диагональных ячеек
                        winX[1] = 'X';
                        if (gameBoard[2][2] == 'X') {
                            winX[2] = 'X';
                            return winX;
                        }
                    }
                }
                if (gameBoard[1][1] == '0') {
                    win0[0] = '0';
                    if (gameBoard[0][2] == '0') { //Проверка диагональных ячеек
                        win0[1] = '0';
                        if (gameBoard[2][0] == '0') {
                            win0[2] = '0';
                            return win0;
                        }
                    } else if (gameBoard[0][0] == '0') { //Проверка диагональных ячеек
                        win0[1] = '0';
                        if (gameBoard[2][2] == '0') {
                            win0[2] = '0';
                            return win0;
                        }
                    }
                }
            }
        }
        return null;
    }

    //Проверка наличия победителя, если выигрышная комбинация на строках
    public static char[] checkWinnerOnLines() {
        for (int i = 0; i < gameBoard.length; i = i + 1) {
            byte countX = 0;
            byte count0 = 0;
            for (int j = 0, k = 0; j < gameBoard[i].length; j = j + 1, k++) {
                if (gameBoard[i][j] == 'X') {
                    countX += 1;
                    winX[k] = gameBoard[i][j];
                }
                if (gameBoard[i][j] == '0') {
                    count0 += 1;
                    win0[k] = gameBoard[i][j];
                }
                if (countX == 3) {
                    return winX;
                }
                if (count0 == 3) {
                    return win0;
                }
            }
        }
        return null;
    }

    //Проверка наличия победителя, если выигрышная комбинация на столбцах
    public static char[] checkWinnerOnColumns() {
        for (int i = 0; i < gameBoard.length; i = i + 1) {
            byte countX = 0;
            byte count0 = 0;
            for (int j = 0, k = 0; j < gameBoard[i].length; j = j + 1, k++) {
                if (gameBoard[j][i] == 'X') {
                    countX += 1;
                    winX[k] = gameBoard[j][i];
                }
                if (gameBoard[j][i] == '0') {
                    count0 += 1;
                    win0[k] = gameBoard[j][i];
                }
                if (countX == 3) {
                    return winX;
                }
                if (count0 == 3) {
                    return win0;
                }
            }
        }
        return null;
    }

    //Проверка ничьи
    public static boolean defineDraw() {
        if (containsEmpty()) { //Если массив не заполнен
            return false; //Нет ничьи
        }

        byte countEmptyCellar = 0; //Счетчик количества пустых ячеек
        for (int i = 0; i < gameBoard.length; i = i + 1) {
            for (int j = 0; j < gameBoard[i].length; j = j + 1) {
                if (gameBoard[i][j] == ' ') { //Если ячейка пустая
                    countEmptyCellar += 1; // + 1
                }
            }
        }
        //Если количество пустых ячеек 0
        return countEmptyCellar == 0; //ничья
    }

    private static boolean containsEmpty() {
        for (char[] line : gameBoard) {
            for (char symbol : line) {
                if (symbol == '\u0000') {
                    return true;
                }
            }
        }
        return false;
    }


    //Определение есть ли победитель
    public static boolean isThereWinner() {
        char[] checkOne = checkWinIfSymInCentralPosition(); //Поиск победителя по диагоналям
        if (checkOne != null) {
            return true;
        }
        char[] winnerLine = checkWinnerOnLines(); //Поиск победителя на строках
        char[] winnerColumns = checkWinnerOnColumns(); //Поиск победителя на колонках
        if ((winnerLine != null) || (winnerColumns != null)) {
            return true;
        }
        return false;
    }

    //Возвращает объект игрока победителя.
    private static Gamer defineWinnerGamer(char[] symbols) {
        char winSymbol;
        if (symbols[0] == 'X' && symbols[1] == 'X' && symbols[2] == 'X') {
            winSymbol = 'X';
        } else {
            winSymbol = '0';
        }
        if (arrayOfGamers[0].getGameSymbol() == winSymbol) {
            return arrayOfGamers[0];
        }
        return arrayOfGamers[1];

    }

    //Определение есть ли победитель
    public static Optional<Gamer> findWinner() {
        char[] checkOne = checkWinIfSymInCentralPosition(); //Поиск победителя по диагоналям
        if (checkOne != null) { //Если массив не пуст
            return Optional.of(defineWinnerGamer(checkOne)); //Определение конкретного игрока
        }

        char[] winnerLine = checkWinnerOnLines(); //Поиск победителя на строках

        if (winnerLine != null) {
            return Optional.of(defineWinnerGamer(winnerLine));
        }

        char[] winnerColumns = checkWinnerOnColumns(); //Поиск победителя на колонках
        if ((winnerColumns != null)) {
            return Optional.of(defineWinnerGamer(winnerColumns));
        }
        return Optional.empty();
    }
}
