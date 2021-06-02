/**
 * 12/8/2020
 * Project 2
 * Kevin Garcia & Miguel Echeverria
 */

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class implements the game
 */
public class Main {
    /**
     * Main methods prompts the user to enter the hero's name and loops the game
     * until the player decide to quit the game or until the player is defeated
     */
    public static void main(String[] args) {
        Map board = new Map();
        board.loadMap(1);
        System.out.print("What is your name, traveler? ");
        Scanner input = new Scanner(System.in);
        Hero h1 = new Hero(input.nextLine(), board);
        ItemGenerator ig = ItemGenerator.getInstance();
        EnemyGenerator eg = EnemyGenerator.getInstance();
        int level = 1;
        int in = 0;
        char c = 'n';
        boolean go = false; // gameover
        boolean run = false;

        //
        h1.pickUpItem(ig.generateItem());
        store(h1);

        // While the game is not over
        while (go == false) {
            System.out.println(h1);
            board.displayMap(h1.getLocation());
            boolean getInput = true;
            // If the player run to another monster room
            if (run && c == 'm') {
                getInput = false;
            }
            if (getInput) {
                System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
                in = CheckInput.getIntRange(1, 5);
                if (in == 1) {
                    c = h1.goNorth();
                } else if (in == 2) {
                    c = h1.goSouth();
                } else if (in == 3) {
                    c = h1.goEast();
                } else if (in == 4) {
                    c = h1.goWest();
                } else {
                    System.out.println("Game Over!");
                    go = true;
                    break;
                }
            }
            if (c == 'm') {
                boolean cleared = monsterRoom(h1, board, eg, level);
                if (!cleared) {
                    run = true;
                    // Possible directions to run away from the monster room
                    boolean north = true, south = true, east = true, west = true;
                    if (h1.getLocation().x == 4) {
                        east = false;
                    }
                    if (h1.getLocation().x == 0) {
                        west = false;
                    }
                    if (h1.getLocation().y == 4) {
                        south = false;
                    }
                    if (h1.getLocation().y == 0) {
                        north = false;
                    }
                    boolean[] direction = {north, south, east, west};
                    ArrayList<Integer> runDir = new ArrayList<Integer>();
                    // Adds the index of each possible direction in direction array to runDir
                    for (int i = 0; i < direction.length; i++) {
                        if (direction[i]) {
                            runDir.add(i);
                        }
                    }
                    int rand = (int) (Math.random() * runDir.size());
                    if (runDir.get(rand) == 0) {
                        c = h1.goNorth();
                    } else if (runDir.get(rand) == 1) {
                        c = h1.goSouth();
                    } else if (runDir.get(rand) == 2) {
                        c = h1.goEast();
                    } else if (runDir.get(rand) == 3) {
                        c = h1.goWest();
                    }
                } else {
                    run = false;
                }
            } else if (c == 'i') {
                itemRoom(h1, board, ig);
            } else if (c == 'n') {
                System.out.println("There was nothing here.");
            } else if (c == 's') {
                System.out.println("You're back at the start.");
                store(h1);
            }
            System.out.println("=============");
            if (c == 'f') {
                if (h1.hasKey()) {
                    level += 1;
                    h1.heal(h1.getMaxHP());
                    System.out.println("Level " + level);
                    int mapNum = level % 3;
                    if (mapNum == 0) {
                        mapNum = 3;
                    }
                    board.loadMap(mapNum);
                    h1.useKey();
                    store(h1);
                } else {
                    System.out.println("You need a key to advance!");
                }

            }
            if (h1.getHP() == 0) {
                System.out.println("You are dead! Game Over");
                go = true;
            }
        }
    }

