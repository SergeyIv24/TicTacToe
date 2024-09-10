package my.project.TicTacToe;

import my.project.TicTacToe.Game.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameVsGamerDraw extends GeneralGamerVsGamer {
    String[] coordinates = new String[]{"01", "00", "10", "12", "02", "11", "22", "21", "20"};
    String[] emptyCoordinate  = new String[]{};


    @BeforeEach
    public void makeAllCourses() {
        super.makeAllCourses(coordinates);
    }

    @Override
    @Test
    void checkAllRightCourses() {
        super.checkCoordinates(coordinates);
    }

    @Override
    @Test
    void checkAllWrongCourses() {
        super.checkEmptyCoordinates(emptyCoordinate);
    }

    @Override
    @Test
    void checkWinner() {
        Assertions.assertTrue(GameService.defineDraw());
    }
}
