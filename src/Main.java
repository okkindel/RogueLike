import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Scene sc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Room room = new Room();
        Corridor corridor = new Corridor();
        Draftsman dungeon = new Draftsman();
        primaryStage.setTitle("Pixel Caves");
        sc = new Scene(dungeon.draw(), 800, 600);
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
