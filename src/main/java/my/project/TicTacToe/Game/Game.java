package my.project.TicTacToe.Game;
import my.project.TicTacToe.Gamers.Gamer;

import java.util.Scanner;
import java.util.Random;

public class Game {
    protected static final char[][] gameBoard = new char[7][8]; //Пустой массив игровой доски
    protected static final char[] winX = new char[3]; //Массив для определения победителя X
    protected static final char[] win0 = new char[3]; //Массив для определения победителя 0
    protected static Gamer[] arrayOfGamers; //Массив игроков для рандома
    protected static char[] gameSymbol; //Массив для рандома игрового символа
    protected static Random rdn;
    protected final Scanner scan;

    public Game(){
        gameBoard[0] = new char[]{'#', '|', '1', '|', '2', '|', '3', '|'}; //Первая строка игровой доски
        arrayOfGamers = new Gamer[2];
        gameSymbol = new char[]{'X', '0'};
        scan = new Scanner(System.in);
        rdn = new Random();
    }

    public static char[][] getGameBoard(){
        return gameBoard;
    }


    public static void setGameBoard(int line, int column, char symbol) {
        gameBoard[line][column] = symbol;
    }

    //Создание первичной игровой доски
    public static void createPrimaryGameBoard() {
        char j = 'A';
        for (int i = 1; i < gameBoard.length; i++) {
            if (i == 2) { //Буквы по своим позициям
                j = 'A';
            } else if (i == 4) {
                j = 'B';
            } else if (i == 6) {
                j = 'C';
            }
            for (int k = 0; k < gameBoard[i].length; k++) {
                if ((k == 0) & (i % 2 == 0)) {
                    gameBoard[i][k] = j;
                } else if (i % 2 != 0) {
                    gameBoard[i][k] = '-';
                } else if (k % 2 != 0) {
                    gameBoard[i][k] = '|';
                } else {
                    gameBoard[i][k] = ' ';
                }
            }
        }
    }

    //Вывод игровой доски
    public static void printGameBoard() {
        for (char[] chars : gameBoard) {
            System.out.println(chars);
        }
    }



    //Определение кто первый ходит
    public static Gamer defineWhoFirst() {
        int indexOfGamer = rdn.nextInt(2);
        System.out.println("Игрок: " + arrayOfGamers[indexOfGamer].getName() + ". Ходит первым!");
        return arrayOfGamers[indexOfGamer];
    }

    //Определение кто каким символом играет
    public static char defineRandomGameSymbol() {
        int indexOfSymbol = rdn.nextInt(2);
        return gameSymbol[indexOfSymbol];
    }


    //Проверка наличия победителя по диагоналям
    public static char[] checkWinIfSymInCentralPosition() {
        for (char[] chars : gameBoard) {
            for (int h = 0; h < chars.length; h++) { //Цикл по элементам
                if (gameBoard[4][4] == 'X') { //Если в центре Х
                    winX[0] = 'X';
                    if (gameBoard[2][6] == 'X') { //Проверка диагональных ячеек
                        winX[1] = 'X';
                        if (gameBoard[6][2] == 'X') {
                            winX[2] = 'X';
                            return winX;
                        }
                    } else if (gameBoard[2][2] == 'X') { //Проверка диагональных ячеек
                        winX[1] = 'X';
                        if (gameBoard[6][6] == 'X') {
                            winX[2] = 'X';
                            return winX;
                        }
                    }
                }
                if (gameBoard[4][4] == '0') {
                    win0[0] = '0';
                    if (gameBoard[2][6] == '0') { //Проверка диагональных ячеек
                        win0[1] = '0';
                        if (gameBoard[6][2] == '0') {
                            win0[2] = '0';
                            return win0;
                        }
                    } else if (gameBoard[2][2] == '0') { //Проверка диагональных ячеек
                        win0[1] = '0';
                        if (gameBoard[6][6] == '0') {
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
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            byte countX = 0;
            byte count0 = 0;
            for (int j = 2, k = 0; j < gameBoard[i].length; j = j + 2, k++) {
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
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            byte countX = 0;
            byte count0 = 0;
            for (int j = 2, k = 0; j < gameBoard[i].length; j = j + 2, k++) {
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
        byte countEmptyCellar = 0; //Счетчик количества пустых ячеек
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            for (int j = 2; j < gameBoard[i].length; j = j + 2) {
                if (gameBoard[i][j] == ' ') { //Если ячейка пустая
                    countEmptyCellar += 1; // + 1
                }
            }
        }
        if (countEmptyCellar == 0) { //Если количество пустых ячеек 0
            return false; //ничья
        }
        return true; //Нет ничьи
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
}