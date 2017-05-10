import java.util.Random;
import java.util.Arrays;

public class Corridor {

    Boolean horizontally = true;
    protected static int lenght = 0;
    protected static int [][] sizes;
    protected static int [] left, right;

    public Corridor() {

        Random generator = new Random();
        lenght = generator.nextInt(10) + 10;
        sizes = new int[3][lenght];
        left = new int[lenght];
        right = new int[lenght];

        innerCorridor();
        addWalls();
        terminalShowing();
    }

    private static void innerCorridor() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < lenght; j++) {
                sizes[i][j] = 1;
            }
        }
    }

    private static void addWalls() {
        Arrays.fill(left, 2);
        Arrays.fill(right, 2);
        addDoors();

        for (int i = 0; i < lenght; i++) {
            sizes[0][i] = left[i];
            sizes[2][i] = left[i];
        }
    }

    private static void addDoors() {
        sizes[1][0] = 3;
        sizes[1][lenght-1] = 3;
        Random generator = new Random();
        if (generator.nextInt(2) == 0) {
            left[generator.nextInt(left.length - 1)] = 3;
        }
        if (generator.nextInt(2) == 1) {
            right[generator.nextInt(right.length - 1)] = 3;
        }
    }

    private static void terminalShowing() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < lenght; j++) {
                System.out.print(sizes[i][j]);
            }
            System.out.println();
        }
    }
}