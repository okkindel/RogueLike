import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Interface {

    static void statusBar(Pane root) {

        root.setStyle("-fx-background-color: rgba(0,0,0,0.95)");
        Label health_points = new Label(
                "Character health: " + Character.health_points
                        + "\nCharacter strength: " + Character.damage_points
                        + "\nCharacter dexterity: " + Character.dexterity_points
                        + "\nCharacter defence: " + Character.defence_points
                        + "\nCharacter experience: " + Character.experience + " / " + Character.next_level
                        + "\nCharacter level: " + Character.level);
        root.getChildren().add(health_points);
        health_points.setPadding(new Insets(10, 230, 10, 230));
        health_points.setAlignment(Pos.CENTER);
        health_points.setStyle("-fx-background-color: gray");
    }
}
