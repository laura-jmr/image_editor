import java.awt.*;
import java.awt.image.BufferedImage;

public class GaussianBlur {
	
	private static BufferedImage origImg;
	public static int radius;
	private static Converter converter;
	private static Pixel[][] bluredPixelArray;
	
	private static int red;
	private static int green;
	private static int blue;
	private static int divR;
	private static int divG;
	private static int divB;
	private static int sum;

	public BufferedImage startBlur(BufferedImage img, int r) {
		origImg = img;
		radius = r;
		converter = new Converter();
		bluredPixelArray = converter.imageToPixelArray(img);
		
		red = 0;
		green = 0;
		blue = 0;
		divR = 0;
		divG = 0;
		divB = 0;
		sum = 0;
		
		BufferedImage bluredImg = blurImage(img);

		return bluredImg;
	}

	private static BufferedImage blurImage (BufferedImage img) {

		if (img.getHeight() >= img.getWidth()) {
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth(); j++) {

					recalculateRGB("red", img, j, i);
					recalculateRGB("green", img, j, i);
					recalculateRGB("blue", img, j, i);

					Color rgb = new Color(red, green, blue);
					bluredPixelArray[j][i] = new Pixel(j, i, rgb);
				}
			}
		}
		else {
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {

					recalculateRGB("red", img, i, j);
					recalculateRGB("green", img, i, j);
					recalculateRGB("blue", img, i, j);

					Color rgb = new Color(red, green, blue);
					bluredPixelArray[i][j] = new Pixel(j, i, rgb);
				}
			}
		}

		BufferedImage bluredImg = converter.pixelArrayToImage(bluredPixelArray);

		return bluredImg;
	}

	private static int countSumOFSurroundings (Pixel centerPixel, Pixel[][] pixelArray, BufferedImage img, String color, int radius) {

		if (radius <= 0)
			radius = 1;

		for (int i = 1; i < radius; i++) {
			for (int counter = 0; counter < 4; counter++) {
				for (int j = -i + 1; j <= i; j++) {
					try {
						if (counter == 0) {
			
							calculateSumAndDiv("red", centerPixel.posX + j, centerPixel.posY - i, pixelArray);
							calculateSumAndDiv("green", centerPixel.posX + j, centerPixel.posY - i, pixelArray);
							calculateSumAndDiv("blue", centerPixel.posX + j, centerPixel.posY - i, pixelArray);
						}
						if (counter == 1) {
							
							calculateSumAndDiv("red", centerPixel.posX + i, centerPixel.posY + j, pixelArray);
							calculateSumAndDiv("green", centerPixel.posX + i, centerPixel.posY + j, pixelArray);
							calculateSumAndDiv("blue", centerPixel.posX + i, centerPixel.posY + j, pixelArray);
						}
						if (counter == 2) {
							
							calculateSumAndDiv("red", centerPixel.posX - j, centerPixel.posY + i, pixelArray);
							calculateSumAndDiv("green", centerPixel.posX - j, centerPixel.posY + i, pixelArray);
							calculateSumAndDiv("blue", centerPixel.posX - j, centerPixel.posY + i, pixelArray);
						}
						if (counter == 3) {
							
							calculateSumAndDiv("red", centerPixel.posX - i, centerPixel.posY - j, pixelArray);
							calculateSumAndDiv("green", centerPixel.posX - i, centerPixel.posY - j, pixelArray);
							calculateSumAndDiv("blue", centerPixel.posX - i, centerPixel.posY - j, pixelArray);
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}
			}
		}

		return sum;
	}
	
	private static void calculateSumAndDiv (String color, int x, int y, Pixel[][] pixelArray) {
		switch (color) {
			case "red":
				sum += pixelArray[x][y].red;
				divR++;
				break;
			case "green":
				sum += pixelArray[x][y].green;
				divG++;
				break;
			case "blue":
				sum += pixelArray[x][y].blue;
				divB++;
				break;
			default:
				break;
		}
	}


	private static void recalculateRGB (String color, BufferedImage img, int x, int y) {
		switch (color) {
			case "red":
				red = countSumOFSurroundings(bluredPixelArray[x][y], bluredPixelArray, img, "red", radius);
				red = red / divR;
				break;
			case "green":
				green = countSumOFSurroundings(bluredPixelArray[x][y], bluredPixelArray, img, "green", radius);
				green = green / divG;
				break;
			case "blue":
				blue = countSumOFSurroundings(bluredPixelArray[x][y], bluredPixelArray, img, "blue", radius);
				blue = blue / divB;
				break;
			default:
				break;
		}
	}
}
