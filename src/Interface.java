import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;

public class Interface {

    static void statusBar(Pane root) {

        root.setStyle("-fx-background-color: rgba(0,0,0,0.95)");
        Label status_bar = new Label(
                     "HP: " + Character.health_points + " / " + Character.max_health
                        + "\nSTR: " + Character.strength_points
                        + "\nDEX: " + Character.dexterity_points
                        + "\nDEF: " + Character.defence_points
                        + "\nEXP: " + Character.experience + " / " + Character.next_level
                        + "\nLVL: " + Character.level);
        status_bar.setStyle (
                "-fx-border-color: white; " +
                "-fx-border-style: solid; " +
                "-fx-border-radius: 5; " +
                "-fx-border-width:1; " +
                "-fx-background-color: rgb(20,20,20); " +
                "-fx-text-fill: white");
        status_bar.setPadding(new Insets(115, 10, 115, 10));
        status_bar.layoutYProperty().bind(root.heightProperty().subtract(status_bar.heightProperty()).divide(2));
        status_bar.layoutXProperty().bind(root.widthProperty().subtract(status_bar.widthProperty()).subtract(30));
        root.getChildren().add(status_bar);
    }
}