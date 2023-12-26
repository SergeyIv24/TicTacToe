import java.util.Scanner;

public class Gamer {
    protected String name;
    protected char gameSymbol;

    public Gamer(String name, char gameSymbol) {
        this.name = name;
        this.gameSymbol = gameSymbol;

    }

    public String getName() {
        return name;
    }

    public void addSymbol(Scanner scanner) {
        System.out.println("Чтобы сделать ход, укажите ячейку на игровом поле.");
        System.out.println("Укажите строку: ");
        String lineLetter = scanner.next();
        char[] lineLetterToChArr = lineLetter.toCharArray();
        char lineLetterCh = lineLetterToChArr[0];

        System.out.println("Укажите колонку: ");
        String column = scanner.next();
        char[] columnToChArr = column.toCharArray();
        char columnCh = columnToChArr[0];
        Game.setGameBoard(lineLetterCh, columnCh, gameSymbol);

    }


}
