import javafx.application.Application;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.ListIterator;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;
    static int howManyRooms = 10;
    private static Character character;
    static ArrayList<Room> rooms = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        StructureGenerator gene = new StructureGenerator();
        gene.generate(howManyRooms);

        /* creating rooms */
        for (int index = 0; index < howManyRooms; index++) {
            Room room = new Room(index);
            rooms.add(room);
            new Enemies(index);
        }

        character = new Character();
        AssetsLoader assets = new AssetsLoader();
        assets.load();
        primaryStage.setTitle("RogueLike");
        scene = new Scene(assets.draw(), 900, 800);

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
            ListIterator<Enemies> iterator = Enemies.enemies_list.listIterator();
            while(iterator.hasNext()) {
                Enemies enemies = iterator.next();
                enemies.enemyMove();
                if(!enemies.isAlive()){
                    iterator.remove();
                }
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
