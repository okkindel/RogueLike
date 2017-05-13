import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    public static Scene sc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Room room = new Room();
        //Corridor corridor = new Corridor();
        Character character = new Character();
        Draftsman dungeon = new Draftsman();
        StructureGenerator gene = new StructureGenerator();
        gene.generate(8);
        dungeon.load();
        primaryStage.setTitle("Pixel Caves");
        sc = new Scene(dungeon.draw(), 800, 600);

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
