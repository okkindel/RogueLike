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
        keyHandler();
    }

    private void keyHandler() {
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
            else if (Character.action_made) {
                enemyMove();
                Character.hunger();
            }
            else if (event.getCode() == KeyCode.DIGIT1) {
                Items.checkItem(0);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT2) {
                Items.checkItem(1);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT3) {
                Items.checkItem(2);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT4) {
                Items.checkItem(3);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT5) {
                Items.checkItem(4);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT6) {
                Items.checkItem(5);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT7) {
                Items.checkItem(6);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT8) {
                Items.checkItem(7);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            else if (event.getCode() == KeyCode.DIGIT9) {
                Items.checkItem(8);
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            if (Character.action_made) {
                enemyMove();
                Character.hunger();
                Character.action_made = false;
            }
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