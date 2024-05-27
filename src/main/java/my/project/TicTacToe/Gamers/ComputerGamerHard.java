package my.project.TicTacToe.Gamers;

import my.project.TicTacToe.Game.*;

import java.util.Arrays;
import java.util.Random;

public class ComputerGamerHard extends Gamer {
    static String[] cornerCages = new String[]{"00", "02", "20", "22"}; //Координаты угловых ячеек
    int[] checkArr = new int[2]; //Проверочный массив 0
    Random rdn;

    public ComputerGamerHard(String name, char gameSymbol, Random rdn) {
        super(name, gameSymbol);
        this.rdn = rdn;
    }

    //Перевод определенной координаты угловых ячеек из строки в массив чисел
    public static int[] parseCoordinates(String[] cornerCages, int indexOfCornerCages) {
        int[] coordinates = new int[2]; //Массив для хранения числовых значений координат
        char[] arrOfCoordinate;
        String particularCoordinate = cornerCages[indexOfCornerCages];
        arrOfCoordinate = particularCoordinate.toCharArray();
        coordinates[0] = Integer.parseInt(String.valueOf(arrOfCoordinate[0]));
        coordinates[1] = Integer.parseInt(String.valueOf(arrOfCoordinate[1]));
        return coordinates;
    }

    //Метод для рандома
    public static int makeRandom() {
        Random rdn = new Random();
        return rdn.nextInt(4);
    }

    //Предотвращает победу противника, если у игрока есть выигрышная комбинация. Поиск по строкам массива
    //Например, когда на одной линии 00...
    public int[] preventVictoryOfEnemyOnLines() {
        int[] coordinatesGap = new int[2]; //Массив для хранения координат пустой ячейки, куда нужно поставить символ
        for (int i = 0; i < GameService.getGameBoard().length; i = i + 1) {
            boolean isThereEmptyCellar = false; //Если есть пустая ячейка
            byte countOfSymbol = 0; //Выигрышных символа противника должно быть 2
            for (int j = 0; j < GameService.getGameBoard()[i].length; j = j + 1) {
                //Если это не свой же символ и не пустой.
                if ((GameService.getGameBoard()[i][j] != gameSymbol) && (GameService.getGameBoard()[i][j] != '\u0000')) {
                    countOfSymbol += 1; //подсчет ячеек
                }
                if (GameService.getGameBoard()[i][j] == '\u0000') { // Если встретил пустую ячейку
                    coordinatesGap[0] = i; //Запись координат пустой ячейки
                    coordinatesGap[1] = j; //Запись координат пустой ячейки
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) { //Если есть пустая ячейка и есть 2 заполненные
                    return coordinatesGap; //ячейки рядом
                }
            }
        }
        return new int[2]; //Если все условия не сработали - массив 0
    }

    //Предотвращает победу противника, если у игрока есть выигрышная комбинация. Поиск по столбцам массива
    public int[] preventVictoryOfEnemyOnColumns() {
        int[] coordinatesGap = new int[2]; //Массив для хранения координат пустой ячейки, куда нужно поставить символ
        for (int i = 0; i < GameService.getGameBoard().length; i = i + 1) {
            boolean isThereEmptyCellar = false; //Если есть пустая ячейка
            byte countOfSymbol = 0; //Выигрышных символа противника должно быть 2
            for (int j = 0; j < GameService.getGameBoard()[i].length; j = j + 1) {
                if ((GameService.getGameBoard()[j][i] != gameSymbol) && (GameService.getGameBoard()[j][i] != '\u0000')) {
                    countOfSymbol += 1; //подсчет ячеек
                }
                if (GameService.getGameBoard()[j][i] == '\u0000') {
                    coordinatesGap[0] = j; //Запись координат пустой ячейки
                    coordinatesGap[1] = i; //Запись координат пустой ячейки
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) { //Если есть пустая ячейка и есть 2 заполненные

                    return coordinatesGap;
                }
            }
        }
        return new int[2]; //Если все условия не сработали - массив 0
    }

    //Поиск собственных выигрышных комбинаций по строкам
    public int[] searchVictoryCombinationsOnLines() {
        int[] coordinatesGap = new int[2]; //Массив для хранения координат пустой ячейки, куда надо ходить
        for (int i = 0; i < GameService.getGameBoard().length; i = i + 1) {
            boolean isThereEmptyCellar = false; //Наличие пустой ячейки
            byte countOfSymbol = 0;
            for (int j = 0; j < GameService.getGameBoard()[i].length; j = j + 1) {
                //Если свой же символ и если не пустая ячейка
                if ((GameService.getGameBoard()[i][j] == gameSymbol) && (GameService.getGameBoard()[i][j] != '\u0000')) {
                    countOfSymbol += 1; //Подсчет количества ячеек подряд
                }
                if (GameService.getGameBoard()[i][j] == '\u0000') {
                    coordinatesGap[0] = i; //Запись координат
                    coordinatesGap[1] = j; //Запись координат
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) { //Если 2 ячейки рядом и есть пустая
                    return coordinatesGap; //Координаты ячейки для хода
                }
            }
        }
        return new int[2]; //Если условия не сработали массив 0
    }

