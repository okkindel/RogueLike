import javafx.scene.input.KeyCode;
import java.util.ListIterator;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Motion {

    private Scene scene;
    private AssetsLoader assets;
    private Stage stage;

    Motion (Scene scene, AssetsLoader assets, Stage stage) {
        this.scene = scene;
        this.assets = assets;
        this.stage = stage;
        characterMove();
    }

    private void characterMove() {
        Character character = new Character();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                character.decreaseY();
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.LEFT) {
                character.decreaseX();
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DOWN) {
                character.increaseY();
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                character.increaseX();
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            Character.hunger();
            if (Character.action_made)
                enemyMove();
        });
    }

    private void enemyMove() {
        ListIterator<Enemies> iterator = Enemies.enemies_list.listIterator();
        while(iterator.hasNext()) {
            Enemies enemies = iterator.next();
            enemies.moveAlgorithm();
            if(!enemies.isAlive()){
                iterator.remove();
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            scene.setRoot(assets.draw());
            stage.setScene(scene);
        }
    }
}
