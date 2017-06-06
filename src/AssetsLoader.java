import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class AssetsLoader {

    private Image shadow, drop_bag, dead_screen;
    private Image wooden_doors, wooden_chest;
    private Image wall_block, wall_broken_block, column_block, bookshelf;
    private Image wall_plant_up, wall_plant_down, wall_plant_left, wall_plant_right,
            wall_moss_up, wall_moss_down, wall_moss_left, wall_moss_right;
    private Image floor_block, floor_broken, grass_up, grass_down, wooden_floor;
    private Image character_left, character_right, character_up, character_down;
    private Image enemy_zombie, enemy_skeleton, enemy_golem, enemy_ghost;
    private static final int tile_size = 32;

    AssetsLoader() {
        //terminalShowing();
    }

    void load() throws IOException {

        shadow = new Image("file:assets/shadow.png");
        dead_screen = new Image("file:assets/dead_screen.png");
        drop_bag = new Image("file:assets/inventory/bag.png");
        wall_block = new Image("file:assets/wall/brick.png");
        wall_broken_block = new Image("file:assets/wall/brick_broken.png");
        wall_plant_up = new Image("file:assets/wall/brick_plant_u.png");
        wall_plant_down = new Image("file:assets/wall/brick_plant_d.png");
        wall_plant_left = new Image("file:assets/wall/brick_plant_l.png");
        wall_plant_right = new Image("file:assets/wall/brick_plant_r.png");
        wall_moss_up = new Image("file:assets/wall/brick_moss_u.png");
        wall_moss_down = new Image("file:assets/wall/brick_moss_d.png");
        wall_moss_left = new Image("file:assets/wall/brick_moss_l.png");
        wall_moss_right = new Image("file:assets/wall/brick_moss_r.png");
        column_block = new Image("file:assets/wall/column.png");
        bookshelf = new Image("file:assets/wall/bookshelf.png");
        floor_block = new Image("file:assets/floor/white.png");
        floor_broken = new Image("file:assets/floor/white_broken.png");
        wooden_floor = new Image("file:assets/floor/wooden_floor.png");
        grass_up = new Image("file:assets/floor/grass_up.png");
        grass_down = new Image("file:assets/floor/grass_down.png");
        wooden_doors = new Image("file:assets/doors.png");
        wooden_chest = new Image("file:assets/chest.png");
        character_left = new Image("file:assets/character_l.png");
        character_right = new Image("file:assets/character_r.png");
        character_up = new Image("file:assets/character_u.png");
        character_down = new Image("file:assets/character_d.png");
        enemy_zombie = new Image("file:assets/enemies/zombie.png");
        enemy_skeleton = new Image("file:assets/enemies/skeleton.png");
        enemy_golem = new Image("file:assets/enemies/golem.png");
        enemy_ghost = new Image("file:assets/enemies/ghost.png");
    }

    Pane draw() {

        Pane root = new Pane();
        new Interface(root);
        Room room = Level.rooms.get(Character.present_room);

        int x_begin, y_begin, x_end, y_end;
        x_begin = Character.x_value - 5;
        y_begin = Character.y_value - 5;
        x_end = Character.x_value + 6;
        y_end = Character.y_value + 6;
        if (Character.x_value < 5) {
            x_begin = 0;
            x_end += (5 - Character.x_value);
        }
        if (Character.y_value < 5) {
            y_begin = 0;
            y_end += (5 - Character.y_value);
        }
        if (Character.x_value > (room.width - 6)) {
            x_end = room.width;
            x_begin += (room.width - Character.x_value - 6);
        }
        if (Character.y_value > (room.height - 6)) {
            y_end = room.height;
            y_begin += (room.height - Character.y_value - 6);
        }

        for (int x_tile = x_begin, x_index = 0; x_tile < x_end; x_tile++, x_index++) {
            for (int y_tile = y_begin, y_index = 0; y_tile < y_end; y_tile++, y_index++) {
                ImageView iV = new ImageView();
                iV.setFitHeight(tile_size);
                iV.setFitWidth(tile_size);

                /* WALL TILES */
                if (room.sizes[x_tile][y_tile] >= 80 && room.sizes[x_tile][y_tile] <= 99) {
                    if (room.sizes[x_tile][y_tile] == 81)
                        iV.setImage(bookshelf);
                    if (room.sizes[x_tile][y_tile] == 87)
                        iV.setImage(column_block);
                    if (room.sizes[x_tile][y_tile] == 88)
                        iV.setImage(wall_block);
                    if (room.sizes[x_tile][y_tile] == 89)
                        iV.setImage(wall_broken_block);
                    if (room.sizes[x_tile][y_tile] == 90)
                        iV.setImage(wall_plant_up);
                    if (room.sizes[x_tile][y_tile] == 91)
                        iV.setImage(wall_plant_down);
                    if (room.sizes[x_tile][y_tile] == 92)
                        iV.setImage(wall_plant_left);
                    if (room.sizes[x_tile][y_tile] == 93)
                        iV.setImage(wall_plant_right);
                    if (room.sizes[x_tile][y_tile] == 94)
                        iV.setImage(wall_moss_up);
                    if (room.sizes[x_tile][y_tile] == 95)
                        iV.setImage(wall_moss_down);
                    if (room.sizes[x_tile][y_tile] == 96)
                        iV.setImage(wall_moss_left);
                    if (room.sizes[x_tile][y_tile] == 97)
                        iV.setImage(wall_moss_right);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                /* FLOOR TILES */
                if (room.sizes[x_tile][y_tile] >= 10 && room.sizes[x_tile][y_tile] < 20) {
                    if (room.sizes[x_tile][y_tile] == 10)
                        iV.setImage(floor_block);
                    if (room.sizes[x_tile][y_tile] == 11)
                        iV.setImage(floor_broken);
                    if (room.sizes[x_tile][y_tile] == 12)
                        iV.setImage(wooden_floor);
                    if (room.sizes[x_tile][y_tile] == 13)
                        iV.setImage(grass_up);
                    if (room.sizes[x_tile][y_tile] == 14)
                        iV.setImage(grass_down);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                /* DOORS TILES */
                if (room.sizes[x_tile][y_tile] >= 20 && room.sizes[x_tile][y_tile] <= 30) {
                    if (room.sizes[x_tile][y_tile] == 20)
                        iV.setImage(wooden_doors);
                    if (room.sizes[x_tile][y_tile] == 25)
                        iV.setImage(wooden_chest);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                /* CHARACTER TILES */
                if (room.sizes[x_tile][y_tile] >= 44 && room.sizes[x_tile][y_tile] <= 47) {
                    iV.setImage(background(Character.last_tile, "character"));
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setFitHeight(tile_size);
                    iV.setFitWidth(tile_size);
                    if (room.sizes[x_tile][y_tile] == 44)
                        iV.setImage(character_left);
                    if (room.sizes[x_tile][y_tile] == 45)
                        iV.setImage(character_right);
                    if (room.sizes[x_tile][y_tile] == 46)
                        iV.setImage(character_up);
                    if (room.sizes[x_tile][y_tile] == 47)
                        iV.setImage(character_down);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                /* DROP */
                for (Drop drop : Level.drop_list) {
                    if (room.index == drop.index && x_tile == drop.x_position && y_tile == drop.y_position) {
                        iV = new ImageView();
                        iV.setFitHeight(tile_size);
                        iV.setFitWidth(tile_size);
                        iV.setImage(drop_bag);
                        iV.setX(x_index * tile_size + 50);
                        iV.setY(y_index * tile_size + 50);
                        root.getChildren().add(iV);
                    }
                }
                /* ENEMIES TILES */
                if (room.sizes[x_tile][y_tile] >= 70 && room.sizes[x_tile][y_tile] < 80) {
                    iV = new ImageView();
                    iV.setImage(background(checkEnemyTile(x_tile, y_tile, room.index), "enemy"));
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setFitHeight(tile_size);
                    iV.setFitWidth(tile_size);
                    if (room.sizes[x_tile][y_tile] == 70)
                        iV.setImage(enemy_zombie);
                    if (room.sizes[x_tile][y_tile] == 71)
                        iV.setImage(enemy_skeleton);
                    if (room.sizes[x_tile][y_tile] == 72)
                        iV.setImage(enemy_golem);
                    if (room.sizes[x_tile][y_tile] == 73)
                        iV.setImage(enemy_ghost);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
            }
        }

        ImageView iV = new ImageView();
        iV.setFitHeight(tile_size * 11);
        iV.setFitWidth(tile_size * 11);
        iV.setImage(shadow);
        if (Character.is_dead)
            iV.setImage(dead_screen);
        iV.setX(50);
        iV.setY(50);
        root.getChildren().add(iV);

        return root;
    }

    private int checkEnemyTile(int posX, int posY, int index) {
        int last_tile = 0;
        for (Enemies enemy : Enemies.enemies_list) {
            if (enemy.index == index) {
                if (posX == enemy.positionX && posY == enemy.positionY)
                    last_tile = enemy.last_tile;
            }
        }
        return last_tile;
    }

    private Image background(int last_tile, String type) {
        if (last_tile == 10)
            return floor_block;
        if (last_tile == 11)
            return floor_broken;
        if (last_tile == 12)
            return wooden_floor;
        if (last_tile == 13) {
            if (Objects.equals(type, "character")) {
                Character.last_tile = 14;
                return grass_down;
            } else if (Objects.equals(type, "enemy"))
                return grass_up;
        }
        if (last_tile == 14)
            return grass_down;
        else
            return floor_block;
    }

    private void terminalShowing() {
        for (int index = 0; index < Level.howManyRooms; index++) {
            Room room = Level.rooms.get(index);
            for (int x = 0; x < room.width; x++) {
                for (int y = 0; y < room.height; y++) {
                    System.out.print(room.sizes[x][y]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}