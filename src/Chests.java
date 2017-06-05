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
        int random = generator.nextInt(33);

        if (random < 2)
            treasure = 0;
        else if (random < 5)
            treasure = 1;
        else if (random < 7)
            treasure = 2;
        else if (random < 9)
            treasure = 3;
        else if (random < 15)
            treasure = 4;
        else if (random < 17)
            treasure = 5;
        else if (random < 19)
            treasure = 6;
        else if (random < 22)
            treasure = 8;
        else if (random < 25)
            treasure = 9;
        else if (random < 28)
            treasure = 10;
    }

    void checkTreasure() {
        if (!was_taken) {
            if (treasure >= 0 && treasure < 7)
                Interface.newItem(Mixtures.prevPotion(treasure) + 1);
            else if (treasure == 8 || treasure == 9)
                Interface.newItem(treasure);
            else if (treasure == 10)
                Interface.scrolls += 1;
            else
                Interface.newEvent("There is nothing here.");
        }
        else
            Interface.newEvent("This chest is empty.");
        was_taken = true;
    }
}
