/**
 * Class Froglok contains the basic values for the Enemy Froglok
 */
public class Froglok extends Enemy {

    /**
     * Creates a Froglok enemy, HP: 2 Attack : 5
     *
     * @param i Item drop
     */
    public Froglok(Item i) {

        super("Froglok", 2, 5, i);

    }
}