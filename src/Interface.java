import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Interface {

    private static String [] message = new String [4];

    public Interface (Pane root) {
        statusBar(root);
        statusArea(root);
    }

    static void newEvent (String message) {
        Interface.message[3] = Interface.message[2];
        Interface.message[2] = Interface.message[1];
        Interface.message[1] = Interface.message[0];
        Interface.message[0] = message;
    }

    private static void statusBar(Pane root) {

        Label status_bar = new Label(
                   "HP: " + Character.health_points + " / " + Character.max_health
                     + "\nSTR: " + Character.strength_points
                     + "\nDEX: " + Character.dexterity_points
                     + "\nDEF: " + Character.defence_points
                     + "\nEXP: " + Character.experience + " / " + Character.next_level
                     + "\nLVL: " + Character.level);

        status_bar.setMinWidth(115);
        status_bar.setMaxWidth(115);
        status_bar.setAlignment(Pos.CENTER);
        status_bar.setPadding(new Insets(115, 10, 115, 10));
        status_bar.getStyleClass().add("status_bar");
        status_bar.setLayoutY(60);
        status_bar.layoutXProperty().bind(root.widthProperty()
                .subtract(status_bar.widthProperty()).subtract(30));

        root.getChildren().add(status_bar);
    }

    private static void statusArea(Pane root) {

        Label status_area = new Label(
                message[3] + "\n"
                   + message[2] + "\n"
                   + message[1] + "\n"
                   + message[0]);

        status_area.setMinWidth(470);
        status_area.setMaxWidth(470);
        status_area.setAlignment(Pos.CENTER);
        status_area.setPadding(new Insets(10, 50, 10, 50));
        status_area.getStyleClass().add("status_area");
        status_area.setLayoutX(60);
        status_area.layoutYProperty().bind(root.heightProperty()
                .subtract(status_area.heightProperty()).subtract(30));

        root.getChildren().add(status_area);
    }
}