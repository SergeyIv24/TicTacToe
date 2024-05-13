package my.project.TicTacToe.Gamers;
import my.project.TicTacToe.Game.*;

import java.util.Arrays;
import java.util.Random;
//Легкий ПК - выбор хода рандом

public class ComputerGamerEasy extends Gamer {
    Random rdn;

    public ComputerGamerEasy(String name, char gameSymbol, Random rdn) {
        super(name, gameSymbol);
        this.rdn = rdn;
    }

    private int selectColumn() { //Определение колонки для хода
        return rdn.nextInt(3);
    }

    private int selectLine() { //Определение строки для хода
        return rdn.nextInt(3);
    }

    @Override
    public String addSymbol(int line, int column) { //Метод добавления хода на доску

        int i = 0;
        do {
            line = selectLine();
            column = selectColumn();
            i++;
            if (i > 30) {
                break;
            }
        } while (GameService.getGameBoard()[line][column] != '\u0000');
        GameService.setGameBoard(line, column, gameSymbol);
        System.out.println(Arrays.deepToString(GameService.getGameBoard()));
        return "" + line + column;






/*
        if (GameService.getGameBoard()[line][column] != '\u0000') {
            addSymbol(0, 0);
        }*/



    }
}
