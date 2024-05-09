package my.project.TicTacToe.Gamers;
import my.project.TicTacToe.Game.*;
import java.util.Objects;

public class Gamer {
    protected String name;
    protected char gameSymbol;

    public Gamer(String name, char gameSymbol) {
        this.name = name;
        this.gameSymbol = gameSymbol;
    }

    public char getGameSymbol() {
        return gameSymbol;
    }

    public String getName() {
        return name;
    }

    //Добавление игрового символа на поле
    public boolean addSymbol(int line, int column) {
        if ((GameService.getGameBoard()[line][column] == '\u0000')
                || (GameService.getGameBoard()[line][column] == ' ')) {
            GameService.setGameBoard(line, column, gameSymbol);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() != object.getClass()) return false;
        Gamer anotherGamer = (Gamer) object;
        return Objects.equals(name, anotherGamer.name) &&
                Objects.equals(gameSymbol, anotherGamer.gameSymbol);
    }
}
