import java.util.Arrays;
import java.util.Random;

public class Items {

    private static boolean [] mixtures_known = new boolean [7];
    private static int randomize;

    Items() {
        Arrays.fill(mixtures_known, false);
        Random generator = new Random();
        randomize = generator.nextInt(7);
    }

    static void checkItem (int position) {

        /* EMPTY */
        if (Interface.inventory[position] == 0)
            Interface.newEvent("This area is empty.");
        /* POTIONS */
        if (Interface.inventory[position] >= 1 && Interface.inventory[position] <= 7)
            mixtureType(Interface.inventory[position]);
        /* FOOD */
        if (Interface.inventory[position] == 8)
            Interface.newEvent("Fresh apple. Where did it come from?");
        if (Interface.inventory[position] == 9)
            Interface.newEvent("Dried meat. Someone hid it here a long time ago.");
    }

    private static void mixtureType(int index) {

        int type = Math.floorMod((randomize+index),7) - 1;

        String [] mixtures = new String [7];
        mixtures[0] = "liquid flame.";
        mixtures[1] = "invisibility.";
        mixtures[2] = "strength.";
        mixtures[3] = "dexterity.";
        mixtures[4] = "healing.";
        mixtures[5] = "experience.";
        mixtures[6] = "harm.";

        String [] colors = new String [8];
        colors[1] = "yellow";
        colors[2] = "blue";
        colors[3] = "red";
        colors[4] = "purple";
        colors[5] = "green";
        colors[6] = "aqua";
        colors[7] = "orange";

        if (!mixtures_known[index])
            Interface.newEvent("Bottle of " + colors[index] + " mixture. Unknown effects.");
        else
            Interface.newEvent("It is potion of " + mixtures[type]);
    }
}
