package my.project.TicTacToe.Gamers;
import my.project.TicTacToe.Game.*;
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
        columnForComp = switch (columnForComp) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            default -> columnForComp;
        };
        return columnForComp;
    }

    @Override
    public int selectLine() { //Определение строки для хода
        int lineForComp = rdn.nextInt(4);

        lineForComp = switch (lineForComp) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            default -> lineForComp;
        };
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
