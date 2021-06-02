/**
 * Class Goblin contains the basic values for the Enemy Goblin
 */
public class Goblin extends Enemy {

    /**
     * Creates a Goblin enemy, HP: 2 Attack : 2
     *
     * @param i Item drop
     */
    public Goblin(Item i) {

        super("Goblin", 2, 2, i);

    }
}