import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Room {

    private int [] north, south, east, west;
    int index = 0;
    int [][] sizes;
    int height, width = 0;
    boolean isEnemy, isZombiable, isSkeletonable, isGolemable, isGhostable = false;
    boolean former_room = false;
    private Random generator = new Random();
    ArrayList <Door> doors;
    ArrayList <Chests> chests_list;
    ArrayList <Drop> drop_list;

    Room (int index) {

        this.index = index;
        doors = new ArrayList<>();
        chests_list = new ArrayList<>();
        drop_list = new ArrayList<>();
        height = generator.nextInt(10) + 11;
        width = generator.nextInt(10) + 11;
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

        /* ROOM TYPE TILES */
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sizes[x][y] = 10;
                if (sizes[x][y] == 10) {
                    if (generator.nextInt(4) == 0)
                        sizes[x][y] = 11;
                }
            }
        }
        /* ROOM TYPE COLUMNS */
        if (height % 2 != 0 && width % 2 != 0) {
            /* ROOM TYPE COLUMNS VERTICAL */
            if (random == 0) {
                for (int y = 2; y < height - 2; y += 2) {
                    sizes[2][y] = 87;
                    sizes[width - 3][y] = 87;
                }
                isSkeletonable = true;
            }
            /* ROOM TYPE COLUMNS ROUND */
            if (random == 1) {
                for (int y = 2; y < height - 2; y += 2) {
                    sizes[2][y] = 87;
                    sizes[width - 3][y] = 87;
                }
                for (int x = 2; x < width - 2; x += 2) {
                    sizes[x][2] = 87;
                    sizes[x][height - 3] = 87;
                }
                isSkeletonable = true;
            }
            /* ROOM TYPE COLUMNS HALL */
            if (random == 2) {
                for (int x = 2; x < width - 2; x += 2) {
                    for (int y = 2; y < height - 2; y += 2) {
                        sizes[x][y] = 87;
                    }
                }
                isSkeletonable = true;
            }
        }
        /* ROOM TYPE GRASS */
        else if (random == 0) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (generator.nextInt(6) < 4)
                        sizes[x][y] = 13;
                    else if (generator.nextInt(6) < 3)
                        sizes[x][y] = 14;
                }
            }
            addChests();
            isGolemable = true;
        }
        /* ROOM TYPE WOODEN */
        else if (random == 1) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    sizes[x][y] = 12;
                }
            }
            /* ROOM TYPE LIBRARY */
            if (height % 2 != 0) {
                for (int x = 2; x < width - 2; x++) {
                    for (int y = 2; y < height - 2; y += 2) {
                        sizes[x][y] = 81;
                        if (generator.nextInt(30) == 0) {
                            sizes[x][y] = 25;
                            Chests chest = new Chests(x, y);
                            chests_list.add(chest);
                        }
                    }
                }
            }
            else {
                isZombiable = true;
                addChests();
            }
        }
        else { isGhostable = true; }

        if (isZombiable || isSkeletonable || isGolemable || isGhostable)
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

        for (int x = 0; x < width; x++) {
            sizes[x][0] = north[x];
            sizes[x][height-1] = south[x];
        }
        for (int y = 0; y < height; y++) {
            sizes[width-1][y] = east[y];
            sizes[0][y] = west[y];
        }
    }

    private void addDoors() {
        Random generator = new Random();
        for (int doorID = 0; doorID < Level.howManyRooms; doorID++) {
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

    private void addChests() {

        if (generator.nextInt(2) == 0) {

            int wall = generator.nextInt(4);

            if (wall == 0) {
                int place = generator.nextInt(width - 2) + 1;
                if (north[place] != 20) {
                    sizes[place][1] = 25;
                    Chests chest = new Chests(place, 1);
                    chests_list.add(chest);
                }
            }
            if (wall == 1) {
                int place = generator.nextInt(width - 2) + 1;
                if (south[place] != 20) {
                    sizes[place][height - 1] = 25;
                    Chests chest = new Chests(place, height - 1);
                    chests_list.add(chest);
                }
            }
            if (wall == 2) {
                int place = generator.nextInt(height - 2) + 1;
                if (east[place] != 20) {
                    sizes[width - 1][place] = 25;
                    Chests chest = new Chests(1, place);
                    chests_list.add(chest);
                }
            }
            if (wall == 3) {
                int place = generator.nextInt(height - 2) + 1;
                if (west[place] != 20) {
                    sizes[1][place] = 25;
                    Chests chest = new Chests(width - 1, place);
                    chests_list.add(chest);
                }
            }
        }
    }
}