import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Interface {

    static void statusBar(BorderPane root) {

        root.setStyle("-fx-background-color: rgba(0,0,0,0.95)");
        Label status_bar = new Label(
                     "HP: " + Character.health_points + " / " + Character.max_health
                        + "\nSTR: " + Character.strength_points
                        + "\nDEX: " + Character.dexterity_points
                        + "\nDEF: " + Character.defence_points
                        + "\nEXP: " + Character.experience + " / " + Character.next_level
                        + "\nLVL: " + Character.level);
        root.setRight(status_bar);
        BorderPane.setAlignment(status_bar, Pos.CENTER);
        status_bar.setStyle("-fx-background-color: gray");
    }
}
