import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static Scene sc;
    private static Character character;
    static int howManyRooms = 5;
    static ArrayList<Room> rooms = new ArrayList<>();;

    @Override
    public void start(Stage primaryStage) throws Exception {

        StructureGenerator gene = new StructureGenerator();
        gene.generate(howManyRooms);
        for(int i = 0; i < howManyRooms; i++) {
            Room room = new Room(i);
            rooms.add(room);
        }
        character = new Character();
        Tiles drawing = new Tiles();
        drawing.load();
        primaryStage.setTitle("RogueLike");
        sc = new Scene(drawing.draw(), 800, 800);

        sc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                character.decreaseY();
                sc.setRoot(drawing.draw());
                primaryStage.setScene(sc);
            }
            if (event.getCode() == KeyCode.LEFT) {
                character.decreaseX();
                sc.setRoot(drawing.draw());
                primaryStage.setScene(sc);
            }
            if (event.getCode() == KeyCode.DOWN) {
                character.increaseY();
                sc.setRoot(drawing.draw());
                primaryStage.setScene(sc);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                character.increaseX();
                sc.setRoot(drawing.draw());
                primaryStage.setScene(sc);
            }
        });

        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
