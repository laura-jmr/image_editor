import java.awt.*;
import java.awt.image.BufferedImage;

public class Converter {

	public Pixel[][] imageToPixelArray (BufferedImage img) {
		Pixel[][] pixelArray = new Pixel[img.getWidth()][img.getHeight()];

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color rgb = new Color(img.getRGB(i, j));
				pixelArray[i][j] = new Pixel(i, j, rgb);
			}
		}

		return pixelArray;
	}

	public BufferedImage pixelArrayToImage (Pixel[][] pixelArray) {
		BufferedImage img = new BufferedImage(pixelArray.length, pixelArray[0].length, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < pixelArray[0].length; i++) {
			for (int j = 0; j < pixelArray.length; j++) {
				int rgb = new Color(pixelArray[j][i].red, pixelArray[j][i].green, pixelArray[j][i].blue).getRGB();
				img.setRGB(j, i, rgb);
			}
		}

		return img;
	}
}
