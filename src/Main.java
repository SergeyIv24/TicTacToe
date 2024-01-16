import Game.*;

public class Main {
    public static void main(String[] args) {
        //GameManager.chooseGameOption();
        int[][] testArr = new int [][] {{3, 2, 3}, {2, 3, 2}, {2, 3, 3}};

        for (int i = 0; i < testArr.length; i++) {
            for (int j = 0; j < testArr[i].length; j++) {
                if (testArr[j][i] == 3) {
                    System.out.println(testArr[j][i]);
                }
            }
        }

    }
}
