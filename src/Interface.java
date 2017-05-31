import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Interface {

    private static String [] message = new String [5];
    private static int [] inventory = new int [10];

    public Interface (Pane root) {
        statusBar(root);
        statusArea(root);
        buffs(root);
        inventory(root);
    }

    static void newEvent (String message) {
        Interface.message[3] = Interface.message[2];
        Interface.message[2] = Interface.message[1];
        Interface.message[1] = Interface.message[0];
        Interface.message[0] = message;
    }

    static void newItem (int item) {
        if (inventory[8] != 0) {
            inventory[8] = inventory[7];
            inventory[7] = inventory[6];
            inventory[6] = inventory[5];
            inventory[5] = inventory[4];
            inventory[4] = inventory[3];
            inventory[3] = inventory[2];
            inventory[2] = inventory[1];
            inventory[1] = inventory[0];
            inventory[0] = item;
        }
        else
            newEvent("Inventory full!");
    }

    private void statusBar (Pane root) {
        Image icons = new Image("file:assets/inventory/status_bar.png");
        Label status_bar = new Label(
                   "HP: " + Character.health_points + " / " + Character.max_health
                     + "\nSTR: " + Character.strength_points
                     + "\nDEX: " + Character.dexterity_points
                     + "\nDEF: " + Character.defence_points
                     + "\nEXP: " + Character.experience + " / " + Character.next_level
                     + "\nLVL: " + Character.level);

        status_bar.setGraphic(new ImageView(icons));
        status_bar.setMaxWidth(125);
        status_bar.setAlignment(Pos.CENTER);
        status_bar.setPadding(new Insets(20, 10, 20, 10));
        status_bar.getStyleClass().add("status_bar");
        status_bar.setLayoutY(50);
        status_bar.layoutXProperty().bind(root.widthProperty()
                .subtract(status_bar.widthProperty()).subtract(20));
        root.getChildren().add(status_bar);
    }

    private void buffs (Pane root) {
        if (Character.hunger > 800) {
            Image hunger = new Image("file:assets/inventory/hunger.png");
            ImageView hunger_buff = new ImageView();
            hunger_buff.setImage(hunger);
            hunger_buff.setX(430);
            hunger_buff.setY(360);
            root.getChildren().add(hunger_buff);
        }
    }

    private void inventory (Pane root) {
        Image empty = new Image("file:assets/inventory/empty.png");
        Image yellow_potion = new Image("file:assets/inventory/yellow_potion.png");
        Image blue_potion = new Image("file:assets/inventory/blue_potion.png");
        Image red_potion = new Image("file:assets/inventory/red_potion.png");
        Image purple_potion = new Image("file:assets/inventory/purple_potion.png");
        Image food_apple = new Image("file:assets/inventory/food_apple.png");
        Image food_steak = new Image("file:assets/inventory/food_steak.png");

        for (int i = 0, index = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView eq_item = new ImageView();
                if (inventory[index] == 0)
                    eq_item.setImage(empty);
                if (inventory[index] == 1)
                    eq_item.setImage(yellow_potion);
                if (inventory[index] == 2)
                    eq_item.setImage(blue_potion);
                if (inventory[index] == 3)
                    eq_item.setImage(red_potion);
                if (inventory[index] == 4)
                    eq_item.setImage(purple_potion);
                if (inventory[index] == 5)
                    eq_item.setImage(food_apple);
                if (inventory[index] == 6)
                    eq_item.setImage(food_steak);
                eq_item.setY(220 + i * 40);
                eq_item.setX(430 + j * 40);
                root.getChildren().add(eq_item);
                index += 1;
            }
        }
    }

    private void statusArea(Pane root) {
        Label status_area = new Label(
                message[3] + "\n"
                   + message[2] + "\n"
                   + message[1] + "\n"
                   + message[0]);
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