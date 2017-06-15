import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Level level = new Level();
        level.newLevel();
        Character character = new Character();
        character.createCharacter();
        AssetsLoader assets = new AssetsLoader();
        assets.load();
        primaryStage.setTitle("RogueLike");
        scene = new Scene(assets.draw(), 570, 540);
        scene.getStylesheets().add("./stylesheet.css");
        new Motion(scene, assets, primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}