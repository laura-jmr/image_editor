import java.awt.*;
import java.awt.image.BufferedImage;

public class GaussianBlur {

	public static int radius;
	private static int red;
	private static int green;
	private static int blue;
	private static int divR;
	private static int divG;
	private static int divB;
	private static Pixel[][] bluredPixelArray;
	private static Converter converter;
	private static BufferedImage origImg;

	public BufferedImage startBlur(BufferedImage img, int r) {
		red = 0;
		green = 0;
		blue = 0;
		divR = 0;
		divG = 0;
		divB = 0;
		converter = new Converter();
		origImg = img;
		radius = r;
		bluredPixelArray = converter.imageToPixelArray(img);
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
		int sum = 0;

		if (radius <= 0)
			radius = 1;

		for (int i = 1; i < radius; i++) {
			for (int counter = 0; counter < 4; counter++) {
				for (int j = -i + 1; j <= i; j++) {
					try {
						if (counter == 0) {
							if (color == "red") {
								sum += pixelArray[centerPixel.posX + j][centerPixel.posY - i].red;
								divR++;
							}
							if (color == "green") {
								sum += pixelArray[centerPixel.posX + j][centerPixel.posY - i].green;
								divG++;
							}
							if (color == "blue") {
								sum += pixelArray[centerPixel.posX + j][centerPixel.posY - i].blue;
								divB++;
							}
						}
						if (counter == 1) {
							if (color == "red") {
								sum += pixelArray[centerPixel.posX + i][centerPixel.posY + j].red;
								divR++;
							}
							if (color == "green") {
								sum += pixelArray[centerPixel.posX + i][centerPixel.posY + j].green;
								divG++;
							}
							if (color == "blue") {
								sum += pixelArray[centerPixel.posX + i][centerPixel.posY + j].blue;
								divB++;
							}
						}
						if (counter == 2) {
							if (color == "red") {
								sum += pixelArray[centerPixel.posX - j][centerPixel.posY + i].red;
								divR++;
							}
							if (color == "green") {
								sum += pixelArray[centerPixel.posX - j][centerPixel.posY + i].green;
								divG++;
							}
							if (color == "blue") {
								sum += pixelArray[centerPixel.posX - j][centerPixel.posY + i].blue;
								divB++;
							}
						}
						if (counter == 3) {
							if (color == "red") {
								sum += pixelArray[centerPixel.posX - i][centerPixel.posY - j].red;
								divR++;
							}
							if (color == "green") {
								sum += pixelArray[centerPixel.posX - i][centerPixel.posY - j].green;
								divG++;
							}
							if (color == "blue") {
								sum += pixelArray[centerPixel.posX - i][centerPixel.posY - j].blue;
								divB++;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}
			}
		}

		return sum;
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