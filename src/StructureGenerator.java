import java.util.Arrays;
import java.util.Random;

public class StructureGenerator {

    protected static boolean [][] structure;
    protected static int [] countRooms;

    public void generate(int vertexs){

        Random rdm = new Random();
        structure = new boolean[vertexs][vertexs];
        countRooms = new int [vertexs];
        Arrays.fill(countRooms, 0);

        for (int i = 0; i < vertexs; i++) {
            for (int j = 0; j < vertexs; j++) {
                structure[j][i] = false;
            }
        }

        for(int i = 0; i < vertexs; i++){
            for(int j = 0; j < vertexs; j++){
                int chance = rdm.nextInt(100);
                if(chance < 20 && countRooms[i] < 4 && countRooms[j] < 4){
                    structure[i][j] = true;
                    structure[j][i] = true;
                    countRooms[i]++;
                    countRooms[j]++;
                }
            }
            if(countRooms[i] == 0){
                int shoot = rdm.nextInt(vertexs);
                structure[i][shoot] = true;
                countRooms[i]++;
            }

        }

        for (int x = 0; x < vertexs; x++) {
            for (int z = 0; z < vertexs; z++) {
                if(structure[x][z] == true)
                     System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }

    System.out.println(countRooms[0]);

    }
}
