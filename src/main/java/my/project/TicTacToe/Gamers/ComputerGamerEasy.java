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
        return rdn.nextInt(3);
    }

    private int selectLine() { //Определение строки для хода
        return rdn.nextInt(3);
    }

    @Override
    public String addSymbol(int column, int line) { //Метод добавления хода на доску
        column = selectColumn();
        line = selectLine();
        GameService.setGameBoard(line, column, gameSymbol);
        return "" + line + column;



/*        if (column == -1) {
            column = selectColumn();
            line = selectLine();
        }*/
/*        if (GameService.getGameBoard()[line][column] == ' ') {
            GameService.setGameBoard(line, column, gameSymbol);
            //return true;
            return "" + line + column;*/
        //} else {
/*            column = selectColumn();
            line = selectLine();
            GameService.setGameBoard(line, column, gameSymbol);
            return "" + line + column;*/
            //return false;

        //}
    }
}
