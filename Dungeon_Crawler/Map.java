import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Map is a model of a map
 */
public class Map {
    // 2d char array that stores the map layout.
    private char[][] map;
    // 2d boolean array that stores true or false.
    private boolean[][] revealed;

    /**
     * The map method is the default constructor of the Map class.
     * Initializes map array.
     */
    public Map() {
        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = 'x';
            }
        }

        revealed = new boolean[5][5];
    }

    /**
     * The loadMap method takes an integer representing the map number, reads the
     * corresponding map layout from a text file, and stores the layout in map array
     *
     * @param mapNum - map number
     */
    public void loadMap(int mapNum) {
        try {
            Scanner read = new Scanner(new File("Map" + mapNum + ".txt"));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = read.next().charAt(0);
                    revealed[i][j] = false;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

    }

    /**
     * The geCharAtLoc method takes a Point p and returns the char value at Point p.
     *
     * @param p - the point to get character
     * @returns the character value of the Player's (x, y) coordinates.
     */
    public char getCharAtLoc(Point p) {
        return map[p.y][p.x];
    }

    /**
     * The displayMap method takes the player's current location and prints out the
     * map with a marker to show where the player is located
     *
     * @param p - the point where the player is located.
     */
    public void displayMap(Point p) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!(j == p.x && i == p.y)) {
                    if (revealed[i][j]) {
                        System.out.print(map[i][j]);
                    } else {
                        System.out.print('x');
                    }
                } else {
                    System.out.print('*');
                }
            }
            System.out.println();
        }
    }

    /**
     * The findStart method locates the starting position of the player in each map.
     *
     * @returns Point object that stores the (x, y) coordinates of the start
     * position of the current level.
     */
    public Point findStart() {
        Point p = new Point();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 's') {
                    p.x = j;
                    p.y = i;
                }
            }
        }
        return p;
    }

    /**
     * The reveal method takes a Point p and reveals the character at the point p.
     *
     * @param p - the point to be revealed.
     */
    public void reveal(Point p) {
        revealed[p.y][p.x] = true;
    }

    /**
     * The removeCharAtLoc method takes a Point p and sets the char at p's (x, y)
     * coordinate as 'n' (nothing).
     *
     * @param p - the point to remove its character.
     */
    public void removeCharAtLoc(Point p) {
        map[p.y][p.x] = 'n';
    }
}
