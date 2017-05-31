public class Items {

    static void checkItem (int position) {
        if (Interface.inventory[position] == 0)
            Interface.newEvent("This area is empty.");
        /* POTIONS */
        if (Interface.inventory[position] == 1)
            Interface.newEvent("Mixture.");
        /* FOOD */
        if (Interface.inventory[position] == 8)
            Interface.newEvent("Fresh apple. Where did it come from?");
        if (Interface.inventory[position] == 9)
            Interface.newEvent("Dried meat Someone hid it here a long time ago.");
    }
}
