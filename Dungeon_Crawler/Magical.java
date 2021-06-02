/**
 * Interface for magical attack
 */
public interface Magical {
    /**
     * Menu of magical attacks
     */
    public final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap";

    /**
     * Method to represent Magic Missile attack
     */
    public String magicMissile(Entity e);

    /**
     * Method to represent Fireball attack
     */
    public String fireball(Entity e);

    /**
     * Method to represent Thunderclap attack
     */
    public String thunderclap(Entity e);
}
