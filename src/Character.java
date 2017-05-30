public class Character {

    static int x_value, y_value;
    static int last_tile = 0;
    static int whereAmI = 0;
    static int max_hp = 500;
    static int health_points = 500;
    static int damage_points = 15;
    static int dexterity_points = 25;
    static int defence_points = 5;
    static int experience = 0;
    static int next_level = 100;
    static int level = 1;

    Character() {
        Room room = Level.rooms.get(0);
        last_tile = room.sizes[room.width/2][room.height/2];
        x_value = room.width / 2;
        y_value = room.height / 2;
        Level.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
        room.iWasHere = true;
    }

    /* CHARACTER GOING DOWN */
    void increaseY() {
        try {
            if (Level.rooms.get(whereAmI).sizes[x_value][y_value + 1] == 20)
                whichDoor(x_value, y_value + 1);
            else if (Level.rooms.get(whereAmI).sizes[x_value][y_value + 1] < 70
                    || Level.rooms.get(whereAmI).sizes[x_value][y_value + 1] > 99) {
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
                last_tile = Level.rooms.get(whereAmI).sizes[x_value][y_value + 1];
                y_value += 1;
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = 47;
            } else if (Level.rooms.get(whereAmI).sizes[x_value][y_value + 1] >= 70
                    || Level.rooms.get(whereAmI).sizes[x_value][y_value + 1] <= 80) {
                for (Enemies enemy : Enemies.enemies_list) {
                    if (enemy.index == whereAmI) {
                        if (x_value == enemy.positionX && (y_value + 1) == enemy.positionY)
                            Battle.characterAttack(enemy);
                    }
                }
            }
        } catch (Exception ex) { teleport(); }
    }

    /* CHARACTER GOING UP */
    void decreaseY() {
        try {
            if (Level.rooms.get(whereAmI).sizes[x_value][y_value - 1] == 20)
                whichDoor(x_value, y_value - 1);
            else if (Level.rooms.get(whereAmI).sizes[x_value][y_value - 1] < 70
                    || Level.rooms.get(whereAmI).sizes[x_value][y_value - 1] > 99) {
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
                last_tile = Level.rooms.get(whereAmI).sizes[x_value][y_value - 1];
                y_value -= 1;
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = 46;
            } else if (Level.rooms.get(whereAmI).sizes[x_value][y_value - 1] >= 70
                    || Level.rooms.get(whereAmI).sizes[x_value][y_value - 1] <= 80) {
                for (Enemies enemy : Enemies.enemies_list) {
                    if (enemy.index == whereAmI) {
                        if (x_value == enemy.positionX && (y_value - 1) == enemy.positionY)
                            Battle.characterAttack(enemy);
                    }
                }
            }
        } catch (Exception ex) { teleport(); }
    }

    /* CHARACTER GOING RIGHT */
    void increaseX() {
        try {
            if (Level.rooms.get(whereAmI).sizes[x_value + 1][y_value] == 20)
                whichDoor(x_value + 1, y_value);
            else if (Level.rooms.get(whereAmI).sizes[x_value + 1][y_value] < 70
                    || Level.rooms.get(whereAmI).sizes[x_value + 1][y_value] > 99) {
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
                last_tile = Level.rooms.get(whereAmI).sizes[x_value + 1][y_value];
                x_value += 1;
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = 45;
            } else if (Level.rooms.get(whereAmI).sizes[x_value + 1][y_value] >= 70
                    || Level.rooms.get(whereAmI).sizes[x_value + 1][y_value] <= 80) {
                for (Enemies enemy : Enemies.enemies_list) {
                    if (enemy.index == whereAmI) {
                        if ((x_value + 1) == enemy.positionX && y_value == enemy.positionY)
                            Battle.characterAttack(enemy);
                    }
                }
            }
        } catch (Exception ex) { teleport(); }
    }

    /* CHARACTER GOING LEFT */
    void decreaseX() {
        try {
            if (Level.rooms.get(whereAmI).sizes[x_value - 1][y_value] == 20)
                whichDoor(x_value - 1, y_value);
            else if (Level.rooms.get(whereAmI).sizes[x_value - 1][y_value] < 70
                    || Level.rooms.get(whereAmI).sizes[x_value - 1][y_value] > 99) {
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
                last_tile = Level.rooms.get(whereAmI).sizes[x_value - 1][y_value];
                x_value -= 1;
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
            } else if (Level.rooms.get(whereAmI).sizes[x_value - 1][y_value] >= 70
                    || Level.rooms.get(whereAmI).sizes[x_value - 1][y_value] <= 80) {
                for (Enemies enemy : Enemies.enemies_list) {
                    if (enemy.index == whereAmI) {
                        if ((x_value - 1) == enemy.positionX && y_value == enemy.positionY)
                            Battle.characterAttack(enemy);
                    }
                }
            }
        } catch (Exception ex) { teleport(); }
    }

    /* theoretically, now there shouldn't be error at the door */
    private void teleport() {
        System.out.println("What? I've been teleported!");
        int index = Level.rooms.get(whereAmI).generator.nextInt(Level.rooms.size());
        Room room = Level.rooms.get(index);
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

        for (Door door : Level.rooms.get(whereAmI).doors) {
            if (door.x == x && door.y == y) {
                Level.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
                int whereIWas = whereAmI;
                whereAmI = door.where;
                Room room = Level.rooms.get(whereAmI);
                if (whereIWas == whereAmI)
                    System.out.println("Strange... I'm back in the same room.");
                else if (room.iWasHere)
                    System.out.println("I already was here...");
                else
                    System.out.println("I've never seen this room before...");
                room.iWasHere = true;
                for (Door newdoor : Level.rooms.get(whereAmI).doors) {
                    if (newdoor.where == whereIWas) {
                        Room newroom = Level.rooms.get(whereAmI);
                        last_tile = newroom.sizes[newdoor.posx][newdoor.posy];
                        if (newdoor.wall == 0)
                            Level.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 47;
                        if (newdoor.wall == 1)
                            Level.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 46;
                        if (newdoor.wall == 2)
                            Level.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 44;
                        if (newdoor.wall == 3)
                            Level.rooms.get(whereAmI).sizes[newdoor.posx][newdoor.posy] = 45;

                        x_value = newdoor.posx;
                        y_value = newdoor.posy;
                    }
                }
            }
        }
    }

    static void characterExp (Enemies enemy) {
        experience += enemy.experience_points;
        if (experience >= next_level) {
            level += 1;
            System.out.println("You are now on level " + level + ".\n");
            experience = experience - next_level;
            next_level += 50;
        }
    }
}