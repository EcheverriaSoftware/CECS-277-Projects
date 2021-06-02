/**
 * Item is a model of an item
 */
public class Item implements Prototype {

    // Name of the item.
    private String name;
    // Name gold value of the item.
    private int value;
    // Type of the item.
    private char type;

    /**
     * The item method is the overloaded constructor method of the Item class.
     * Initializes item's name
     *
     * @param n - name of the item.
     */
    public Item(String n, int v, char t) {
        name = n;
        value = v;
        type = t;
    }

    /**
     * The Item method constructor copies Item objects
     *
     * @param i - The item object to copy.
     */
    public Item(Item i) {
        if (i != null) {
            name = i.getName();
            value = i.getValue();
            type = i.getType();
        }
    }

    /**
     * Returns a copy of the item
     *
     * @return Item copy
     */
    @Override
    public Prototype clone() {
        return new Item(this);
    }

    /**
     * The getName method gets the name of the item.
     *
     * @returns the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * The getValue method gets the value of the item.
     *
     * @returns the value of the item.
     */
    public int getValue() {
        return value;
    }

    /**
     * The getType method gets the type of the item.
     *
     * @returns the type of type item.
     */
    public char getType() {
        return type;
    }
}
