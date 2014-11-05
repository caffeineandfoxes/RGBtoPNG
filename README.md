RGBtoPNG
========

A Java program that generates one PNG image file for every RGB color. Takes in a folder path and generates PNG files from each RGB color (thus generating ~16.8 million image files).

This was created with the intention to be used to create swatches in the design phase of programming and have the colors selected be passed to a development team for easy identification of colors and what they should look like in a project.

##Note

This program currently outputs image files with a 250px x 250px resolution, and has no option to change the resolution during execution. This is due to the amount of time it takes to generate all of the files and the processing power it demands. In future releases, this may be adjustable, but it must be noted that processing time may not see significant improvement until a new file generation method is implemented. Furthermore, this program will run until it finishes the operation or reaches an error. If you need to kill the program prior to either of these events, use your operating system's task manager to kill "Java SE Binary".

Enjoy!
======
