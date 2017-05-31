import javafx.application.Application;
import javafx.scene.input.KeyCode;
import java.util.ListIterator;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        new Level();
        Character character = new Character();
        AssetsLoader assets = new AssetsLoader();
        assets.load();
        primaryStage.setTitle("RogueLike");
        scene = new Scene(assets.draw(), 570, 525);
        scene.getStylesheets().add("./stylesheet.css");

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
            Character.hunger();
            ListIterator<Enemies> iterator = Enemies.enemies_list.listIterator();
            while(iterator.hasNext()) {
                Enemies enemies = iterator.next();
                enemies.enemyMove();
                if(!enemies.isAlive()){
                    iterator.remove();
                    scene.setRoot(assets.draw());
                    primaryStage.setScene(scene);
                }
                scene.setRoot(assets.draw());
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