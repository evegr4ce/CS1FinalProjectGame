import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		Game game = new Game();
		
		Hero h = new Hero("test.png", 50, 50, 10, "Test");
        game.addHero(h);
        
        game.run();
		
		// Game level1 = new LevelOne();

	}

}
