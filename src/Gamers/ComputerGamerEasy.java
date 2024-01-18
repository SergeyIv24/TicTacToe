package Gamers;
import Game.*;
import java.util.Random;
//Легкий ПК - выбор хода рандом

public class ComputerGamerEasy extends Gamer {
    Random rdn;

    public ComputerGamerEasy(String name, char gameSymbol, Random rdn) {
        super(name, gameSymbol);
        this.rdn = rdn;
    }

    @Override
    public int selectColumn() { //Определение колонки для хода
        int columnForComp = rdn.nextInt(4);

        //Соответствие отображаемых колонок доски с индексами элементов
        switch (columnForComp) {
            case 1:
                columnForComp = 2;
                break;
            case 2:
                columnForComp = 4;
                break;
            case 3:
                columnForComp = 6;
                break;
        }
        return columnForComp;
    }

    @Override
    public int selectLine() { //Определение строки для хода
        int lineForComp = rdn.nextInt(4);

        switch (lineForComp) {
            case 1:
                lineForComp = 2;
                break;
            case 2:
                lineForComp = 4;
                break;
            case 3:
                lineForComp = 6;
                break;
        }
        return lineForComp;
    }

    @Override
    public boolean addSymbol(int column, int line) { //Метод добавления хода на доску
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
