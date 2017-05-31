import java.util.Random;

public class Battle {

    private static Random generator = new Random();

    static void enemyAttack (Enemies enemy) {

        int strength = generator.nextInt(enemy.strength_points);
        int defence = generator.nextInt(Character.defence_points) / 2;
        int true_damage = strength - defence;
        if (true_damage <= 0) {
            Interface.newEvent("Character blocked attack.");
        } else if (generator.nextInt(100) <= Character.dexterity_points) {
            Interface.newEvent("Character dodged attack.");
        } else {
            Interface.newEvent(enemy.type + " attacks for : " + true_damage);
            Character.health_points -= true_damage;
        }
        Character.checkIfAlive();
    }

    static void characterAttack (Enemies enemy) {

        int strength = generator.nextInt(Character.strength_points);
        int defence = generator.nextInt(enemy.defence_points) / 2;
        int true_damage = strength - defence;
        if (true_damage <= 0) {
            Interface.newEvent(enemy.type + " blocked attack.");
        } else if (generator.nextInt(100) <= enemy.dexterity_points) {
            Interface.newEvent(enemy.type + " dodged attack.");
        } else {
            Interface.newEvent("Character attacks for : " + true_damage);
            enemy.health_points -= true_damage;
        }

        if (enemy.health_points < 0)
            enemy.health_points = 0;
    }
}