import java.util.Random;

public class ComputerGamerEasy extends Gamer {
    Random rdn;

    public ComputerGamerEasy(String name, char gameSymbol, Random rdn) {
        super(name, gameSymbol);
        this.rdn = rdn;
    }

    @Override
    public int selectColumn() {
        int columnForComp = rdn.nextInt(3);

        //Соответствие отображаемых колонок доски с индексами элементов
        switch (columnForComp) {
            case 1:
                columnForComp = 2;
                break;
            case 2:
                columnForComp = 4;
                break;
            case 3:
                columnForComp = 6;
                break;
        }
        return columnForComp;
    }
    @Override
    public int selectLine() {
        int lineForComp = rdn.nextInt(3);

        switch (lineForComp) {
            case 1:
                lineForComp = 2;
                break;
            case 2:
                lineForComp = 4;
                break;
            case 3:
                lineForComp = 6;
                break;
        }
        return lineForComp;
    }

    @Override
    public boolean addSymbol(int column, int line) {
        if (Game.getGameBoard()[line][column] == ' ') {
            Game.setGameBoard(line, column, gameSymbol);
            return true;
        } else {
            selectColumn();
            selectLine();
            return false;
        }
    }

}
