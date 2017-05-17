import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Tiles {

    private Image wooden_doors;
    private Image wall_block, column_block, wall_plant_up, wall_plant_left, wall_plant_right;
    private Image floor_block, floor_broken, grass_up, grass_down, wooden_floor;
    private Image character_left, character_right, character_up, character_down;

    Tiles() {
        for (int i = 0; i < Main.howManyRooms; i++)
            terminalShowing(i);
    }

    void load() throws IOException {

        File file = new File("./assets/wall/brick.png");
        BufferedImage wall_black_block = ImageIO.read(file);
        wall_block = SwingFXUtils.toFXImage(wall_black_block, null);
        file = new File("./assets/wall/brick_plant_u.png");
        BufferedImage brick_plant_up = ImageIO.read(file);
        wall_plant_up = SwingFXUtils.toFXImage(brick_plant_up, null);
        file = new File("./assets/wall/brick_plant_l.png");
        BufferedImage brick_plant_left = ImageIO.read(file);
        wall_plant_left = SwingFXUtils.toFXImage(brick_plant_left, null);
        file = new File("./assets/wall/brick_plant_r.png");
        BufferedImage brick_plant_right = ImageIO.read(file);
        wall_plant_right = SwingFXUtils.toFXImage(brick_plant_right, null);
        file = new File("./assets/wall/column.png");
        BufferedImage column = ImageIO.read(file);
        column_block = SwingFXUtils.toFXImage(column, null);
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
    }

    Pane draw() {

        Pane root = new Pane();
        Room room = Main.rooms.get(Character.whereAmI);
        root.setStyle("-fx-background-color: rgba(0,0,0,0.95)");

        for (int i = 0; i < room.height; i++) {
            for(int j = 0; j < room.width; j++) {
                ImageView iV = new ImageView();

                if (room.sizes[j][i] >= 80 && room.sizes[j][i] < 90) {
                    if (room.sizes[j][i] == 84)
                        iV.setImage(wall_plant_up);
                    if (room.sizes[j][i] == 85)
                        iV.setImage(wall_plant_left);
                    if (room.sizes[j][i] == 86)
                        iV.setImage(wall_plant_right);
                    if (room.sizes[j][i] == 87)
                        iV.setImage(column_block);
                    if (room.sizes[j][i] == 88)
                        iV.setImage(wall_block);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
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
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 20) {
                    iV.setImage(wooden_doors);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] >= 44 && room.sizes[j][i] <= 47) {
                    iV.setImage(background(Character.last_tile));
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    if (room.sizes[j][i] == 44)
                        iV.setImage(character_left);
                    if (room.sizes[j][i] == 45)
                        iV.setImage(character_right);
                    if (room.sizes[j][i] == 46)
                        iV.setImage(character_up);
                    if (room.sizes[j][i] == 47)
                        iV.setImage(character_down);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
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
        else
            return floor_block;
    }

    private void terminalShowing (int index) {
        Room room = Main.rooms.get(index);
        for (int i = 0; i < room.height; i++) {
            for (int j = 0; j < room.width; j++) {
                System.out.print(room.sizes[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
