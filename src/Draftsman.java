import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Draftsman {
    protected Image black, white, doors, character_l, character_r;
    protected ImageView iV;
    protected  Pane root;

    void load() throws IOException{
        File f = new File("./assets/black.png");
        BufferedImage wall_block = ImageIO.read(f);
        black = SwingFXUtils.toFXImage(wall_block, null);
        f = new File("./assets/white.png");
        BufferedImage floor_block = ImageIO.read(f);
        white = SwingFXUtils.toFXImage(floor_block, null);
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

        for (int i = 0; i < Room.height; i++) {
            for(int j = 0; j < Room.width; j++) {
                iV = new ImageView();


                if (Room.sizes[j][i] == 2) {
                    iV.setImage(black);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if (Room.sizes[j][i] == 1) {
                    iV.setImage(white);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if (Room.sizes[j][i] == 3) {
                    iV.setImage(doors);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if (Room.sizes[j][i] == 4) {
                    iV.setImage(character_l);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if (Room.sizes[j][i] == 5) {
                    iV.setImage(character_r);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
            }
        }
//
//        for (int i = 0; i < Corridor.length; i++) {
//            for(int j = 0; j < 3; j++) {
//                ImageView iV = new ImageView();
//
//                if (Corridor.sizes[j][i] == 2) {
//                    iV.setImage(black);
//                    iV.setX(j*16 + 500);
//                    iV.setY(i*16 + 100);
//                    root.getChildren().add(iV);
//                }
//                if (Corridor.sizes[j][i] == 1) {
//                    iV.setImage(white);
//                    iV.setX(j*16 + 500);
//                    iV.setY(i*16 + 100);
//                    root.getChildren().add(iV);
//                }
//                if (Corridor.sizes[j][i] == 3) {
//                    iV.setImage(doors);
//                    iV.setX(j*16 + 500);
//                    iV.setY(i*16 + 100);
//                    root.getChildren().add(iV);
//                }
//            }
//        }

        return root;
    }

}
