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

/**
 *
 * @author David Fink
 */
public class RGBFileGenerator {

    //generates a .rgb file which contains a serialized array of String objects
    //for every possible RGB value
    public void writeRGBFile() throws FileNotFoundException, IOException {
        //declare and initialize variables for use in generating the array which
        //will then be serialized into a .rgb file
        String[] rgbVals = new String[16777216];
        int r = 0;
        int g = 0;
        int b = 0;
        int rgbCount = 0;
        FileOutputStream fos;
        ObjectOutputStream outputStream;

        //loop through all possible combinations of RGB values and add each RGB
        //value to the rgbVals array as a String object for use with the color
        //sample generator
        for (int i = 0; i < 256; i++) {
            if (r < 255) {
                r++;
            } else {
                r = 0;
            }
            for (int j = 0; j < 256; j++) {
                if (g < 255) {
                    g++;
                } else {
                    g = 0;
                }
                for (int k = 0; k < 256; k++) {
                    rgbVals[rgbCount] = r + "," + g + "," + b;
                    rgbCount++;

                    if (b < 255) {
                        b++;
                    } else {
                        b = 0;
                    }
                }
            }
        }

        //creates a file called "rgbValueArray.rgb" and adds the serialized
        //array rgbVals to the file
        fos = new FileOutputStream("rgbValueArray.rgb");
        outputStream = new ObjectOutputStream(fos);
        outputStream.writeObject(rgbVals);
        outputStream.close();
    }

    //reads in a .rgb file containing a serialized String array of all possible
    //RGB values
    public String[] readRGBFile() throws IOException, ClassNotFoundException {
        //declare and initialize variables for use in reading a RGB file and
        //adding the values to an array of length 16777216 which will be returned
        String[] rgbValueInput = new String[16777216];
        FileInputStream fis = new FileInputStream("rgbValueArray.rgb");
        ObjectInputStream inputStream = new ObjectInputStream(fis);

        //read in the array of RGB values from the file and assign it to the
        //rgbValueInput variable to be returned
        rgbValueInput = (String[]) inputStream.readObject();
        return rgbValueInput;
    }

}
