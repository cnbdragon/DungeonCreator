/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncreator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jwulf
 */
public class DungeonCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DungeonCreator dc = new DungeonCreator();
        Map bob = new Map();
        bob.sizeX = 20;
        bob.sizeY = 10;
        dc.generate(bob);
        dc.generateRooms(bob);
        System.out.println(bob.map);
        System.out.println(bob.rooms);
    }

    public void generate(Map m) {
        Random rand = new Random(100);
        for (int i = 0; i < m.sizeX; i++) {
            List<String> nodes = new LinkedList<>();
            for (int j = 0; j < m.sizeY; j++) {
                //System.out.println(rand.nextInt(100)); 
                if (rand.nextInt(100) < 40) {
                    System.out.print("#");
                    nodes.add("#");
                } else {
                    System.out.print(" ");
                    nodes.add(" ");
                }
            }
            m.map.add(nodes);
            System.out.println();
        }

    }

    public void generateRooms(Map m) {
        boolean overlaps;
        System.out.println("genRooms");
        Random rand = new Random(1000);
        System.out.println("rand "+rand);
        int numRooms = randInt(4, 10, rand);
        for (int n = 1; n < numRooms; n++) {
            overlaps = false;
            Room tempRoom = generateRoom(m, rand);
            tempRoom.name = "" + n;
            /*tempRoom.x =  rand.nextInt(m.sizeX/2);
             tempRoom.y = rand.nextInt(m.sizeY/2);
             tempRoom.sizeX = rand.nextInt(1+(m.sizeX/2));
             tempRoom.sizeY = rand.nextInt(1+(m.sizeY/2));*/
            for (Room r : m.rooms){
                if(tempRoom.overlaps(r)){
                    //n--;
                    //continue;
                    overlaps = true;
                }
            }
            if(tempRoom.x+tempRoom.sizeX > m.sizeX || tempRoom.y+tempRoom.sizeY> m.sizeY || overlaps){
                System.out.println(tempRoom.name+" Bad Room");
                n--;
            } else {
                m.rooms.add(tempRoom);
            }

            
        }

    }

    // TODO remove hardcode of room size
    private Room generateRoom(Map m, Random rand) {
        Room tempRoom = new Room();
        tempRoom.x = randInt(2, m.sizeX, rand);
        tempRoom.y = randInt(2, m.sizeY, rand);
        tempRoom.sizeX = randInt(2, 15, rand);
        tempRoom.sizeY = randInt(2, 15, rand);
        return tempRoom;
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive. The
     * difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @param seed the random seed
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public int randInt(int min, int max, Random rand) {

    // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        //Random rand = new Random(100);
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
