import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.util.ArrayList;

/**
 * Main class for the game
 */
public abstract class Game extends JFrame {
	private boolean isRunning = true;
	private int fps = 60;
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;

	private Image backgroundImage;
	private InputHandler input;
	
	JLayeredPane characters;
	GamePanel gamePanel; 
	
	protected ArrayList<Enemy> enemies;
	private ArrayList<Item> items;
	private ArrayList<Sprite> all_sprites;

	protected Hero hero;
	
	
	public Game() {
		
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		all_sprites = new ArrayList<Sprite>();
		
		characters = new JLayeredPane();
		
	}
	
	public void addHero(Hero h) {
		hero = h;
		all_sprites.add(h);
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
		all_sprites.add(e);
	}
	
	public void addItem(Item it) {
		items.add(it);
		all_sprites.add(it);
	}
	
	/**
     * This method starts the game and runs it in a loop
     */
    public void run() {
        initialize();

        while(isRunning) {
            long time = System.currentTimeMillis();

            update();
            draw();

            //  delay for each frame  -   time it took for one frame
            time = (1000 / fps) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                }
                catch(Exception e){}
            }
        }

        setVisible(false);
    }

    /**
     * This method will set up everything need for the game to run
     */
    public void initialize() {
        setTitle("Adventure Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
//        insets = getInsets();
//        setSize(insets.left + WINDOW_WIDTH + insets.right,
//                insets.top + WINDOW_HEIGHT + insets.bottom);
        
        BackgroundPanel bg = setBackground();
        backgroundImage = bg.getBackgroundImage();
        
        gamePanel = new GamePanel();
        gamePanel.setOpaque(false);
        gamePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        add(gamePanel);
     
        gamePanel.setVisible(true);
        setVisible(true);

        gamePanel.setFocusable(true);
        
        requestFocusInWindow();
        input = new InputHandler(this);
    }
    
    
    /**
     * This class will set the background image for each level.
     */
    public abstract BackgroundPanel setBackground();
    
    public static Game getCurrentLevel() {
        return null; 
    }
    
    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            // Draw sprites
            for (Sprite sprite : all_sprites) {
                sprite.draw(g);
            }
        }
    }

    /**
     * This method will check for input, move things
     * around and check for win conditions, etc
     */
    void update() {

    	if (hero == null) return;

    	
        if (input.isKeyDown(KeyEvent.VK_LEFT)) {
            hero.moveLeft();
        }
        if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
            hero.moveRight();
        }
        if (input.isKeyDown(KeyEvent.VK_UP)) {
            hero.moveUp();
        }
        if (input.isKeyDown(KeyEvent.VK_DOWN)) {
            hero.moveDown();
        }
        if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
        if (input.isKeyDown(KeyEvent.VK_SPACE)) {
            hero.attack();
        } else {
            hero.resetAttack();
        }


        // Perform all Sprite updates
        checkCollisions();
        for (int i = 0; i < all_sprites.size(); i++) {
            all_sprites.get(i).update();
        }
    }
    
    
    protected void checkCollisions() {
    	ArrayList<Sprite> removal = new ArrayList<>();
    	
    	for (Sprite s : all_sprites) {
    		if (!(s instanceof Hero)) {
    			if (s.overlaps(hero)) {
    				boolean remove = s.collidedWith(hero);
    				
    				if (remove) {
    					removal.add(s);
    				}
    			}
    		}
    	}
    	
    	for (Sprite s : removal) {
    		all_sprites.remove(s);
    	}
    }


    /**
     * This method will draw everything
     */
    void draw() {
        gamePanel.repaint();
    }
    
    public static int getWindowHeight() {
    	return WINDOW_HEIGHT;
    }
    
    public static int getWindowWidth() {
    	return WINDOW_WIDTH;
    }
    
    public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
    
    public ArrayList<Sprite> getAll_sprites() {
		return all_sprites;
	}
    
}
