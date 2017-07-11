import java.util.Random;

public class Armory {
    static Random generator = new Random();
    static int DEX = 0;
    static int STR = 0;
    static int DEF = 0;

    static void editAbilities() {
        DEX = 0; STR = 0; DEF = 0;

        for (int place = 0; place < 2; place++) {
            if (Interface.equipment[place] == 11)
                DEX += 2;
            if (Interface.equipment[place] == 12)
                DEX -= 5;
            if (Interface.equipment[place] == 13)
                STR += 2;
            if (Interface.equipment[place] == 14)
                STR -= 5;
            if (Interface.equipment[place] == 15)
                DEF += 2;
            if (Interface.equipment[place] == 16)
                DEF -= 5;
        }
    }
}

class Swords extends Armory {
}

class Rings extends Armory {

    static boolean[] rings_known = new boolean[7];

    static void ringType(int index) {
        String[] rings = new String[7];
        rings[1] = "holy ring of dexterity: DEX + 2";
        rings[2] = "cursed ring of dexterity: DEX - 5";
        rings[3] = "holy ring of strength: STR + 2";
        rings[4] = "cursed ring of strength: STR - 5";
        rings[5] = "holy ring of defence: DEF + 2";
        rings[6] = "cursed ring of defence: DEF - 5";

        if (!rings_known[index])
            Interface.newEvent("It is a ring. I don't know it's properties.");
        else
            Interface.newEvent("It is a " + rings[index]);
    }

    static void identify(int index) {
        rings_known[index] = true;
        ringType(index);
    }

    static void useRing(int index) {
        Interface.addRing(index + 10);
    }

    static int dropRing() {
        int random = generator.nextInt(6);
        if (random == 0)
            return 11;
        else if (random == 1)
            return 12;
        else if (random == 2)
            return 13;
        else if (random == 3)
            return 14;
        else if (random == 4)
            return 15;
        else
            return 16;
    }
}

class Shields extends Armory {

    static int dropShield() {
        int random = generator.nextInt(30);
        if (random < 10)
            return 1;
        else if (random < 20)
            return 2;
        else
            return 3;
    }
}

class Armors extends Armory {

    static int dropArmor() {
        int random = generator.nextInt(30);
        if (random < 10)
            return 1;
        else if (random < 20)
            return 2;
        else
            return 3;
    }
}
