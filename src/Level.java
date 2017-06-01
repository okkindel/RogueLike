import java.util.ArrayList;

public class Level {

    static int howManyRooms = 15;
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList <Drop> drop_list = new ArrayList<>();

    Level() {
        Interface.newEvent(" ");
        Interface.newEvent("You are now in sewers under the city.");
        Interface.newEvent("Good Luck!");
        Interface.newEvent(" ");

        addRooms();
    }

    private static void addRooms() {
        StructureGenerator structure = new StructureGenerator();
        structure.generate(howManyRooms);

        for (int index = 0; index < howManyRooms; index++) {
            Room room = new Room(index);
            rooms.add(room);
            new Enemies(index);
        }
    }
}