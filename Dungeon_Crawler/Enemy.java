/**
 * Enemy is a representation of a monster, subclass of Entity.
 */
public abstract class Enemy extends Entity {

    // An item that the enemy holds in their inventory.
    private Item item;
    // Attack value of an entity
    private int atk;

    /**
     * Initializes enemy's name, max health points, and item.
     *
     * @param n   - name of the Enemy.
     * @param mHp - maximum health points of the enemy.
     * @param i   - an item in the Enemy's inventory.
     */
    public Enemy(String n, int mHp, int a, Item i) {
        // Calls the constructor of the superclass, in this case, the superclass is the
        // Entity class.
        super(n, mHp);
        item = i;
        atk = a;
    }

    /**
     * The getItem retrieves info about the item in the Enemy's inventory.
     *
     * @returns the Item in the Enemy's inventory
     */
    public Item getItem() {
        return item;
    }

    /**
     * The getAtk retrieves info about the attack value of an enemy.
     *
     * @return Attack value
     */
    public int getAtk() {
        return atk;
    }

    /**
     * The attack method passes in an Entity e and calculates the damage that the
     * Entity will take. The attack method overrides the attack abstract method in
     * the superclass.
     *
     * @param e - The Entity that is being attacked.
     * @returns A description about the battle and damage done to Entity e.
     */
    @Override
    public String attack(Entity e) {
        int range = (int) (getMaxHP() * 1.5);
        int damage = (int) (Math.random() * range);
        e.takeDamage(damage);
        return getName() + " attacks " + e.getName() + " for " + damage + " damage.";
    }
}
