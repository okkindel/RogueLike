import java.util.Random;

public class Drop {

    int x_position;
    int y_position;
    int index;
    private int treasure;

    Drop(int index, int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;
        this.index = index;

        Random generator = new Random();
        int random = generator.nextInt(125);

        if (random < 10)
            treasure = 1;
        else if (random < 20)
            treasure = 2;
        else if (random < 25)
            treasure = 3;
        else if (random < 30)
            treasure = 4;
        else if (random < 55)
            treasure = 5;
        else if (random < 60)
            treasure = 6;
        else if (random < 70)
            treasure = 7;
        else if (random < 90)
            treasure = 8;
        else if (random < 110)
            treasure = 9;
        else if (random < 115)
            treasure = 10;
        else if (random < 125)
            treasure = 11;
    }

    void checkTreasure() {
        if (treasure > 0 && treasure < 8)
            Interface.newItem(Mixtures.prevPotion(treasure - 1) + 1);
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            Interface.newItem(treasure);
        else if (treasure == 11) {
            Items.scrolls += 1;
            Interface.newEvent("I found an identify scroll!");
        }
    }
}
