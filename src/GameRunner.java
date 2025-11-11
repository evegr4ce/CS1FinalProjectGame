import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		MushroomLevelBossFight game = new MushroomLevelBossFight();
		//TitleScreen screen = new TitleScreen();
		
		Hero hero = new Hero("test.png", 100, 300, 10, "Test Hero");
        Enemy boss = new Enemy("test.png", 200, 100, 10, 50);
        game.addHero(hero);
        game.addEnemy(boss);
        boss.activate();
        
        new Thread(() -> game.run()).start();
        //new Thread(() -> screen.run()).start();
	}
}
