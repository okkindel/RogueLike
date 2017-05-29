import java.util.Random;

public class Battle {

    static Random generator = new Random();

    static void enemyAttack(Enemies enemy) {

        int damage = generator.nextInt(enemy.damage_points);
        int defence = generator.nextInt(Character.defence_points) / 2;
        int true_damage = damage - defence;
        if (true_damage <= 0) {
            System.out.println("Character blocked attack.");
        } else if (generator.nextInt(100) <= Character.dexterity_points) {
            System.out.println("Character dodged attack.");
        } else {
            System.out.println(enemy.type + " attacks for : " + true_damage);
            Character.health_points -= true_damage;
        }
        if (Character.health_points < 0)
            System.exit(0);
    }

    static void characterAttack(Enemies enemy) {

        int damage = generator.nextInt(Character.damage_points);
        int defence = generator.nextInt(enemy.defence_points) / 2;
        int true_damage = damage - defence;
        if (true_damage <= 0) {
            System.out.println(enemy.type + " blocked attack.");
        } else if (generator.nextInt(100) <= enemy.dexterity_points) {
            System.out.println(enemy.type + " dodged attack.");
        } else {
            System.out.println("Character attacks for : " + true_damage);
            enemy.health_points -= true_damage;
        }

        if (enemy.health_points < 0)
            enemy.health_points = 0;
    }
}
