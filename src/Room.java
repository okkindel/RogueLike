import java.lang.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;


public class Room {

    protected static int height = 0;
    protected static int width = 0;
    protected static int index = 0;
    protected static int [][] sizes;
    protected static int [] north, south, east, west;
    protected static ArrayList<Door> doors;

    public Room(int index) {
        Random generator = new Random();
        doors = new ArrayList<Door>();
        this.index = index;
        height = generator.nextInt(10) + 10;
        width = generator.nextInt(10) + 10;
        sizes = new int[width][height];
        north = new int[width];
        south = new int[width];
        east = new int[height];
        west = new int[height];


        innerRoom();
        addWalls();
        terminalShowing();
    }

    private static void innerRoom() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 1;
            }
        }
    }

    private static void addWalls() {
        Arrays.fill(north, 2);
        Arrays.fill(south, 2);
        Arrays.fill(east, 2);
        Arrays.fill(west, 2);

        addDoors();

        for (int i = 0; i < width; i++) {
            sizes[i][0] = north[i];
            sizes[i][height-1] = south[i];
        }
        for (int i = 0; i < height; i++) {
            sizes[0][i] = east[i];
            sizes[width-1][i] = west[i];
        }
    }

    private static void addDoors() {
        Random generator = new Random();
        for (int i = 0; i < Main.howManyRooms; i++) {
            if(StructureGenerator.structure[index][i] == true) {
                int random = generator.nextInt(4);
                int place = generator.nextInt(5)+1;
                Door dr = new Door(index, i, random, place); //do poprawy losowanie
                if(random == 0){
                    north[place] = 3;
                }
                if(random == 1){
                    east[place] = 3;
                }
                if(random == 2){
                    south[place] = 3;
                }
                if(random == 3){
                    west[place] = 3;
                }
                doors.add(dr);
            }
        }
    }

    private static void terminalShowing() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(sizes[j][i]);
            }
            System.out.println();
        }
    }
}