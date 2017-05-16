public class Character {

    private static int x_value = 1;
    private static int y_value = 1;
    static int last_tile = 10;
    static int whereAmI = 0;

    Character() {
        Main.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
        Room room = Main.rooms.get(0);
        room.iWasHere = true;
    }

    void increaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value + 1] == 20)
            whichDoor(x_value, y_value + 1);
        else if (Main.rooms.get(whereAmI).sizes[x_value][y_value+1] != 88) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value ] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value+1];
            y_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 47;
        }
    }

    void decreaseY() {
        if (Main.rooms.get(whereAmI).sizes[x_value][y_value - 1] == 20)
            whichDoor(x_value, y_value - 1);
        else if (Main.rooms.get(whereAmI).sizes[x_value][y_value-1] != 88) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value ] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value][y_value-1];
            y_value -= 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 46;
        }
    }
    void increaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value + 1][y_value] == 20)
            whichDoor(x_value + 1, y_value);
        else if (Main.rooms.get(whereAmI).sizes[x_value+1][y_value] != 88) {
            Main.rooms.get(whereAmI).sizes[x_value ][y_value] = last_tile;
            last_tile = Main.rooms.get(whereAmI).sizes[x_value+1][y_value];
            x_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 45;
        }
    }

    void decreaseX() {
        if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] == 20)
            whichDoor(x_value - 1, y_value);
        else if (Main.rooms.get(whereAmI).sizes[x_value-1][y_value] != 88) {
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
                for (Door newroom: Main.rooms.get(whereAmI).doors) {
                    if (newroom.where == whereIWas) {
                        Main.rooms.get(whereAmI).sizes[newroom.posx][newroom.posy] = 44;
                        x_value = newroom.posx;
                        y_value = newroom.posy;
                    }
                }
            }
        }
    }

}