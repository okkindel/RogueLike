import java.util.Random;

public class Armory {
    static Random generator = new Random();
    static int DEX = 0;
    static int STR = 0;
    static int DEF = 0;
    static int ATT = 0;

    static void editAbilities() {
        DEX = 0; STR = 0; DEF = 0; ATT = 0;

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
        if (Interface.equipment[3] == 21)
            ATT += 1;
        if (Interface.equipment[3] == 22)
            ATT += 3;
        if (Interface.equipment[3] == 23)
            ATT += 5;
        if (Interface.equipment[2] == 31)
            DEF += 1;
        if (Interface.equipment[2] == 32)
            DEF += 3;
        if (Interface.equipment[2] == 33)
            DEF += 5;
    }
}

class Swords extends Armory {

    static void swordType(int index) {
        String[] swords = new String[4];
        swords[1] = "It's just a simple dagger. It will gently improves your impact.";
        swords[2] = "It's a short sword. Your attacks will become much stronger.";
        swords[3] = "It's a long sword. You have to have the strength of a warrior to use it.";

        Interface.newEvent(swords[index]);
    }

    static void useSword(int index) {
        Interface.addStuff(index + 20);
    }

    static int dropSword() {
        int random = generator.nextInt(10);
        if (random < 6)
            return 21;
        else if (random < 9)
            return 22;
        else
            return 23;
    }
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
        Interface.addStuff(index + 10);
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

    static void armorType(int index) {
        String[] armors = new String[4];
        armors[1] = "Just ordinary cloth, but better than nothing.";
        armors[2] = "This chain mail will surely stop many smites.";
        armors[3] = "Real plate armor! How did it get here?";

        Interface.newEvent(armors[index]);
    }

    static void useArmor(int index) {
        Interface.addStuff(index + 30);
    }

    static int dropArmor() {
        int random = generator.nextInt(10);
        if (random < 6)
            return 31;
        else if (random < 9)
            return 32;
        else
            return 33;
    }
}
