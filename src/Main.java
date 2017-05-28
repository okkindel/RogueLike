import javafx.application.Application;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;
    static int howManyRooms = 10;
    private static Character character;
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Enemies> enemies_list = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        StructureGenerator gene = new StructureGenerator();
        gene.generate(howManyRooms);

        /* creating rooms */
        for (int index = 0; index < howManyRooms; index++) {
            Room room = new Room(index);
            rooms.add(room);
        }
        /* filling rooms with enemies */
        for (Room room: rooms) {
            Random generator = new Random();
            if (room.isEnemy) {
                for (int numberOf = 0; numberOf < generator.nextInt(4); numberOf++) {
                    int x_position = generator.nextInt(room.width - 2) + 1;
                    int y_position = generator.nextInt(room.height - 2) + 1;
                    if (room.sizes[x_position][y_position] >= 10 && room.sizes[x_position][y_position] < 20) {
                        Enemies wolf = new Enemies("wolf", room.index, x_position, y_position);
                        enemies_list.add(wolf);
                    } else
                        numberOf--;
                }
            }
        }

        character = new Character();
        AssetsLoader assets = new AssetsLoader();
        assets.load();
        primaryStage.setTitle("RogueLike");
        scene = new Scene(assets.draw(), 800, 800);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                character.decreaseY();
                scene.setRoot(assets.draw());
                primaryStage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.LEFT) {
                character.decreaseX();
                scene.setRoot(assets.draw());
                primaryStage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DOWN) {
                character.increaseY();
                scene.setRoot(assets.draw());
                primaryStage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                character.increaseX();
                scene.setRoot(assets.draw());
                primaryStage.setScene(scene);
            }
            try {
                for (Enemies enemy : enemies_list) {
                    enemy.enemyMove();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            } catch (Exception ignore) {}
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
