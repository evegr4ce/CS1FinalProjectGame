import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class IslandScreen extends Game implements ActionListener{

    private JButton mushroomButton;
    private JButton crystalButton;
    private JButton iceButton;
    
    private int chosenIsland;
    
    public IslandScreen() {
    	super();
    	addButtons();
    }
    
    public void addButtons() {
 
    	setLayout(null); 
    	
    	ImageIcon mushroomIcon = new ImageIcon("Mushroom.png");
    	mushroomButton = createButton(mushroomIcon, 50, 50);
    	mushroomButton.addActionListener(this);
    	add(mushroomButton, -1);
    	
    	ImageIcon crystalIcon = new ImageIcon("Crystal.png");
    	crystalButton = createButton(crystalIcon, 250, 300);
    	crystalButton.addActionListener(this);
    	add(crystalButton, -1);
    	
    	ImageIcon iceIcon = new ImageIcon("Ice.png");
    	iceButton = createButton(iceIcon, 490, 50);
    	iceButton.addActionListener(this);
    	add(iceButton, -1);
    }
    
    private JButton createButton(ImageIcon icon, int x, int y) {
    	Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    	
    	JButton button = new JButton(scaledIcon);
    	button.setBounds(x,y,250,250);
    	
    	button.setBorderPainted(false);
        button.setContentAreaFilled(false);
    	
    	return button;
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == mushroomButton) {
    		setChosenIsland(1);
    		setRunning(false);
    	}
    	else if (e.getSource() == crystalButton) {
    		setChosenIsland(2);
    		setRunning(false);
    	}
    	else if (e.getSource() == iceButton) {
    		setChosenIsland(3);
    		setRunning(false);
    	}
    }

	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("islandscreen.jpg");
	}

	public int getChosenIsland() {
		return chosenIsland;
	}

	public void setChosenIsland(int chosenIsland) {
		this.chosenIsland = chosenIsland;
	}
}
