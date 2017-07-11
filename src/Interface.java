import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Interface {

    private static String[] message = new String[5];
    static boolean inventory_shown = true;
    static int[] inventory = new int[10];
    static int[] equipment = new int[5];

    private Image ring_dex = new Image("file:assets/armory/ring_dex.png");
    private Image ring_str = new Image("file:assets/armory/ring_str.png");
    private Image ring_def = new Image("file:assets/armory/ring_def.png");

    public Interface(Pane root) {
        statusBar(root);
        statusArea(root);
        buffs(root);
        inventory(root);
        equipment(root);
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

    static void addRing(int item) {
        if (equipment[0] == 0)
            equipment[0] = item;
        else if (equipment[1] == 0)
            equipment[1] = item;
        else {
            newEvent("I have no more fingers!");
            Items.eq_full = true;
        }
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
            buff.setX(350);
            buff.setY(50);
            root.getChildren().add(buff);
        }
        if (Mixtures.character_paralyze > 0) {
            ImageView buff = new ImageView();
            buff.setImage(rooted);
            buff.setX(315);
            buff.setY(50);
            root.getChildren().add(buff);
        }
        if (Mixtures.character_confused > 0) {
            ImageView buff = new ImageView();
            buff.setImage(confuse);
            buff.setX(280);
            buff.setY(50);
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

        if (inventory_shown) {
            for (int row = 0, index = 0; row < 4; row++) {
                for (int column = 0; column < 3; column++) {
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
                /* RINGS */
                    if (inventory[index] == 11 || inventory[index] == 12)
                        eq_item.setImage(ring_dex);
                    if (inventory[index] == 13 || inventory[index] == 14)
                        eq_item.setImage(ring_str);
                    if (inventory[index] == 15 || inventory[index] == 16)
                        eq_item.setImage(ring_def);
                    eq_item.setY(235 + row * 40);
                    eq_item.setX(430 + column * 40);

                    if (Items.was_clicked && Items.last_position == index) {
                        eq_item.setFitHeight(38);
                        eq_item.setFitWidth(38);
                        eq_item.setY(235 + row * 40 - 3);
                        eq_item.setX(430 + column * 40 - 3);
                    }

                    root.getChildren().add(eq_item);
                    if (row == 3)
                        break;
                    index += 1;
                }
            }
        }
    }

    private void equipment(Pane root) {

        Image sword_empty = new Image("file:assets/armory/sword_empty.png");
        Image ring_empty = new Image("file:assets/armory/ring_empty.png");
        Image armor_empty = new Image("file:assets/armory/armor_empty.png");
        Image shield_empty = new Image("file:assets/armory/shield_empty.png");
        Image background = new Image("file:assets/armory/background.png");

        if (!inventory_shown) {

            ImageView background_image = new ImageView();
            background_image.setImage(background);
            background_image.setY(235);
            background_image.setX(430);
            root.getChildren().add(background_image);

            ImageView left_ring = new ImageView();
            if (equipment[0] == 0)
                left_ring.setImage(ring_empty);
            if (equipment[0] == 11 || equipment[0] == 12)
                left_ring.setImage(ring_dex);
            if (equipment[0] == 13 || equipment[0] == 14)
                left_ring.setImage(ring_str);
            if (equipment[0] == 15 || equipment[0] == 16)
                left_ring.setImage(ring_def);
            left_ring.setY(235);
            left_ring.setX(430);
            if (Items.was_clicked && Items.last_position == 0) {
                left_ring.setFitHeight(38);
                left_ring.setFitWidth(38);
                left_ring.setY(232);
                left_ring.setX(427);
            }
            root.getChildren().add(left_ring);

            ImageView right_ring = new ImageView();
            if (equipment[1] == 0)
                right_ring.setImage(ring_empty);
            if (equipment[1] == 11 || equipment[1] == 12)
                right_ring.setImage(ring_dex);
            if (equipment[1] == 13 || equipment[1] == 14)
                right_ring.setImage(ring_str);
            if (equipment[1] == 15 || equipment[1] == 16)
                right_ring.setImage(ring_def);
            right_ring.setY(235);
            right_ring.setX(510);
            if (Items.was_clicked && Items.last_position == 1) {
                right_ring.setFitHeight(38);
                right_ring.setFitWidth(38);
                right_ring.setY(232);
                right_ring.setX(507);
            }
            root.getChildren().add(right_ring);

            ImageView armor = new ImageView();
            if (equipment[2] == 0)
                armor.setImage(armor_empty);
            armor.setY(275);
            armor.setX(470);
            if (Items.was_clicked && Items.last_position == 2) {
                armor.setFitHeight(38);
                armor.setFitWidth(38);
                armor.setY(272);
                armor.setX(467);
            }
            root.getChildren().add(armor);

            ImageView sword = new ImageView();
            if (equipment[3] == 0)
                sword.setImage(sword_empty);
            sword.setY(315);
            sword.setX(430);
            if (Items.was_clicked && Items.last_position == 3) {
                sword.setFitHeight(38);
                sword.setFitWidth(38);
                sword.setY(312);
                sword.setX(427);
            }
            root.getChildren().add(sword);

            ImageView shield = new ImageView();
            if (equipment[4] == 0)
                shield.setImage(shield_empty);
            shield.setY(315);
            shield.setX(510);
            if (Items.was_clicked && Items.last_position == 4) {
                shield.setFitHeight(38);
                shield.setFitWidth(38);
                shield.setY(312);
                shield.setX(507);
            }
            root.getChildren().add(shield);
        }
    }

    private void IdScrolls(Pane root) {
        if (inventory_shown) {
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