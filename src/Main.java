import java.util.Random;

public class Main {

    public static int[] health = {700, 250, 250, 250, 200};
    public static int[] damage = {50, 20, 20, 20, 0};
    public static String[] hitTypes = {"Physical", "Physical", "Magical", "Mental", "Medical"};

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            hitTypes[0] = genarateBossDefenceType();
            gameRounddd();
            heal();
        }
    }

    public static void heal() {
        if (health[4] > 0) {
            if (health[1] > 0 && health[2] > 0 && health[3] > 0) {
                for (int i = 1; i <= 3; i++) {
                    health[i] = health[i] + 20;
                }
            }
        }
    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Герои won!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void gameRounddd() {
        for (int i = 1; i <= 4; i++) {
            int healthRemainBoss = hitBoss(i);
            if (healthRemainBoss < 0) {
                health[0] = 0;
            } else {
                health[0] = healthRemainBoss;
            }
            int healthRemainHeroes = hitPlayer(i);
            if (healthRemainHeroes < 0) {
                health[i] = 0;
            } else {
                health[i] = healthRemainHeroes;
            }

        }
        printStatistics();
    }

    public static String genarateBossDefenceType(){
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;
        return hitTypes[randomNumber];
    }

    public static int hitBoss(int playerIndex) {

        Random r = new Random();
        int randomNumber = r.nextInt(8) + 1; // генерируем случайное число от 1 до 5
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            System.out.println(hitTypes[playerIndex] + " нанес критический удар " +
                    damage[playerIndex] * randomNumber);
            return health[0] - damage[playerIndex] * randomNumber;
        }
        return health[0] - damage[playerIndex];

    }


    public static int hitPlayer(int playerIndex) {
        return health[playerIndex] - damage[0];
    }

    public static void printStatistics() {
        System.out.println("_________________________");
        System.out.println("Boss health " + health[0]);
        System.out.println("Warrior health " + health[1]);
        System.out.println("Magic health " + health[2]);
        System.out.println("Kinetic health " + health[3]);
        System.out.println("Medic health " + health[4]);
        System.out.println("_________________________");

    }
}



