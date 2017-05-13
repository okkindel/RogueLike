/**
 * Created by LenovoY500 on 13.05.2017.
 */
public class Door {
    protected int from;
    protected int where;
    protected int wall;
    protected int place;
    protected int whichRoom =  Character.whereAmI;

    public Door(int a, int b, int wall, int place){
        from = a;
        where = b;
        if(wall == 0){
            Main.rooms.get(Character.whereAmI).north[place] = 3;
        }
        if(wall == 1){
            Main.rooms.get(Character.whereAmI).east[place] = 3;
        }
        if(wall == 2){
            Main.rooms.get(Character.whereAmI).south[place] = 3;
        }
        if(wall == 3){
            Main.rooms.get(Character.whereAmI).west[place] = 3;
        }

    }
}
