/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncreator;

/**
 *
 * @author jwulf
 */
public class Room {

    int x, x2;
    int y, y2;

    int sizeX;
    int sizeY;

    String name;
    String description;

    @Override
    public String toString() {
        return "Room: " + name + " x: " + x + " y: " + y + " xS: " + sizeX + " yS: " + sizeY;
    }

    public boolean overlaps(Room r2) {
        this.x2 = this.x + this.sizeX;
        this.y2 = this.y + this.sizeY;
        r2.x2 = r2.x + r2.sizeX;
        r2.y2 = r2.y + r2.sizeY;

        //see http://stackoverflow.com/questions/3269434/whats-the-most-efficient-way-to-test-two-integer-ranges-for-overlap
        if ((this.x < r2.x2 && r2.x < this.x2)) {
            System.out.println(name + " posible overlap: " + r2.name);
            if ((this.y < r2.y2 && r2.y < this.y2)) {
                System.out.println(name +" overlap: "+ r2.name);
                return true;
            }
        }
        return false;
    }
}
