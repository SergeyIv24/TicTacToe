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

    private int selectColumn() { //Определение колонки для хода
        return rdn.nextInt(4);
    }

    private int selectLine() { //Определение строки для хода
        return rdn.nextInt(4);
    }

    @Override
    public boolean addSymbol(int column, int line) { //Метод добавления хода на доску
        if (column == -1) {
            column = selectColumn();
            line = selectLine();
        }

        if (GameService.getGameBoard()[line][column] == ' ') {
            GameService.setGameBoard(line, column, gameSymbol);
            return true;
        } else {
            selectColumn();
            selectLine();
            return false;
        }
    }
}
