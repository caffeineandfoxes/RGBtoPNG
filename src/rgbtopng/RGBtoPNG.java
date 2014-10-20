/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgbtopng;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author David Fink
 */
public class RGBtoPNG {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        //instance variables
        int r = 0;
        int g = 0;
        int b = 0;
        String rString = new String();
        String gString = new String();
        String bString = new String();
        String rgbVal = new String();
        String rgbValCondensed = new String();
        String tileFolder = new String();
        
        //prompt user to enter file path for tileFolder
        tileFolder = JOptionPane.showInputDialog(null, "Please copy and paste the file path for the folder in which you wish to store the color tiles: ", "Please Enter A File Path", JOptionPane.WARNING_MESSAGE);
        
        
        //parse int r, g, and b values to strings to form rgbVal and make file and increment values
        while (!rgbVal.equals("255, 255, 255")) {
            //parse int values to strings and combine
            rString = "" + r;
            gString = "" + g;
            bString = "" + b;
            rgbVal = rString + ", " + gString+ ", " + bString;
            
            //create PNG file filled with defined rgb color
            rgbValCondensed = rString + "-" + gString + "-" + bString; 
            BufferedImage img = new BufferedImage(1, 1, TYPE_INT_RGB);
            File f = new File(tileFolder + rgbValCondensed + ".png");
            Graphics2D g2 = img.createGraphics();
            Color imgColor = new Color(r, g, b);
            g2.setColor(imgColor);
            g2.fillRect(0, 0, 1, 1);
            ImageIO.write(img, "PNG", f);
            
            
            //increment r, g, and b
            if (r<255)  {
                r++;
            }
            else if(r == 255 && g != 255) {
                r = 0;
                g++;
            }
            else if(r == 255)    {
                r = 0;
                g = 0;
                b++;
            }
        }
    }
}
