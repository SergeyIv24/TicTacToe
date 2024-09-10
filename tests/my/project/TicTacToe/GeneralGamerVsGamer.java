package my.project.TicTacToe;

import my.project.TicTacToe.Game.Game;
import my.project.TicTacToe.Game.GameService;
import org.junit.jupiter.api.*;

abstract class GeneralGamerVsGamer {
    protected static char firstGamerSymbol;
    protected static char secondGamerSymbol;

    @BeforeAll
    public static void prepareGame() {
        GameService.createFirstGamer("Тест");
        GameService.createSecondGamer("Тестов");
        Game.createGame();
        firstGamerSymbol = Game.getFirstCourseGamer().getGameSymbol();
        secondGamerSymbol = firstGamerSymbol == 'X' ? '0' : 'X';
    }

    @AfterAll
    public static void clearAll() {
        Game.stopGame();
    }

    @Test
    public void shouldCreateFields() {
        Assertions.assertNotNull(Game.getFirstCourseGamer());
        Assertions.assertNotEquals(firstGamerSymbol, secondGamerSymbol);
    }

    public void makeAllCourses(String... coordinates) {
        for (String coordinate : coordinates) {
            int line = Integer.parseInt(coordinate.substring(0, 1));
            int column = Integer.parseInt(coordinate.substring(1));
            Game.game(line, column);
            Game.increaseCourse();
        }
    }

    public void checkCoordinates(String... coordinates) {
        int i = 0;
        for (String coordinate : coordinates) {
            int line = Integer.parseInt(coordinate.substring(0, 1));
            int column = Integer.parseInt(coordinate.substring(1));
            if (i % 2 == 0) {
                Assertions.assertEquals(firstGamerSymbol, GameService.getGameBoard()[line][column]);
            } else {
                Assertions.assertEquals(secondGamerSymbol, GameService.getGameBoard()[line][column]);
            }
            i++;
        }
    }

    public void checkEmptyCoordinates(String... coordinates) {
        if (coordinates == null) {
            return;
        }
        for (String coordinate : coordinates) {
            int line = Integer.parseInt(coordinate.substring(0, 1));
            int column = Integer.parseInt(coordinate.substring(1));
            Assertions.assertNotEquals(firstGamerSymbol, GameService.getGameBoard()[line][column]);
            Assertions.assertNotEquals(secondGamerSymbol, GameService.getGameBoard()[line][column]);
        }
    }

    @Test
    abstract void checkAllRightCourses();

    @Test
    abstract void checkAllWrongCourses();

    @Test
    abstract void checkWinner();


}
