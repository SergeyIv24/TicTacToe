package my.project.TicTacToe.Game;
import my.project.TicTacToe.Gamers.*;

public class GamerVsComputer extends Game{
    private static boolean isLevelEasy = true;

    public void setIsLevelEasy(boolean newLevel) {
        isLevelEasy = newLevel;
    }

    //Создание игрока
    public FirstGamer createGamer() {
        System.out.println("Введите Ваше имя: ");
        String nameOfFirstUser = scan.next();
        char userSymbol = defineRandomGameSymbol(); //Вызов метода рандомного определения игрового символа
        System.out.println("Игрок: " + nameOfFirstUser + ".");
        System.out.println("Игровой символ: " + userSymbol);

        FirstGamer firstGamer = new FirstGamer(nameOfFirstUser, userSymbol);

        arrayOfGamers[0] = firstGamer; //Добавление в массив игроков
        return firstGamer;
    }

    //Создание игрока компьютера
    public Gamer createComputerGamer() {
        char compSymb = defineRandomGameSymbol(); //Вывзов метода рандомного определения игрового символа
        if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[0])) { //Если символы игроков равны и если символ ПК - Х
            compSymb = '0'; //То символ будет 0
        } else if ((arrayOfGamers[0] != null) && (compSymb == arrayOfGamers[0].getGameSymbol())
                && (compSymb == gameSymbol[1])) { //Если символы игроков равны и если символ ПК - 0
            compSymb = 'X'; //То символ будет Х
        }

        System.out.println("Игрок компьютер.");
        System.out.println("Игровой символ:" + compSymb);
        Gamer computerGamer;
        //Создание нужного объекта компьютера в зависимости от сложности игры
        if (isLevelEasy) { //Легкий уровень
            computerGamer = new ComputerGamerEasy("Компьютер", compSymb, rdn);
        } else { //Сложный уровень
            computerGamer = new ComputerGamerHard("Компьютер", compSymb, rdn);
        }

        arrayOfGamers[1] = computerGamer;
        return computerGamer;
    }

    //Игра против компьютера
    public void gameAgainstComputer() {
        FirstGamer firstGamer = createGamer();
        Gamer computerGamer = createComputerGamer();
        createPrimaryGameBoard();
        printGameBoard();
        Gamer whoFirst = defineWhoFirst(); //Рандомное определение первого хода

        while (true) { //Пока нет победителя
            if (whoFirst.equals(firstGamer)) {
                System.out.println("Игрок 1: ");
                while (true) {
                    if (firstGamer.addSymbol(firstGamer.selectColumn(), firstGamer.selectLine())) { //ход
                        break;
                    }
                }
                printGameBoard(); //Вывод доски с ходами
                if (isThereWinner()) { //Если игрок 1 победил
                    System.out.println("Победил игрок: " + firstGamer.getName() + "!");
                    break;
                }
                if(!defineDraw()) { //Проверка ничьи
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
                if(!defineDraw()) { //Проверка ничьи
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
                if(!defineDraw()) { //Проверка ничьи
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
                if(!defineDraw()) { //Проверка ничьи
                    System.out.println("Ничья!");
                    break;
                }
            }
        }
    }

}
