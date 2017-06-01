import java.util.ArrayList;
import java.util.Random;

public class Enemies {

    static ArrayList<Enemies> enemies_list = new ArrayList<>();
    private Random generator = new Random();
    String type = "";
    Room room;
    int positionX, positionY = 0;
    private int prevX, prevY;
    int experience_points;
    int enemy_type_tile;
    int dexterity_points;
    int strength_points;
    int defence_points;
    int health_points;
    int last_tile;
    int index;

    Enemies (int index) {

        room = Level.rooms.get(index);
        if (room.isEnemy) {
            for (int numberOf = 0; numberOf < generator.nextInt(5); numberOf++) {
                int x_position = generator.nextInt(room.width - 4) + 2;
                int y_position = generator.nextInt(room.height - 4) + 2;
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

    Enemies() {}

    boolean isAlive(){
        if (this.health_points == 0) {
            room.sizes[prevX][prevY] = last_tile;
            Interface.newEvent(type + " died");
            Character.experience(this.experience_points);
            if (generator.nextInt(5) == 0) {
                Drop drop = new Drop (positionX, positionY);
                Level.rooms.get(index).drop_list.add(drop);
            }
            return false;
        }
        return true;
    }

    void moveAlgorithm() {

        prevX = positionX;
        prevY = positionY;
        room.sizes[positionX][positionY] = last_tile;

        if (Character.present_room == index &&
            Math.sqrt ((positionX - Character.x_value) * (positionX - Character.x_value) +
            (positionY - Character.y_value) * (positionY - Character.y_value)) < 6) {
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
                Battle.enemyAttack(this);
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
        health_points = 25;
        strength_points = 10;
        defence_points = 5;
        dexterity_points = 5;
        experience_points = 25;
        room = Level.rooms.get(index);
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
        health_points = 25;
        strength_points = 10;
        defence_points = 5;
        dexterity_points = 15;
        experience_points = 35;
        room = Level.rooms.get(index);
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
        health_points = 50;
        strength_points = 20;
        defence_points = 10;
        dexterity_points = 1;
        experience_points = 50;
        room = Level.rooms.get(index);
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
        health_points = 5;
        strength_points = 5;
        defence_points = 1;
        dexterity_points = 85;
        experience_points = 10;
        room = Level.rooms.get(index);
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 73;
    }
}