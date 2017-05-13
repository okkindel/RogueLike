/**
 * Created by LenovoY500 on 13.05.2017.
 */
public class Door {
    protected int from;
    protected int where;
    protected int wall;
    protected int place;
    protected int whichRoom;
    protected int x, y;

    public Door(int from, int where, int wall, int place, int height, int width) {
        this.wall = wall;
        this.place = place;
        this.from = from;
        this.where = where;
        whichRoom = from;

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
