public class Door {

    int where;
    int x, y;

    Door(int from, int where, int wall, int place, int height, int width) {

        this.where = where;
        if(wall == 0) {
            x = place;
            y = 0;
        }
        if(wall == 1) {
            x = width-1;
            y = place;
        }
        if(wall == 2) {
            x = place;
            y = height-1;
        }
        if(wall == 3) {
            x = 0;
            y = place;
        }
    }
}
