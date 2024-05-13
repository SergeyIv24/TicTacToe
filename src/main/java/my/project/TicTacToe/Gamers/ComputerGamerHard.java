package my.project.TicTacToe.Gamers;
import my.project.TicTacToe.Game.*;
import java.util.Arrays;
import java.util.Random;

public class ComputerGamerHard extends Gamer{
    static String[] cornerCages = new String[]{"22", "26", "62", "66"}; //Координаты угловых ячеек
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
        for (int i = 2; i < GameService.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false; //Если есть пустая ячейка
            byte countOfSymbol = 0; //Выигрышных символа противника должно быть 2
            for (int j = 2; j < GameService.getGameBoard()[i].length; j = j + 2) {
                //Если это не свой же символ и не пустой.
                if ((GameService.getGameBoard()[i][j] != gameSymbol) && (GameService.getGameBoard()[i][j] != ' ')) {
                    countOfSymbol += 1; //подсчет ячеек
                }
                if (GameService.getGameBoard()[i][j] == ' ') { // Если встретил пустую ячейку
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
        for (int i = 2; i < GameService.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false; //Если есть пустая ячейка
            byte countOfSymbol = 0; //Выигрышных символа противника должно быть 2
            for (int j = 2; j < GameService.getGameBoard()[i].length; j = j + 2) {
                if ((GameService.getGameBoard()[j][i] != gameSymbol) && (GameService.getGameBoard()[j][i] != ' ')) {
                    countOfSymbol += 1; //подсчет ячеек
                }
                if (GameService.getGameBoard()[j][i] == ' ') {
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
        for (int i = 2; i < GameService.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false; //Наличие пустой ячейки
            byte countOfSymbol = 0;
            for (int j = 2; j < GameService.getGameBoard()[i].length; j = j + 2) {
                //Если свой же символ и если не пустая ячейка
                if ((GameService.getGameBoard()[i][j] == gameSymbol) && (GameService.getGameBoard()[i][j] != ' ')) {
                    countOfSymbol += 1; //Подсчет количества ячеек подряд
                }
                if (GameService.getGameBoard()[i][j] == ' ') {
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
        for (int i = 2; i < GameService.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false;
            byte countOfSymbol = 0;
            for (int j = 2; j < GameService.getGameBoard()[i].length; j = j + 2) {
                if ((GameService.getGameBoard()[j][i] == gameSymbol) && (GameService.getGameBoard()[j][i] != ' ')) {
                    countOfSymbol += 1; //Подсчет количества ячеек подряд
                }
                if (GameService.getGameBoard()[j][i] == ' ') {
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
        boolean isThereSymbolInCorners = ((GameService.getGameBoard()[2][2] != ' ') && (GameService.getGameBoard()[2][6] != ' ')
                && (GameService.getGameBoard()[6][2] != ' ') && (GameService.getGameBoard()[6][6] != ' ')); //Если нет диагональных ячеек
        int[] arrOfIndexes = new int[2]; //Строка, столбец для добавления символа

        //Проверка, что противник не побеждает
        int[] checkGapOnLines = preventVictoryOfEnemyOnLines(); //Проверка выигрышных комбинаций противника
        int[] checkGapOnColumns = preventVictoryOfEnemyOnColumns(); //Проверка выигрышных комбинаций противника

        if(!Arrays.equals(checkGapOnLines, checkArr)) {
            return checkGapOnLines; //Если массив не пустой, ставить по его координатам
        }
        if (!Arrays.equals(checkGapOnColumns, checkArr)) {
            return checkGapOnColumns; //Если массив не пустой, ставить по его координатам
        }

        // Если центр пуст, всегда ходить в него
        if (GameService.getGameBoard()[4][4] == ' ') {
            arrOfIndexes[0] = 4; //Координаты центра
            arrOfIndexes[1] = 4; //Координаты центра
            return arrOfIndexes;
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
                for (int i = 2; i < GameService.getGameBoard().length; i = i + 2) {
                    for (int j = 2; j < GameService.getGameBoard()[i].length; j = j + 1) {
                        if (GameService.getGameBoard()[i][j] == ' ') {
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
/*    @Override
    public boolean addSymbol(int line, int column) {
        if (column == -1) {
            column = selectColumn();
            line = selectLine();
        }
        if (GameService.getGameBoard()[line][column] == ' ') {
            GameService.setGameBoard(line, column, gameSymbol);
            return true;
        } else { //Ход пока не поставит в пустую ячейку
            selectColumn();
            selectLine();
            return false;
        }
    }*/

}
