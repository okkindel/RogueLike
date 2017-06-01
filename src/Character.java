import java.util.ListIterator;

public class Character {

    static int x_value, y_value;
    static int last_tile = 0;
    static int present_room = 0;
    static int max_health = 200;
    static int health_points = 200;
    static int strength_points = 15;
    static int dexterity_points = 15;
    static int defence_points = 5;
    static int experience = 0;
    static int next_level = 100;
    static int level = 1;
    static int hunger = 0;
    static boolean action_made = false;

    void createCharacter() {
        Room room = Level.rooms.get(0);
        last_tile = room.sizes[room.width/2][room.height/2];
        x_value = room.width / 2;
        y_value = room.height / 2;
        Level.rooms.get(present_room).sizes[x_value][y_value] = 44;
        room.former_room = true;
        Interface.inventory[0] = 9;
    }

    /* CHARACTER GOING DOWN */
    void increaseY() {
        nextStep (x_value, y_value + 1);
    }

    /* CHARACTER GOING UP */
    void decreaseY() {
        nextStep (x_value, y_value - 1);
    }

    /* CHARACTER GOING RIGHT */
    void increaseX() {
        nextStep (x_value + 1, y_value);
    }

    /* CHARACTER GOING LEFT */
    void decreaseX() {
        nextStep (x_value - 1, y_value);
    }

    /* ACTION AFTER STEP */
    private void nextStep (int step_x, int step_y) {

        Items.was_clicked = false;
        if (Level.rooms.get(present_room).sizes[step_x][step_y] == 20)
            whichDoor(step_x, step_y);
        else if (Level.rooms.get(present_room).sizes[step_x][step_y] == 25) {
            for (Chests chest: Level.rooms.get(present_room).chests_list) {
                if (chest.x_position == step_x && chest.y_position == step_y) {
                    chest.checkTreasure();
                }
            }
        }
        else if (Level.rooms.get(present_room).sizes[step_x][step_y] < 70
                || Level.rooms.get(present_room).sizes[step_x][step_y] > 99) {
            if (Mixtures.character_paralyze == 0) {
            Level.rooms.get(present_room).sizes[x_value][y_value] = last_tile;
            last_tile = Level.rooms.get(present_room).sizes[step_x][step_y];
            if (step_x == x_value - 1)
                Level.rooms.get(present_room).sizes[step_x][step_y] = 44;
            if (step_x == x_value + 1)
                Level.rooms.get(present_room).sizes[step_x][step_y] = 45;
            if (step_y == y_value - 1)
                Level.rooms.get(present_room).sizes[step_x][step_y] = 46;
            if (step_y == y_value + 1)
                Level.rooms.get(present_room).sizes[step_x][step_y] = 47;

            ListIterator <Drop> iterator = Level.drop_list.listIterator();
            while (iterator.hasNext()) {
                Drop drop = iterator.next();
                if (drop.index == present_room && drop.x_position == step_x && drop.y_position == step_y) {
                    drop.checkTreasure();
                    iterator.remove();
                }
            }
                x_value = step_x;
                y_value = step_y;
            } else
                Mixtures.character_paralyze -= 1;
            action_made = true;
        }
        else if (Level.rooms.get(present_room).sizes[step_x][step_y] >= 70
                || Level.rooms.get(present_room).sizes[step_x][step_y] <= 80) {
            for (Enemies enemy : Enemies.enemies_list) {
                if (enemy.index == present_room) {
                    if ((step_x) == enemy.positionX && step_y == enemy.positionY) {
                        Battle.characterAttack(enemy);
                        action_made = true;
                    }
                }
            }
        }
    }

    private void whichDoor (int x, int y) {

        for (Door door : Level.rooms.get(present_room).doors) {
            if (door.x == x && door.y == y) {
                Level.rooms.get(present_room).sizes[x_value][y_value] = last_tile;
                int whereIWas = present_room;
                present_room = door.where;
                Room room = Level.rooms.get(present_room);
                if (whereIWas == present_room)
                    Interface.newEvent("Strange... I'm back in the same room.");
                else if (room.former_room)
                    Interface.newEvent("I already was here...");
                else
                    Interface.newEvent("I've never seen this room before...");
                room.former_room = true;
                for (Door newdoor : Level.rooms.get(present_room).doors) {
                    if (newdoor.where == whereIWas) {
                        Room newroom = Level.rooms.get(present_room);
                        last_tile = newroom.sizes[newdoor.posx][newdoor.posy];
                        if (newdoor.wall == 0)
                            Level.rooms.get(present_room).sizes[newdoor.posx][newdoor.posy] = 47;
                        if (newdoor.wall == 1)
                            Level.rooms.get(present_room).sizes[newdoor.posx][newdoor.posy] = 46;
                        if (newdoor.wall == 2)
                            Level.rooms.get(present_room).sizes[newdoor.posx][newdoor.posy] = 44;
                        if (newdoor.wall == 3)
                            Level.rooms.get(present_room).sizes[newdoor.posx][newdoor.posy] = 45;

                        x_value = newdoor.posx;
                        y_value = newdoor.posy;
                    }
                }
            }
        }
    }

    static void checkIfAlive() {
        if (health_points <= 0) {
            health_points = 0;
            System.out.println("Character died.");
            System.exit(0);
        }
    }

    static void hunger() {
        hunger += 1;
        if (hunger == 100)
            Interface.newEvent("You are still full.");
        if (hunger == 200)
            Interface.newEvent("Your stomach is fine.");
        if (hunger == 300)
            Interface.newEvent("You feel normally.");
        if (hunger == 400)
            Interface.newEvent("You are hungry!");
        if (hunger == 500)
            Interface.newEvent("You are starving!");
        if (hunger > 500) {
            health_points -= 1;
            checkIfAlive();
        }
    }

    static void addHealth (int points) {
        health_points += points;
        if (health_points > max_health)
            health_points = max_health;
    }

    static void experience (int experience_points) {
        experience += experience_points;
        if (experience >= next_level) {
            level += 1;
            Interface.newEvent("You are now on level " + level);
            experience = experience - next_level;
            next_level += 50;
            strength_points += 5;
            dexterity_points += 5;
            defence_points += 5;
            max_health += 25;
            addHealth (50 + 10 * level);
        }
    }
}