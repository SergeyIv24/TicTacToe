package Game;
//Утилитарный класс для запуска нужного режима игры
import java.util.Scanner;

public class GameManager {

    public static void chooseGameOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Это крестики нолики!");
        System.out.println("1 - Игра против другого игрока, 2 - игра против компьютера");
        byte userOption = scanner.nextByte();

        switch (userOption) {
            case 1:
                GamerVsGamer game = new GamerVsGamer();
                game.game();
                break;
            case 2:
                GamerVsComputer gamerVsComputer = new GamerVsComputer();
                System.out.println("1 - легко. 2 - сложно.");
                byte levelSelection = scanner.nextByte();
                switch (levelSelection) {
                    case 1:
                        gamerVsComputer.setIsLevelEasy(true);
                        gamerVsComputer.gameAgainstComputer();
                        break;
                    case 2:
                        gamerVsComputer.setIsLevelEasy(false);
                        gamerVsComputer.gameAgainstComputer();
                        break;
                    default:
                        System.out.println("Такой опции нет");
                        break;
                }
                break;
            default:
                System.out.println("Такой опции нет");
        }
    }
}
