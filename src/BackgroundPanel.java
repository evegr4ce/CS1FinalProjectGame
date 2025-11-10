import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = -590708273272753510L;
	private static Image backgroundImage;

	public BackgroundPanel(String file) {
		try {
            backgroundImage = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
	
	public Image getBackgroundImage() {
		return backgroundImage;
	}
}
