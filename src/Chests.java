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
        int random = generator.nextInt(130);

        if (random < 10)
            treasure = 0;
        else if (random < 20)
            treasure = 1;
        else if (random < 30)
            treasure = 2;
        else if (random < 35)
            treasure = 3;
        else if (random < 40)
            treasure = 4;
        else if (random < 65)
            treasure = 5;
        else if (random < 70)
            treasure = 6;
        else if (random < 80)
            treasure = 7;
        else if (random < 100)
            treasure = 8;
        else if (random < 120)
            treasure = 9;
        else if (random < 130)
            treasure = 10;
    }

    void checkTreasure() {
        if (!was_taken) {
            if (treasure == 0)
                Interface.newEvent("There is nothing here.");
            else if (treasure > 0 && treasure < 8)
                Interface.newItem(Mixtures.prevPotion(treasure - 1) + 1);
            else if (treasure == 8 || treasure == 9)
                Interface.newItem(treasure);
            else if (treasure == 10)
                Items.scrolls += 1;
        }
        else
            Interface.newEvent("This chest is empty.");
        was_taken = true;
    }
}
