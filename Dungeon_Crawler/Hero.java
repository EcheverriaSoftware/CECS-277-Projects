import java.util.ArrayList;
import java.awt.Point;
import java.lang.Math;

/**
 * Hero is a representation of a hero, subclass of Entity and implements Magical.
 */
public class Hero extends Entity implements Magical {

    // The player's inventory.
    private ArrayList<Item> items;
    // The map layout the player is traversing.
    private Map map;
    // The player's location on the map.
    private Point location;
    // The player's gold amount
    private int gold;

    /**
     * The Hero method is the overloaded constructor for the Hero class
     * Initializes hero's name and map
     *
     * @param n - name of the player.
     * @param m - the map the player is playing on.
     */
    public Hero(String n, Map m) {
        super(n, 25);
        items = new ArrayList<Item>();
        map = m;
        location = m.findStart();
        gold = 50;
    }

    /**
     * The toString method in the Hero class overrides the toString method of the
     * super class and provides info about the hero.
     *
     * @return String containing info about the hero such as *health and inventory.
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + itemsToString();
    }

    /**
     * The itemsToString method in the Hero class returns the name of all the items
     * in the Hero's inventory.
     *
     * @return String containing info about the hero's *inventory.
     */
    public String itemsToString() {
        String s = "Inventory: ";
        for (int i = 0; i < items.size(); i++) {
            s += "\n" + (i + 1) + ". " + items.get(i).getName();
        }

        return s;
    }

    /**
     * The getNumItem methods gets the number of items in the Hero's inventory.
     *
     * @return An integer number of the items in the Hero's inventory.
     */
    public int getNumItems() {
        return items.size();
    }

    /**
     * The pickUpItem method passes in an Item i and allows the player to pick up
     * that Item i if they have less than 5 items in their inventory.
     *
     * @param i - the item the player picks up
     * @returns A boolean, true if the player can pick up the item or false if they
     * can not.
     */
    public boolean pickUpItem(Item i) {
        boolean result = false;
        if (items.size() < 5) {
            items.add(i);
            result = true;
        }
        return result;
    }

    /**
     * The drinkPotion methods allows the player to drink a potion during battle (if
     * they have one) and restore their health points.
     */
    public void drinkPotion() {
        boolean potionFound = false;
        for (int i = 0; (i < items.size()) && (!potionFound); i++) {
            if (items.get(i).getName().compareTo("Health Potion") == 0) {
                heal(25);
                dropItem(i);
                potionFound = true;
            }
        }

    }


    /**
     * The dropItem method passes in an int index and the player would drop the item
     * that is in the index passed.
     *
     * @param index the index of the item the player wants to drop.
     * @return Item dropped
     */
    public Item dropItem(int index) {

        Item itemDropped = items.get(index);
        items.remove(index);
        return itemDropped;
    }

