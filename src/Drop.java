import java.util.Random;

public class Drop {

    int x_position;
    int y_position;
    private int treasure;

    Drop(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;

        Random generator = new Random();
        int random = generator.nextInt(150);

        if (random < 70)
            treasure = Mixtures.dropMixture();
        else if (random < 90)
            treasure = 8;
        else if (random < 110)
            treasure = 9;
        else if (random < 115)
            treasure = 10;
        else if (random < 125)
            treasure = 50;
        else if (random < 135)
            treasure = Rings.dropRing();
        else if (random < 140)
            treasure = Swords.dropSword();
        else if (random < 145)
            treasure = Armors.dropArmor();
        else if (random < 150)
            treasure = Shields.dropShield();
    }

    Drop(int x_position, int y_position, int treasure) {
        this.x_position = x_position;
        this.y_position = y_position;
        if (treasure > 0 && treasure < 8)
            this.treasure = Mixtures.nextPotion(treasure - 1) + 1;
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            this.treasure = treasure;
        else if (treasure > 20)
            this.treasure = treasure;
    }

    void checkTreasure() {
        if (treasure > 0 && treasure < 8)
            Interface.newItem(Mixtures.prevPotion(treasure - 1) + 1);
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            Interface.newItem(treasure);
        else if (treasure >= 11 && treasure <= 16)
            Interface.newItem(treasure);
        else if (treasure == 50) {
            Items.scrolls += 1;
            Interface.newEvent("I found an identify scroll!");
        } else if (treasure >= 11 && treasure <= 16) {
            Interface.newItem(treasure);
        } else if (treasure == 21 || treasure == 22 || treasure == 23) {
            Interface.newItem(treasure);
        } else if (treasure == 31 || treasure == 32 || treasure == 33) {
            Interface.newItem(treasure);
        } else if (treasure == 41 || treasure == 42 || treasure == 43) {
            Interface.newItem(treasure);
        }
    }
}
