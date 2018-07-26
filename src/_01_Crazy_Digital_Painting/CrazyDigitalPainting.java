package _01_Crazy_Digital_Painting;

import java.awt.Color;

public class CrazyDigitalPainting {
	// 1. Create two final static integers for the width and height of the display.
	final static int width = 2100;
	final static int height = 1400;

	// 2. Create a 2D array of Color objects. You will need to import
	// java.awt.Color. Initialize the size of the array using the
	// integers created in step 1.
	static Color[][] colorarray = new Color[width][height];

	public CrazyDigitalPainting() {
		// 3. Open the crazy_digital_painting.png file and look at the image.
		// ok
		// 4. Iterated through the 2D array and initialize each Color object
		// to a new color. The sample image was created using the following
		// pattern:
		// colors[i][j] = new Color(i % 256, (i * j) % 256, j % 256);
		/*
		 * for (int i = 0; i < colorarray.length; i++) { for (int j = 0; j <
		 * colorarray[i].length; j++) { colorarray[i][j] = new Color(i % 256, (i * j) %
		 * 256, j % 256); } }
		 */
		// Above commented out to switch...
		// 5. Come up with your own pattern to make a cool crazy image.ok
		for (int i = 0; i < colorarray.length; i++) {
			for (int j = 0; j < colorarray[i].length; j++) {
				colorarray[i][j] = new Color(i % 25, (i * j) % 50, j % 118);
			}
		}
		// 6. Use the ColorArrayDisplayer class to call the displayColorsAsImage method
		// to show off your picture. below
	}

	public static void main(String[] args) {
		new CrazyDigitalPainting();
		ColorArrayDisplayer CAD = new ColorArrayDisplayer();
		CAD.displayColorsAsImage(colorarray);

	}
}
