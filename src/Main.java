import java.util.Scanner;
import java.util.Random;

// =========================
// SUPERCLASS
// =========================
class Character {

    protected String name;
    protected int health;

    // Constructor
    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // Method to be overridden
    public void attack() {
        System.out.println(name + " attacks!");
    }

    // Encapsulation
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health >= 0) {
            this.health = health;
        }
    }

    public String getName() {
        return name;
    }
}

// =========================
// WARRIOR CLASS
// =========================
class Warrior extends Character {

    private int strength;

    public Warrior(String name, int health, int strength) {
        super(name, health);
        this.strength = strength;
    }

    // Method Overriding
    @Override
    public void attack() {
        System.out.println(name + " swings a sword!");
    }

    // Method Overloading
    public int attack(int bonus) {
        return strength + bonus;
    }

    public int getStrength() {
        return strength;
    }
}

// =========================
// ARCHER CLASS
// =========================
class Archer extends Character {

    private int arrows;

    public Archer(String name, int health, int arrows) {
        super(name, health);
        this.arrows = arrows;
    }

    // Method Overriding
    @Override
    public void attack() {

        if (arrows > 0) {
            System.out.println(name + " shoots an arrow!");
            arrows--;
        } else {
            System.out.println(name + " has no arrows left!");
        }
    }

    // Method Overloading
    public int attack(String enemy) {
        System.out.println(name + " fires at " + enemy + "!");
        arrows--;
        return 15;
    }

    public int getArrows() {
        return arrows;
    }
}

// =========================
// MULTILEVEL INHERITANCE
// =========================
class EliteWarrior extends Warrior {

    private int armor;

    public EliteWarrior(String name, int health, int strength, int armor) {
        super(name, health, strength);
        this.armor = armor;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " performs an ELITE attack!");
    }

    public int getArmor() {
        return armor;
    }
}

// =========================
// ENEMY CLASS
// =========================
class Enemy {

    private String enemyName;
    private int enemyHealth;

    public Enemy(String enemyName, int enemyHealth) {
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
    }

    public void takeDamage(int damage) {
        enemyHealth -= damage;

        if (enemyHealth < 0) {
            enemyHealth = 0;
        }
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public String getEnemyName() {
        return enemyName;
    }
}

// =========================
// KINGDOM CLASS
// =========================
class Kingdom {

    private String kingdomName;
    private int gold;

    private static int totalKingdoms = 0;

    // Constructor
    public Kingdom(String kingdomName, int gold) {
        this.kingdomName = kingdomName;
        this.gold = gold;
        totalKingdoms++;
    }

    // Overloaded Constructor
    public Kingdom(String kingdomName) {
        this(kingdomName, 100);
    }

    public void addGold(int amount) {
        gold += amount;
        System.out.println(amount + " gold added to the kingdom.");
    }

    public void spendGold(int amount) {

        if (amount <= gold) {
            gold -= amount;
            System.out.println(amount + " gold spent.");
        } else {
            System.out.println("Not enough gold!");
        }
    }

    public int getGold() {
        return gold;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public static int getTotalKingdoms() {
        return totalKingdoms;
    }
}

// =========================
// MAIN GAME CLASS
// =========================
class KingdomSurvival {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=================================");
        System.out.println("     KINGDOM SURVIVAL GAME");
        System.out.println("=================================");

        // Kingdom Object
        Kingdom kingdom = new Kingdom("Eldoria", 200);

        // Character Objects
        Warrior warrior = new Warrior("Ragnar", 100, 25);
        Archer archer = new Archer("Luna", 80, 5);
        EliteWarrior elite = new EliteWarrior("Drake", 150, 40, 50);

        // Polymorphism
        Character player;

        System.out.println("\nChoose your fighter:");
        System.out.println("1. Warrior");
        System.out.println("2. Archer");
        System.out.println("3. Elite Warrior");

        int choice = input.nextInt();

        if (choice == 1) {
            player = warrior;
        } else if (choice == 2) {
            player = archer;
        } else {
            player = elite;
        }

        System.out.println("\nYou selected: " + player.getName());

        Enemy enemy = new Enemy("Dark Goblin", 100);

        // GAME LOOP
        while (enemy.getEnemyHealth() > 0 && player.getHealth() > 0) {

            System.out.println("\n========================");
            System.out.println("Enemy: " + enemy.getEnemyName());
            System.out.println("Enemy Health: " + enemy.getEnemyHealth());
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("Kingdom Gold: " + kingdom.getGold());
            System.out.println("========================");

            System.out.println("\nChoose Action:");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Search Gold");

            int action = input.nextInt();

            // ATTACK
            if (action == 1) {

                player.attack();

                int damage = random.nextInt(20) + 10;

                enemy.takeDamage(damage);

                System.out.println("You dealt " + damage + " damage!");
            }

            // HEAL
            else if (action == 2) {

                player.setHealth(player.getHealth() + 20);

                System.out.println(player.getName() + " healed for 20 health!");
            }

            // SEARCH GOLD
            else if (action == 3) {

                int foundGold = random.nextInt(50) + 10;

                kingdom.addGold(foundGold);
            }

            // Enemy Attack
            if (enemy.getEnemyHealth() > 0) {

                int enemyDamage = random.nextInt(15) + 5;

                player.setHealth(player.getHealth() - enemyDamage);

                System.out.println(enemy.getEnemyName() +
                        " attacked you for " + enemyDamage + " damage!");
            }
        }

        // GAME RESULT
        System.out.println("\n=================================");

        if (player.getHealth() > 0) {

            System.out.println("YOU WON!");
            System.out.println("The kingdom survived!");
            kingdom.addGold(100);

        } else {

            System.out.println("GAME OVER!");
            System.out.println("Your kingdom has fallen...");
        }

        System.out.println("\nFinal Gold: " + kingdom.getGold());
        System.out.println("Total Kingdoms Created: " + Kingdom.getTotalKingdoms());

        input.close();
    }
}