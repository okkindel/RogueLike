public class Items {

    static boolean was_clicked = false;
    static int last_position;

    Items() {
        new Mixtures();
    }

    void checkItem (int position, boolean action) {

        was_clicked = true;
        /* EMPTY */
        if (Interface.inventory[position] == 0) {
            if (!action)
                Interface.newEvent("This area is empty.");
            else
                Interface.newEvent("You cannot use it.");
        }
        /* POTIONS */
        if (Interface.inventory[position] >= 1 && Interface.inventory[position] <= 7) {
            if (!action)
                Mixtures.mixtureType(Interface.inventory[position] - 1);
            else
                Mixtures.drinkPotion(Interface.inventory[position] - 1);
        }
        /* FOOD */
        if (Interface.inventory[position] == 8) {
            if (!action)
                Interface.newEvent("Fresh apple. Where did it come from?");
            else {
                Character.hunger = 0;
                Interface.newEvent("Apple tastes great and cures hunger.");
            }
        }
        if (Interface.inventory[position] == 9) {
            if (!action)
                Interface.newEvent("Dried meat. Someone hid it here a long time ago.");
            else {
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
}