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

public class Room {

    protected static int height = 0;
    protected static int width = 0;
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
        terminalShowing();
    }

    private static void innerRoom() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sizes[j][i] = 1;
            }
        }
    }

    private static void addWalls() {
        Arrays.fill(north, 2);
        Arrays.fill(south, 2);
        Arrays.fill(east, 2);
        Arrays.fill(west, 2);

        addDoors();

        for (int i = 0; i < width; i++) {
            sizes[i][0] = north[i];
            sizes[i][height-1] = south[i];
        }
        for (int i = 0; i < height; i++) {
            sizes[0][i] = east[i];
            sizes[width-1][i] = west[i];
        }
    }

    private static void addDoors() {
        Random generator = new Random();
        int random = generator.nextInt(4);
        if (random == 0) {
            north[generator.nextInt(north.length -3) + 2] = 3;
        }
        if (random == 1) {
            south[generator.nextInt(south.length -3) + 2] = 3;
        }
        if (random == 2) {
            east[generator.nextInt(east.length -3) + 2] = 3;
        }
        if (random == 3) {
            west[generator.nextInt(west.length -2) + 2] = 3;
        }
    }

    private static void terminalShowing() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(sizes[j][i]);
            }
            System.out.println();
        }
    }

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

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                ImageView iV = new ImageView();

                if (sizes[j][i] == 2) {
                    iV.setImage(black);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if (sizes[j][i] == 1) {
                    iV.setImage(white);
                    iV.setX(j*16 + 100);
                    iV.setY(i*16 + 100);
                    root.getChildren().add(iV);
                }
                if (sizes[j][i] == 3) {
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