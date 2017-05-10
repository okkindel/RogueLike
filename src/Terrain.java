import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Maku on 08.05.2017.
 */
public class Terrain {
    protected static int columns = 50;
    protected static int rows = 50;
    protected static int [][] array = new int[columns][rows];
    protected static Random generator = new Random();
    static int startX = generator.nextInt(10) + 20;
    static int startY = generator.nextInt(10) + 20;
    static int sizeX = generator.nextInt(10)+ 10;
    static int sizeY = generator.nextInt(10) + 10;


    protected static void fillArray(){


        //Arrays.fill(array, 0);
        System.out.println(startX + " " + sizeX);
        addRooms();
        addWalls();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

    }

    protected static void addWalls(){
        for (int i = startX-1; i < sizeX+startX+1; i++) {
            array[i][startY] = 2;
        }
        for (int i = startY; i < sizeY+startY+1; i++) {
            array[startX-1][i] = 2;
        }

    }

    protected static void addDoors() {

    }

    protected static void addRooms(){
        for (int i = startX; i < sizeX+startX; i++) {
            for (int j = startY; j < sizeY+startY; j++) {
                array[i][j] = 1;
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
