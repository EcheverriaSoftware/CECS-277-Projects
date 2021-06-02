import java.lang.Math;

/**
 * EnemyGenerator is a model of an enemy generator
 */
public class EnemyGenerator extends Factory {

    //Instance of enemy generator
    private static EnemyGenerator instance = null;

    /**
     * Method randomly creates a enemy, default enemy being a goblin
     *
     * @return Randomly created enemy
     */
    @Override
    public Enemy createEnemy() {
        int min = 0;
        int max = 3;
        int range = (max - min) + 1;
        int randNum = (int) (Math.random() * range) + min;
        if (randNum == 0) {
            return new Troll(ItemGenerator.getInstance().generateItem());
        } else if (randNum == 1) {
            return new Froglok(ItemGenerator.getInstance().generateItem());
        } else if (randNum == 2) {
            return new Orc(ItemGenerator.getInstance().generateItem());
        } else if (randNum == 3) {
            return new Goblin(ItemGenerator.getInstance().generateItem());
        } else {
            return new Goblin(ItemGenerator.getInstance().generateItem());
        }

    }

    /**
     * Returns a single instance of enemy generator or creates one if there is not one already.
     *
     * @return A single instance of the enemy generator
     */
    public static EnemyGenerator getInstance() {
        if (instance == null) {
            instance = new EnemyGenerator();
        }
        return instance;
    }
}
