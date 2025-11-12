import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * MushroomLevelBossFight where plays must attack the boss 3 times
 * in order to complete the level
 */
public class MushroomLevelBossFight extends Game implements ActionListener{

	private static Game currentLevel;
	private JLabel[] hearts = new JLabel[3]; //Array to hold heart labels of the boss
	private int bossHealth = 3; //Boss health (3 times Hero must attack)
	private JLabel levelComplete; //Label to show when boss is attacked 3 times
	private boolean bossHit = false; //Tracks if boss has been attacked
	private boolean wasAttacking = false; //Tracks if hero was attacking
	private JButton continueButton; //Continue button to leave boss fight once won
	
	/**
	 * Constructor for MushroomLevelBossFight
	 */
	public MushroomLevelBossFight() {
		super();
		currentLevel = this;
		addHearts();
		addLevelComplete();
		addContinueButton();
	}
	
	/**
	 * Returns current game level
	 * @return - the current level instance (MushroomLevelBossFight)
	 */
	public static Game getCurrentLevel() {
        return currentLevel;
    }
	
	/**
	 * Sets the background image for boss fight
	 */
	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_2.png");

	 }
	
	/**
	 * Adds heart images to the screen as JLabels to represent Enemy life
	 */
	private void addHearts() {
	    ImageIcon originalHeart = new ImageIcon("HealthHeart.png");
	    int heartWidth = 50;  
	    int heartHeight = 50; 
	    
	    //Positions the 3 hearts
	    for (int i = 0; i < hearts.length; i++) {
	    	
	        Image scaled = originalHeart.getImage().getScaledInstance(heartWidth, heartHeight, java.awt.Image.SCALE_SMOOTH);
	        hearts[i] = new JLabel(new ImageIcon(scaled));

	        hearts[i].setBounds(20 + (i * (heartWidth + 5)), 10, heartWidth, heartHeight);
	        getContentPane().add(hearts[i]);
	    }
	}
	
	/**
	 * Adds a Level Complete label to the screen when boss is defeated
	 * Hidden then shown when boss health is 0
	 */
	private void addLevelComplete() {
	    ImageIcon original = new ImageIcon("LevelComplete.png");
	    
	    int width = 400;
	    int height = 200;
	    
	    Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    levelComplete = new JLabel(new ImageIcon(scaled));
	    
	    levelComplete.setBounds((getWindowWidth() - width) / 2, (getWindowHeight() - height) / 2, width, height);
	    levelComplete.setVisible(false);
	    getContentPane().add(levelComplete);
	   
	}
	
	/**
	 * Adds a continue button to the screen when boss is defeated
	 * Hidden then shown when boss health is 0
	 */
	private void addContinueButton() {
		ImageIcon continueIcon = new ImageIcon("continue.png");
        continueButton = createButton(continueIcon);
        continueButton.addActionListener(this);
        
        int buttonWidth = 150;
        int buttonHeight = 100;
        
        int buttonX = (getWindowWidth() - buttonWidth) / 2;
        int buttonY = levelComplete.getY() + levelComplete.getHeight() + 20;
        
        continueButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        
        continueButton.setVisible(false);
        getContentPane().add(continueButton);
       
	}

	/**
	 * Decreases the boss health by one and updates the heart health on screen
	 * If health is zero, the level complete is shown and the boss is positioned 
	 * off the screen
	 */
	public void decrementBossHealth() {
	    if (bossHealth <= 0) return;

	    SwingUtilities.invokeLater(() -> {
	        bossHealth--; //reduce boss health
	        hearts[bossHealth].setVisible(false); //Hide the heart

	        //Update display
	        getContentPane().revalidate();
	        getContentPane().repaint();

	        if (bossHealth == 0) {
	            levelComplete.setVisible(true); //Show level complete label
	            continueButton.setVisible(true);
	            for (Sprite s : getAll_sprites()) {
	                if (s instanceof Enemy) { //Move the boss off screen so its not shown
	                    s.setX(-1000);
	                    s.setY(-1000);
	                }
	            }
	            
	            getContentPane().revalidate();
	            getContentPane().repaint();
	        }
	    });
	}

	/**
	 * Displays level complete message
	 */
    public void showLevelComplete() {
        levelComplete.setVisible(true);
        levelComplete.repaint();
        
        continueButton.setVisible(true);
    }

    /**
     * Checks for collisions between enemy and hero
     * If hero is overlapping and attacking, then the boss health goes down
     */
    @Override
    protected void checkCollisions() {
        if (hero == null) return;

        for (Sprite s : getAll_sprites()) {
            if (s instanceof Enemy) {
                Enemy boss = (Enemy) s;

                if (hero.isAttacking() && hero.overlaps(boss) && !bossHit) {
                    bossHit = true; //Helps prevent multiple hits
                    decrementBossHealth();
                }
            }
        }

        if (!hero.isAttacking()) {
            bossHit = false; //reset the boolean
        }
    }
    
    /**
     * Creates a button using icon image
     * @param icon
     * @return a new button with icon
     */
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
    	if (e.getSource() == continueButton) {
    		setRunning(false);
    	}
    }

}
