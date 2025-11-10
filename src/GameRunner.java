import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		IslandScreen game = new IslandScreen();
		
		Hero h = new Hero("test.png", 50, 50, 5, "Mario");
        game.addHero(h);
        
        new Thread(() -> game.run()).start();
        
	}

}
