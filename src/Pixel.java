import java.awt.*;

public class Pixel {

	public int posX;
	public int posY;
	public int red;
	public int green;
	public int blue;

	public Pixel (int x, int y, Color rgb) {
		this.posX = x;
		this.posY = y;
		this.red = rgb.getRed();
		this.green = rgb.getGreen();
		this.blue = rgb.getBlue();
	}
}
