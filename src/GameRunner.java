import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		TitleScreen game = new TitleScreen();
		
		Hero h = new Hero("test.png", 50, 50, 10, "Test");
        game.addHero(h);
        game.run();
		
		// Game level1 = new LevelOne();

	}

}
