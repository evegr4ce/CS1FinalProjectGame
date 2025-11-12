import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		//MushroomLevelBossFight game = new MushroomLevelBossFight();
		//IceLevelBossFight game = new IceLevelBossFight();
		CrystalLevelBossFight game = new CrystalLevelBossFight();
		//TitleScreen screen = new TitleScreen();
		
		Hero hero = new Hero("hero2.png", 100, 300, 8, "Test Hero");
        Enemy boss = new Enemy("crystalBoss.png", 200, 100, 15, 50);
        game.addHero(hero);
        game.addEnemy(boss);
        boss.activate();
        
        new Thread(() -> game.run()).start();
        //new Thread(() -> screen.run()).start();
	}
}
