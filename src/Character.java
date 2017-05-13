public class Character {

    protected static int x_value = 1;
    protected static int y_value = 1;
    protected static int whereAmI = 0;

    public Character() {

        Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
    }
    public void increaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] != 2) {
            y_value++;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
            Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] = 1;
        }
    }
    public void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] != 2) {
            y_value--;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
            Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] = 1;
        }
    }
    public void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] != 2) {
            x_value++;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 5;
            Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] = 1;
        }
    }
    public void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] != 2) {
            x_value--;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 4;
            Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] = 1;
        }
    }

}