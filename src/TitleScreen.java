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
	
	private int character;
	
	JButton hero1;
	JButton hero2;
	JButton hero3;

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
		add(charsPanel, -1);

	}

	private JButton createButton(ImageIcon icon) {
		Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JButton button = new JButton(scaledIcon);
		button.setBounds(325, 400, 150, 150);

		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);

		return button;
	}
	
	private void generateCharacters() {
		
		ImageIcon icon1 = new ImageIcon("hero1.png");
        ImageIcon icon2 = new ImageIcon("hero2.png");
        ImageIcon icon3 = new ImageIcon("hero3.png");

        int targetWidth = 250; 
        int targetHeight = 250;

        
        ImageIcon[] icons = {icon1, icon2, icon3};

        
        hero1 = new JButton(new ImageIcon(icons[0].getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH)));
        hero1.setBorderPainted(false);
        hero1.setContentAreaFilled(false);
        hero1.setFocusPainted(false);
        hero1.setBounds(100, 400, targetWidth, targetHeight);
        hero1.addActionListener(this);
        
        hero2 = new JButton(new ImageIcon(icons[1].getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH)));
        hero2.setBorderPainted(false);
        hero2.setContentAreaFilled(false);
        hero2.setFocusPainted(false);
        hero2.setBounds(400, 400, targetWidth, targetHeight);
        hero2.addActionListener(this);
        
        hero3 = new JButton(new ImageIcon(icons[2].getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH)));
        hero3.setBorderPainted(false);
        hero3.setContentAreaFilled(false);
        hero3.setFocusPainted(false);
        hero3.setBounds(700, 400, targetWidth, targetHeight);
        hero3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == startButton) {
			startButton.setVisible(false);
			title.setVisible(false);
			showingCharacterSelect = true;
			
			generateCharacters();
			
			charsPanel.setOpaque(false);
	        charsPanel.setBounds(0, 0, Game.getWindowWidth(), Game.getWindowHeight());
	        
	        charsPanel.add(hero1);
	        charsPanel.add(hero2);    
	        charsPanel.add(hero3);
		}
		
		if (e.getSource() == hero1) {
			setRunning(false);
			setCharacter(1);
		}
		
		if (e.getSource() == hero2) {
			setRunning(false);
			setCharacter(2);
		}
		
		if (e.getSource() == hero3) {
			setRunning(false);
			setCharacter(3);
		}
	}
	
	

	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("titlescreen.jpg");
	}

	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}
}