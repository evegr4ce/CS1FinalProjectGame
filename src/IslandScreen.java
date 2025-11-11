import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class IslandScreen extends Game implements ActionListener{

    private JButton mushroomButton;
    private JButton crystalButton;
    private JButton iceButton;

    public IslandScreen() {
    	super();
    	addButtons();
    }

    public void addButtons() {
    	
    	JLayeredPane layeredPane = new JLayeredPane();
    	layeredPane.setLayout(null); 
 
    	setLayout(null); 

    	ImageIcon mushroomIcon = new ImageIcon("Mushroom.png");
    	mushroomButton = createButton(mushroomIcon, 100, 100);
    	mushroomButton.addActionListener(this);
    	layeredPane.add(mushroomButton, 10);
    	add(mushroomButton, -1);

    	ImageIcon crystalIcon = new ImageIcon("Crystal.png");
    	crystalButton = createButton(crystalIcon, 850,300);
    	crystalButton.addActionListener(this);
    	layeredPane.add(crystalButton, 10);
    	add(crystalButton, -1);

    	ImageIcon iceIcon = new ImageIcon("Ice.png");
    	iceButton = createButton(iceIcon, 150, 550);
    	iceButton.addActionListener(this);
    	layeredPane.add(iceButton, 10);
    	add(layeredPane, -1);
    }

    private JButton createButton(ImageIcon icon, int x, int y) {
    	Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

    	JButton button = new JButton(scaledIcon);
    	button.setBounds(x,y,150,150);

    	button.setBorderPainted(false);
        button.setContentAreaFilled(false);

    	return button;
    }

    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == mushroomButton) {
    		//
    	}
    	else if (e.getSource() == crystalButton) {
    		//
    	}
    	else if (e.getSource() == iceButton) {
    		//
    	}

    }

    @Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("islandscreen.jpg");
	}
}