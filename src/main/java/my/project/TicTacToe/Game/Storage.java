package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.Gamer;

public class Storage {
    protected static Gamer[] arrayOfGamers = new Gamer[2]; //Массив игроков для рандома
    protected static char[] gameSymbol = new char[]{'X', '0'}; //Массив для рандома игрового символа
    protected static final char[] winX = new char[3]; //Массив для определения победителя X
    protected static final char[] win0 = new char[3]; //Массив для определения победителя 0

}
