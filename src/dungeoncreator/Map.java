/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncreator;
import java.util.Collections.*;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
/**
 *
 * @author jwulf
 */
public class Map {
    Set objects;
    
    int sizeX;
    int sizeY;
    String name;
    String description;
    List<List<String>> map;
    
    Map(){
        map = new LinkedList<>();
    }
    
    public String toString(){
        
        return "string";
        
    }
}
