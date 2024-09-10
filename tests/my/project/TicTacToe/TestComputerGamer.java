package my.project.TicTacToe;

import my.project.TicTacToe.Game.Game;
import my.project.TicTacToe.Game.GameService;
import my.project.TicTacToe.Gamers.Gamer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class TestComputerGamer extends GeneralGamerVsGamer {
    static Gamer pc;
    static String[] pcCourses = new String[3];
    static String[] pcCoordinates = new String[3];

    @BeforeAll
    public static void prepareGame() {
        Gamer fg = GameService.createFirstGamer("Тест");
        pc = GameService.createComputerGamer(false, "EasyPc");
        Game.createGame();
        firstGamerSymbol = fg.getGameSymbol();
        secondGamerSymbol = pc.getGameSymbol();
        pcCoordinates = makeAllPcCourses(pcCourses);
    }

    public static String[] makeAllPcCourses(String[] coordinates) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = pc.addSymbol(0, 0);
        }
        return coordinates;
    }

    @Override
    public void checkCoordinates(String... coordinates) {
        for (String coordinate : coordinates) {
            int line = Integer.parseInt(coordinate.substring(0, 1));
            int column = Integer.parseInt(coordinate.substring(1));
            Assertions.assertEquals(secondGamerSymbol, GameService.getGameBoard()[line][column]);
        }
    }


    @Override
    @Test
    public void checkAllRightCourses() {
        this.checkCoordinates(pcCoordinates);
    }

    @Override
    @Test
    void checkAllWrongCourses() {
        int emptyCellsAmount = 0;
        int j = 0;
        for (int i = 0; i < GameService.getGameBoard()[j].length; ) {
            if (GameService.getGameBoard()[j][i] == '\u0000') {
                emptyCellsAmount++;
            }

            if (i == (GameService.getGameBoard()[j].length - 1)) {
                i = -1;
                j++;
            }
            i++;
            if (j >= GameService.getGameBoard().length) {
                break;
            }

        }
        Assertions.assertEquals(6, emptyCellsAmount);
    }

    @Override
    @Test
    public void checkWinner() {
        List<Optional<Gamer>> winOptions = List.of(Optional.of(pc), Optional.empty());
        Optional<Gamer> winner = GameService.findWinner();
        Assertions.assertTrue(winOptions.contains(winner));
    }
}
