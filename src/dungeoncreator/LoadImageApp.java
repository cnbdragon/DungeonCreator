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

    int GRID_SIZE = 20;
    int GRID_X = 30;
    int GRID_y = 30;

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
        DungeonCreator dc = new DungeonCreator();
        Map m = new Map();
        m.sizeX = 25;
        m.sizeY = 25;
        //dc.generate(m);
        dc.generateRooms(m);
        this.buildImage();
        //this.addRoom(3, 5, 2, 6);
        for (Room r : m.rooms) {
            //this.addRoom(r);
            System.out.println(r);
            this.addRoom(r);
        }

    }

    @Override
    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(200, 200);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public void buildImage() {
        //BufferedImage off_Image
        int tw = 10 + (this.GRID_SIZE * this.GRID_X);
        int th = 10 + (this.GRID_SIZE * this.GRID_y);
        img = new BufferedImage(tw, th,
                BufferedImage.TYPE_INT_ARGB);
        System.out.println(img.getHeight()+","+img.getWidth());
        
        //grind
        for (int i = 5 + this.GRID_SIZE; i < tw-5; i += this.GRID_SIZE) {
            for (int j = 5; j < tw-5; j++) {
                img.setRGB(i, j, Color.GRAY.getRGB());
            }
        }
        for (int i = 5; i < tw-5; i++) {
            for (int j = 5 + this.GRID_SIZE; j < th-5; j += this.GRID_SIZE) {
                img.setRGB(i, j, Color.GRAY.getRGB());
            }
        }
        
        //border
        for (int i = 5; i < tw-5; i++) {
            img.setRGB(i, 5, Color.BLACK.getRGB());
        }
        for (int i = 5; i <= tw-5; i++) {
            img.setRGB(i, th-5, Color.BLACK.getRGB());
        }
        for (int i = 5; i < th-5; i++) {
            img.setRGB(5, i, Color.BLACK.getRGB());
        }
        for (int i = 5; i <= th-5; i++) {
            img.setRGB(tw-5, i, Color.BLACK.getRGB());
        }

        //Graphics2D g2 = off_Image.createGraphics();
    }

    public void addRoom(Room r) {
        addRoom(r.x, r.x + r.sizeX, r.y, r.y + r.sizeY);
        //room name
        img = process(img, r);
    }

    public void addRoom(int x1, int x2, int y1, int y2) {
        //west wall
        for (int i = 4 + (x1 * this.GRID_SIZE); i <= 6 + (x1 * this.GRID_SIZE); i++) {
            for (int j = 5 + (y1 * this.GRID_SIZE); j < 5 + (y2 * this.GRID_SIZE); j++) {
                img.setRGB(i, j, Color.RED.getRGB());
            }
        }
        //east wall
        for (int i = 4 + (x2 * this.GRID_SIZE); i <= 6 + (x2 * this.GRID_SIZE); i++) {
            for (int j = 5 + (y1 * this.GRID_SIZE); j < 5 + (y2 * this.GRID_SIZE); j++) {
                img.setRGB(i, j, Color.RED.getRGB());
            }
        }

        //north wall
        for (int i = 5 + (x1 * this.GRID_SIZE); i <= 5 + (x2 * this.GRID_SIZE); i++) {
            for (int j = 4 + (y1 * this.GRID_SIZE); j <= 6 + (y1 * this.GRID_SIZE); j++) {
                img.setRGB(i, j, Color.RED.getRGB());
            }
        }
        //south wall
        for (int i = 5 + (x1 * this.GRID_SIZE); i <= 5 + (x2 * this.GRID_SIZE); i++) {
            for (int j = 4 + (y2 * this.GRID_SIZE); j <= 6 + (y2 * this.GRID_SIZE); j++) {
                img.setRGB(i, j, Color.RED.getRGB());
            }
        }
        
        

    }

    private BufferedImage process(BufferedImage old, Room r) {
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, null);
        g2d.setPaint(Color.BLUE);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        String s = r.name;
        FontMetrics fm = g2d.getFontMetrics();
        int x = 5+(this.GRID_SIZE*r.x);
        int y = 5+this.GRID_SIZE+(this.GRID_SIZE*r.y);//fm.getHeight();
        g2d.drawString(s, x, y);
        g2d.dispose();
        return img;
    }
    
    
    public static void main(String[] args) {

        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter() {
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
