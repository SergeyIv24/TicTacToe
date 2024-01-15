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
        if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].gameSymbol)
                && (compSymb == gameSymbol[0])) {
            compSymb = '0';
        } else if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].gameSymbol)
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
    }

}
