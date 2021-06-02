/**
 * WarriorDecorator adds modifications to a monster,
 * It will add 2 extra max hp and 2 extra damage
 */
public class WarriorDecorator extends EnemyDecorator {

    /**
     * Creates a enemy with added stats
     *
     * @param e Enemy to receive the modifications
     */
    public WarriorDecorator(Enemy e) {

        super(e, e.getName() + " Warrior", e.getHP() + 2, e.getAtk() + 2, e.getItem());

    }
}