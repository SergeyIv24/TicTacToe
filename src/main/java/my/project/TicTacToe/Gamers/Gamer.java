package my.project.TicTacToe.Gamers;
import my.project.TicTacToe.Game.*;
import java.util.Objects;

public class Gamer {
    protected String name;
    protected char gameSymbol;
    protected boolean computer = false;

    public Gamer(String name, char gameSymbol) {
        this.name = name;
        this.gameSymbol = gameSymbol;
    }

    public void setComputer(boolean isItComputer) {
        computer = isItComputer;
    }

    public boolean getComputer() {
        return computer;
    }

    public char getGameSymbol() {
        return gameSymbol;
    }

    public String getName() {
        return name;
    }

    //Добавление игрового символа на поле
    public String addSymbol(int line, int column) {
        if ((GameService.getGameBoard()[line][column] == '\u0000')
                || (GameService.getGameBoard()[line][column] == ' ')) {
            GameService.setGameBoard(line, column, gameSymbol);
            //return true;
            return "" + line + column;
        } else {
            //return false;
            return "";
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
