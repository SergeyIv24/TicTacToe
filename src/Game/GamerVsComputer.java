package Game;
import Gamers.*;
import java.util.Random;

public class GamerVsComputer extends Game{

    //Создание игрока
    public FirstGamer createGamer() {
        System.out.println("Введите Ваше имя: ");
        String nameOfFirstUser = scan.next();

        char userSymbol = defineRandomGameSymbol();

        System.out.println("Игрок: " + nameOfFirstUser + ".");
        System.out.println("Игровой символ: " + userSymbol);

        FirstGamer firstGamer = new FirstGamer(nameOfFirstUser, userSymbol);

        arrayOfGamers[0] = firstGamer;
        return firstGamer;
    }

    //Создание игрока компьютера
    public ComputerGamerEasy createComputerGamer() {
        char compSymb = defineRandomGameSymbol();
        Random rnd = new Random();
        if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[0])) {
            compSymb = '0';
        } else if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[1])) {
            compSymb = 'X';
        }

        System.out.println("Игрок компьютер.");
        System.out.println("Игровой символ:" + compSymb);

        ComputerGamerEasy computerGamerEasy = new ComputerGamerEasy("Компьютер", compSymb, rdn);

        arrayOfGamers[1] = computerGamerEasy;
        return computerGamerEasy;
    }

    public void gameAgainstComputer() {
        FirstGamer firstGamer = createGamer();
        ComputerGamerEasy computerGamerEasy = createComputerGamer();
        createPrimaryGameBoard();
        printGameBoard();
        Gamer whoFirst = defineWhoFirst();

        while (true) { //Пока нет победителя
            if (whoFirst.equals(firstGamer)) {
                System.out.println("Игрок 1: ");
                while (true) {
                    if (firstGamer.addSymbol(firstGamer.selectColumn(), firstGamer.selectLine())) { //ход
                        break;
                    }
                }
                printGameBoard(); //Вывод доски с ходом
                if (isThereWinner()) { //Если игрок 1 победил
                    System.out.println("Победил игрок: " + firstGamer.getName() + "!");
                    break;
                }

                System.out.println("Игрок компьютер: ");

                while (true) {
                    if (computerGamerEasy.addSymbol(computerGamerEasy.selectColumn(), computerGamerEasy.selectLine())) { //ход
                        break;
                    }
                }
                printGameBoard(); //Вывод доски
                if (isThereWinner()) { //Если второй игрок победил
                    System.out.println("Победил игрок: " + computerGamerEasy.getName() + "!");
                    break;
                }

            } else {
                System.out.println("Игрок компьютер: ");
                while (true) {
                    if (computerGamerEasy.addSymbol(computerGamerEasy.selectColumn(), computerGamerEasy.selectLine())) { //ход
                        break;
                    }
                }

                printGameBoard();
                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + computerGamerEasy.getName() + "!");
                    break;
                }
                System.out.println("Игрок 1: ");

                while (true) {
                    if (firstGamer.addSymbol(firstGamer.selectColumn(), firstGamer.selectLine())) { //ход
                        break;
                    }
                }

                printGameBoard();
                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + firstGamer.getName() + "!");
                    break;
                }
            }
        }
    }

}