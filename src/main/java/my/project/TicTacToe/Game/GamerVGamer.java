package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.Gamer;

import java.util.Optional;

public class GamerVGamer {
    private static short courses = 0;
    private static Gamer oddCourses;
    private static Gamer evenCourse;
    private static final int firstCourseGamerIndex = GameService.defineWhoFirstIndex();
    private final Gamer firstGamer = GameService.arrayOfGamers[0];
    private final Gamer secondGamer = GameService.arrayOfGamers[1];
    private final char[][] gameBoardArr = GameService.prepareGameBoard();

    public static void startGame() {
        courses++;
    }


    public static short getCourses() {
        return courses;
    }

    public static void setCourses(short courses) {
        GamerVGamer.courses = courses;
    }

    //Определяет игрока чей ход
    public static Optional<Gamer> whichCourse() {

        if ((courses == 1) && (firstCourseGamerIndex == 0)) {
            oddCourses = GameService.arrayOfGamers[0];
            evenCourse = GameService.arrayOfGamers[1];
            return Optional.of(oddCourses);
        }

        if ((courses == 1) && (firstCourseGamerIndex == 1)) {
            oddCourses = GameService.arrayOfGamers[1];
            evenCourse = GameService.arrayOfGamers[0];
            return Optional.of(oddCourses);
        }

        if (courses == -1) {
            return Optional.empty();
        }

        if (courses % 2 != 0) {
            return Optional.of(oddCourses);
        } else {
            return Optional.of(evenCourse);
        }
    }


    public static void game(int line, int column) {
        //Определяет чей ход и делает ход
        if (whichCourse().isPresent()) {
            whichCourse().get().addSymbol1(line, column);
        }
        GameService.isThereWinner();
        courses++;





    }


}
