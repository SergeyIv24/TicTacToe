package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.Gamer;

import java.util.Optional;

public class GamerVGamer {
    private static short courses = 0;
    private static Gamer oddCourses;
    private static Gamer evenCourse;
    private static final int firstCourseGamerIndex = GameService.defineWhoFirstIndex();

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


    public static void game(Gamer firstGamer, Gamer secondGamer) {



        //Определяет чей ход и делает ход
        courses++;





    }


}