    //Поиск собственных выигрышных комбинаций по столбцам
    public int[] searchVictoryCombinationsOnColumns() {
        int[] coordinatesGap = new int[2]; //Массив для хранения координат пустой ячейки, куда надо ходить
        for (int i = 0; i < GameService.getGameBoard().length; i = i + 1) {
            boolean isThereEmptyCellar = false;
            byte countOfSymbol = 0;
            for (int j = 0; j < GameService.getGameBoard()[i].length; j = j + 1) {
                if ((GameService.getGameBoard()[j][i] == gameSymbol) && (GameService.getGameBoard()[j][i] != '\u0000')) {
                    countOfSymbol += 1; //Подсчет количества ячеек подряд
                }
                if (GameService.getGameBoard()[j][i] == '\u0000') {
                    coordinatesGap[0] = j; //Запись координат
                    coordinatesGap[1] = i; //Запись координат
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) { //Если 2 ячейки рядом и есть пустая
                    return coordinatesGap; //Координаты ячейки для хода
                }
            }
        }
        return new int[2]; //Если условия не сработали массив 0
    }


    //Алгоритм хода компьютера
    public int[] algorithms() {
        int[] arrOfIndexes = new int[2]; //Строка, столбец для добавления символа
        // Если центр пуст, всегда ходить в него
        if (GameService.getGameBoard()[1][1] == '\u0000') {
            arrOfIndexes[0] = 1; //Координаты центра
            arrOfIndexes[1] = 1; //Координаты центра
            return arrOfIndexes;
        }
        boolean isThereSymbolInCorners = ((GameService.getGameBoard()[0][0] != '\u0000') && (GameService.getGameBoard()[0][2] != '\u0000')
                && (GameService.getGameBoard()[2][0] != '\u0000') && (GameService.getGameBoard()[2][2] != '\u0000')); //Если нет диагональных ячеек


        //Проверка, что противник не побеждает
        int[] checkGapOnLines = preventVictoryOfEnemyOnLines(); //Проверка выигрышных комбинаций противника
        int[] checkGapOnColumns = preventVictoryOfEnemyOnColumns(); //Проверка выигрышных комбинаций противника

        if (!Arrays.equals(checkGapOnLines, checkArr)) {
            return checkGapOnLines; //Если массив не пустой, ставить по его координатам
        }
        if (!Arrays.equals(checkGapOnColumns, checkArr)) {
            return checkGapOnColumns; //Если массив не пустой, ставить по его координатам
        }


        //Если все угла заняты
        if (isThereSymbolInCorners) {
            int[] checkWinGapsOnLines = searchVictoryCombinationsOnLines(); //Проверка своих выигрышных комбинаций
            int[] checkWinGapsOnColumns = searchVictoryCombinationsOnColumns(); //Проверка своих выигрышных комбинаций

            if (!Arrays.equals(checkWinGapsOnLines, checkArr)) {
                return checkWinGapsOnLines; //Если массив не пустой, ставить по его координатам
            } else if (!Arrays.equals(checkWinGapsOnColumns, checkArr)) {
                return checkWinGapsOnColumns; //Если массив не пустой, ставить по его координатам
            } else { //Во всех остальных случаях, поиск любой пустой ячейки и ход в нее
                for (int i = 0; i < GameService.getGameBoard().length; i = i + 1) {
                    for (int j = 0; j < GameService.getGameBoard()[i].length; j = j + 1) {
                        if (GameService.getGameBoard()[i][j] == '\u0000') {
                            arrOfIndexes[0] = i;
                            arrOfIndexes[1] = j;
                            return arrOfIndexes;
                        }
                    }
                }
            }
        }
        //Если хотя одна угловая ячейка свободна - ход в нее.
        int[] coord = parseCoordinates(cornerCages, makeRandom());
        arrOfIndexes[0] = coord[0];
        arrOfIndexes[1] = coord[1];
        return arrOfIndexes;

    }

    public int selectColumn() {
        int[] coordinates = algorithms(); //Вызов алгоритма для определения колонки
        return coordinates[1];
    }

    public int selectLine() {
        int[] coordinates = algorithms(); // Вызов алгоритма для определения координат строки
        return coordinates[0];
    }

    //Делает ход, ставит символ
    @Override
    public String addSymbol(int line, int column) {
        int i = 0; //Количество попыток сделать ход
        do {
            line = selectLine(); //Выбор строки
            column = selectColumn(); //Выбор колонки
            i++;
            if (i > 30) { //Если сделал больше 30 попыток выход
                break;
            }
        } while (GameService.getGameBoard()[line][column] != '\u0000'); //Проверка не займет ли чужую клетку
        GameService.setGameBoard(line, column, gameSymbol); //Установка символа в массив
        return "" + line + column;
    }
}
