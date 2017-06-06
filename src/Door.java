public class Door {

    int x, y, posx, posy, where, wall;

    Door(int from, int where, int wall, int place, int height, int width) {

        this.where = where;
        this.wall = wall;

        /* NORTH */
        if (wall == 0) {
            x = place;
            y = 0;
            posx = x;
            posy = y + 1;
        } /* SOUTH */
        if (wall == 1) {
            x = place;
            y = height - 1;
            posx = x;
            posy = y - 1;
        } /* EAST */
        if (wall == 2) {
            x = width - 1;
            y = place;
            posx = x - 1;
            posy = y;
        } /* WEST */
        if (wall == 3) {
            x = 0;
            y = place;
            posx = x + 1;
            posy = y;
        }
    }
}