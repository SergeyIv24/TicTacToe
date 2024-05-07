package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.FirstGamer;
import my.project.TicTacToe.Gamers.Gamer;
import my.project.TicTacToe.Gamers.SeckondGamer;

import java.util.Random;

public class GameService {
    protected static Gamer[] arrayOfGamers = new Gamer[2]; //Массив игроков для рандома
    protected static char[] gameSymbol = new char[]{'X', '0'}; //Массив для рандома игрового символа
    protected static Random rdn = new Random();

    //Создание игрока 1 //todo перегружен, чтобы поддерживать совместимость с консольным интерфейсом
    //todo нужно сделать единый метод, независящий от интерфейса. Также, для второго игрока
    public static FirstGamer createFirstGamer(String name) {
        char firstGamerSymbol = defineRandomGameSymbol();
        FirstGamer firstGamer = new FirstGamer(name, firstGamerSymbol);
        arrayOfGamers[0] = firstGamer;
        return firstGamer;
    }

    //todo нужен рефакторинг метода и алгоритма
    public static SeckondGamer createSecondGamer(String name) {
        char secondGamerSymbol = defineRandomGameSymbol();
        if ((arrayOfGamers[0] != null) && (secondGamerSymbol == arrayOfGamers[0].getGameSymbol())
                && (secondGamerSymbol == gameSymbol[0])) {
            secondGamerSymbol = '0';
        } else if ((arrayOfGamers[0] != null) && (secondGamerSymbol == arrayOfGamers[0].getGameSymbol())
                && (secondGamerSymbol == gameSymbol[1])) {
            secondGamerSymbol = 'X';
        }
        SeckondGamer seckondGamer = new SeckondGamer(name, secondGamerSymbol);
        arrayOfGamers[1] = seckondGamer;
        return seckondGamer;
    }

    //Определение кто каким символом играет
    private static char defineRandomGameSymbol() {
        int indexOfSymbol = rdn.nextInt(2);
        return gameSymbol[indexOfSymbol];
    }

    //Определение кто первый ходит
    private static Gamer defineWhoFirst() {
        int indexOfGamer = rdn.nextInt(2);
        return arrayOfGamers[indexOfGamer];
    }

    public char[][] prepareGameBoard() {
        return new char[3][3];
    }

}
