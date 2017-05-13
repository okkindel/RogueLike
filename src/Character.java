public class Character {

    protected static int x_value = 1;
    protected static int y_value = 1;

    public Character() {

        Room.sizes[x_value][y_value] = 4;
    }
    public void increaseY() {
        if (Room.sizes[x_value][y_value+1] != 2) {
            y_value++;
            Room.sizes[x_value][y_value] = 4;
            Room.sizes[x_value][y_value - 1] = 1;
        }
    }
    public void decreaseY() {
        if (Room.sizes[x_value][y_value-1] != 2) {
            y_value--;
            Room.sizes[x_value][y_value] = 4;
            Room.sizes[x_value][y_value + 1] = 1;
        }
    }
    public void increaseX() {
        if (Room.sizes[x_value+1][y_value] != 2) {
            x_value++;
            Room.sizes[x_value][y_value] = 4;
            Room.sizes[x_value - 1][y_value] = 1;
        }
    }
    public void decreaseX() {
        if (Room.sizes[x_value-1][y_value] != 2) {
            x_value--;
            Room.sizes[x_value][y_value] = 4;
            Room.sizes[x_value + 1][y_value] = 1;
        }
    }

}