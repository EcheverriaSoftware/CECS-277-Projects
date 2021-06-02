/**
 * Factory creates an enemy using the current hero level,
 * It also decorates an enemy giving it special spells if it is a warlock
 */
public abstract class Factory {

    /**
     * @param level Hero Level
     * @return Decorated enemy
     */
    public Enemy generateEnemy(int level) {

        Enemy e = createEnemy();
        boolean warriorType = true;
        if (Math.random() == 1) {
            warriorType = false;
        }
        for (int i = 1; i < level; i++) {
            if (warriorType) {
                e = new WarriorDecorator(e);
            } else {
                e = new WarlockDecorator(e);
            }
        }
        return e;
    }

    /**
     * Creates a new random enemy
     *
     * @return random enemy
     */
    public abstract Enemy createEnemy();
}