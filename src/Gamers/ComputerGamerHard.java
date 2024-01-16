package Gamers;

import Game.Game;
import java.util.Random;

public class ComputerGamerHard extends Gamer{
    static String[] cornerCages = new String[]{"22", "26", "62", "66"}; //Координаты угловых ячеек

    public ComputerGamerHard(String name, char gameSymbol) {
        super(name, gameSymbol);
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


    public int[] checkGapBetweenSymbolsOnLines() {
        boolean isThereEmptyCellar = false;
        int[] coordinatesGap = new int[2];
        for (int i = 2; i <= Game.getGameBoard().length; i = i + 2) {
            byte countOfSymbol = 0;
            for (int j = 2; j <= Game.getGameBoard()[i].length; j = j + 2) {
                if (Game.getGameBoard()[i][j] == '0') {
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

    public int[] checkGapBetweenSymbolsOnColumns() {
        boolean isThereEmptyCellar = false;
        int[] coordinatesGap = new int[2];

        for (int j = 2; j <= Game.getGameBoard().length; j = j + 2) {

        }

        return new int[2];
    }


    @Override
    public int selectColumn() {
        int[] arrOfIndexes = new int[2]; //Столбец, строка
        if (Game.getGameBoard()[4][4] == ' ') {
            arrOfIndexes[0] = 4;
            arrOfIndexes[1] = 4;
        } else if ((Game.getGameBoard()[4][4] != ' ') && ((Game.getGameBoard()[4][2] != ' ')
                || (Game.getGameBoard()[4][6] != ' ') || (Game.getGameBoard()[2][4] != ' ')
                || (Game.getGameBoard()[6][4] != ' '))) {
            int[] coord = parseCoordinates(cornerCages, makeRandom());
            arrOfIndexes[0] = coord[0];
            arrOfIndexes[1] = coord[1];
        }



        return 0;
    }

    @Override
    public int selectLine() {
        return 0;
    }


    public boolean addSymbol(int[] indexes) {
        return false;
    }
}