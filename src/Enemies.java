import java.util.ArrayList;
import java.util.Random;

public class Enemies {

    int positionX, positionY = 0;
    int enemy_type_tile;
    int index;
    int last_tile;
    int health_points;
    int damage_points;
    Room room;

    static ArrayList<Enemies> enemies_list = new ArrayList<>();

    Enemies () {}

    static void addEnemy() {
        for (Room room: Main.rooms) {
            Random generator = new Random();
            if (room.isEnemy) {
                for (int numberOf = 0; numberOf < generator.nextInt(4); numberOf++) {
                    int x_position = generator.nextInt(room.width - 2) + 1;
                    int y_position = generator.nextInt(room.height - 2) + 1;
                    if (room.sizes[x_position][y_position] >= 10 && room.sizes[x_position][y_position] < 20) {
                        if (room.isWolfable) {
                            Wolf wolf = new Wolf(room.index, x_position, y_position);
                            enemies_list.add(wolf);
                        }
                        if (room.isSkeletonable) {
                            Skeleton skeleton = new Skeleton(room.index, x_position, y_position);
                            enemies_list.add(skeleton);
                        }
                    } else
                        numberOf--;
                }
            }
        }
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
            if (room.sizes[positionX][positionY] >= 44 && room.sizes[positionX][positionY] <= 47) {
                Actions.fight(this);
            }
            positionX = prevX;
            positionY = prevY;
            room.sizes[positionX][positionY] = enemy_type_tile;

        }
    }
}

class Wolf extends Enemies {

    Wolf (int index, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.index = index;
        enemy_type_tile = 70;
        health_points = 20;
        damage_points = 5;
        room = Main.rooms.get(index);
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 70;
    }
}

class Skeleton extends Enemies {

    Skeleton (int index, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.index = index;
        enemy_type_tile = 71;
        health_points = 30;
        damage_points = 7;
        room = Main.rooms.get(index);
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 71;
    }
}