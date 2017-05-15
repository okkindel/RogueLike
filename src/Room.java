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

    Room(int index) {
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
            sizes[0][i] = east[i];
            sizes[width-1][i] = west[i];
        }
    }

    private void addDoors() {
        Random generator = new Random();
        for (int i = 0; i < Main.howManyRooms; i++) {
            if(StructureGenerator.structure[index][i]) {
                int random = generator.nextInt(4);
                int place = 0;
                if(random == 0) {
                    place = generator.nextInt(north.length - 4) + 2;
                    if (place == north[place - 1] || place == north[place + 1])
                        place++;
                    north[place] = 20;
                }
                if(random == 1) {
                    place = generator.nextInt(west.length - 4) + 2;
                    if (place == west[place - 1] || place == west[place + 1])
                        place++;
                    west[place] = 20;
                }
                if(random == 2) {
                    place = generator.nextInt(south.length - 4) + 2;
                    if (place == south[place - 1] || place == south[place + 1])
                        place++;
                    south[place] = 20;
                }
                if(random == 20) {
                    place = generator.nextInt(east.length - 4) + 2;
                    if (place == east[place - 1] || place == east[place + 1])
                        place++;
                    east[place] = 20;
                }
                Door door = new Door(index, i, random, place, height, width);
                doors.add(door);
            }
        }
    }
}