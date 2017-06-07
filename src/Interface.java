import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Interface {

    private static String[] message = new String[5];
    static int[] inventory = new int[10];

    public Interface(Pane root) {
        statusBar(root);
        statusArea(root);
        buffs(root);
        inventory(root);
        IdScrolls(root);
    }

    static void newEvent(String message) {
        Interface.message[3] = Interface.message[2];
        Interface.message[2] = Interface.message[1];
        Interface.message[1] = Interface.message[0];
        Interface.message[0] = message;
    }

    static void newItem(int item) {
        if (inventory[9] == 0) {
            inventory[9] = inventory[8];
            inventory[8] = inventory[7];
            inventory[7] = inventory[6];
            inventory[6] = inventory[5];
            inventory[5] = inventory[4];
            inventory[4] = inventory[3];
            inventory[3] = inventory[2];
            inventory[2] = inventory[1];
            inventory[1] = inventory[0];
            inventory[0] = item;
        } else
            newEvent("Inventory full!");
    }

    private void statusBar(Pane root) {
        Image icons = new Image("file:assets/gui/status_bar.png");
        Label status_bar = new Label(
                "HP: " + Character.health_points + " / " + Character.max_health
                        + "\nSTR: " + Character.strength_points
                        + "\nDEX: " + Character.dexterity_points
                        + "\nDEF: " + Character.defence_points
                        + "\nEXP: " + Character.experience + " / " + Character.next_level
                        + "\nLVL: " + Character.level);
        status_bar.setGraphic(new ImageView(icons));
        status_bar.setAlignment(Pos.CENTER);
        status_bar.getStyleClass().add("status_bar");
        status_bar.setPadding(new Insets(20, 10, 20, 10));
        status_bar.setLayoutY(50);
        status_bar.layoutXProperty().bind(root.widthProperty()
                .subtract(status_bar.widthProperty()).subtract(20));
        root.getChildren().add(status_bar);
    }

    private void buffs(Pane root) {

        Image rooted = new Image("file:assets/gui/buff_root.png");
        Image hunger = new Image("file:assets/gui/buff_hunger.png");
        Image confuse = new Image("file:assets/gui/buff_confuse.png");

        if (Character.hunger > 400) {
            ImageView buff = new ImageView();
            buff.setImage(hunger);
            buff.setX(432);
            buff.setY(204);
            root.getChildren().add(buff);
        }
        if (Mixtures.character_paralyze > 0) {
            ImageView buff = new ImageView();
            buff.setImage(rooted);
            buff.setX(472);
            buff.setY(204);
            root.getChildren().add(buff);
        }
        if (Mixtures.character_confused > 0) {
            ImageView buff = new ImageView();
            buff.setImage(confuse);
            buff.setX(512);
            buff.setY(204);
            root.getChildren().add(buff);
        }
    }

    private void inventory(Pane root) {

        Image empty = new Image("file:assets/inventory/empty.png");
        /* POTIONS */
        Image green_potion = new Image("file:assets/inventory/green_potion.png");
        Image aqua_potion = new Image("file:assets/inventory/aqua_potion.png");
        Image orange_potion = new Image("file:assets/inventory/orange_potion.png");
        Image yellow_potion = new Image("file:assets/inventory/yellow_potion.png");
        Image blue_potion = new Image("file:assets/inventory/blue_potion.png");
        Image red_potion = new Image("file:assets/inventory/red_potion.png");
        Image purple_potion = new Image("file:assets/inventory/purple_potion.png");
        /* FOOD */
        Image food_apple = new Image("file:assets/inventory/food_eggplant.png");
        Image food_steak = new Image("file:assets/inventory/food_steak.png");
        Image food_mushroom = new Image("file:assets/inventory/food_mushroom.png");

        for (int i = 0, index = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView eq_item = new ImageView();
                if (inventory[index] == 0)
                    eq_item.setImage(empty);
                /* POTIONS */
                if (inventory[index] == 1)
                    eq_item.setImage(yellow_potion);
                if (inventory[index] == 2)
                    eq_item.setImage(blue_potion);
                if (inventory[index] == 3)
                    eq_item.setImage(red_potion);
                if (inventory[index] == 4)
                    eq_item.setImage(purple_potion);
                if (inventory[index] == 5)
                    eq_item.setImage(green_potion);
                if (inventory[index] == 6)
                    eq_item.setImage(aqua_potion);
                if (inventory[index] == 7)
                    eq_item.setImage(orange_potion);
                /* FOOD */
                if (inventory[index] == 8)
                    eq_item.setImage(food_apple);
                if (inventory[index] == 9)
                    eq_item.setImage(food_steak);
                if (inventory[index] == 10)
                    eq_item.setImage(food_mushroom);
                eq_item.setY(235 + i * 40);
                eq_item.setX(430 + j * 40);

                if (Items.was_clicked && Items.last_position == index) {
                    eq_item.setFitHeight(38);
                    eq_item.setFitWidth(38);
                    eq_item.setY(235 + i * 40 - 3);
                    eq_item.setX(430 + j * 40 - 3);
                }

                root.getChildren().add(eq_item);
                if (i == 3 && j == 0)
                    break;
                index += 1;
            }
        }
    }

    private void IdScrolls(Pane root) {
        Image scroll = new Image("file:assets/gui/scroll.png");
        Label scroll_counter = new Label(": " + Items.scrolls);
        scroll_counter.setGraphic(new ImageView(scroll));
        scroll_counter.setAlignment(Pos.CENTER);
        scroll_counter.getStyleClass().add("scrolls");
        scroll_counter.setPadding(new Insets(5));
        scroll_counter.setLayoutY(356);
        scroll_counter.layoutXProperty().bind(root.widthProperty()
                .subtract(scroll_counter.widthProperty()).subtract(28));
        root.getChildren().add(scroll_counter);
    }

    private void statusArea(Pane root) {
        Label status_area = new Label(
                message[3] + "\n"
                        + message[2] + "\n"
                        + message[1] + "\n"
                        + message[0]);
        status_area.setAlignment(Pos.CENTER);
        status_area.setPadding(new Insets(10, 50, 10, 50));
        status_area.getStyleClass().add("status_area");
        status_area.setLayoutX(60);
        status_area.layoutYProperty().bind(root.heightProperty()
                .subtract(status_area.heightProperty()).subtract(30));
        root.getChildren().add(status_area);
    }
}