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
        System.out.print(bob.map);
    }
    
    public void generate( Map m){
        Random rand = new Random(100);
        for(int i=0 ; i < m.sizeX; i++){
            List<String> nodes = new LinkedList<>();
            for (int j=0; j < m.sizeY; j++){
               //System.out.println(rand.nextInt(100)); 
                if (rand.nextInt(100)<40){
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
}
