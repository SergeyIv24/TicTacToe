import java.util.Objects;
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

    public int selectColumn() {

        System.out.println("Чтобы сделать ход, укажите ячейку на игровом поле.");

        int indColumn = 0;
        Scanner scanner;
        while (true) {
            System.out.println("Укажите колонку: ");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                indColumn = scanner.nextInt();
                scanner.close();
                break;
            }
        }

        scanner.close();
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
            default:
                System.out.println("Такой колонки нет. Доступные колонки: 1, 2, 3");
                indColumn = selectColumn();
                break;
        }

        return indColumn;
    }

    public int selectLine(Scanner scanner) {
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
                break;
            default:
                System.out.println("Такой строки нет. Доступные строки: A, B, C");
                indLine = selectLine(scanner);
                break;
        }
        return indLine;
    }

    public void addSymbol(int column, int line) {
            Game.setGameBoard(line, column, gameSymbol);
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
