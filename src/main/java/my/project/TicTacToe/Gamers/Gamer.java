package my.project.TicTacToe.Gamers;
import my.project.TicTacToe.Game.*;
import java.util.Objects;
import java.util.Scanner;

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





    //Выбор колонки для хода
    public int selectColumn() {
        System.out.println("Чтобы сделать ход, укажите ячейку на игровом поле.");
        int indColumn;
        Scanner scanner;
        while (true) { //Бесконечный цикл для проверки типа данных ввода
            System.out.println("Укажите колонку: ");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                indColumn = scanner.nextInt();
                break;
            }
        }
        //Соответствие отображаемых колонок доски с индексами элементов
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
                indColumn = selectColumn(); //Рекурсия, если колонки не существует
                break;
        }

        return indColumn;
    }


    //Выбор линии для хода
    public int selectLine() {
        System.out.println("Укажите строку: ");
        Scanner scanner = new Scanner(System.in);
        int indLine;
        String line = scanner.next();
        char[] lineToChArr = line.toCharArray();
        char columnCh = lineToChArr[0];
        columnCh = Character.toUpperCase(columnCh);
        //Соответствие отображаемых колонок доски с индексами элементов
        //Рекурсия, если строки не существует
        indLine = switch (columnCh) {
            case 'A' -> 2;
            case 'B' -> 4;
            case 'C' -> 6;
            default -> {
                System.out.println("Такой строки нет. Доступные строки: A, B, C");
                yield selectLine();
            }
        };
        return indLine;
    }

    public boolean addSymbol(int column, int line) {
        if (Game.getGameBoard()[line][column] == ' ') {
            Game.setGameBoard(line, column, gameSymbol);
            return true;
        } else {
            System.out.println("Позиция занята");
            return false;
        }
    }












    public boolean addSymbol1(int line, int column) {
        if ((Game.getGameBoard()[line][column] == '\u0000')
                || (Game.getGameBoard()[line][column] == ' ')) {
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
