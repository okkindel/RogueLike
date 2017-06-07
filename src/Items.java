public class Items {

    static boolean was_clicked = false;
    static int last_position;
    static int scrolls = 0;

    Items() {
        new Mixtures();
    }

    void checkItem(int position, boolean action) {

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
                Interface.newEvent("Fresh eggplant. Where did it come from?");
            else {
                Character.hunger = 0;
                Character.modifyHealth(25);
                Interface.newEvent("Eggplant tastes great and cures hunger.");
            }
        }
        if (Interface.inventory[position] == 9) {
            if (!action)
                Interface.newEvent("Dried meat. Someone hid it here a long time ago.");
            else {
                Character.hunger = 0;
                Character.modifyHealth(35);
                Interface.newEvent("Old and stiff but nutritious.");
            }
        }
        if (Interface.inventory[position] == 10) {
            if (!action)
                Interface.newEvent("A mushroom. I do not know if I should eat it.");
            else {
                Mixtures.character_confused = 15;
                Character.max_health += 10;
                Character.modifyHealth(-20);
                Interface.newEvent("I feel a bit ... weird.");
            }
        }
        if (action)
            dropItem(position);
    }

    void dropItem(int position) {
        System.arraycopy(Interface.inventory, position + 1,
                Interface.inventory, position, 9 - position);
        Interface.inventory[9] = 0;
        was_clicked = false;
    }

    void identify(int position) {
        if (scrolls > 0) {
            if (Interface.inventory[position] >= 1 && Interface.inventory[position] <= 7
                    && !Mixtures.mixtures_known[position - 1]) {
                Mixtures.identify(Interface.inventory[position] - 1);
                scrolls -= 1;
            } else
                Interface.newEvent("You don't have to identify it.");
        } else
            Interface.newEvent("You don't have enough scrolls.");
    }
}