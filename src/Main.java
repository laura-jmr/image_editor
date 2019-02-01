import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {

	public static String fileURL;
	public static String savedFileURL;

	public static void main (String[] args) {
		GUI gui = new GUI();
		GaussianBlur blur = new GaussianBlur();
		gui.createGUI("image_editor");
		try {
			fileURL = gui.getPath();
			URL url = new URL(fileURL);
			BufferedImage origImg = ImageIO.read(url);
			blur.startBlur(origImg, 1);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static String defaultSaveURL() {
		int savedFileVersion = 0;
		String tempURL = "";
		savedFileURL = fileURL;

		for (int i = fileURL.length() - 1 ; i >= 0; i--) {
			if (fileURL.charAt(i) == '.') {
				for (int j = 0; j < i; j++) {
					tempURL += fileURL.charAt(j);
				}
			}
		}

		while (existingImageName(savedFileURL)) {
			savedFileVersion++;
			savedFileURL = tempURL + "_blured" + savedFileVersion + ".jpg";
		}

		return savedFileURL;
	}

	private static boolean existingImageName (String url) {
		BufferedImage temp = null;

		try {
			temp = ImageIO.read(new File(url));
		} catch (IOException e) {}

		if (temp == null)
			return false;
		else
			return true;
	}
}
