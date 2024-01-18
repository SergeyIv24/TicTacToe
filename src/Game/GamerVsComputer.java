package Game;
import Gamers.*;

public class GamerVsComputer extends Game{
    private static boolean isLevelEasy = true;

    public void setIsLevelEasy(boolean newLevel) {
        isLevelEasy = newLevel;
    }

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
    public Gamer createComputerGamer() {
        char compSymb = defineRandomGameSymbol();
        if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[0])) {
            compSymb = '0';
        } else if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[1])) {
            compSymb = 'X';
        }

        System.out.println("Игрок компьютер.");
        System.out.println("Игровой символ:" + compSymb);
        Gamer computerGamer;
        if (isLevelEasy) {
            computerGamer = new ComputerGamerEasy("Компьютер", compSymb, rdn);
        } else {
            computerGamer = new ComputerGamerHard("Компьютер", compSymb, rdn);
        }

        arrayOfGamers[1] = computerGamer;
        return computerGamer;
    }

    public void gameAgainstComputer() {
        FirstGamer firstGamer = createGamer();
        Gamer computerGamer = createComputerGamer();
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
                printGameBoard(); //Вывод доски с ходо
                if (isThereWinner()) { //Если игрок 1 победил
                    System.out.println("Победил игрок: " + firstGamer.getName() + "!");
                    break;
                }
                if(!defineDraw()) {
                    System.out.println("Ничья!");
                    break;
                }

                System.out.println("Игрок компьютер: ");

                while (true) {
                    if (computerGamer.addSymbol(computerGamer.selectLine(), computerGamer.selectColumn())) { //ход
                        break;
                    }
                }
                printGameBoard(); //Вывод доски

                if (isThereWinner()) { //Если второй игрок победил
                    System.out.println("Победил игрок: " + computerGamer.getName() + "!");
                    break;
                }
                if(!defineDraw()) {
                    System.out.println("Ничья!");
                    break;
                }

            } else {
                System.out.println("Игрок компьютер: ");
                while (true) {
                    if (computerGamer.addSymbol(computerGamer.selectLine(), computerGamer.selectColumn())) { //ход
                        break;
                    }
                }

                printGameBoard();

                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + computerGamer.getName() + "!");
                    break;
                }
                if(!defineDraw()) {
                    System.out.println("Ничья!");
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
                if(!defineDraw()) {
                    System.out.println("Ничья!");
                    break;
                }
            }
        }
    }

}