    /**
     * The hasPotion method checks to see if the player has a potion in their
     * inventory.
     *
     * @return A boolean, true if the player has a potion or false if they do not.
     */
    public boolean hasPotion() {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().compareTo("Health Potion") == 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * The getLocation method gets the player's location.
     *
     * @return A Point, that represents the player's location.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * The goNorth method allows the player to move North and checks to see if the
     * player's next step is within map bounds.
     *
     * @returns A char indicating what type of room the player is in.
     */
    public char goNorth() {
        if (location.y > 0) {
            map.reveal(location);
            location.y -= 1;
        } else {
            System.out.println("You cannot go North");
        }
        return map.getCharAtLoc(location);
    }

    /**
     * The goSouth method allows the player to move South and checks to see if the
     * player's next step is within map bounds.
     *
     * @returns A char indicating what type of room the player is in.
     */
    public char goSouth() {
        if (location.y < 4) {
            map.reveal(location);
            location.y += 1;
        } else {
            System.out.println("You cannot go South");
        }
        return map.getCharAtLoc(location);
    }

    /**
     * The goEast method allows the player to move East and checks to see if the
     * player's next step is within map bounds.
     *
     * @returns A char indicating what type of room the player *is in.
     */
    public char goEast() {
        if (location.x < 4) {
            map.reveal(location);
            location.x += 1;
        } else {
            System.out.println("You cannot go East");
        }
        return map.getCharAtLoc(location);
    }

    /**
     * The goWest method allows the player to move West and checks to see if the
     * player's next step is within map bounds.
     *
     * @returns A char indicating what type of room the player is in.
     */
    public char goWest() {
        if (location.x > 0) {
            map.reveal(location);
            location.x -= 1;
        } else {
            System.out.println("You cannot go West");
        }
        return map.getCharAtLoc(location);
    }

    /**
     * The attack method passes in an Entity e and allows the player to attack or
     * run away from the entity. If they choose to fight, they can use a physical
     * attack or magical attack. The method returns a String of the battle
     * interaction b/w the entity and Hero.
     *
     * @param e - the entity that is battling the Hero.
     * @return A string of the battle.
     */
    @Override
    public String attack(Entity e) {
        String s = "";
        int range = (int) (getMaxHP() / 2);
        int damage = (int) (Math.random() * range);
        System.out.println("1. Physical Attack\n2. Magic Attack");
        if (hasPotion()) {
            System.out.println("3. Drink Health Potion");
        }
        int in = 0;
        if (hasPotion()) {
            in = CheckInput.getIntRange(1, 3);
        } else {
            in = CheckInput.getIntRange(1, 2);
        }
        if (in == 1) {
            s += getName() + " attacks " + e.getName() + " for " + damage + " damage.";
            e.takeDamage(damage);
        } else if (in == 2) {
            System.out.println(MAGIC_MENU);
            int choice = CheckInput.getIntRange(1, 3);
            switch (choice) {
                case 1:
                    s += magicMissile(e);
                    break;
                case 2:
                    s += fireball(e);
                    break;
                case 3:
                    s += thunderclap(e);
                    break;
            }
        } else if (in == 3) {
            s += getName() + " +25 HP";
            drinkPotion();
        }
        return s;
    }

    /**
     * The magicMissile method passes in an Entity e, returns a String of the battle
     * interaction b/w the entity and the hero after using magicMissile.
     *
     * @param e - the entity that is battling the hero.
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
        return getName() + " hits " + e.getName() + " with Fireball for " + damage + " damage.";
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
        return getName() + " hits " + e.getName() + " with Thunderclap for " + damage + " damage.";
    }

    /**
     * The getGold method returns how much gold the hero has.
     *
     * @return gold amount on the hero
     */
    public int getGold() {
        return gold;
    }

    /**
     * The collectGold method passes in a int g and adds that amount to the hero's gold.
     *
     * @param g - The gold the hero gains
     */
    public void collectGold(int g) {
        gold += g;
    }

    /**
     * The spendGold method passes in a int g and subtracts that amount from hero's gold.
     *
     * @param g - The gold the hero spends
     */
    public void spendGold(int g) {
        if (g > gold) {
            System.out.println("~You don't have enough gold!~");
        } else {
            System.out.println("You spent " + g + " gold.");
            gold -= g;
        }
    }

    /**
     * The hasArmor method checks if the hero has armor and returns the index of the first piece of armor .
     *
     * @return index of first piece of armor
     */
    public int hasArmor() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType() == 'a') {
                return i;
            }
        }
        return -1;
    }

    /**
     * The hasArmor method drops a piece of armor from Hero's inventory after being attacked.
     *
     * @return Item armor
     */
    public Item dropArmor(int index) {
        Item droppedItem = items.get(index);
        items.remove(index);
        return droppedItem;
    }

    /**
     * The hasKey method checks if the hero has a key.
     *
     * @return Boolean value if hero has a key in their inventory.
     */
    public boolean hasKey() {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().compareTo("Key") == 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * The hasKey method uses the key when at gate of the next level.
     */
    public void useKey() {
        for (int i = 0; (i < items.size()); i++) {
            if (items.get(i).getName().compareTo("Key") == 0) {
                dropItem(i);
            }
        }
        System.out.println("The door has been unlocked!");
    }
}
