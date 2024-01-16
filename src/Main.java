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
