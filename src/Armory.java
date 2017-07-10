import java.util.Random;

public class Armory {
    static Random generator = new Random();

    static int newRing() {
        Rings ring = new Rings();
        return ring.type;
    }
}

class Swords extends Armory {
}

class Rings extends Armory {

    int type = 0;
    boolean known = false;

    Rings() {
        dropRing();
    }

    private void dropRing() {
        type = generator.nextInt(6);
    }

    private String ringType(int ring_type) {
        if (ring_type == 0 || ring_type == 3)
            return "dexterity";
        if (ring_type == 1 || ring_type == 4)
            return "strength";
        if (ring_type == 2 || ring_type == 5)
            return "defence";
        else return "";
    }

    private boolean ringCursed(int ring_type) {
        return !(ring_type == 0 || ring_type == 1 || ring_type == 2);
    }

    void checkRing(int ring_type) {
        if (!known)
            Interface.newEvent("It is ring of " + ringType(ring_type) + ".");
        if (known) {
            if (ringCursed(ring_type))
                Interface.newEvent("It is cursed ring of " + ringType(ring_type) + ": - 5.");
            if (!ringCursed(ring_type))
                Interface.newEvent("It is holy ring of " + ringType(ring_type) + ": + 5.");
        }
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
