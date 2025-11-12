import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TitleScreen extends Game implements ActionListener{

	private JButton startButton;
	private JButton testCharacter;
	private BufferedImage titleImage;
	private Image scaledTitleImage;
	private JLabel title;
	private boolean showingCharacterSelect;

	JPanel charsPanel;

	public TitleScreen() {
		super();

		try {
			titleImage = ImageIO.read(new File("adventureGame.png"));
			scaledTitleImage = titleImage.getScaledInstance(700, -1, Image.SCALE_SMOOTH);
			title = new JLabel((new ImageIcon(scaledTitleImage)));
		} catch (IOException e) {
			e.printStackTrace();
            System.out.println(e.getMessage());
		}

		ImageIcon startIcon = new ImageIcon("start.png");
		startButton = createButton(startIcon);
		startButton.addActionListener(this);

		int x = (Game.getWindowWidth() - scaledTitleImage.getWidth(null)) /2;
        int y = (Game.getWindowHeight() - scaledTitleImage.getHeight(null)) / 2;

		title.setBounds(x, y, scaledTitleImage.getWidth(null), scaledTitleImage.getHeight(null));
		add(startButton, -1);
		add(title, -1);

		charsPanel = new JPanel();
		charsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		charsPanel.setOpaque(false);
		add(charsPanel, -1);

	}

	private JButton createButton(ImageIcon icon) {
		Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JButton button = new JButton(scaledIcon);
		//button.setBounds(325, 400, 150, 150);

		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);

		return button;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startButton.setVisible(false);
			title.setVisible(false);
			showingCharacterSelect = true;
		}

		//charsPanel.setOpaque(false);
        //charsPanel.setBounds(0, 0, Game.getWindowWidth(), Game.getWindowHeight());
		//charsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		ImageIcon icon1 = new ImageIcon("test.png");
        ImageIcon icon2 = new ImageIcon("test.png");
        ImageIcon icon3 = new ImageIcon("test.png");

        int targetWidth = 300; 
        int targetHeight = 300;
        int spacing = 10;

        JButton[] chars = new JButton[3];
        ImageIcon[] icons = {icon1, icon2, icon3};

        for (int i = 0; i < 3; i++) {
            chars[i] = new JButton(new ImageIcon(
                    icons[i].getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH)));
            chars[i].setBorderPainted(false);
            chars[i].setContentAreaFilled(false);
            chars[i].setFocusPainted(false);
            chars[i].addActionListener(this);
            charsPanel.add(chars[i]);
            
        }
        
        charsPanel.revalidate();
        charsPanel.repaint();

        /**
        int totalWidth = 3 * targetWidth + 2 * spacing;
        int startX = (Game.getWindowWidth() - totalWidth) / 2;
        int y = 250; 


		for (int i = 0; i < 3; i++) {
	        int x = startX + ( i * (targetWidth + spacing));
	        chars[i].setBounds(x, y, targetWidth, targetHeight);
	        charsPanel.add(chars[i], -1);
	    }
	    */
        
        
		
		if (e.getSource() == chars[0]) {
			setRunning(false);
		}
		
		if (e.getSource() == chars[1]) {
			setRunning(false);
		}
		
		if (e.getSource() == chars[2]) {
			setRunning(false);
		}
		
	}
	


	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("titlescreen.jpg");
	}
}