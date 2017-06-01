public class Items {
    static boolean was_clicked = false;
    static int last_position;

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
            Mixtures.mixtureType(Interface.inventory[position] - 1, false);
            if (action)
                Mixtures.mixtureType(Interface.inventory[position] - 1, true);
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
}