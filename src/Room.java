import java.util.Random;
import java.util.Arrays;

/**
 * Created by okkindel on 10.05.17.
 */
public class Room {

    protected static int height = 0;
    protected static int width = 0;
    protected static int [][] sizes;
    protected static int [] north, south, east, west;

    public Room() {
        Random generator = new Random();
        height = generator.nextInt(10) + 10;
        width = generator.nextInt(10) + 10;
        sizes = new int[width][height];
        north = new int[width+2];
        south = new int[width+2];
        east = new int[height];
        west = new int[height];


        innerRoom();
        addWalls();
        showing();
    }

    protected static void innerRoom() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 1;
            }
        }
    }

    protected static void addWalls() {
        Arrays.fill(north, 2);
        Arrays.fill(south, 2);
        Arrays.fill(east, 2);
        Arrays.fill(west, 2);
    }

    protected static void doorPosition() {
        Random generator = new Random();
        int [] array_choosed = new int [10];
        if (generator.nextInt(4) + 1 == 1)
            array_choosed = north;
        if (generator.nextInt(4) + 1 == 2)
            array_choosed = south;
        if (generator.nextInt(4) + 1 == 3)
            array_choosed = east;
        if (generator.nextInt(4) + 1 == 4)
            array_choosed = west;
        int position_random = generator.nextInt(array_choosed.length) + 1;
        addDoors(position_random, array_choosed);
    }

    protected static void addDoors (int position, int[] array) {
        position = position;
        array = array;
    }

    protected static void showing() {
        for (int i = 0; i < north.length; i++)
            System.out.print(north[i]);
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(west[i]);
            for (int j = 0; j < width; j++) {
                System.out.print(sizes[j][i]);
            }
            System.out.print(east[i]);
            System.out.println();
        }
        for (int i = 0; i < south.length; i++)
            System.out.print(south[i]);
    }
}
