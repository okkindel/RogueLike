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
            treasure = 0;
        else if (random < 10)
            treasure = 1;
        else if (random < 12)
            treasure = 2;
        else if (random < 14)
            treasure = 3;
        else if (random < 19)
            treasure = 4;
        else if (random < 22)
            treasure = 5;
        else if (random < 24)
            treasure = 6;
        else if (random < 27)
            treasure = 7;
        else if (random < 30)
            treasure = 8;
    }

    void checkTreasure() {
        if (!was_taken) {
            if (treasure == 88)
                Interface.newEvent("There is nothing here.");
            if (treasure == 0)
                Interface.newItem(Mixtures.prevPotion(0));
            if (treasure == 1)
                Interface.newItem(Mixtures.prevPotion(1));
            if (treasure == 2)
                Interface.newItem(Mixtures.prevPotion(2));
            if (treasure == 3)
                Interface.newItem(Mixtures.prevPotion(3));
            if (treasure == 4)
                Interface.newItem(Mixtures.prevPotion(4));
            if (treasure == 5)
                Interface.newItem(Mixtures.prevPotion(5));
            if (treasure == 6)
                Interface.newItem(Mixtures.prevPotion(6));
            if (treasure == 7)
                Interface.newItem(8);
            if (treasure == 8)
                Interface.newItem(9);
        }
        else
            Interface.newEvent("This chest is empty.");
        was_taken = true;
    }
}
