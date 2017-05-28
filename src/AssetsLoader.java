import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class AssetsLoader {

    private Image wooden_doors, wooden_chest;
    private Image wall_block, wall_broken_block, column_block, bookshelf;
    private Image wall_plant_up, wall_plant_down, wall_plant_left, wall_plant_right, wall_moss_up, wall_moss_down, wall_moss_left, wall_moss_right;
    private Image floor_block, floor_broken, grass_up, grass_down, wooden_floor;
    private Image character_left, character_right, character_up, character_down;
    private Image enemy_zombie, enemy_skeleton, enemy_golem;
    private static final int tile_size = 32;

    AssetsLoader() {
        //terminalShowing();
    }

    void load() throws IOException {

        File file = new File("./assets/wall/brick.png");
        BufferedImage wall_black_block = ImageIO.read(file);
        wall_block = SwingFXUtils.toFXImage(wall_black_block, null);
        file = new File("./assets/wall/brick_broken.png");
        BufferedImage brick_broken = ImageIO.read(file);
        wall_broken_block = SwingFXUtils.toFXImage(brick_broken, null);
        file = new File("./assets/wall/brick_plant_u.png");
        BufferedImage brick_plant_up = ImageIO.read(file);
        wall_plant_up = SwingFXUtils.toFXImage(brick_plant_up, null);
        file = new File("./assets/wall/brick_plant_d.png");
        BufferedImage brick_plant_down = ImageIO.read(file);
        wall_plant_down = SwingFXUtils.toFXImage(brick_plant_down, null);
        file = new File("./assets/wall/brick_plant_l.png");
        BufferedImage brick_plant_left = ImageIO.read(file);
        wall_plant_left = SwingFXUtils.toFXImage(brick_plant_left, null);
        file = new File("./assets/wall/brick_plant_r.png");
        BufferedImage brick_plant_right = ImageIO.read(file);
        wall_plant_right = SwingFXUtils.toFXImage(brick_plant_right, null);
        file = new File("./assets/wall/brick_moss_u.png");
        BufferedImage brick_moss_up = ImageIO.read(file);
        wall_moss_up = SwingFXUtils.toFXImage(brick_moss_up, null);
        file = new File("./assets/wall/brick_moss_d.png");
        BufferedImage brick_moss_down = ImageIO.read(file);
        wall_moss_down = SwingFXUtils.toFXImage(brick_moss_down, null);
        file = new File("./assets/wall/brick_moss_l.png");
        BufferedImage brick_moss_left = ImageIO.read(file);
        wall_moss_left = SwingFXUtils.toFXImage(brick_moss_left, null);
        file = new File("./assets/wall/brick_moss_r.png");
        BufferedImage brick_moss_right = ImageIO.read(file);
        wall_moss_right = SwingFXUtils.toFXImage(brick_moss_right, null);
        file = new File("./assets/wall/column.png");
        BufferedImage column = ImageIO.read(file);
        column_block = SwingFXUtils.toFXImage(column, null);
        file = new File("./assets/wall/bookshelf.png");
        BufferedImage books = ImageIO.read(file);
        bookshelf = SwingFXUtils.toFXImage(books, null);
        file = new File("./assets/floor/white.png");
        BufferedImage floor_white_block = ImageIO.read(file);
        floor_block = SwingFXUtils.toFXImage(floor_white_block, null);
        file = new File("./assets/floor/white_broken.png");
        BufferedImage floor_broken_block = ImageIO.read(file);
        floor_broken = SwingFXUtils.toFXImage(floor_broken_block, null);
        file = new File("./assets/floor/wooden_floor.png");
        BufferedImage wooden_floor_block = ImageIO.read(file);
        wooden_floor = SwingFXUtils.toFXImage(wooden_floor_block, null);
        file = new File("./assets/floor/grass_up.png");
        BufferedImage grass_full = ImageIO.read(file);
        grass_up = SwingFXUtils.toFXImage(grass_full, null);
        file = new File("./assets/floor/grass_down.png");
        BufferedImage grass_less = ImageIO.read(file);
        grass_down = SwingFXUtils.toFXImage(grass_less, null);
        file = new File("./assets/doors.png");
        BufferedImage door_block = ImageIO.read(file);
        wooden_doors = SwingFXUtils.toFXImage(door_block, null);
        file = new File("./assets/chest.png");
        BufferedImage chest_block = ImageIO.read(file);
        wooden_chest = SwingFXUtils.toFXImage(chest_block, null);
        file = new File("./assets/character_l.png");
        BufferedImage character_l = ImageIO.read(file);
        character_left = SwingFXUtils.toFXImage(character_l, null);
        file = new File("./assets/character_r.png");
        BufferedImage character_r = ImageIO.read(file);
        character_right = SwingFXUtils.toFXImage(character_r, null);
        file = new File("./assets/character_u.png");
        BufferedImage character_u = ImageIO.read(file);
        character_up = SwingFXUtils.toFXImage(character_u, null);
        file = new File("./assets/character_d.png");
        BufferedImage character_d = ImageIO.read(file);
        character_down = SwingFXUtils.toFXImage(character_d, null);
        file = new File("./assets/enemies/zombie.png");
        BufferedImage zombie = ImageIO.read(file);
        enemy_zombie = SwingFXUtils.toFXImage(zombie, null);
        file = new File("./assets/enemies/skeleton.png");
        BufferedImage skeleton = ImageIO.read(file);
        enemy_skeleton = SwingFXUtils.toFXImage(skeleton, null);
        file = new File("./assets/enemies/golem.png");
        BufferedImage golem = ImageIO.read(file);
        enemy_golem = SwingFXUtils.toFXImage(golem, null);
    }

    Pane draw() {

        Pane root = new Pane();
        Room room = Main.rooms.get(Character.whereAmI);
        root.setStyle("-fx-background-color: rgba(0,0,0,0.95)");

        for (int i = 0; i < room.height; i++) {
            for(int j = 0; j < room.width; j++) {
                ImageView iV = new ImageView();
                iV.setFitHeight(tile_size);
                iV.setFitWidth(tile_size);

                /* WALL TILES */
                if (room.sizes[j][i] >= 80 && room.sizes[j][i] <= 99) {
                    if (room.sizes[j][i] == 81)
                        iV.setImage(bookshelf);
                    if (room.sizes[j][i] == 82)
                        iV.setImage(wooden_chest);
                    if (room.sizes[j][i] == 87)
                        iV.setImage(column_block);
                    if (room.sizes[j][i] == 88)
                        iV.setImage(wall_block);
                    if (room.sizes[j][i] == 89)
                        iV.setImage(wall_broken_block);
                    if (room.sizes[j][i] == 90)
                        iV.setImage(wall_plant_up);
                    if (room.sizes[j][i] == 91)
                        iV.setImage(wall_plant_down);
                    if (room.sizes[j][i] == 92)
                        iV.setImage(wall_plant_left);
                    if (room.sizes[j][i] == 93)
                        iV.setImage(wall_plant_right);
                    if (room.sizes[j][i] == 94)
                        iV.setImage(wall_moss_up);
                    if (room.sizes[j][i] == 95)
                        iV.setImage(wall_moss_down);
                    if (room.sizes[j][i] == 96)
                        iV.setImage(wall_moss_left);
                    if (room.sizes[j][i] == 97)
                        iV.setImage(wall_moss_right);
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                }
                /* FLOOR TILES */
                if (room.sizes[j][i] >= 10 && room.sizes[j][i] < 20) {
                    if (room.sizes[j][i] == 10)
                        iV.setImage(floor_block);
                    if (room.sizes[j][i] == 11)
                        iV.setImage(floor_broken);
                    if (room.sizes[j][i] == 12)
                        iV.setImage(wooden_floor);
                    if (room.sizes[j][i] == 13)
                        iV.setImage(grass_up);
                    if (room.sizes[j][i] == 14)
                        iV.setImage(grass_down);
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                }
                /* DOORS TILES */
                if (room.sizes[j][i] >= 20 && room.sizes[j][i] <= 30) {
                    if (room.sizes[j][i] == 20)
                        iV.setImage(wooden_doors);
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                }
                /* CHARACTER TILES */
                if (room.sizes[j][i] >= 44 && room.sizes[j][i] <= 47) {
                    iV.setImage(background(Character.last_tile));
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setFitHeight(tile_size);
                    iV.setFitWidth(tile_size);
                    if (room.sizes[j][i] == 44)
                        iV.setImage(character_left);
                    if (room.sizes[j][i] == 45)
                        iV.setImage(character_right);
                    if (room.sizes[j][i] == 46)
                        iV.setImage(character_up);
                    if (room.sizes[j][i] == 47)
                        iV.setImage(character_down);
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                }
                /* ENEMIES TILES */
                if (room.sizes[j][i] >= 70 && room.sizes[j][i] < 80) {
                    iV.setImage(background(Character.last_tile));
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setFitHeight(tile_size);
                    iV.setFitWidth(tile_size);
                    if (room.sizes[j][i] == 70)
                        iV.setImage(enemy_zombie);
                    if (room.sizes[j][i] == 71)
                        iV.setImage(enemy_skeleton);
                    if (room.sizes[j][i] == 72)
                        iV.setImage(enemy_golem);
                    iV.setX(j*tile_size + 100);
                    iV.setY(i*tile_size + 100);
                    root.getChildren().add(iV);
                }
            }
        }
        return root;
    }

    private Image background (int last_tile) {
        if (last_tile == 10)
            return floor_block;
        if (last_tile == 11)
            return floor_broken;
        if (last_tile == 12)
            return wooden_floor;
        if (last_tile == 13) {
            Character.last_tile = 14;
            return grass_down;
        }
        if (last_tile == 14)
            return grass_down;
        else
            return floor_block;
    }

    private void terminalShowing () {
        for (int x = 0; x < Main.howManyRooms; x++) {
            Room room = Main.rooms.get(x);
            for (int i = 0; i < room.height; i++) {
                for (int j = 0; j < room.width; j++) {
                    System.out.print(room.sizes[j][i]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}