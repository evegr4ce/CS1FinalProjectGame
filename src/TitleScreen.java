
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
import javax.swing.SwingUtilities;

public class TitleScreen extends Game implements ActionListener{
	
	private JButton startButton;
	private JButton testCharacter;
	private BufferedImage titleImage;
	private Image scaledTitleImage;
	
	public TitleScreen() {
		super();
		
		try {
			titleImage = ImageIO.read(new File("Title.png"));
			scaledTitleImage = titleImage.getScaledInstance(400, -1, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
            System.out.println(e.getMessage());
		}
		
		ImageIcon startIcon = new ImageIcon("startButton.png");
		startButton = createButton(startIcon);
		startButton.addActionListener(this);
		//add(startButton);
		
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startButton.setVisible(false);
		}
		
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
        }
        
        int totalWidth = 3 * targetWidth + 2 * spacing;
        int startX = (getWidth() - totalWidth) / 2;
        int y = 250; 
		
		
		for (int i = 0; i < 3; i++) {
	        int x = startX + i * (targetWidth + spacing);
	        chars[i].setBounds(x, y, targetWidth, targetHeight);
	        getContentPane().add(chars[i]);
	    }
        
        getContentPane().revalidate();
        getContentPane().repaint();
	}
	

	@Override
	public void setBackground() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("titlescreen.jpg");
        backgroundPanel.setLayout(null); 
		setContentPane(backgroundPanel);
        backgroundPanel.add(startButton);
	}
	
	/**
	public void paintComponent(Graphics g) {
		super.paint(g);
		
		if (titleImage != null) {
            g.drawImage(titleImage, (getWidth() - titleImage.getWidth()) / 2, 20, null);
        }
	}
	*/
	

	@Override
	public void paint(Graphics g) {   //added this
	    super.paint(g);

	    if (titleImage != null) {
	        int x = (getWidth() - scaledTitleImage.getWidth(null)) /2;
	        int y = (getHeight() - scaledTitleImage.getHeight(null)) / 2;
	        
	        g.drawImage(scaledTitleImage, x, y, this);
	    }
	}


	
	 public static void main(String[] args) {
		 SwingUtilities.invokeLater(() -> {
		        TitleScreen titleScreen = new TitleScreen();
		        titleScreen.setTitle("Title Screen Test");
		        titleScreen.setSize(800, 600);
		        titleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        titleScreen.setLayout(null);
		        titleScreen.repaint();
		        titleScreen.setBackground();
		        titleScreen.setVisible(true);
		    });

	 }
}
