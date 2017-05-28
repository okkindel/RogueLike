import java.util.Objects;

public class Enemies {

    private int positionX, positionY = 0;
    private int enemy_type_tile;
    private Room room;
    private int index;
    private int last_tile;

    Enemies (String type, int index, int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.index = index;
        room = Main.rooms.get(index);

        last_tile = room.sizes[positionX][positionY];
        if (Objects.equals(type, "wolf"))
            addWolf();
    }

    private void addWolf() {
        enemy_type_tile = 50;
        room = Main.rooms.get(index);
        room.sizes[positionX][positionY] = 50;
    }

    void enemyMove() {

        int prevX = positionX;
        int prevY = positionY;
        room.sizes[positionX][positionY] = last_tile;

        if (Character.whereAmI == index) {
            if (positionX > Character.x_value) {
                if (positionY > Character.y_value) {
                    positionX--;
                    positionY--;
                } else if (positionY < Character.y_value) {
                    positionX--;
                    positionY++;
                } else
                    positionX--;
            } else if (positionX < Character.x_value) {
                if (positionY > Character.y_value) {
                    positionX++;
                    positionY--;
                } else if (positionY < Character.y_value) {
                    positionX++;
                    positionY++;
                } else
                    positionX++;
            } else {
                if (positionY > Character.y_value) {
                    positionY--;
                } else if (positionY < Character.y_value) {
                    positionY++;
                }
            }
        }

        if (room.sizes[positionX][positionY] >= 10 && room.sizes[positionX][positionY] < 20) {
            last_tile = room.sizes[positionX][positionY];
            room.sizes[positionX][positionY] = enemy_type_tile;
        } else {
            positionX = prevX;
            positionY = prevY;
            room.sizes[positionX][positionY] = enemy_type_tile;
        }
    }
}