import javax.swing.*;
import java.awt.*;

public class GUI {

	private static JFrame mainFrame;
	private static JPanel mainPanel;
	private static JPanel editButtonRow;
	private static JButton editButton;
	private static String projectTitle;
	private static int projectWidth;
	private static int projectHeight;
	private static boolean resizability;

	public GUI () {
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		editButtonRow = new JPanel();
		editButton = new JButton();
		projectTitle = "standard_title";
		projectWidth = 800;
		projectHeight = 650;
		resizability = false;
	}

	public void start() {
		initializeJFrame(mainFrame, projectTitle, projectWidth, projectHeight, resizability);
	}

	private void setProjectTitle(String title) {
		projectTitle = title;
	}

	private void setProjectWidth(int width) {
		projectWidth = width;
	}

	private void setProjectHeight(int height) {
		projectHeight = height;
	}

	private void setResizability(boolean resize) {
		resizability = resize;
	}

	private static void initializeJFrame(JFrame frame, String title, int width, int height, boolean resizability) {
		frame.setTitle(title);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(width, height));
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(resizability);
		frame.setVisible(true);
	}
}
