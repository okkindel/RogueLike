import java.util.ArrayList;
import java.util.Random;

public class Enemies {

    int positionX, positionY = 0;
    int enemy_type_tile;
    int index;
    int last_tile;
    int health_points;
    int damage_points;
    String type = "";
    private int prevX = positionX;
    private int prevY = positionY;
    Room room;

    static ArrayList<Enemies> enemies_list = new ArrayList<>();

    Enemies (int index) {

        room = Main.rooms.get(index);
        if (room.isEnemy) {
            Random generator = new Random();
            for (int numberOf = 0; numberOf < generator.nextInt(4); numberOf++) {
                int x_position = generator.nextInt(room.width - 2) + 1;
                int y_position = generator.nextInt(room.height - 2) + 1;
                if (room.sizes[x_position][y_position] >= 10 && room.sizes[x_position][y_position] < 20) {
                    if (room.isZombiable) {
                        Zombie zombie = new Zombie(room.index, x_position, y_position);
                        enemies_list.add(zombie);
                    } if (room.isSkeletonable) {
                        Skeleton skeleton = new Skeleton(room.index, x_position, y_position);
                        enemies_list.add(skeleton);
                    } if (room.isGolemable) {
                        Golem golem = new Golem(room.index, x_position, y_position);
                        enemies_list.add(golem);
                    } if (room.isGhostable) {
                        Ghost ghost = new Ghost(room.index, x_position, y_position);
                        enemies_list.add(ghost);
                    }
                } else
                    numberOf--;
            }
        }
    }

    public Enemies() {
    }

    public boolean isAlive(){
        if (this.health_points == 0) {
            room.sizes[prevX][prevY] = last_tile;
            System.out.println(type + " killed.\n");
            return false;

        }
        return true;
    }

    void enemyMove() {

        prevX = positionX;
        prevY = positionY;
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
                Conflict.enemyAtack(this);
            }
            positionX = prevX;
            positionY = prevY;
            room.sizes[positionX][positionY] = enemy_type_tile;
        }
    }
}

class Zombie extends Enemies {

    Zombie (int index, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.index = index;
        type = "Zombie";
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
        type = "Skeleton";
        enemy_type_tile = 71;
        health_points = 30;
        damage_points = 7;
        room = Main.rooms.get(index);
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 71;
    }
}

class Golem extends Enemies {

    Golem (int index, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.index = index;
        type = "Golem";
        enemy_type_tile = 72;
        health_points = 40;
        damage_points = 5;
        room = Main.rooms.get(index);
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 72;
    }
}
class Ghost extends Enemies {

    Ghost (int index, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.index = index;
        type = "Ghost";
        enemy_type_tile = 73;
        health_points = 40;
        damage_points = 5;
        room = Main.rooms.get(index);
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 73;
    }
}