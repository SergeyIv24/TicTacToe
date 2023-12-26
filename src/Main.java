import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        char[][] gameBoard = new char[7][7];
        gameBoard[0] = new char[]{' ', '|', '1', '|', '2', '|', '3'};

        char[] winX = new char[3];
        char[] winO = new char[3];

        char j = 'A';
        for (int i = 1; i < gameBoard.length; i++) {
            if (i == 2) {
                j = 'A';
            } else if (i == 4) {
                j = 'B';
            } else if (i == 6) {
                j = 'C';
            }
            for (int k = 0; k < gameBoard[i].length; k++) {
                if ((k == 0) & (i % 2 == 0)) {
                    gameBoard[i][k] = j;
                } else if (i % 2 != 0) {
                    gameBoard[i][k] = '-';
                } else if (k % 2 != 0) {
                    gameBoard[i][k] = '|';
                } else {
                    gameBoard[i][k] = ' ';
                }
            }
        }
        gameBoard[2][4] = 'X';
        gameBoard[4][4] = 'X';
        gameBoard[6][2] = 'X';
        for (char[] chars : gameBoard) {
            System.out.println(chars);
        }

        boolean checkAmount = false;
        int amountX = 0;
        int amountO = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int h = 0; h < gameBoard[i].length; h++) {
                if (gameBoard[i][h] == 'X') {
                    amountX += 1;
                } else if (gameBoard[i][h] == '0') {
                    amountO += 1;
                }
            }
        }
        if (amountX >= 3) {
            checkAmount = true;
        } else if (amountO >= 3) {
            checkAmount = true;
        } else {
            checkAmount = false;
        }

        boolean checkWinX = false;
        boolean checkWin0 = false;
        if ((gameBoard[4][4] == 'X') || (gameBoard[4][4] == '0')) {
            for (int i = 0; i < gameBoard.length; i++) {
                for (int h = 0; h < gameBoard[i].length; h++) {
                    if (gameBoard[4][4] == 'X') {
                        winX[0] = 'X';
                        if (gameBoard[2][6] == 'X') {
                            winX[1] = 'X';
                            if (gameBoard[6][2] == 'X') {
                                winX[2] = 'X';
                            }
                        } else if (gameBoard[2][2] == 'X'){
                            winX[1] = 'X';
                            if (gameBoard[6][6] == 'X') {
                                winX[2] = 'X';
                            }
                        }
                    }
                    if (gameBoard[4][4] == '0') {
                        winO[0] = '0';
                        if (gameBoard[2][6] == '0') {
                            winO[1] = '0';
                            if (gameBoard[6][2] == '0') {
                                winO[2] = '0';
                            }
                        } else if (gameBoard[2][2] == '0'){
                            winO[1] = '0';
                            if (gameBoard[6][6] == '0') {
                                winO[2] = '0';
                            }
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(winX));









        amountX = 0;
        int h;
        int l;
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            for (h = 2, l = 0; h < gameBoard[i].length; h = h + 2, l++) {
                if (gameBoard[i][h] == 'X') {
                    winX[l] = 'X';
                    amountX += 1;

                } else {
                    winX[0] = ' ';
                    winX[1] = ' ';
                    winX[2] = ' ';
                    break;
                }

            }
            if (amountX == winX.length) {
                break;
            }

        }

        //System.out.println(Arrays.toString(winX));
        amountO = 0;
        for (int i = 2; i < gameBoard.length; i = i + 2) {
            for (h = 2, l = 0; h < gameBoard[i].length; h = h + 2, l++) {
                if (gameBoard[i][h] == '0') {
                    winO[l] = '0';
                    amountO += 1;
                } else {
                    winO[0] = ' ';
                    winO[1] = ' ';
                    winO[2] = ' ';
                    break;
                }
            }
            if (amountO == winO.length) {
                break;
            }
        }

        //System.out.println(Arrays.toString(winO));






    }
}
