import java.util.Random;

public class public class Main {

    public static int bossHealth = 2500;
    public static int bossDamage = 40;
    public static String bossDefendsType = "";
    public static int[] heroesHealth = {400, 310, 390, 800, 350, 250, 330, 200};
    public static int[] heroesDamage = {20, 25, 15, 2, 5, 10, 30, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Golem", "Lucky", "Berserk", "Thor", "Medic"};
    public static int roundNumber = 1;
    public static int healHeroes = 70;
    public static boolean luckPlayer, stunThor, golemBuff, randomMiss;;
    public static int minValue = heroesHealth[0];

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            chooseDefence();
            System.out.println("ROUND " + (roundNumber++));
            round();
        }
    }

    public static void setHealHeroes() {
        if (heroesHealth[7] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] <= 0) {
                    heroesHealth[i] = 0;
                }
                if (heroesHealth[i] < 100 && heroesHealth[i] > 1) {
                    System.out.println(heroesHealth[i]);
                    for (int j = 1; j < heroesHealth.length; j++) {
                        if (heroesHealth[j] < minValue && heroesHealth[j] > 0) {
                            minValue = heroesHealth[j];
                            heroesHealth[j] = minValue + healHeroes;
                        }
                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println(" ");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("_______________");
        System.out.println(" ");
    }

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); //0, 1, 2
        bossDefendsType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefendsType);

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes win!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) System.out.println("Boss won!!!");
        return allHeroesDead;
    }

    public static void bossHits() {
        if (stunThor) System.out.println("Босс получил оглушение от Тора и не может ударить. ");
        else {
            if (heroesHealth[3] > 0) {
                heroesHealth[3] = heroesHealth[3] + bossDamage;
                heroesHealth[3] = heroesHealth[3] - (bossDamage + (bossDamage / 5) * heroesHealth.length)-(bossDamage/5);
                golemBuff = true;
                if (heroesHealth[3] - bossDamage < 0) {
                    heroesHealth[3] = 0;  //
                }
            }
            else golemBuff = false;
            if (heroesHealth.length > 0 && bossHealth > 0) {
                Random random = new Random();
                randomMiss = random.nextBoolean();
                if (randomMiss && heroesHealth[4] > 0) {
                    heroesHealth[4] += bossDamage;
                    System.out.println("Лаки не получил урона. ");