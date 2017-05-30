import java.util.ArrayList;
import java.util.Arrays;

public class Level {

    static int howManyRooms = 10;
    static ArrayList<Room> rooms = new ArrayList<>();

    static void addRooms() {
        Arrays.fill(Interface.message, " ");
        StructureGenerator structure = new StructureGenerator();
        structure.generate(howManyRooms);

        for (int index = 0; index < howManyRooms; index++) {
            Room room = new Room(index);
            rooms.add(room);
            new Enemies(index);
        }
    }
}