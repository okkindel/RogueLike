import java.util.Random;

public class Chests {

    int x_position;
    int y_position;
    private boolean was_taken = false;
    private int treasure;

    Chests (int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;

        Random generator = new Random();
        int random = generator.nextInt(30);

        if (random < 5)
            treasure = 88;
        else if (random < 7)
            treasure = 1;
        else if (random < 10)
            treasure = 2;
        else if (random < 12)
            treasure = 3;
        else if (random < 14)
            treasure = 4;
        else if (random < 19)
            treasure = 5;
        else if (random < 22)
            treasure = 6;
        else if (random < 24)
            treasure = 7;
        else if (random < 27)
            treasure = 8;
        else if (random < 30)
            treasure = 9;
    }

    void checkTreasure() {
        if (!was_taken) {
            if (treasure == 88)
                Interface.newEvent("There is nothing here.");
            else if (treasure > 0 && treasure < 7)
                Interface.newItem(Mixtures.prevPotion(treasure));
            else
                Interface.newItem(treasure);
        }
        else
            Interface.newEvent("This chest is empty.");
        was_taken = true;
    }
}
