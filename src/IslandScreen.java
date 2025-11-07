import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class IslandScreen extends JPanel implements ActionListener{

    private JButton mushroomButton;
    private JButton crystalButton;
    private JButton iceButton;
    
    public IslandScreen() {
    	setLayout(null); 
    	
    	ImageIcon mushroomIcon = new ImageIcon("Mushroom.png");
    	mushroomButton = createButton(mushroomIcon, 100, 100);
    	mushroomButton.addActionListener(this);
    	add(mushroomButton);
    	
    	ImageIcon crystalIcon = new ImageIcon("Crystal.png");
    	crystalButton = createButton(crystalIcon, 850,300);
    	crystalButton.addActionListener(this);
    	add(crystalButton);
    	
    	ImageIcon iceIcon = new ImageIcon("Ice.png");
    	iceButton = createButton(iceIcon, 150, 550);
    	iceButton.addActionListener(this);
    	add(iceButton);
    }
    
    private JButton createButton(ImageIcon icon, int x, int y) {
    	Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    	
    	JButton button = new JButton(scaledIcon);
    	button.setBounds(x,y,150,150);
    	
    	//button.setBorderPainted(false);
        //button.setContentAreaFilled(false);
    	
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
    
    //TO TEST
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Island Screen Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            IslandScreen islandScreen = new IslandScreen();
            frame.setContentPane(islandScreen);
            frame.setSize(1280, 768);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
