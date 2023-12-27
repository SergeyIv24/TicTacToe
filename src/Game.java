import java.util.Scanner;
import java.util.Random;

//todo рандом выбора символа

public class Game {
    private static final char[][] gameBoard = new char[7][8]; //Пустой массив игровой доски
    private static final char[] winX = new char[3]; //Массив для определения победителя X
    private static final char[] win0 = new char[3]; //Массив для определения победителя 0
    private static Gamer[] arrayOfGamers; //Массив игроков для рандома
    private static Random rdn;
    private final Scanner scan;

    public Game(){
        gameBoard[0] = new char[]{' ', '|', '1', '|', '2', '|', '3', '|'}; //Первая строка игровой доски
        arrayOfGamers = new Gamer[2];
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

    //Создание игрока 1
    public FirstGamer createFirsGamer() {
        System.out.println("Введите Ваше имя: ");
        String nameOfFirstUser = scan.next();
        System.out.println("Введите каким символом будете играть:\n");
        System.out.println("1 - X, 2 - 0");
        int user1Select = scan.nextInt();
        char user1SelectCh = ' ';
        if ((user1Select == 1) && (arrayOfGamers != null)) {
            if ((arrayOfGamers[1] != null) && (arrayOfGamers[1].gameSymbol == 'X')) {
                System.out.println("Символ \"X\" уже занят. Ваш символ: \"0\"");
                user1SelectCh = '0';
            } else {
                user1SelectCh = 'X';
            }
        }

        if (user1Select == 2) {
            if ((arrayOfGamers[1] != null) && (arrayOfGamers[1].gameSymbol == '0')) {
                System.out.println("Символ \"0\" уже занят. Ваш символ: \"X\"");
                user1SelectCh = 'X';
            } else {
                user1SelectCh = '0';
            }

        }

        System.out.println("Игрок 1: " + nameOfFirstUser + ".");
        System.out.println("Выбранный символ:" + user1SelectCh);

        FirstGamer firstGamer = new FirstGamer(nameOfFirstUser, user1SelectCh);

        arrayOfGamers[0] = firstGamer;
        return firstGamer;
    }

    //Создание игрока 2
    public SeckondGamer createSecondGamer() {
        System.out.println("Введите Ваше имя: ");
        String nameOfSecondUser = scan.next();
        System.out.println("Введите каким символом будете играть:\n");
        System.out.println("1 - X, 2 - 0");

        int user2Select = scan.nextInt();
        char user2SelectCh = ' ';

        if (user2Select == 1) {
            if ((arrayOfGamers[0] != null) && (arrayOfGamers[0].gameSymbol == 'X')) {
                System.out.println("Символ \"X\" уже занят. Ваш символ: \"0\"");
                user2SelectCh = '0';
            } else {
                user2SelectCh = 'X';
            }
        }

        if (user2Select == 2) {
            if ((arrayOfGamers[0] != null) && (arrayOfGamers[1].gameSymbol == '0')) {
                System.out.println("Символ \"0\" уже занят. Ваш символ: \"X\"");
                user2SelectCh = 'X';
            } else {
                user2SelectCh = '0';
            }

        }
        System.out.println("Игрок 2: " + nameOfSecondUser + ".");
        System.out.println("Выбранный символ:" + user2SelectCh);

        SeckondGamer seckondGamer = new SeckondGamer(nameOfSecondUser, user2SelectCh);

        arrayOfGamers[1] = seckondGamer;
        return seckondGamer;

    }

    //Определение кто первый ходит
    public static Gamer defineWhoFirst() {
        int indexOfGamer = rdn.nextInt(2);
        System.out.println("Игрок: " + arrayOfGamers[indexOfGamer].getName() + ". Ходит первым!");
        return arrayOfGamers[indexOfGamer];
    }

    public static boolean checkAmountOfSymb() {
        boolean checkAmount = false;
        int amountX = 0;
        int amountO = 0;
        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    amountX += 1;
                } else if (aChar == '0') {
                    amountO += 1;
                }
            }
        }
        if (amountX >= 3) {
            return true;
        } else if (amountO >= 3) {
            return true;
        }
        return false;
    }

    //Проверка ходов, если игровой символ в центральной позиции
    public static char[] checkWinIfSymInCentralPosition() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int h = 0; h < gameBoard[i].length; h++) { //Цикл по элементам
                if (gameBoard[4][4] == 'X') { //Если в центре Х
                    winX[0] = 'X';
                    if (gameBoard[2][6] == 'X') { //Проверка диагональных ячеек
                        winX[1] = 'X';
                        if (gameBoard[6][2] == 'X') {
                            winX[2] = 'X';
                        }
                    } else if (gameBoard[2][2] == 'X'){ //Проверка дигональных ячеек
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
                        }
                    } else if (gameBoard[2][2] == '0'){ //Проверка диагональных ячеек
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

    //Проверка ходом, если игровой символ в любой ячейке
    public static char[] checkWinWhenSymInDifferentPos() {
        int amountX = 0;
        int j;
        int k;
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            for (j = 2, k = 0; j < gameBoard[i].length; j = j + 2, k++) {
                if (gameBoard[i][j] == 'X') {
                    winX[k] = 'X';
                    amountX += 1;
                } else {
                    winX[0] = ' ';
                    winX[1] = ' ';
                    winX[2] = ' ';
                    break;
                }
            }
            if (amountX == winX.length) {
                return winX;
            }

        }
        int amountO = 0;
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            for (j = 2, k = 0; j < gameBoard[i].length; j = j + 2, k++) {
                if (gameBoard[i][j] == '0') {
                    win0[k] = '0';
                    amountO += 1;
                } else {
                    win0[0] = ' ';
                    win0[1] = ' ';
                    win0[2] = ' ';
                    break;
                }
            }
            if (amountO == win0.length) {
                return win0;
            }
        }
        return null;

    }

    //Определение есть ли победитель
    public static boolean isThereWinner() {
        char[] checkOne = checkWinIfSymInCentralPosition();

        if (checkOne != null) {
            return true;
        }
        char[] checkTwo = checkWinWhenSymInDifferentPos();
        if (checkTwo != null) {
            return true;
        }
        return false;
    }


    //Игра
    public void game() {
        FirstGamer first = createFirsGamer();
        SeckondGamer second = createSecondGamer();
        createPrimaryGameBoard();
        printGameBoard();

        Gamer whoFirst = defineWhoFirst();

        while (true) { //Пока нет победителя
            if (whoFirst.equals(first)) { //Если первый ходит игрок 1
                System.out.println("Игрок 1: ");
                //Бесконечный цикл для неверного хода
                while (!first.addSymbol(first.selectColumn(), first.selectLine())) {
                    first.addSymbol(first.selectColumn(), first.selectLine()); //Ход
                }

                printGameBoard(); //Вывод доски с ходом
                if (isThereWinner()) { //Если игрок 1 победил
                    System.out.println("Победил игрок: " + first.name + "!");
                    break;
                }
                System.out.println("Игрок 2: ");

                while (!second.addSymbol(second.selectColumn(), second.selectLine())) {
                    second.addSymbol(second.selectColumn(), second.selectLine()); //Ход
                }

                printGameBoard(); //Вывод доски
                if (isThereWinner()) { //Если второй игрок победил
                    System.out.println("Победил игрок: " + second.name + "!");
                    break;
                }
            } else { //Если первый ходит игрок 2
                System.out.println("Игрок 2: ");

                while (!second.addSymbol(second.selectColumn(), second.selectLine())) {
                    second.addSymbol(second.selectColumn(), second.selectLine()); //Ход
                }

                printGameBoard();
                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + second.name + "!");
                    break;
                }
                System.out.println("Игрок 1: ");

                while (!first.addSymbol(first.selectColumn(), first.selectLine())) {
                    first.addSymbol(first.selectColumn(), first.selectLine()); //Ход
                }

                printGameBoard();
                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + first.name + "!");
                    break;
                }
            }
        }
    }

}
