import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static Scene scene;
    private static Character character;
    static int howManyRooms = 10;
    static ArrayList<Room> rooms = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        StructureGenerator gene = new StructureGenerator();
        gene.generate(howManyRooms);
        for (int index = 0; index < howManyRooms; index++) {
            Room room = new Room(index);
            rooms.add(room);
        }
        character = new Character();
        Tiles drawing = new Tiles();
        drawing.load();
        primaryStage.setTitle("RogueLike");
        scene = new Scene(drawing.draw(), 800, 800);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                character.decreaseY();
                scene.setRoot(drawing.draw());
                primaryStage.setScene(scene);
            }
            if (event.getCode() == KeyCode.LEFT) {
                character.decreaseX();
                scene.setRoot(drawing.draw());
                primaryStage.setScene(scene);
            }
            if (event.getCode() == KeyCode.DOWN) {
                character.increaseY();
                scene.setRoot(drawing.draw());
                primaryStage.setScene(scene);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                character.increaseX();
                scene.setRoot(drawing.draw());
                primaryStage.setScene(scene);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