    /**
     * The monsterRoom method represents the interaction between the hero and the
     * monster in a monster room
     *
     * @param h     - the hero
     * @param m     - the current map
     * @param eg    - the enemy generator
     * @param level - the current level
     * @return true if the monster room is cleared, false otherwise
     */
    public static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg, int level) {
        Enemy e = eg.getInstance().generateEnemy(level);
        System.out.println("You've encountered a " + e.getName());
        boolean run = false;
        boolean cleared = false;
        while (h.getHP() != 0 && !run && !cleared) {
            System.out.println(h);
            System.out.println(e.getName() + "\nHP: " + e.getHP() + "/" + e.getMaxHP());
            System.out.println("1. Fight\n2. Run");
            int in = CheckInput.getIntRange(1, 2);
            if (in == 1) {
                cleared = fight(h, e);
            } else {
                System.out.println("You managed to escape!");
                run = true;
            }

            if (cleared) {
                m.removeCharAtLoc(h.getLocation());
            }
        }
        return cleared;
    }

    /**
     * The fight methods represents the battle between the hero and the monster
     *
     * @param h - the hero
     * @param e - the monster
     * @return - true if the hero win, false otherwise
     */
    public static boolean fight(Hero h, Enemy e) {
        boolean win = false;
        System.out.println(h.attack(e));
        if (e.getHP() == 0) {
            win = true;
            System.out.println("You defeated the " + e.getName());
            System.out.println("You reveived a " + e.getItem().getName() + " from its corpse.");
            // if item cannot be picked up because inventory is full
            if (!h.pickUpItem(e.getItem())) {
                System.out.println(
                        "Your inventory is already full\n1. Drop an item from inventory and replace with new item\n2. Drop new item");
                int choice = CheckInput.getIntRange(1, 2);
                if (choice == 1) {
                    System.out.println("Choose an item to drop");
                    System.out.println(h.itemsToString());
                    int drop_choice = CheckInput.getIntRange(1, h.getNumItems());
                    h.dropItem(drop_choice - 1);
                    h.pickUpItem(e.getItem());
                }
            }
        } else {
            if (h.hasArmor() != -1) {
                Item brokenArmor = h.dropArmor(h.hasArmor());
                ;
                System.out.println("Damage blocked by " + brokenArmor.getName() + "!");
            } else {
                System.out.println(e.attack(h));
            }
        }
        return win;
    }

    /**
     * The itemRoom methods represents the hero's action in an item room
     *
     * @param h  - the hero
     * @param m  - the current map
     * @param ig - the item generator
     */
    public static void itemRoom(Hero h, Map m, ItemGenerator ig) {
        boolean pickedUp = true;
        Item i = ig.generateItem();
        System.out.println("You found a " + i.getName());
        if (h.getNumItems() < 5) {
            h.pickUpItem(i);
        } else {
            System.out.println(
                    "Your inventory is already full\n1. Drop an item from inventory and replace with new item\n2. Drop new item");
            int choice = CheckInput.getIntRange(1, 2);
            if (choice == 1) {
                System.out.println("Choose an item to drop");
                System.out.println(h.itemsToString());
                int drop_choice = CheckInput.getIntRange(1, h.getNumItems());
                h.dropItem(drop_choice - 1);
                h.pickUpItem(i);
            } else {
                pickedUp = false;
            }
        }
        if (pickedUp) {
            m.removeCharAtLoc(h.getLocation());
        }
    }

    /**
     * Store where hero can sell or buy items
     *
     * @param h Object Hero
     */
    public static void store(Hero h) {

        ItemGenerator ig = ItemGenerator.getInstance();
        int option = 0;
        int itemSelection = 0;
        Item itemSold;

        System.out.println("Welcome to my humble store...");

        while (option != 3) {

            System.out.println("You have : " + h.getGold() + " gold.");
            System.out.println("What would you like to do?");

            System.out.println("1. Buy");
            System.out.println("2. Sell");
            System.out.println("3. Exit");

            option = CheckInput.getIntRange(1, 3);

            // Buy Options
            if (option == 1) {

                System.out.println("Here is what we offer.");
                System.out.println("1. Potion : " + ig.getPotion().getValue() + " gold");
                System.out.println("2. Key : " + ig.getKey().getValue() + " gold");
                System.out.println("3. Go back");

                itemSelection = CheckInput.getIntRange(1, 3);

                // Buying a potion
                if (itemSelection == 1) {

                    if (h.getGold() >= ig.getPotion().getValue()) {
                        if (h.pickUpItem(ig.getPotion())) {
                            h.spendGold(ig.getPotion().getValue());
                        } else {
                            System.out.println("I think your inventory is full. You should sell something");
                        }
                    } else {
                        System.out.println("You don't have enough money, are you trying to scam me?");
                    }
                }
                // Buying a Key
                else if (itemSelection == 2) {
                    if (h.getGold() >= ig.getKey().getValue()) {
                        if (h.pickUpItem(ig.getKey())) {
                            h.spendGold(ig.getKey().getValue());
                        } else {
                            System.out.println("I think your inventory is full. You should sell something");
                        }
                    } else {
                        System.out.println("You don't have enough money, are you trying to scam me?");
                    }
                }
                //Go back
                else if (itemSelection == 3) {
                    System.out.println("If you are not going to buy something don't waste my time");
                }
            }

            //Selling option
            else if (option == 2) {
                if (h.getNumItems() > 0) {

                    System.out.println("What are you selling?");
                    System.out.println(h.itemsToString());
                    System.out.println(h.getNumItems() + 1 + ". Go back");
                    itemSelection = CheckInput.getIntRange(1, h.getNumItems() + 1);

                    if (itemSelection != h.getNumItems() + 1) {
                        itemSold = h.dropItem(itemSelection - 1);
                        h.collectGold(itemSold.getValue());
                        System.out.println("The shopkeeper gives you " + itemSold.getValue() + " gold for your " + itemSold.getName());
                    } else if (itemSelection == h.getNumItems() + 1) {
                        System.out.println("Let me know if you change your mind.");
                    }


                } else {
                    System.out.println("You don't have anything, do you want me to call the guards?!");
                }
            }

        }
        System.out.println("Thank you, come again.");

    }

}
