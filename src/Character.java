public class Character {

    static int x_value, y_value;
    static int last_tile = 0;
    static int whereAmI = 0;
    static int health_points = 500;
    static int damage_points = 10;

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
            whichDoor(x_value, y_value + 1);
        else if (Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] < 70
              || Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] >= 99) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value ] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value+1];
            y_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 47;
        }
    }
    /* CHARACTER GOING UP */
    void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] == 20)
            whichDoor(x_value, y_value - 1);
        else if (Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] < 70
              || Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] >= 99) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value ] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value-1];
            y_value -= 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 46;
        }
    }
    /* CHARACTER GOING RIGHT */
    void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] == 20)
            whichDoor(x_value + 1, y_value);
        else if (Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] < 70
              || Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] >= 99) {
            Main.rooms.get(whereAmI).sizes[x_value ][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value+1][y_value];
            x_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 45;
        }
    }
    /* CHARACTER GOING LEFT */
    void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] == 20)
            whichDoor(x_value - 1, y_value);
        else if (Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] < 70
              || Main.rooms.get(whereAmI).sizes[x_value - 1][y_value] >= 99) {
            Main.rooms.get(whereAmI).sizes[x_value ][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value - 1][y_value];
            x_value -= 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
        }
    }

    private void whichDoor (int x, int y) {

        for (Door door: Main.rooms.get(whereAmI).doors) {
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
                for (Door newdoor: Main.rooms.get(whereAmI).doors) {
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