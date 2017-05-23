public class Character {

    private static int x_value, y_value;
    static int last_tile = 0;
    static int whereAmI = 0;

    Character() {
        Room room = Main.rooms.get(0);
        last_tile = room.sizes[room.width/2][room.height/2];
        x_value = room.width / 2;
        y_value = room.height / 2;
        Main.rooms.get(whereAmI).sizes[x_value][y_value] = 44;
        room.iWasHere = true;
    }

    void increaseY() {
        int next_block = Main.rooms.get(whereAmI).sizes[x_value][y_value + 1];

        if (Tiles.checkThisBlock(next_block) == 3)
            whichDoor(x_value, y_value + 1);
        else if (Tiles.checkThisBlock(next_block) != 1) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = next_block;
            y_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 47;
        }
    }

    void decreaseY() {
        int next_block = Main.rooms.get(whereAmI).sizes[x_value][y_value - 1];

        if (Tiles.checkThisBlock(next_block) == 3)
            whichDoor(x_value, y_value - 1);
        else if (Tiles.checkThisBlock(next_block) != 1) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = next_block;
            y_value -= 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 46;
        }
    }
    void increaseX() {
        int next_block = Main.rooms.get(whereAmI).sizes[x_value + 1][y_value];

        if (Tiles.checkThisBlock(next_block) == 3)
            whichDoor(x_value + 1, y_value);
        else if (Tiles.checkThisBlock(next_block) != 1) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = next_block;
            x_value += 1;
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = 45;
        }
    }

    void decreaseX() {
        int next_block = Main.rooms.get(whereAmI).sizes[x_value - 1][y_value];

        if (Tiles.checkThisBlock(next_block) == 3)
            whichDoor(x_value - 1, y_value);
        if (Tiles.checkThisBlock(next_block) != 1) {
            Main.rooms.get(whereAmI).sizes[x_value][y_value] = last_tile;
            last_tile = next_block;
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