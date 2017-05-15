public class Character {

    private static int x_value = 1;
    private static int y_value = 1;
    static int whereAmI = 0;

    Character() {

        Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
    }

    void increaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] == 1) {
            y_value++;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
            Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] = 1;
        }
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] == 3) {
            whitchDoor(x_value, y_value+1);
        }
    }

    void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] == 1) {
            y_value--;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
            Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] = 1;
        }
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] == 3) {
            whitchDoor(x_value, y_value-1);
        }
    }

    void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] == 1) {
            x_value++;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 5;
            Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] = 1;
        }
        if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] == 3) {
            whitchDoor(x_value+1, y_value);
        }
    }

    void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] == 1) {
            x_value--;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
            Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] = 1;
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