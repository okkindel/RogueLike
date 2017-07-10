import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Timer;
import java.util.TimerTask;

public class Actions {

    Actions(Pane root) {
        death(root);
    }

    private void death(Pane root) {

        if (Character.is_dead) {
            Image dead_screen = new Image("file:assets/gui/dead_screen.png");
            ImageView iV = new ImageView();
            iV.setFitHeight(AssetsLoader.tile_size * 11);
            iV.setFitWidth(AssetsLoader.tile_size * 11);
            iV.setImage(dead_screen);
            iV.setX(50);
            iV.setY(50);
            root.getChildren().add(iV);
            Mixtures.character_paralyze = 100;
            new TimeKeeper(2500);
        }
    }

    class TimeKeeper {
        Timer timer;

        TimeKeeper(int time) {
            timer = new Timer();
            timer.schedule(new TaskExit(), time);
        }

        class TaskExit extends TimerTask {
            public void run() {
                System.exit(0);
                timer.cancel();
            }
        }
    }
}
