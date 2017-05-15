import java.lang.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Room {

    ArrayList<Door> doors;
    private int [] north, south, east, west;
    protected int index = 0;
    int [][] sizes;
    int height = 0;
    int width = 0;

    Room(int index) {
        Random generator = new Random();
        doors = new ArrayList<Door>();
        height = generator.nextInt(10) + 10;
        width = generator.nextInt(10) + 10;
        sizes = new int[width][height];
        north = new int[width];
        south = new int[width];
        east = new int[height];
        west = new int[height];
        this.index = index;

        innerRoom();
        addWalls();
    }

    private void innerRoom() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 1;
            }
        }
    }

    private void addWalls() {
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

    private void addDoors() {
        Random generator = new Random();
        for (int i = 0; i < Main.howManyRooms; i++) {
            if(StructureGenerator.structure[index][i]) {
                int random = generator.nextInt(4);
                int place = generator.nextInt(5)+1;
                Door door = new Door(index, i, random, place, height, width);
                if(random == 0)
                    north[place] = 3;
                if(random == 1)
                    west[place] = 3;
                if(random == 2)
                    south[place] = 3;
                if(random == 3)
                    east[place] = 3;
                doors.add(door);
            }
        }
    }
}