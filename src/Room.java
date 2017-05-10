import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by okkindel on 10.05.17.
 */
public class Room {

    protected static int height = 0;
    protected static int width = 0;
    protected static int door_position;
    protected static int [][] sizes;
    protected static int [] north, south, east, west;

    public Room() {
        Random generator = new Random();
        height = generator.nextInt(10) + 10;
        width = generator.nextInt(10) + 10;
        sizes = new int[width][height];
        north = new int[width];
        south = new int[width];
        east = new int[height];
        west = new int[height];


        innerRoom();
        addWalls();
        showing();
    }

    protected static void innerRoom() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 1;
            }
        }
    }

    protected static void addWalls() {
        Arrays.fill(north, 2);
        Arrays.fill(south, 2);
        Arrays.fill(east, 2);
        Arrays.fill(west, 2);
        for (int i = 0; i < width; i++) {
            sizes[i][0] = north[i];
            sizes[i][height-1] = south[i];
        }
        for (int i = 0; i < height; i++) {
            sizes[0][i] = east[i];
            sizes[width-1][i] = west[i];
        }
    }

    protected static void showing() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(sizes[j][i]);
            }
            System.out.println();
        }
    }

    protected Pane draw() throws IOException {
        Image black;
        Image white;

        File f = new File("./assets/black.png");
        BufferedImage blackbrick = ImageIO.read(f);
        black = SwingFXUtils.toFXImage(blackbrick, null);

        f = new File("./assets/white.png");
        BufferedImage whitebrick = ImageIO.read(f);
        white = SwingFXUtils.toFXImage(whitebrick, null);

        Pane root = new Pane();

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                ImageView iV = new ImageView();
                if( sizes[j][i] == 2) {
                    iV.setImage(black);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if(sizes[j][i] == 1) {
                    iV.setImage(white);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
            }
        }
        return root;
    }
}