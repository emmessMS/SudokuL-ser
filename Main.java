import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static private final int groesse = 9;
    Main() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Geben Sie einen maximal 9 Zeichen langen String ein: ");
        String sudokuCode = scan.next();
        while(sudokuCode.length()!=81){
            sudokuCode = scan.next();
        }
        char[] chars = sudokuCode.toCharArray();
        Block[][] array = new Block[groesse][groesse];
        int reihe = 0;
        int spalte = 0;
        for (char c : chars) {
            if (Character.isDigit(c) && Character.getNumericValue(c) != 0) {
                int number = Character.getNumericValue(c);
                array[reihe][spalte] = new Block(number, true);;
                spalte++;
                if (spalte == groesse) {
                    spalte = 0;
                    reihe++;
                    if (reihe == groesse) {
                        break;
                    }
                }
            } else {
                array[reihe][spalte] = new Block(0, false);
                spalte++;
                if (spalte == groesse) {
                    spalte = 0;
                    reihe++;
                    if (reihe == groesse) {
                        break;
                    }
                }
            }
        }
        System.out.println("Das Sudoku enthält folgende Elemente:");
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {
                System.out.print(array[i][j].gibWert() + " ");
            }
            System.out.println();
        }
        SudokuLöser löser = new SudokuLöser(array);
        long startTime = System.currentTimeMillis();

        löser.lösen();

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Laufzeit: " + totalTime + " Millisekunden");
    }
}
