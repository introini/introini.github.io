/* Michael Introini
 * mbi2105
 * Rectangle.java - This class defines the the properties of a Rectangle object
 */

public class Rectangle implements Comparable<Rectangle> {

    // Declare variables
    int length;
    int width;
    int perimeter;

    // Initialize the Rectangle object
    public Rectangle(int l, int w) {
        length = l;
        width = w;
        perimeter = 2 * (length + width);
    }

    // Return length of the Rectangle object
    public int getLength() {
        return length;
    }

    // Return width of the Rectangle object
    public int getWidth() {
        return width;
    }

    // Compare perimeter
    @Override
    public int compareTo(Rectangle other) {

        // Is the current perimeter smaller than the next perimeter?
        // Is it larger than the next?
        // If it's neither, then it's equal.
        if (this.perimeter < other.perimeter) {
            return -1;
        } else if (this.perimeter > other.perimeter) {
            return 1;
        } else {
            return 0;
        }
    }

    // Make the description more legible
    public String toString() {
        String recDescription;

        // Output the Width and Length of the Rectangle
        recDescription = "Width = " + getWidth() + " | " + "Length = " +
                getLength() + " | " + "Perimeter = " + perimeter;

        return recDescription;
    }
}
