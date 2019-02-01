import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

	private static JFrame mainFrame;
	private static JPanel mainPanel;
	private static JPanel editButtonRow;
	private static JButton editButton;
	private static String projectTitle;
	private static int projectWidth;
	private static int projectHeight;
	private static boolean resizability;
	private static String path;

	public GUI () {
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		editButtonRow = new JPanel();
		editButton = new JButton("edit");
		projectTitle = "standard_title";
		projectWidth = 800;
		projectHeight = 650;
		resizability = false;

	}

	public static void createGUI (String title) {
		projectTitle = title;
		initializeJFrame(mainFrame, projectTitle, projectWidth, projectHeight, resizability);
		JTextField userEntryField = createNewTextField( "enter image path...", 20, 50, 0,0, 30);
		mainPanel.add(userEntryField);
		mainPanel.add(editButton);
		mainFrame.add(mainPanel);
		mainFrame.show();
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

	private static JTextField createNewTextField (String shownText, int height, int width, int x, int y, int columns) {
		JTextField textField;

		if (columns != 0)
			textField = new JTextField(shownText, columns);
		else
			textField = new JTextField(shownText);

		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				path = getContentOfTextField(textField);
			}
		});

		return textField;
	}

	private static String getContentOfTextField (JTextField textField) {
		String content = textField.getText();

		return content;
	}

	public String getPath () {
		return path;
	}
}
