import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static Scene sc;
    public static ArrayList<Room> rooms;
    protected static int howManyRooms = 5;
    protected Character character;

    @Override
    public void start(Stage primaryStage) throws Exception {
        rooms = new ArrayList<Room>();

        StructureGenerator gene = new StructureGenerator();
        gene.generate(howManyRooms);

        for(int i = 0; i < howManyRooms; i++) {
            Room room = new Room(i);
            rooms.add(room);
        }
        character = new Character();

        Draftsman dungeon = new Draftsman();
        dungeon.load();
        primaryStage.setTitle("Pixel Caves");
        sc = new Scene(dungeon.draw(), 900, 900);

        sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    character.decreaseY();
                    sc.setRoot(dungeon.draw());
                    primaryStage.setScene(sc);
                }
                if (event.getCode() == KeyCode.A) {
                    character.decreaseX();
                    sc.setRoot(dungeon.draw());
                    primaryStage.setScene(sc);
                }
                if (event.getCode() == KeyCode.S) {
                    character.increaseY();
                    sc.setRoot(dungeon.draw());
                    primaryStage.setScene(sc);
                }
                if (event.getCode() == KeyCode.D) {
                    character.increaseX();
                    sc.setRoot(dungeon.draw());
                    primaryStage.setScene(sc);
                }
            }
        });

        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
