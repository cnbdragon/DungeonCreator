/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncreator;

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
        bob.sizeY = 20;
        dc.generate(bob);
    }
    
    public void generate( Map m){
        Random rand = new Random(100);
        for(int i =0 ; i < 100; i++){
            System.out.println(rand.nextInt(50));
        }
        
    }
}
