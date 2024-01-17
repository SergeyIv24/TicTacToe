package Game;

import Gamers.*;


public class GamerVsGamer extends Game {

    //Создание игрока 1
    public FirstGamer createFirsGamer() {
        System.out.println("Введите Ваше имя: ");
        String nameOfFirstUser = scan.next();

        char user1Symbol = defineRandomGameSymbol();

        System.out.println("Игрок 1: " + nameOfFirstUser + ".");
        System.out.println("Игровой символ:" + user1Symbol);

        FirstGamer firstGamer = new FirstGamer(nameOfFirstUser, user1Symbol);

        arrayOfGamers[0] = firstGamer;
        return firstGamer;
    }

    //Создание игрока 2
    public SeckondGamer createSecondGamer() {
        System.out.println("Введите Ваше имя: ");
        String nameOfSecondUser = scan.next();

        char user2Symbol = defineRandomGameSymbol();

        if ((arrayOfGamers[0] != null) && (user2Symbol == arrayOfGamers[0].getGameSymbol())
                && (user2Symbol == gameSymbol[0])) {
            user2Symbol = '0';
        } else if ((arrayOfGamers[0] != null) && (user2Symbol == arrayOfGamers[0].getGameSymbol())
                && (user2Symbol == gameSymbol[1])) {
            user2Symbol = 'X';
        }

        System.out.println("Игрок 2: " + nameOfSecondUser + ".");
        System.out.println("Игровой символ:" + user2Symbol);

        SeckondGamer seckondGamer = new SeckondGamer(nameOfSecondUser, user2Symbol);

        arrayOfGamers[1] = seckondGamer;
        return seckondGamer;
    }

    //Игра
    public void game() {
        FirstGamer first = createFirsGamer();
        SeckondGamer second = createSecondGamer();
        createPrimaryGameBoard();
        printGameBoard();

        Gamer whoFirst = defineWhoFirst();

        while (true) { //Пока нет победителя
            if (whoFirst.equals(first)) { //Если первый ходит игрок 1
                System.out.println("Игрок 1: ");
                //Бесконечный цикл для неверного хода
                while (true) {
                    if (first.addSymbol(first.selectColumn(), first.selectLine())) { //ход
                        break;
                    }
                }

                printGameBoard(); //Вывод доски с ходом

                if (isThereWinner()) { //Если игрок 1 победил
                    System.out.println("Победил игрок: " + first.getName() + "!");
                    break;
                }
                if(!defineDraw()) {
                    System.out.println("Ничья!");
                    break;
                }
                System.out.println("Игрок 2: ");

                while (true) {
                    if (second.addSymbol(second.selectColumn(), second.selectLine())) { //ход
                        break;
                    }
                }

                printGameBoard(); //Вывод доски

                if (isThereWinner()) { //Если второй игрок победил
                    System.out.println("Победил игрок: " + second.getName() + "!");
                    break;
                }
                if(!defineDraw()) {
                    System.out.println("Ничья!");
                    break;
                }
            } else { //Если первый ходит игрок 2
                System.out.println("Игрок 2: ");

                while (true) {
                    if (second.addSymbol(second.selectColumn(), second.selectLine())) { //ход
                        break;
                    }
                }

                printGameBoard();

                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + second.getName() + "!");
                    break;
                }
                if(!defineDraw()) {
                    System.out.println("Ничья!");
                    break;
                }
                System.out.println("Игрок 1: ");

                while (true) {
                    if (first.addSymbol(first.selectColumn(), first.selectLine())) { //ход
                        break;
                    }
                }

                printGameBoard();

                if (isThereWinner()) {
                    System.out.println("Победил игрок: " + first.getName() + "!");
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
