import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Room {

    ArrayList<Door> doors;
    private int [] north, south, east, west;
    private int index = 0;
    int [][] sizes;
    int height = 0;
    int width = 0;
    boolean iWasHere = false;

    Room (int index) {
        Random generator = new Random();
        doors = new ArrayList<>();
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
        roomType();
    }

    private void innerRoom() {
        Random generator = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 10;
                if (sizes[j][i] == 10) {
                    if (generator.nextInt(4) == 0)
                        sizes[j][i] = 11;
                }
            }
        }
    }

    private void addWalls() {
        Arrays.fill(north, 88);
        Arrays.fill(south, 88);
        Arrays.fill(east, 88);
        Arrays.fill(west, 88);

        addDoors();

        for (int i = 0; i < width; i++) {
            sizes[i][0] = north[i];
            sizes[i][height-1] = south[i];
        }
        for (int i = 0; i < height; i++) {
            sizes[width-1][i] = east[i];
            sizes[0][i] = west[i];
        }
    }

    private void addDoors() {
        Random generator = new Random();
        for (int doorID = 0; doorID < Main.howManyRooms; doorID++) {
            if (StructureGenerator.structure[index][doorID]) {
                int random = generator.nextInt(4);
                int place;
                while (true) {
                    if (random == 0) {
                        place = generator.nextInt(north.length - 4) + 2;
                        if (north[place] != 3 && north[place - 1] != 20 && north[place + 1] != 20) {
                            north[place] = 20;
                            break;
                        }
                        else
                            random += 1;
                    }
                    if (random == 1) {
                        place = generator.nextInt(south.length - 4) + 2;
                        if (south[place] != 20 && south[place - 1] != 20 && south[place + 1] != 20) {
                            south[place] = 20;
                            break;
                        }
                        else
                            random += 1;
                    }
                    if (random == 2) {
                        place = generator.nextInt(east.length - 4) + 2;
                        if (east[place] != 20 && east[place - 1] != 20 && east[place + 1] != 20) {
                            east[place] = 20;
                            break;
                        }
                        else
                            random += 1;
                    }
                    if (random == 3) {
                        place = generator.nextInt(west.length - 4) + 2;
                        if (west[place] != 20 && west[place - 1] != 20 && west[place + 1] != 20) {
                            west[place] = 20;
                            break;
                        }
                        else
                            random = 0;
                    }
                }
                Door door = new Door(index, doorID, random, place, height, width);
                doors.add(door);
            }
        }
    }

    private void roomType() {
        Random generator = new Random();
        int random = generator.nextInt(5);

        /*ROOM TYPE COLUMNS*/
        if (random == 0) {
            for (int i = 2; i < height - 2; i+=2) {
                sizes[2][i] = 88;
                sizes[width-3][i] = 88;
            }
        }
    }
}