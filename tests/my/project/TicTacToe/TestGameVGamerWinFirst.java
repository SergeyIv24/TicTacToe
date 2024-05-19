package my.project.TicTacToe;

import my.project.TicTacToe.Game.GameService;
import my.project.TicTacToe.Gamers.Gamer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

public class TestGameVGamerWinFirst extends GeneralGamerVsGamer {
    String[] coordinates = new String[]{"00", "10", "11", "01", "22"};
    String[] emptyCoordinates = new String[]{"20", "20", "12", "12"};

    @BeforeEach
    public void makeAllCourses() {
        super.makeAllCourses(coordinates);
        System.out.println(Arrays.deepToString(GameService.getGameBoard()));
    }

    @Override
    @Test
    public void checkAllRightCourses() {
        super.checkCoordinates(coordinates);
    }

    @Override
    @Test
    public void checkAllWrongCourses() {
        super.checkEmptyCoordinates(emptyCoordinates);
    }

    @Override
    @Test
    public void checkWinner() {
        Optional<Gamer> winner = GameService.findWinner();
        Assertions.assertEquals(winner.get().getGameSymbol(), firstGamerSymbol);
    }
}
