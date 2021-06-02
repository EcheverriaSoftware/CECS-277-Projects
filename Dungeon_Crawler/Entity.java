/**
 * Entity is an abstract representation of an entity
 */
public abstract class Entity {

    // Name of the entity.
    private String name;
    // Maximum health points of the entity.
    private int maxHp;
    // Current health points of the entity.
    private int hp;

    /**
     * The Entity method is the overloaded constructor for the Entity
     * Initializes entity's name and maximum health points
     *
     * @param n   - the name of the entity.
     * @param mHp - max health points of the entity.
     */
    public Entity(String n, int mHp) {
        name = n;
        maxHp = mHp;
        hp = maxHp;
    }

    /**
     * The attack method represents the attack of the entity.
     */
    abstract public String attack(Entity e);

    /**
     * The getName method returns the name of the entity.
     *
     * @returns the name of the entity
     */
    public String getName() {
        return name;
    }

    /**
     * The getHP method returns the current health points of the entity.
     *
     * @returns the current health points of the entity
     */
    public int getHP() {
        return hp;
    }

    /**
     * The getMaxHP method returns the max health points of the entity.
     *
     * @returns the max health points of the entity
     */
    public int getMaxHP() {
        return maxHp;
    }

    /**
     * The heal method passes in an int h and increases the health points of the
     * entity by h.
     *
     * @param h - the amount of health to increase by.
     */
    public void heal(int h) {
        hp += h;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    /**
     * The takeDamage passes in an int h and decreases the health points of the
     * entity by h.
     *
     * @param h - the amount of health to decrease by.
     */
    public void takeDamage(int h) {
        if (hp > h) {
            hp -= h;
        } else
            hp = 0;
    }

    /**
     * toString method return the entity's information
     *
     * @returns the name, current health points, and max health points of the
     * entity.
     */
    public String toString() {
        return name + "\nHP: " + hp + "/" + maxHp;
    }
}
