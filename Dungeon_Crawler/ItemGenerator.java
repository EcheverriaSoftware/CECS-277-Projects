import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ItemGenerator is a model of an item generator
 */
public class ItemGenerator {

    // An ArrayList stores Item objects.
    private ArrayList<Item> itemList;
    // An instance of ItemGenerator.
    private static ItemGenerator instance = null;

    /**
     * The ItemGenerator method is the constructor method of the class and reads the
     * name of items from a text file to constructs Item objects and adds the newly
     * constructed objects to itemList.
     */
    public ItemGenerator() {
        itemList = new ArrayList<Item>();
        try {
            Scanner read = new Scanner(new File("ItemList.txt"));
            read.useDelimiter("[,\n]");
            while (read.hasNext()) {
                String itemName = read.next();
                int value = Integer.parseInt(read.next());
                char type = read.next().charAt(0);
                itemList.add(new Item(itemName, value, type));
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    /**
     * Gets a new instance of itemGenerator, or creates a new one if it doesn't exists.
     *
     * @return Instance of item Generator
     */
    public static ItemGenerator getInstance() {
        if (instance == null) {
            instance = new ItemGenerator();
        }
        return instance;
    }

    /**
     * The generateItem method randomly returns an item from the itemList ArrayList.
     *
     * @returns an Item object from the itemList ArrayList.
     */
    public Item generateItem() {
        int random = (int) (Math.random() * itemList.size());
        return itemList.get(random);
    }

    /**
     * Generates a potion using design pattern
     *
     * @return Potion item
     */
    public Item getPotion() {
        Prototype potion = null;
        for (int i = 0; i < itemList.size(); i++) {

            if (itemList.get(i).getType() == 'p') {
                potion = itemList.get(i).clone();
            }
        }
        return (Item) potion;
    }

    /**
     * Generates a key using design pattern
     *
     * @return Key item
     */
    public Item getKey() {
        Prototype key = null;
        for (int i = 0; i < itemList.size(); i++) {

            if (itemList.get(i).getType() == 'k') {
                key = itemList.get(i).clone();
            }
        }
        return (Item) key;
    }

}
