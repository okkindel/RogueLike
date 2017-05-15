import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Draftsman {
    protected Image black, white, broken, doors, character_l, character_r;
    protected ImageView iV;
    protected  Pane root;

    protected Draftsman() {
        for (int i = 0; i < Main.howManyRooms; i++)
            terminalShowing(i);
    }

    void load() throws IOException{
        File f = new File("./assets/black.png");
        BufferedImage wall_block = ImageIO.read(f);
        black = SwingFXUtils.toFXImage(wall_block, null);
        f = new File("./assets/white.png");
        BufferedImage floor_block = ImageIO.read(f);
        white = SwingFXUtils.toFXImage(floor_block, null);
        f = new File("./assets/white_broken.png");
        BufferedImage floor_broken = ImageIO.read(f);
        broken = SwingFXUtils.toFXImage(floor_broken, null);
        f = new File("./assets/doors.png");
        BufferedImage door_block = ImageIO.read(f);
        doors = SwingFXUtils.toFXImage(door_block, null);
        f = new File("./assets/character_l.png");
        BufferedImage character_left = ImageIO.read(f);
        character_l = SwingFXUtils.toFXImage(character_left, null);
        f = new File("./assets/character_r.png");
        BufferedImage character_right = ImageIO.read(f);
        character_r = SwingFXUtils.toFXImage(character_right, null);
    }

    Pane draw(){
       root = new Pane();

        Random generator = new Random();
        Room room = Main.rooms.get(Character.whereAmI);

        for (int i = 0; i < room.height; i++) {
            for(int j = 0; j < room.width; j++) {
                iV = new ImageView();

                if (room.sizes[j][i] == 2) {
                    iV.setImage(black);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 1) {
                    if (generator.nextInt(4) == 0)
                        iV.setImage(broken);
                    else
                        iV.setImage(white);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 3) {
                    iV.setImage(doors);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 4) {
                    iV.setImage(white);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setImage(character_l);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                }
                if (room.sizes[j][i] == 5) {
                    iV.setImage(white);
                    iV.setX(j*32 + 100);
                    iV.setY(i*32 + 100);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setImage(character_r);
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
