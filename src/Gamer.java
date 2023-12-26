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
        System.out.println("Укажите колонку: ");
        int indColumn = scanner.nextInt();
        switch (indColumn) {
            case 1:
                indColumn = 2;
                break;
            case 2:
                indColumn = 4;
                break;
            case 3:
                indColumn = 6;
                break;
        }

        System.out.println("Укажите строку: ");
        int indLine = 0;
        String column = scanner.next();
        char[] columnToChArr = column.toCharArray();
        char columnCh = columnToChArr[0];

        switch (columnCh) {
            case 'A':
                indLine = 2;
                break;
            case 'B':
                indLine = 4;
                break;
            case 'C':
                indLine = 6;
        }
        Game.setGameBoard(indLine, indLine, gameSymbol);
    }


}
