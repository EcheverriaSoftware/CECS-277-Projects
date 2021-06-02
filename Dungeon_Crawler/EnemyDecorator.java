/**
 * Allows enemies to be modified with extra stats or/and magic
 */
public abstract class EnemyDecorator extends Enemy {

    //Enemy to be decorated
    private Enemy enemy;

    /**
     * Constructor of EnemyDecorator, will pass all values that are required to decorate an enemy
     *
     * @param e   Enemy object to be decorated
     * @param n   Enemy Name
     * @param Hp  Enemy max HP
     * @param atk Enemy attack value
     * @param i   Enemy loot item
     */
    public EnemyDecorator(Enemy e, String n, int Hp, int atk, Item i) {
        super(n, Hp, atk, i);
        enemy = e;
    }
}