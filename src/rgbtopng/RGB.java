/*
 * The MIT License
 *
 * Copyright 2016 David Fink
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package rgbtopng;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author David Fink
 */
public class RGB implements Serializable {

    int red, green, blue;

    public RGB() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void generateFile(int imageWidth, int imageHeight, String savePath) {
        //Declare Graphics2D object g2D
        Graphics2D g2D;

        //create the image and write to file in the selected directory
        BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, TYPE_INT_RGB);
        File outputFile = new File(savePath + "/" + this.toString() + ".png");
        if (!outputFile.exists()) {
            g2D = outputImage.createGraphics();
            g2D.setColor(this.toColor());
            g2D.fillRect(0, 0, imageWidth, imageHeight);
            try {
                ImageIO.write(outputImage, "PNG", outputFile);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "The application could "
                        + "not generate the file. Please try again.",
                        "Could Not Generate File", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void generateFileToDirectory(int imageWidth, int imageHeight) {
        //Declare variables for the operation
        JFileChooser fileChooser = new javax.swing.JFileChooser();
        int performOperation;
        File outputDir;
        String savePath = "";

        //choose output directory for generated files
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        performOperation = fileChooser.showDialog(null, "Select Output Directory");

        //continues generating the file only if the user selected a directory
        //in the file chooser
        if (performOperation == JFileChooser.APPROVE_OPTION) {
            outputDir = fileChooser.getSelectedFile();
            try {
                savePath = outputDir.getCanonicalPath();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "The application could "
                        + "not get the specified path. Please try again.",
                        "Could Not Get Path", JOptionPane.ERROR_MESSAGE);
            }

            //calls the generateFile method to create the image and write it to file
            this.generateFile(imageWidth, imageHeight, savePath);
        }
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "" + red + "-" + green + "-" + blue;
    }

    public Color toColor() {
        return new Color(red, green, blue);
    }
}
