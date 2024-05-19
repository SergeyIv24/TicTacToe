package my.project.TicTacToe.Game;

import my.project.TicTacToe.Gamers.Gamer;
import java.util.Optional;

public class Game {
    private static short courses = 0; //Счетчик ходов. Не может быть больше 128.
    private static Gamer oddCourses; // Игрок, который ходит на нечетные ходы (первый)
    private static Gamer evenCourse; // Игрок, который ходит на четные ходы (второй)
    private static int firstCourseGamerIndex; //Определение первого хода
    private static Gamer firstCourseGamer;
    private static String currentCourseCoordinates; //Координаты текущего хода

    public static void createGame() {
        GameService.prepareGameBoard();
        firstCourseGamerIndex = GameService.defineWhoFirstIndex();
        firstCourseGamer = GameService.defineWhoFirst(firstCourseGamerIndex);
        courses = 1;
    }

    public static Gamer getFirstCourseGamer() {
        return firstCourseGamer;
    }

    public static String getCurrentCourseCoordinates() {
        return currentCourseCoordinates;
    }

    public static short getCourses() {
        return courses;
    }

    //По окончанию игры количество ходов отрицательное
    public static void stopGame() {
        //firstCourseGamer = null;
        GameService.clearGameBoard();
        courses = -1;
    }

    //При старте или совершении хода - увеличение на 1.
    public static void increaseCourse() {
        courses++;
    }


    //Определение игрока чей ход
    public static Optional<Gamer> whoseCourse() {
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
        if (whoseCourse().isPresent()) {
            currentCourseCoordinates = whoseCourse().get().addSymbol(line, column); //Выполнение хода, текущее координаты
        }
        Optional<Gamer> winner = GameService.findWinner(); //Определение победителя
        if (winner.isPresent()) { //Определение есть ли победитель
            courses = -1;
            return winner;
        }
        if (GameService.defineDraw()) { //Если есть ничья
            courses = Constance.EXIT_NUMBER_FOR_DRAW; //Установка выходного значения
            return Optional.empty();
        }
        return Optional.empty();
    }

    public static boolean checkAbilityToContinue() {
        return courses != -1;
    }

}
