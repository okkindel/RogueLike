public class Items {

    static boolean was_clicked = false;
    static int last_position;
    static int scrolls = 1;
    static boolean eq_full = false;

    Items() {
        new Mixtures();
    }

    void checkItem(int position, boolean action) {

        was_clicked = true;

        if (Interface.inventory_shown) {
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
            if (Interface.inventory[position] >= 11 && Interface.inventory[position] <= 16) {
                if (!action)
                    Rings.ringType(Interface.inventory[position] - 10);
                else
                    Rings.useRing(Interface.inventory[position] - 10);
            }
            if (Interface.inventory[position] >= 21 && Interface.inventory[position] <= 23) {
                if (!action)
                    Swords.swordType(Interface.inventory[position] - 20);
                else
                    Swords.useSword(Interface.inventory[position] - 20);
            }
            if (action && !eq_full)
                removeItem(position);
        } else {
            if (Interface.equipment[position] >= 11 && Interface.equipment[position] <= 16) {
                if (!action)
                    Rings.ringType(Interface.equipment[position] - 10);
            }
            if (Interface.equipment[position] >= 21 && Interface.equipment[position] <= 23) {
                if (!action)
                    Swords.swordType(Interface.equipment[position] - 20);
            }
            if (action)
                dropEquipment(position);
        }
        eq_full = false;
    }

    void dropItem(int position) {
        if (Interface.inventory_shown) {
            Room room = Level.levels_list.get(Character.present_level).get(Character.present_room);
            room.drop_list.add(new Drop(Character.x_value, Character.y_value, Interface.inventory[position]));
            System.arraycopy(Interface.inventory, position + 1,
                    Interface.inventory, position, 9 - position);
            Interface.inventory[9] = 0;
        }
        was_clicked = false;
    }

    private void removeItem(int position) {
        System.arraycopy(Interface.inventory, position + 1,
                Interface.inventory, position, 9 - position);
        Interface.inventory[9] = 0;
        was_clicked = false;
    }

    private void dropEquipment(int position) {
        if (!Interface.inventory_shown) {
            Interface.newItem(Interface.equipment[position]);
            Interface.equipment[position] = 0;
        }
        was_clicked = false;
    }

    void identify(int position) {
        if (scrolls > 0) {
            if (Interface.inventory[position] >= 1 && Interface.inventory[position] <= 7
                    && !Mixtures.mixtures_known[Interface.inventory[position] - 1]) {
                Mixtures.identify(Interface.inventory[position] - 1);
                scrolls -= 1;
            } else if (Interface.inventory[position] >= 11 && Interface.inventory[position] <= 16
                    && !Rings.rings_known[Interface.inventory[position] - 10]) {
                Rings.identify(Interface.inventory[position] - 10);
                scrolls -= 1;
            } else
                Interface.newEvent("You don't have to identify it.");
        } else
            Interface.newEvent("You don't have enough scrolls.");
    }
}