/**
 * Created by LenovoY500 on 13.05.2017.
 */
public class Door {
    protected int from;
    protected int where;
    protected int wall;
    protected int place;
    protected int whichRoom;

    public Door(int a, int b, int wall, int place) {
        this.wall = wall;
        this.place = place;
        from = a;
        where = b;
        whichRoom = a;
    }
}
