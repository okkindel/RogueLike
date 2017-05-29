public class Character {

    static int x_value, y_value;
    static int last_tile = 0;
    static int whereAmI = 0;
    static int health_points = 500;
    static int damage_points = 15;
    static int defence_points = 5;

    Character() {
        Room room = Main.rooms.get(0);
        last_tile = room.sizes[room.width/2][room.height/2];
        x_value = room.width / 2;
        y_value = room.height / 2;
        Main.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
        room.iWasHere = true;
    }

    /* CHARACTER GOING DOWN */
    void increaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] == 20)
            try {
            whichDoor(x_value, y_value + 1);
            } catch (Exception ex) { teleport(); }
        else if (Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] < 70
              || Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] > 99) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value + 1];
            y_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 47;
        } else if (Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] >= 70
                || Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] <= 80) {
            for (Enemies enemy: Enemies.enemies_list) {
                if (enemy.index == whereAmI) {
                    if (x_value == enemy.positionX && (y_value + 1) == enemy.positionY)
                        Battle.characterAttack(enemy);
                }
            }
        }
    }

    /* CHARACTER GOING UP */
    void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] == 20)
            try {
            whichDoor(x_value, y_value - 1);
            } catch (Exception ex) { teleport(); }
        else if (Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] < 70
              || Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] > 99) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value - 1];
            y_value -= 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 46;
        } else if (Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] >= 70
                || Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] <= 80) {
            for (Enemies enemy: Enemies.enemies_list) {
                if (enemy.index == whereAmI) {
                    if (x_value == enemy.positionX && (y_value - 1) == enemy.positionY)
                        Battle.characterAttack(enemy);
                }
            }
        }
    }

    /* CHARACTER GOING RIGHT */
    void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] == 20)
            try {
                whichDoor(x_value + 1, y_value);
            } catch (Exception ex) { teleport(); }
        else if (Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] < 70
              || Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] > 99) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value + 1][y_value];
            x_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 45;
        } else if (Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] >= 70
                || Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] <= 80) {
            for (Enemies enemy: Enemies.enemies_list) {
                if (enemy.index == whereAmI) {
                    if ((x_value + 1) == enemy.positionX && y_value == enemy.positionY)
                        Battle.characterAttack(enemy);
                }
            }
        }
    }

    /* CHARACTER GOING LEFT */
    void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] == 20)
            try {
                whichDoor(x_value - 1, y_value);
            } catch (Exception ex) { teleport(); }
        else if (Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] < 70
              || Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] > 99) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value - 1][y_value];
            x_value -= 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
        } else if (Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] >= 70
                || Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] <= 80) {
            for (Enemies enemy: Enemies.enemies_list) {
                if (enemy.index == whereAmI) {
                    if ((x_value - 1) == enemy.positionX && y_value == enemy.positionY)
                        Battle.characterAttack(enemy);
                }
            }
        }
    }

    /* theoretically, now there shouldn't be error at the door */
    private void teleport() {
        System.out.println("What? I've been teleported!");
        int index = Main.rooms.get(whereAmI).generator.nextInt(Main.rooms.size());
        Room room = Main.rooms.get(index);
        while (true)
        {
            whereAmI = index;
            int x_position = room.generator.nextInt(room.width - 2) + 1;
            int y_position = room.generator.nextInt(room.height - 2) + 1;
            if (room.sizes[x_value][y_value] >= 10 && room.sizes[x_value][y_value] < 20) {
                x_value = x_position;
                y_value = y_position;
                break;
            }
        }
    }

    private void whichDoor (int x, int y) {

        for (Door door : Main.rooms.get(whereAmI).doors) {
            if (door.x == x && door.y == y) {
                Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
                int whereIWas = whereAmI;
                whereAmI = door.where;
                Room room = Main.rooms.get(whereAmI);
                if (whereIWas == whereAmI)
                    System.out.println("Strange... I'm back in the same room.");
                else if (room.iWasHere)
                    System.out.println("I already was here...");
                else
                    System.out.println("I've never seen this room before...");
                room.iWasHere = true;
                for (Door newdoor : Main.rooms.get(whereAmI).doors) {
                    if (newdoor.where == whereIWas) {
                        Room newroom = Main.rooms.get(whereAmI);
                        last_tile = newroom.sizes[newdoor.posx][newdoor.posy];
                        if (newdoor.wall == 0)
                            Main.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 47;
                        if (newdoor.wall == 1)
                            Main.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 46;
                        if (newdoor.wall == 2)
                            Main.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 44;
                        if (newdoor.wall == 3)
                            Main.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 45;

                        x_value = newdoor.posx;
                        y_value = newdoor.posy;
                    }
                }
            }
        }
    }
}