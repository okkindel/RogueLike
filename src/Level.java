import java.util.ArrayList;

public class Level {

    static int room_number = 10;
    static int level_number = 10;
    static ArrayList<ArrayList<Room>> levels_list = new ArrayList<>();
    private ArrayList<Room> rooms_list;

    void newLevel() {
        Interface.newEvent(" ");
        Interface.newEvent("You are now in sewers under the city.");
        Interface.newEvent("Good Luck!");
        Interface.newEvent(" ");
        addLevels();
    }

    private void addLevels() {
        for (int index = 0; index < level_number; index++) {
            addRooms();
            levels_list.add(rooms_list);
        }
    }

    private void addRooms() {
        StructureGenerator structure = new StructureGenerator();
        structure.generate(room_number);
        rooms_list = new ArrayList<>();
        for (int index = 0; index < room_number; index++) {
            Room room = new Room(index);
            rooms_list.add(room);
        }
    }
}