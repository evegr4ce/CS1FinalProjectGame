import java.awt.*;

import javax.swing.*;

public class CrystalLevelBossFight extends Game{

	private static Game currentLevel;
	private JLabel[] hearts = new JLabel[3];
	private int bossHealth = 3;
	private JLabel levelComplete;
	private boolean bossHit = false;
	private boolean wasAttacking = false;
	
	public CrystalLevelBossFight() {
		super();
		currentLevel = this;
		addHearts();
		addLevelComplete();
	}
	
	public static Game getCurrentLevel() {
        return currentLevel;
    }
	
	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_3.png");
	}
	

	private void addHearts() {
	    ImageIcon originalHeart = new ImageIcon("HealthHeart.png");
	    int heartWidth = 50; 
	    int heartHeight = 50; 

	    for (int i = 0; i < hearts.length; i++) {
	    	
	        Image scaled = originalHeart.getImage().getScaledInstance(heartWidth, heartHeight, java.awt.Image.SCALE_SMOOTH);
	        hearts[i] = new JLabel(new ImageIcon(scaled));

	        hearts[i].setBounds(20 + (i * (heartWidth + 5)), 10, heartWidth, heartHeight);
	        getContentPane().add(hearts[i]);
	    }
	}
	
	private void addLevelComplete() {
	    ImageIcon original = new ImageIcon("LevelComplete.png");
	    
	    int width = 400;
	    int height = 200;
	    
	    Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    levelComplete = new JLabel(new ImageIcon(scaled));
	    
	    levelComplete.setBounds((getWindowWidth() - width) / 2, 
	                            (getWindowHeight() - height) / 2, 
	                            width, height);
	    levelComplete.setVisible(false);
	    getContentPane().add(levelComplete);
	}

	
	public void decrementBossHealth() {
	    if (bossHealth <= 0) return;

	    SwingUtilities.invokeLater(() -> {
	        bossHealth--;
	        hearts[bossHealth].setVisible(false);

	        getContentPane().revalidate();
	        getContentPane().repaint();

	        if (bossHealth == 0) {
	            levelComplete.setVisible(true);
	            for (Sprite s : all_sprites) {
	                if (s instanceof Enemy) {
	                    s.setX(-1000);
	                    s.setY(-1000);
	                }
	            }
	            getContentPane().revalidate();
	            getContentPane().repaint();
	            
	        }
	    });
	}


    public void showLevelComplete() {
        levelComplete.setVisible(true);
        levelComplete.repaint();
    }


    @Override
    protected void checkCollisions() {
        if (hero == null) return;

        for (Sprite s : all_sprites) {
            if (s instanceof Enemy) {
                Enemy boss = (Enemy) s;

                if (hero.isAttacking() && hero.overlaps(boss) && !bossHit) {
                    bossHit = true;
                    decrementBossHealth();
                }
            }
        }

        if (!hero.isAttacking()) {
            bossHit = false;
        }
    }
	
}
