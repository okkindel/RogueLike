import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Room {

    ArrayList<Door> doors;
    private int [] north, south, east, west;
    int index = 0;
    int [][] sizes;
    int height, width = 0;
    boolean isEnemy, isZombiable, isSkeletonable, isGolemable = false;
    boolean iWasHere = false;
    private Random generator = new Random();

    Room (int index) {

        this.index = index;
        doors = new ArrayList<>();
        height = generator.nextInt(10) + 10;
        width = generator.nextInt(10) + 10;
        sizes = new int[width][height];
        north = new int[width];
        south = new int[width];
        east = new int[height];
        west = new int[height];
        roomType();
        addWalls();
    }

    private void roomType() {
        Random generator = new Random();
        int random = generator.nextInt(3);

        /*ROOM TYPE TILES*/
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 10;
                if (sizes[j][i] == 10) {
                    if (generator.nextInt(4) == 0)
                        sizes[j][i] = 11;
                }
            }
        }

        if (height % 2 != 0 && width % 2 != 0) {
            /*ROOM TYPE COLUMNS VERTICAL*/
            if (random == 0) {
                for (int i = 2; i < height - 2; i += 2) {
                    sizes[2][i] = 87;
                    sizes[width - 3][i] = 87;
                }
                isSkeletonable = true;
            }
            /*ROOM TYPE COLUMNS ROUND*/
            if (random == 1) {
                for (int i = 2; i < height - 2; i += 2) {
                    sizes[2][i] = 87;
                    sizes[width - 3][i] = 87;
                }
                for (int i = 2; i < width - 2; i += 2) {
                    sizes[i][2] = 87;
                    sizes[i][height - 3] = 87;
                }
                isSkeletonable = true;
            }
            /*ROOM TYPE COLUMNS HALL*/
            if (random == 2) {
                for (int i = 2; i < height - 2; i += 2) {
                    for (int j = 2; j < width - 2; j += 2) {
                        sizes[j][i] = 87;
                    }
                }
                isSkeletonable = true;
            }
        }
        /*ROOM TYPE GRASS*/
        else if (random == 0) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (generator.nextInt(6) < 4)
                        sizes[j][i] = 13;
                    else if (generator.nextInt(6) < 3)
                        sizes[j][i] = 14;
                }
            }
            isGolemable = true;
        }
        /*ROOM TYPE WOODEN*/
        else if (random == 1) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    sizes[j][i] = 12;
                }
            }
            /*ROOM TYPE LIBRARY*/
            if (height % 2 != 0) {
                for (int i = 2; i < height - 2; i += 2) {
                    for (int j = 2; j < width - 2; j++) {
                        sizes[j][i] = 81;
                        if (generator.nextInt(30) == 0)
                            sizes[j][i] = 82;
                    }
                }
            }
            else { isZombiable = true; }
        }

        if (isZombiable || isSkeletonable || isGolemable)
            isEnemy = true;
    }

    private void addWalls() {
        Arrays.fill(north, 88);
        Arrays.fill(south, 88);
        Arrays.fill(east, 88);
        Arrays.fill(west, 88);

        for (int i = 2; i < north.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                north[i] = 90;
            else if (generator.nextInt(5) == 0)
                north[i] = 94;
            else if (generator.nextInt(50) == 0)
                north[i] = 89;
        }
        for (int i = 2; i < south.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                south[i] = 91;
            else if (generator.nextInt(5) == 0)
                south[i] = 95;
            else if (generator.nextInt(50) == 0)
                south[i] = 89;
        }
        for (int i = 2; i < west.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                west[i] = 92;
            else if (generator.nextInt(5) == 0)
                west[i] = 96;
            else if (generator.nextInt(50) == 0)
                west[i] = 89;
        }
        for (int i = 2; i < east.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                east[i] = 93;
            else if (generator.nextInt(5) == 0)
                east[i] = 97;
            else if (generator.nextInt(50) == 0)
                east[i] = 89;
        }

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
                int wall = generator.nextInt(4);
                int place;
                while (true) {
                    if (wall == 0) {
                        place = generator.nextInt(north.length - 4) + 2;
                        if (north[place] != 3 && north[place - 1] != 20 && north[place + 1] != 20) {
                            north[place] = 20;
                            break;
                        }
                        else
                            wall += 1;
                    }
                    if (wall == 1) {
                        place = generator.nextInt(south.length - 4) + 2;
                        if (south[place] != 20 && south[place - 1] != 20 && south[place + 1] != 20) {
                            south[place] = 20;
                            break;
                        }
                        else
                            wall += 1;
                    }
                    if (wall == 2) {
                        place = generator.nextInt(east.length - 4) + 2;
                        if (east[place] != 20 && east[place - 1] != 20 && east[place + 1] != 20) {
                            east[place] = 20;
                            break;
                        }
                        else
                            wall += 1;
                    }
                    if (wall == 3) {
                        place = generator.nextInt(west.length - 4) + 2;
                        if (west[place] != 20 && west[place - 1] != 20 && west[place + 1] != 20) {
                            west[place] = 20;
                            break;
                        }
                        else
                            wall = 0;
                    }
                }
                Door door = new Door(index, doorID, wall, place, height, width);
                doors.add(door);
            }
        }
    }
}