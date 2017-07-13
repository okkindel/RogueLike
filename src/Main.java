import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        gameStart(primaryStage);
        primaryStage.setTitle("RogueLike");
        primaryStage.getIcons().add(new Image("file:assets/action/chest.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void gameStart(Stage primaryStage) throws IOException {
        Level level = new Level();
        level.newLevel();
        Character character = new Character();
        character.createCharacter();
        AssetsLoader assets = new AssetsLoader();
        assets.load();
        Scene scene = new Scene(assets.draw(), 570, 540);
        scene.getStylesheets().add("./stylesheet.css");
        new Motion(scene, assets, primaryStage);
        primaryStage.setScene(scene);
    }
}