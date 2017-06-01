import java.util.Arrays;
import java.util.Random;

public class Items {

    private static boolean [] mixtures_known = new boolean [7];
    private static int randomize;
    static boolean was_clicked = false;
    static int last_position;
    static int character_paralyze = 0;

    Items() {
        Arrays.fill(mixtures_known, false);
        Random generator = new Random();
        randomize = generator.nextInt(7);
    }

    void checkItem (int position, boolean action) {

        was_clicked = true;
        /* EMPTY */
        if (Interface.inventory[position] == 0) {
            Interface.newEvent("This area is empty.");
            if (action)
                Interface.newEvent("You cannot use it.");
        }
        /* POTIONS */
        if (Interface.inventory[position] >= 1 && Interface.inventory[position] <= 7) {
            mixtureType(Interface.inventory[position] - 1, false);
            if (action)
                mixtureType(Interface.inventory[position] - 1, true);
        }
        /* FOOD */
        if (Interface.inventory[position] == 8) {
            Interface.newEvent("Fresh apple. Where did it come from?");
            if (action) {
                Character.hunger = 0;
                Interface.newEvent("Apple tastes great and cures hunger.");
            }
        }
        if (Interface.inventory[position] == 9) {
            Interface.newEvent("Dried meat. Someone hid it here a long time ago.");
            if (action) {
                Character.hunger = 0;
                Interface.newEvent("Old and stiff but nutritious.");
            }
        }
        if (action)
            dropItem(position);
    }

    void dropItem (int position) {
        System.arraycopy(Interface.inventory, position + 1,
                Interface.inventory, position, 9 - position);
        Interface.inventory[8] = 0;
        was_clicked = false;
    }

    private void mixtureType (int index, boolean action) {

        int type = Math.floorMod((randomize+index),7);
        String [] mixtures = new String [7];
        mixtures[0] = "liquid flame";
        mixtures[1] = "paralytic gas";
        mixtures[2] = "strength";
        mixtures[3] = "dexterity";
        mixtures[4] = "healing";
        mixtures[5] = "experience";
        mixtures[6] = "harm";
        String [] colors = new String [7];
        colors[0] = "yellow";
        colors[1] = "blue";
        colors[2] = "red";
        colors[3] = "purple";
        colors[4] = "green";
        colors[5] = "aqua";
        colors[6] = "orange";

        if (!mixtures_known[index])
            Interface.newEvent("Bottle of " + colors[index] + " mixture. Unknown effects.");
        else
            Interface.newEvent("It is potion of " + mixtures[type] + ". Are you sure you want to drink it?");


        if (action) {
            if (type == 0) {
                Interface.newEvent("Nothing happened.");
            }
            if (type == 1) {
                Interface.newEvent("Strange... I cannot move.");
                character_paralyze = 5;
            }
            if (type == 2) {
                Interface.newEvent("You feel much stronger!");
                Character.strength_points += 5;
            }
            if (type == 3) {
                Interface.newEvent("You feel much more skilful.");
                Character.dexterity_points += 5;
            }
            if (type == 4) {
                Interface.newEvent("You feel awesome.");
                Character.health_points = Character.max_health;
            }
            if (type == 5) {
                Interface.newEvent("You feel much more experienced.");
                Character.experience(Character.next_level - Character.experience);
            }
            if (type == 6) {
                Interface.newEvent("You feel terrible.");
                Character.health_points /= 2;
            }
            mixtures_known[index] = true;
        }
    }
}
