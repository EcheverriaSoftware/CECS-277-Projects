/**
 * Class Orc contains the basic values for the Enemy Orc
 */
class Orc extends Enemy {

    /**
     * Creates an Orc enemy, HP: 4 Attack : 4
     *
     * @param i Item drop
     */
    public Orc(Item i) {

        super("Orc", 4, 4, i);

    }
}