import java.util.Random;

public class Drop {

    int x_position;
    int y_position;
    int treasure;

    Drop (int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;

        Random generator = new Random();
        int random = generator.nextInt(30);

        if (random < 3)
            treasure = 1;
        else if (random < 6)
            treasure = 2;
        else if (random < 9)
            treasure = 3;
        else if (random < 12)
            treasure = 4;
        else if (random < 16)
            treasure = 5;
        else if (random < 19)
            treasure = 6;
        else if (random < 22)
            treasure = 7;
        else if (random < 26)
            treasure = 8;
        else if (random < 30)
            treasure = 9;
    }

    void checkTreasure() {
        if (treasure > 0 && treasure < 8)
            Interface.newItem(Mixtures.prevPotion(treasure));
        else
            Interface.newItem(treasure);
    }
}
