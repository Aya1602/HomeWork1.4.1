import java.util.Random;

public class Main {

    public static int[] health = {2000, 250, 250, 250, 250};
    public static int[] hits = {50, 20, 20, 20, 15};
    public static String[] hitTypes = {"Physical", "Physical",
            "Magical", "Mental", "Medic"};

    public static void main(String[] args) {
        printStatistic();
        while (!isFinished()) {
            changeBossDefence();
            round();
            printStatistic();
        }
    }

    public static int healingPlayer (int indexPlayer) {

        if (health [indexPlayer] > 0 && health [4] > 0) {
            return hits [4] + health [indexPlayer];
        }

        return health [indexPlayer];
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;
        hitTypes[0] = hitTypes[randomNumber];
    }

    public static void round() {
        for (int i = 1; i <= 4; i++) {
            int healthRemain = bossHit(i);
            if (healthRemain < 0) {
                health[i] = 0;
            } else {
                health[i] = healthRemain;
            }
        }
        for (int i = 1; i <= 3; i++) {
            int healthRemain = playerHit(i);
            if (healthRemain < 0) {
                health[0] = 0;
            } else {
                health[0] = healthRemain;
            }

        }

        for (int u = 1; u <= 3; u++) {
            health [u] = healingPlayer(u);

        }
    }

    public static int playerHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            Random r = new Random();
            int randomNumber = r.nextInt(7) + 2;
            System.out.println(hitTypes[playerIndex]
                    + " critically hits Boss " +
                    hits[playerIndex] * randomNumber);
            return health[0] - hits[playerIndex] * randomNumber;
        } else {
            return health[0] - hits[playerIndex];
        }
    }

    public static int bossHit(int playerIndex) {
        return health[playerIndex] - hits[0];
    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won the game!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0) {
            System.out.println("Boss won the game!!!");
            return true;
        }
        return false;
    }

    public static void printStatistic() {
        System.out.println("________________");
        System.out.println("Boss health: " + health[0]);
        System.out.println("Boss defence: " + hitTypes[0]);
        System.out.println("Warrior health: " + health[1]);
        System.out.println("Magic health: " + health[2]);
        System.out.println("Mental health: " + health[3]);
        System.out.println("________________");
    }
}

