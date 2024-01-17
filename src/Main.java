import Game.*;
import Gamers.ComputerGamerEasy;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GameManager.chooseGameOption();
/*        int[][] testArr = new int [][] {{3, 2, 3}, {2, 3, 2}, {2, 3, 3}};

        for (int i = 0; i < testArr.length; i++) {
            for (int j = 0; j < testArr[i].length; j++) {
                if (testArr[j][i] == 3) {
                    System.out.println(testArr[j][i]);
                }
            }
        }*/

    }
}

/*    public ComputerGamerEasy createComputerGamer() {
        char compSymb = defineRandomGameSymbol();
        Random rnd = new Random();
        if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[0])) {
            compSymb = '0';
        } else if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[1])) {
            compSymb = 'X';
        }

        System.out.println("Игрок компьютер.");
        System.out.println("Игровой символ:" + compSymb);

        ComputerGamerEasy computerGamerEasy = new ComputerGamerEasy("Компьютер", compSymb, rdn);

        arrayOfGamers[1] = computerGamerEasy;
        return computerGamerEasy;
    }*/

/*        for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
            for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 2) {
                if (Game.getGameBoard()[i][j] == ' ') {
                    arrOfIndexes[0] = i;
                    arrOfIndexes[1] = j;
                    return arrOfIndexes;
                }
            }
        }
        return new int[2];*/

/*        for (int i = 2; i < Game.getGameBoard().length; i = i + 2) {
            for (int j = 2; j < Game.getGameBoard()[i].length; j = j + 2) {

            }

        }*/

/*        boolean courseEnemyNotCorner = ((Game.getGameBoard()[4][2] != ' ') || (Game.getGameBoard()[4][6] != ' ')
                || (Game.getGameBoard()[2][4] != ' ') || (Game.getGameBoard()[6][4] != ' '));
        boolean courseEnemyCorner = ((Game.getGameBoard()[2][2] != ' ') || (Game.getGameBoard()[2][6] != ' ')
                || (Game.getGameBoard()[6][2] != ' ') || (Game.getGameBoard()[6][6] != ' '));*/


/*        if (courseEnemyCorner || courseEnemyNotCorner) {
            int[] coord = parseCoordinates(cornerCages, makeRandom());
            arrOfIndexes[0] = coord[0];
            arrOfIndexes[1] = coord[1];
            return arrOfIndexes;
        }*/
