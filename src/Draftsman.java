import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Draftsman {
    Pane draw() throws IOException {
        Image black, white, doors;

        File f = new File("./assets/black.png");
        BufferedImage wall_block = ImageIO.read(f);
        black = SwingFXUtils.toFXImage(wall_block, null);
        f = new File("./assets/white.png");
        BufferedImage floor_block = ImageIO.read(f);
        white = SwingFXUtils.toFXImage(floor_block, null);
        f = new File("./assets/doors.png");
        BufferedImage door_block = ImageIO.read(f);
        doors = SwingFXUtils.toFXImage(door_block, null);

        Pane root = new Pane();

        for (int i = 0; i < Room.height; i++) {
            for(int j = 0; j < Room.width; j++) {
                ImageView iV = new ImageView();

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
            }
        }
        return root;
    }
}
