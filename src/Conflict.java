public class Conflict {

    static void enemyAtack (Enemies enemy) {

        System.out.println("Character HP: " + Character.health_points);
        System.out.println(enemy.type + " HP: " + enemy.health_points);

        enemy.health_points -= Character.damage_points;
        Character.health_points -= enemy.damage_points;

        if (Character.health_points < 0)
            Character.health_points = 0;
        if (enemy.health_points < 0)
            enemy.health_points = 0;

        if (Character.health_points == 0)
            System.exit(0);
    }
}
