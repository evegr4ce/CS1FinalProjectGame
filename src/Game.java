import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.ArrayList;

/**
 * Main class for the game
 */
public abstract class Game extends JFrame {
	private boolean isRunning = true;
	private int fps = 60;
	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 768;
	
	private BufferedImage backBuffer;
	private BufferedImage backgroundImage;
	private Insets insets;
	private InputHandler input;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> items;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Sprite> all_sprites;
	
	private Hero hero;
	
	public Game() {
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		all_sprites = new ArrayList<Sprite>();
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
        setTitle("Adventure");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setBackground();
        
        insets = getInsets();
        setSize(insets.left + WINDOW_WIDTH + insets.right,
                insets.top + WINDOW_HEIGHT + insets.bottom);
        
        setVisible(true);
        
        try {
        	backgroundImage = ImageIO.read(new File("titlescreen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backBuffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        input = new InputHandler(this);
    }
    
    
    /**
     * This class will set the background image for each level.
     */
    public abstract void setBackground();

    /**
     * This method will check for input, move things
     * around and check for win conditions, etc
     */
    void update() {

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

        // Perform all Sprite updates
        checkCollisions();
        for (int i = 0; i < all_sprites.size(); i++) {
            all_sprites.get(i).update();
        }
    }
    
    void checkCollisions() {
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
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();
        
        if (backgroundImage != null) {
        	bbg.drawImage(backgroundImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        }
        else {
        	bbg.setColor(Color.WHITE);
            bbg.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        for (int i = 0; i < all_sprites.size(); i++) {
            all_sprites.get(i).draw(bbg);
        }

        g.drawImage(backBuffer, insets.left, insets.top, this);
    }
    
    public static int getWindowHeight() {
    	return WINDOW_HEIGHT;
    }
    
    public static int getWindowWidth() {
    	return WINDOW_WIDTH;
    }
}
