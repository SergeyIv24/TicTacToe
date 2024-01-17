package Gamers;

import Game.Game;

import java.util.Arrays;
import java.util.Random;
//todo алгоритм если противник сделал ход в угл
public class ComputerGamerHard extends Gamer{
    static String[] cornerCages = new String[]{"22", "26", "62", "66"}; //Координаты угловых ячеек
    int[] checkArr = new int[2];
    Random rdn;

    public ComputerGamerHard(String name, char gameSymbol, Random rdn) {
        super(name, gameSymbol);
        this.rdn = rdn;
    }

    public static int[] parseCoordinates(String[] cornerCages, int indexOfCornerCages) {
        int[] coordinates = new int[2];
        char[] arrOfCoordinate;
        String particularCoordinate = cornerCages[indexOfCornerCages];
        arrOfCoordinate = particularCoordinate.toCharArray();
        coordinates[0] = Integer.parseInt(String.valueOf(arrOfCoordinate[0]));
        coordinates[1] = Integer.parseInt(String.valueOf(arrOfCoordinate[1]));
        return coordinates;
    }

    public static int makeRandom() {
        Random rdn = new Random();
        int index = rdn.nextInt(4);
        return index;
    }


    public int[] preventVictoryOfEnemyOnLines() {
        int[] coordinatesGap = new int[2];
        for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false;
            byte countOfSymbol = 0;
            for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 2) {
                if ((Game.getGameBoard()[i][j] != gameSymbol) && (Game.getGameBoard()[i][j] != ' ')) {
                    countOfSymbol += 1;
                }
                if (Game.getGameBoard()[i][j] == ' ') {
                    coordinatesGap[0] = i;
                    coordinatesGap[1] = j;
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) {

                    return coordinatesGap;
                }
            }
        }
        return new int[2];
    }

    public int[] preventVictoryOfEnemyOnColumns() {
        int[] coordinatesGap = new int[2];
        for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false;
            byte countOfSymbol = 0;
            for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 2) {
                if ((Game.getGameBoard()[j][i] != gameSymbol) && (Game.getGameBoard()[j][i] != ' ')) {
                    countOfSymbol += 1;
                }
                if (Game.getGameBoard()[j][i] == ' ') {
                    coordinatesGap[0] = j;
                    coordinatesGap[1] = i;
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) {

                    return coordinatesGap;
                }
            }
        }
        return new int[2];
    }

    public int[] searchVictoryCombinationsOnLines() {
        int[] coordinatesGap = new int[2];
        for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false;
            byte countOfSymbol = 0;
            for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 2) {
                if ((Game.getGameBoard()[i][j] == gameSymbol) && (Game.getGameBoard()[i][j] != ' ')) {
                    countOfSymbol += 1;
                }
                if (Game.getGameBoard()[i][j] == ' ') {
                    coordinatesGap[0] = i;
                    coordinatesGap[1] = j;
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) {

                    return coordinatesGap;
                }
            }
        }
        return new int[2];
    }

    public int[] searchVictoryCombinationsOnColumns() {
        int[] coordinatesGap = new int[2];
        for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
            boolean isThereEmptyCellar = false;
            byte countOfSymbol = 0;
            for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 2) {
                if ((Game.getGameBoard()[j][i] == gameSymbol) && (Game.getGameBoard()[j][i] != ' ')) {
                    countOfSymbol += 1;
                }
                if (Game.getGameBoard()[j][i] == ' ') {
                    coordinatesGap[0] = j;
                    coordinatesGap[1] = i;
                    isThereEmptyCellar = true;
                }
                if ((countOfSymbol == 2) && (isThereEmptyCellar)) {

                    return coordinatesGap;
                }
            }
        }
        return new int[2];
    }



        //Определяет куда делать ход
    public int[] algorithms() {
        boolean isThereSymbolInCorners = ((Game.getGameBoard()[2][2] != ' ') && (Game.getGameBoard()[2][6] != ' ')
                && (Game.getGameBoard()[6][2] != ' ') && (Game.getGameBoard()[6][6] != ' '));
        int[] arrOfIndexes = new int[2]; //Строка, столбец
        int[] checkGapOnLines = preventVictoryOfEnemyOnLines();
        int[] checkGapOnColumns = preventVictoryOfEnemyOnColumns();


        if(!Arrays.equals(checkGapOnLines, checkArr)) {
            return checkGapOnLines;
        }
        if (!Arrays.equals(checkGapOnColumns, checkArr)) {
            return checkGapOnColumns;
        }

        if (Game.getGameBoard()[4][4] == ' ') {
            arrOfIndexes[0] = 4;
            arrOfIndexes[1] = 4;
            return arrOfIndexes;
        }

        if (isThereSymbolInCorners) {
            int[] checkWinGapsOnLines = searchVictoryCombinationsOnLines();
            int[] checkWinGapsOnColumns = searchVictoryCombinationsOnColumns();

            if (!Arrays.equals(checkWinGapsOnLines, checkArr)) {
                return checkWinGapsOnLines;
            } else if (!Arrays.equals(checkWinGapsOnColumns, checkArr)) {
                return checkWinGapsOnColumns;
            } else {
                for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
                    for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 1) {
                        if (Game.getGameBoard()[i][j] == ' ') {
                            arrOfIndexes[0] = i;
                            arrOfIndexes[1] = j;
                            return arrOfIndexes;
                        }
                    }
                }
            }
        }
        int[] coord = parseCoordinates(cornerCages, makeRandom());
        arrOfIndexes[0] = coord[0];
        arrOfIndexes[1] = coord[1];
        return arrOfIndexes;

    }

    @Override
    public int selectColumn() {
        int[] coordinates = algorithms();
        return coordinates[1];
    }

    @Override
    public int selectLine() {
        int[] coordinates = algorithms();
        return coordinates[0];
    }

    //Делает ход, ставит символ
    @Override
    public boolean addSymbol(int line, int column) {
        if (Game.getGameBoard()[line][column] == ' ') {
            Game.setGameBoard(line, column, gameSymbol);
            return true;
        } else {
            selectColumn();
            selectLine();
            return false;
        }
    }

}
