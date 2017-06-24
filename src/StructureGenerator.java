import java.util.Random;

public class StructureGenerator {

    static boolean[][] structure;

    void generate(int vertex) {

        Random generator = new Random();
        structure = new boolean[vertex][vertex];

        /* MAKING LINE GRAPH */
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                structure[j][i] = false;
                if (i > 0 && j > 0) {
                    structure[i][i - 1] = true;
                    structure[j - 1][j] = true;
                }
            }
        }
        /* RANDOM CYCLES */
        for (int i = 0; i < vertex; i++) {
            if (i == 0 || i == (vertex - 1)) {
                for (int index = 0; index < 2; index++) {
                    if (generator.nextInt(2) == 0) {
                        int vertex_got = generator.nextInt(vertex);
                        structure[i][vertex_got] = true;
                        structure[vertex_got][i] = true;
                    }
                }
            } else {
                if (generator.nextInt(2) == 0) {
                    int vertex_got = generator.nextInt(vertex);
                    structure[i][vertex_got] = true;
                    structure[vertex_got][i] = true;
                }
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