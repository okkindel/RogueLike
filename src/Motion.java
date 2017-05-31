import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ListIterator;

public class Motion {

    static void characterMove (Scene scene, AssetsLoader assets, Stage primaryStage) {
        Character character = new Character();
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
            enemyMove(scene, assets, primaryStage);
        });
    }

    static void enemyMove (Scene scene, AssetsLoader assets, Stage primaryStage) {
        ListIterator<Enemies> iterator = Enemies.enemies_list.listIterator();
        while(iterator.hasNext()) {
            Enemies enemies = iterator.next();
            enemies.moveAlgorithm();
            if(!enemies.isAlive()){
                iterator.remove();
                scene.setRoot(assets.draw());
                primaryStage.setScene(scene);
            }
            scene.setRoot(assets.draw());
            primaryStage.setScene(scene);
        }
    }
}
