/*
 * The MIT License
 *
 * Copyright 2016 David Fink.
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author David Fink
 */
public class RGBFileManager {

    private ArrayList<RGB> rgbVals = new ArrayList<>();

    //generates a .rgb file which contains a serialized array of String objects
    //for every possible RGB value
    public void writeRGBFile() throws FileNotFoundException, IOException {
        //declare and initialize variables for use in generating the ArrayList which
        //will then be serialized into a .rgb file
        int red = 0;
        int green = 0;
        int blue = 0;
        int rgbCount = 0;
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        //loop through all possible combinations of RGB values and add each RGB
        //value to the rgbVals ArrayList as a String object for use with the color
        //sample generator
        for (int i = 0; i < 256; i++) {
            if (red < 255) {
                red++;
            } else {
                red = 0;
            }
            for (int j = 0; j < 256; j++) {
                if (green < 255) {
                    green++;
                } else {
                    green = 0;
                }
                for (int k = 0; k < 256; k++) {
                    rgbVals.add(new RGB(red, green, blue));
                    rgbCount++;

                    if (blue < 255) {
                        blue++;
                    } else {
                        blue = 0;
                    }
                }
            }
        }

        //creates a file called "rgbValues.rgb" and adds the serialized
        //array rgbVals to the file
        fileOutputStream = new FileOutputStream("rgbValues.rgb");
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(rgbVals);
        objectOutputStream.close();
    }

    //reads in a .rgb file containing a serialized String ArrayList of all possible
    //RGB values and returns to the application
    public ArrayList<RGB> readRGBFile() throws IOException, ClassNotFoundException {
        //declare and initialize variables for use in reading a RGB file and
        //adding the values to an ArrayList which will be returned
        FileInputStream fileInputStream = new FileInputStream("rgbValues.rgb");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        //read in the ArrayList of RGB values from the file and assign it to the
        //rgbValueInput variable to be returned
        if (rgbVals.isEmpty()) {
            rgbVals = (ArrayList<RGB>) (objectInputStream.readObject());
        }

        return rgbVals;
    }

}
