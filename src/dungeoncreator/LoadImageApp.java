/*
 * Copyright 2015 cnbdr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dungeoncreator;

/**
 *
 * @author cnbdr
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class LoadImageApp extends Component {
          
    BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public LoadImageApp() {
       try {
           img = ImageIO.read(new File("./test.jpg"));
       } catch (IOException e) {
           System.out.println(e);
       }

    }

    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(200,200);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("Load Image Sample");
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        String current;
        try {
            current = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + current);
        } catch (IOException ex) {
            Logger.getLogger(LoadImageApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);
        
        f.add(new LoadImageApp());
        f.pack();
        f.setVisible(true);
    }
}
