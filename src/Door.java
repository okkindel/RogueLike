/**
 * Created by LenovoY500 on 13.05.2017.
 */
public class Door {
    protected int from;
    protected int where;
    protected int wall;
    protected int place;
    protected int whichRoom;

    public Door(int a, int b, int wall, int place){
        from = a;
        where = b;
        whichRoom = a;
//        if(wall == 0){
//            Main.rooms.get(a).north[place] = 3;
//        }
//        if(wall == 1){
//            Main.rooms.get(a).east[place] = 3;
//        }
//        if(wall == 2){
//            Main.rooms.get(a).south[place] = 3;
//        }
//        if(wall == 3){
//            Main.rooms.get(a).west[place] = 3;
//        }

    }
}
