/**
 * WarlockDecorator adds magic usage for a enemy, plus some extra stats
 */
public class WarlockDecorator extends EnemyDecorator implements Magical {

    /**
     * Adds the Warlock modifier, extra stats.
     *
     * @param e Enemy to be modified
     */
    public WarlockDecorator(Enemy e) {
        super(e, e.getName() + " Warrior", e.getHP() + 1, e.getAtk() + 1, e.getItem());
    }

    /**
     * Overridden method that replaces the original attack for a magical attack
     *
     * @param en Entity doing the magic attack
     * @return Attack String description
     */
    @Override
    public String attack(Entity en) {

        int min = 0;
        int max = 2;
        int range = (max - min) + 1;
        int randNum = (int) (Math.random() * range) + min;

        if (randNum == 0) {
            return magicMissile(en);
        } else if (randNum == 1) {
            return fireball(en);
        } else {
            return thunderclap(en);
        }
    }

    /**
     * The magicMissile method passes in an Entity e, returns a String of the battle
     * interaction b/w the entity and the hero after using a magic missile.
     *
     * @param e the entity that is battling the hero
     * @return A String of the battle.
     */
    @Override
    public String magicMissile(Entity e) {

        int range = (int) (getMaxHP() / 2);
        int damage = (int) (Math.random() * range);
        e.takeDamage(damage);
        return getName() + " hits " + e.getName() + " with Magic Missile for " + damage + " damage.";
    }

    /**
     * The fireball method passes in an Entity e, returns a String of the battle
     * interaction b/w the entity and the hero after using fireball.
     *
     * @param e - the entity that is battling the hero.
     * @return A String of the battle.
     */
    @Override
    public String fireball(Entity e) {

        int range = (int) (getMaxHP() / 2);
        int damage = (int) (Math.random() * range);
        e.takeDamage(damage);
        return getName() + " burns " + e.getName() + " with Fireball for " + damage + " damage.";
    }

    /**
     * The thunderclap method passes in an Entity e, returns a String of the battle
     * interaction b/w the entity and the hero after using thunderclap.
     *
     * @param e - the entity that is battling the hero.
     * @return A String of the battle.
     */
    @Override
    public String thunderclap(Entity e) {

        int range = (int) (getMaxHP() / 2);
        int damage = (int) (Math.random() * range);
        e.takeDamage(damage);
        return getName() + " zaps " + e.getName() + " with Thunderclap for " + damage + " damage.";
    }
}