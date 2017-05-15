public class Character {

<<<<<<< HEAD
    private static int x_value = 1;
    private static int y_value = 1;
    static int whereAmI = 0;
=======
    protected static int x_value = 1;
    protected static int y_value = 1;
    protected static int whereAmI = 0;
    protected int last_tile = 1;
>>>>>>> origin/maku

    Character() {

        Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
    }

<<<<<<< HEAD
    void increaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] == 1) {
=======
    public void increaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] != 2) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value ] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value+1];
>>>>>>> origin/maku
            y_value++;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;

        }
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] == 3) {
            whitchDoor(x_value, y_value+1);
        }
    }

<<<<<<< HEAD
    void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] == 1) {
=======
    public void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] != 2) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value ] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value-1];
>>>>>>> origin/maku
            y_value--;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;

        }
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] == 3) {
            whitchDoor(x_value, y_value-1);
        }
    }

<<<<<<< HEAD
    void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] == 1) {
=======
    public void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] != 2) {
            Main.rooms.get(whereAmI).sizes[x_value ][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value+1][y_value];
>>>>>>> origin/maku
            x_value++;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 5;

        }
        if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] == 3) {
            whitchDoor(x_value+1, y_value);
        }
    }

<<<<<<< HEAD
    void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] == 1) {
=======
    public void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] != 2) {
            Main.rooms.get(whereAmI).sizes[x_value ][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value - 1][y_value];
>>>>>>> origin/maku
            x_value--;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;

        }
        if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] == 3) {
            whitchDoor(x_value-1, y_value);
        }
    }

    private static void whitchDoor(int x, int y) {

        for (Door door: Main.rooms.get(whereAmI).doors) {
            if (door.x == x && door.y == y) {
                Main.rooms.get(whereAmI).sizes[x_value][y_value] = 1;
                System.out.println("wchodze na drzwi na kordach:" + x + " " + y);
                whereAmI = door.where;
                Main.rooms.get(whereAmI).sizes[1][1] = 4;
                x_value = 1;
                y_value = 1;
            }
        }
    }

}