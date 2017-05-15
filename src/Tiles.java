import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {

    private Image wall_block, floor_block, floor_broken, wooden_doors;
    private Image character_left, character_right;

    Tiles() {
        for (int i = 0; i < Main.howManyRooms; i++)
            terminalShowing(i);
    }

    void load() throws IOException{
        File f = new File("./assets/black.png");
        BufferedImage wall_black_block = ImageIO.read(f);
        wall_block = SwingFXUtils.toFXImage(wall_black_block, null);
        f = new File("./assets/white.png");
        BufferedImage floor_white_block = ImageIO.read(f);
        floor_block = SwingFXUtils.toFXImage(floor_white_block, null);
        f = new File("./assets/white_broken.png");
        BufferedImage floor_broken_block = ImageIO.read(f);
        floor_broken = SwingFXUtils.toFXImage(floor_broken_block, null);
        f = new File("./assets/doors.png");
        BufferedImage door_block = ImageIO.read(f);
        wooden_doors = SwingFXUtils.toFXImage(door_block, null);
        f = new File("./assets/character_l.png");
        BufferedImage character_l = ImageIO.read(f);
        character_left = SwingFXUtils.toFXImage(character_l, null);
        f = new File("./assets/character_r.png");
        BufferedImage character_r = ImageIO.read(f);
        character_right = SwingFXUtils.toFXImage(character_r, null);
    }

    Pane draw(){
        Pane root = new Pane();

        Room room = Main.rooms.get(Character.whereAmI);

        for (int i = 0; i < room.height; i++) {
            for(int j = 0; j < room.width; j++) {
                ImageView iV = new ImageView();

                if (room.sizes[j][i] == 88) {
                    iV.setImage(wall_block);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 10) {
                    iV.setImage(floor_block);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 11) {
                    iV.setImage(floor_broken);
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
                if (room.sizes[j][i] == 44) {
                    iV.setImage(character_left);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 45) {
                    iV.setImage(character_right);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
            }
        }
        return root;
    }

    private void terminalShowing(int index) {

        Room room = Main.rooms.get(index);
        for (int i = 0; i < room.height; i++) {
            for (int j = 0; j < room.width; j++) {
                System.out.print(room.sizes[j][i]);
            }
            System.out.println();
        }
    }

}
