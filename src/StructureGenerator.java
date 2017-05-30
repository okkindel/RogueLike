import java.util.Arrays;
import java.util.Random;

public class StructureGenerator {

    static boolean [][] structure;

    void generate (int vertex) {

        Random generator = new Random();
        structure = new boolean[vertex][vertex];
        int[] countRooms = new int[vertex];
        Arrays.fill(countRooms, 0);

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                structure[j][i] = false;
            }
        }
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                int chance = generator.nextInt(100);
                if (chance < 20 && countRooms[i] < 4 && countRooms[j] < 4) {
                    structure[i][j] = true;
                    structure[j][i] = true;
                    countRooms[i]++;
                    countRooms[j]++;
                }
            }
            if (countRooms[i] == 0) {
                int emergency_door = generator.nextInt(vertex);
                structure[i][emergency_door] = true;
                structure[emergency_door][i] = true;
            }
        }
        terminalShowing(vertex);
    }

    private void terminalShowing(int vertex) {
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if (structure[i][j])
                     System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
