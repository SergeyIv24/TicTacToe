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
    public String addSymbol(int line, int column) { //Метод добавления хода на доску
        int i = 0; //Количество попыток сделать ход
        do {
            line = selectLine(); //Выбор строки
            column = selectColumn(); //Выбор колонки
            i++;
            if (i > 30) { //Если сделал больше 30 попыток выход
                break;
            }
        } while (GameService.getGameBoard()[line][column] != '\u0000'); //Проверка не займет ли чужую клетку
        GameService.setGameBoard(line, column, gameSymbol); //Установка символа в массив
        return "" + line + column;
    }
}
