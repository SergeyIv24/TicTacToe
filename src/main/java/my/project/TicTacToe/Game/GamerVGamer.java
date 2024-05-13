package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.Gamer;
import java.util.Optional;

public class GamerVGamer {
    private static short courses = 0; //Счетчик ходов. Не может быть больше 128.
    private static Gamer oddCourses; // Игрок, который ходит на нечетные ходы (первый)
    private static Gamer evenCourse; // Игрок, который ходит на четные ходы (второй)
    private static final int firstCourseGamerIndex = GameService.defineWhoFirstIndex(); //Определение первого хода
    private static final Gamer firstCourseGamer = GameService.defineWhoFirst(firstCourseGamerIndex);
    private static String currentCourseCoordinates;

    public static Gamer getFirstCourseGamer() {
        return firstCourseGamer;
    }

    public static String getCurrentCourseCoordinates() {
        return currentCourseCoordinates;
    }

    //При старте игры счетчик ходов 0
    public static void startGame() {
        courses++;
    }

    public static void increaseCourse() {
        courses++;
    }

    public static short getCourses() {
        return courses;
    }


    //Определяет игрока чей ход
    public static Optional<Gamer> whichCourse() {
        //Если первый ход и индекс первого игрока 0.
        if ((courses == 1) && (firstCourseGamerIndex == 0)) {
            oddCourses = GameService.arrayOfGamers[0];
            evenCourse = GameService.arrayOfGamers[1];
            return Optional.of(oddCourses);
        }
        //Если первый ход и индекс первого игрока 1.
        if ((courses == 1) && (firstCourseGamerIndex == 1)) {
            oddCourses = GameService.arrayOfGamers[1];
            evenCourse = GameService.arrayOfGamers[0];
            return Optional.of(oddCourses);
        }

        //Игра закончена или не начата, хода нет.
        if (courses == -1) {
            return Optional.empty();
        }

        //Для нечетных ходов (первый игрок)
        if (courses % 2 != 0) {
            return Optional.of(oddCourses);
        } else { //Четные ходы (второй игрок)
            return Optional.of(evenCourse);
        }
    }


    public static Optional<Gamer> game(int line, int column) {
        //Определение чей ход.
        if (whichCourse().isPresent()) {
            currentCourseCoordinates = whichCourse().get().addSymbol(line, column); //Выполнение хода
        }
        Optional<Gamer> winner = GameService.findWinner();
        if (winner.isPresent()) { //Определение есть ли победитель
            courses = -1;
            return winner;
        }
        if (!GameService.defineDraw()) {
            courses = -1;
            return Optional.empty();
        }
        //courses++; //При вызове метода делается ход, счетчик увеличивается
        return Optional.empty();
    }
}
